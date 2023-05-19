package at.ac.tuwien.sepm.groupphase.backend.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.Getter;

public class HeaderTokenAuthenticationFilter extends OncePerRequestFilter {

    private final WebAuthenticationDetailsSource authenticationDetailsSource = new WebAuthenticationDetailsSource();

    @Getter
    private final AuthenticationManager authenticationManager;
    
    public HeaderTokenAuthenticationFilter(AuthenticationManager authenticationManager) {
    	this.authenticationManager = authenticationManager;
        Assert.notNull(authenticationManager, "authenticationManager cannot be null");
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException{
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if ((header != null) && header.startsWith(AuthenticationConstants.TOKEN_PREFIX)) {
            try {
                AuthenticationHeaderToken authenticationRequest = new AuthenticationHeaderToken(header.substring(AuthenticationConstants.TOKEN_PREFIX.length()));
                authenticationRequest.setDetails(authenticationDetailsSource.buildDetails(request));
                Authentication authResult = getAuthenticationManager().authenticate(authenticationRequest);
                SecurityContextHolder.getContext().setAuthentication(authResult);
            } catch (AuthenticationException failed) {
                SecurityContextHolder.clearContext();
            }
        }
        chain.doFilter(request, response);
    }

}
