package at.ac.tuwien.sepm.groupphase.backend.adapter.controller;

import at.ac.tuwien.sepm.groupphase.backend.domain.model.AuthCheck;
import at.ac.tuwien.sepm.groupphase.backend.domain.model.MsgUser;
import at.ac.tuwien.sepm.groupphase.backend.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/rest/auth")
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/authhorize")
    public Mono<AuthCheck> postAutorize(@RequestBody AuthCheck authCheck,@RequestHeader Map<String, String> header){
        return authenticationService.authorizeUser(authCheck, header);
    }

    @GetMapping("/confirm/{uuid}")
    public Mono<Boolean> getConfirmUUid(@PathVariable String uuid){
        return authenticationService.confirmUuid(uuid);
    }

    @PostMapping("/login")
    public Mono<MsgUser> postUserLogin(MsgUser myUser){
        return authenticationService.userLogin(myUser);
    }
}
