package at.ac.tuwien.sepm.groupphase.backend.domain.common;

import at.ac.tuwien.sepm.groupphase.backend.domain.exception.JwtTokenValidationException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {
    private final static Logger log = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${security.jwt.token.secret-key}")
    private String secretKey;

    @Value("${security.jwt.token.expire-length}")
    private long validityInMilliseconds;

    public String createToken(String username, List<Role> roles, Optional<Date> issuedAtOpt){
        Claims claims = Jwts.claims().setSubject(username);
        claims.put(WebUtils.TOKENAUTHKEY, roles.stream().map(s -> new SimpleGrantedAuthority(s.getAuthority())).filter(Objects::nonNull).collect(Collectors.toList()));
        claims.put(WebUtils.TOKENLASTMSGKEY, new Date().getTime());
        Date issuedAt = issuedAtOpt.orElse(new Date());
        Date validity = new Date(issuedAt.getTime() + validityInMilliseconds);
        SecretKey key = createSecretKey();
        return Jwts.builder().setClaims(claims).setIssuedAt(issuedAt).setExpiration(validity).signWith(key, SignatureAlgorithm.HS256).compact();
    }

    public Optional<Jws<Claims>> getClaims(Optional<String> token){
        if(!token.isPresent()){
            return Optional.empty();
        }
        SecretKey key = createSecretKey();
        return Optional.of(Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token.get()));
    }

    public Authentication getAuthentication(String token){
        if(getAuthorities(token).stream().filter(role -> role.equals(Role.GUEST)).count() > 0){
            return new UsernamePasswordAuthenticationToken(getUsername(token), null);
        }
        return new UsernamePasswordAuthenticationToken(getUsername(token), getAuthorities(token));
    }

    public String getUsername(String token){
        SecretKey key = createSecretKey();
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
    }

    public Collection<Role> getAuthorities(String token){
        Collection<Role> roles = new LinkedList<>();
        for(Role role : Role.values()){
            roles.add(role);
        }
        SecretKey key = createSecretKey();
        List<Map<String, String>> myRoles = (List<Map<String, String>>) Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().get("auth");
        return myRoles.stream().map(map -> map.values()).flatMap(Collection::stream).map(str-> roles.stream().filter(r -> r.name().equals(str)).findFirst().orElse(Role.GUEST)).collect(Collectors.toList());
    }

    public long getTtl(String token){
        try{
            SecretKey key = createSecretKey();
            Date expiryDate = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getExpiration();
            return expiryDate.getTime() - System.currentTimeMillis();
        }catch (JwtException | IllegalArgumentException e){
            return -1;
        }
    }

    public String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader(WebUtils.AUTHORIZATION);
        if(bearerToken != null && bearerToken.startsWith(WebUtils.BEARER)){
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    public boolean validateToken(String token){
        SecretKey key = createSecretKey();
        try{
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        }catch(JwtException | IllegalArgumentException e){
            throw new JwtTokenValidationException("Expired or invalid JWT token", e);
        }
    }

    private SecretKey createSecretKey(){
        return Keys.hmacShaKeyFor(Base64.getUrlDecoder().decode(secretKey.getBytes(StandardCharsets.ISO_8859_1)));
    }
}
