package at.ac.tuwien.sepm.groupphase.backend.service;


import at.ac.tuwien.sepm.groupphase.backend.domain.common.JwtTokenProvider;
import at.ac.tuwien.sepm.groupphase.backend.domain.common.Role;
import at.ac.tuwien.sepm.groupphase.backend.domain.common.WebUtils;
import at.ac.tuwien.sepm.groupphase.backend.domain.model.AuthCheck;
import at.ac.tuwien.sepm.groupphase.backend.domain.model.MsgUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


import javax.validation.Valid;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthenticationService {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationService.class);
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;
    private final MyRepository myRepository;
    @Value("${messenger.url.uuid.confirm}")
    private String confirmUrl;

    public AuthenticationService(JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder, MailService mailService, MyRepository myRepository) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
        this.myRepository = myRepository;
    }

    public Mono<AuthCheck> authorizeUser(AuthCheck authCheck, Map<String, String> headers){
        String tokenRoles = WebUtils.getTokenRoles(headers, jwtTokenProvider);
        if(tokenRoles.contains(Role.USERS.name()) && !tokenRoles.contains(Role.GUEST.name())){
            return Mono.just(new AuthCheck(authCheck.getPath(), true));
        }else{
            return Mono.just(new AuthCheck(authCheck.getPath(), false));
        }
    }

    public Mono<MsgUser> userSignin(@Valid MsgUser myUser){
        try{
            Query query = new Query();
            query.addCriteria(Criteria.where("username").is(myUser.getUsername()));
            MsgUser user = myRepository.findOne(query, MsgUser.class).switchIfEmpty(Mono.just(new MsgUser())).block();
            if(user.getUsername() == null){
                String encryptedPassword = passwordEncoder.encode(myUser.getPassword());
                myUser.setPassword(encryptedPassword);
                UUID uuid = UUID.randomUUID();
                myUser.setUuid(uuid.toString());
                myRepository.save(myUser).block();
                mailService.sendConfirmMail(myUser, this.confirmUrl);
                myUser.setUserId(myUser.getId().toString());
                myUser.setUuid(null);
                return Mono.just(myUser);
            }
        }catch (RuntimeException re){
            log.error("Signin failed for : " + myUser.getUsername());
        }
        return Mono.just(new MsgUser());
    }

    public Mono<Boolean> confirmUuid(String uuid){
        Query query = new Query();
        query.addCriteria(Criteria.where("uuid").is(uuid));
        query.addCriteria(Criteria.where("confirmed").is(false));
        return myRepository.findOne(query, MsgUser.class).switchIfEmpty(Mono.just(new MsgUser())).flatMap(user -> {
            if(user.getUuid() != null && user.getUuid().equalsIgnoreCase(uuid)){
                user.setConfirmed(true);
            }
            return myRepository.save(user);
        }).map(user ->user.isConfirmed());
    }

    private MsgUser loginHelper(MsgUser user, String password){
        if(user.getUsername() != null){
            if(passwordEncoder.matches(password, user.getPassword())){
                String jwtToken = jwtTokenProvider.createToken(user.getUsername(), Arrays.asList(Role.USERS), Optional.empty());
                user.setToken(jwtToken);
                user.setPassword("XXX");
                return user;
            }
        }
        return new MsgUser();
    }

    public Mono<MsgUser> userLogin(MsgUser myUser){
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(myUser.getUsername()));
        if(confirmUrl != null && !confirmUrl.isBlank()){
            query.addCriteria(Criteria.where("confirmed").is(true));
        }
        return myRepository.findOne(query, MsgUser.class).switchIfEmpty(Mono.just(new MsgUser())).map(user -> {
            if(user.getId() != null){
                user.setUserId(user.getId().toString());
            }
            return user;
        }).map(user -> loginHelper(user, myUser.getPassword())).onErrorResume(re -> {
            log.info("login failed for : " + myUser.getUsername(), re);
            return Mono.just(new MsgUser());
        });
    }
}
