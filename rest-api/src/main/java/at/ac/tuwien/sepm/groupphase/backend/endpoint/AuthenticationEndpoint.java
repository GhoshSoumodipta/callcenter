package at.ac.tuwien.sepm.groupphase.backend.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.authentication.AuthenticationRequest;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.authentication.AuthenticationToken;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.authentication.AuthenticationTokenInfo;
import at.ac.tuwien.sepm.groupphase.backend.security.AuthenticationConstants;
import at.ac.tuwien.sepm.groupphase.backend.service.HeaderTokenAuthenticationService;
import at.ac.tuwien.sepm.groupphase.backend.service.implementation.SimpleHeaderTokenAuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/api/v1/authentication")
@Tag(name = "/api/v1/authentication")
public class AuthenticationEndpoint {

    private final HeaderTokenAuthenticationService authenticationService;
    private final static Logger LOGGER = LoggerFactory.getLogger(AuthenticationEndpoint.class);

    public AuthenticationEndpoint(SimpleHeaderTokenAuthenticationService simpleHeaderTokenAuthenticationService) {
        authenticationService = simpleHeaderTokenAuthenticationService;
    }

    @RequestMapping(method = RequestMethod.POST)
    @Operation(summary = "Get an authentication token with your username and password")
    public AuthenticationToken authenticate(@RequestBody final AuthenticationRequest authenticationRequest) {
        LOGGER.info("authenticating "+ authenticationRequest.getUsername());
        return authenticationService.authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
    }

    @RequestMapping(method = RequestMethod.GET)
	@Operation(summary = "Get some valid authentication tokens"/*
																 * , authorizations = {@Authorization(value = "apiKey")}
																 */)
    public AuthenticationToken authenticate(@Parameter(hidden = true) @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        return authenticationService.renewAuthentication(authorizationHeader.substring(AuthenticationConstants.TOKEN_PREFIX.length()).trim());
    }

    @RequestMapping(value = "/info/{token}", method = RequestMethod.GET)
	@Operation(summary = "Get information about a specific authentication token"/*
																				 * , authorizations =
																				 * {@Authorization(value = "apiKey")}
																				 */)
    public AuthenticationTokenInfo tokenInfoAny(@PathVariable String token) {
        return authenticationService.authenticationTokenInfo(token);
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
	@Operation(summary = "Get information about the current users authentication token"/*
																						 * , authorizations =
																						 * {@Authorization(value =
																						 * "apiKey")}
																						 */)
    public AuthenticationTokenInfo tokenInfoCurrent(@Parameter(hidden = true) @RequestHeader(value = HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        return authenticationService.authenticationTokenInfo(authorizationHeader.substring(AuthenticationConstants.TOKEN_PREFIX.length()).trim());
    }
}
