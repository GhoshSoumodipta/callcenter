package at.ac.tuwien.sepm.groupphase.backend.endpoint;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.UserDTO;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.UserDTO;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.requestparameter.PasswordChangeRequest;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;
import at.ac.tuwien.sepm.groupphase.backend.exception.ServiceException;
import at.ac.tuwien.sepm.groupphase.backend.service.UserService;
import at.ac.tuwien.sepm.groupphase.backend.service.VerifyCaptchaTokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;



@RestController
@RequestMapping(value = "/api/v1/users")
@Tag(name = "/api/v1/users")
public class UserEndpoint {

    private final UserService userService;

    private final VerifyCaptchaTokenService verifyCaptchaTokenService;
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    public UserEndpoint(UserService userService, VerifyCaptchaTokenService verifyCaptchaTokenService) {
        this.userService = userService;
        this.verifyCaptchaTokenService = verifyCaptchaTokenService;
    }


    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Get users"/*, authorizations = {@Authorization(value = "apiKey")} */)
    public Page<UserDTO> getUsers(@RequestParam(value = "username", required = false) String username,
                                     @RequestParam(value = "page", required = false) Integer page,
                                     @RequestParam(value = "pageSize", required = false) @Positive Integer pageSize) {
        LOGGER.info("Get users");
        if (username==null || username.isBlank() || username.equals("null"))
            username = null;
        try {
            return userService.getUsers(username, page, pageSize);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @Operation(summary = "Get a user by id"/*, authorizations = {@Authorization(value = "apiKey")} */)
    public UserDTO find(@PathVariable Long id, @RequestParam(name = "usertype", required = true) String userType) {
        LOGGER.info("Get user with id " + id);
        try {
            return userService.findOne(id, userType);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }


    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
//    @PreAuthorize("hasRole('ADMIN')")
   // @Operation(summary = "Create a user"/*, authorizations = {@Authorization(value = "apiKey")} */)
    public UserDTO create(@Valid @RequestBody UserDTO userPostDTO, BindingResult result) {
    	List<FieldError> errors = result.getFieldErrors();
    	   for (FieldError error : errors ) {
    	     LOGGER.error(error.getDefaultMessage());
    	   }
        LOGGER.info("Post user " + userPostDTO.getUsername());
        try {
           // boolean captchaResult = verifyCaptchaTokenService.verifyToken(userPostDTO);
            if(true) {
                return userService.createUser(userPostDTO);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unauthorized");
            }
        } catch (ServiceException e) {
            if (e.getCause() instanceof DataIntegrityViolationException) {
                return new UserDTO();
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String create1(){
        return "test";
    }




    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Delete a user by id"/*, authorizations = {@Authorization(value = "apiKey")} */)
    public void delete(@PathVariable Long id) {
        LOGGER.info("Delete user with id " + id);
        try {
            userService.deleteUser(id);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @RequestMapping(value = "/blocked/{id}" ,method = RequestMethod.PUT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "block user by id"/*, authorizations = {@Authorization(value = "apiKey")} */)
    public boolean blockUser(@PathVariable Long id){
        LOGGER.info("blocking user with id" + id);
        try {
            return userService.blockUser(id);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "error during blocking user with id: " + id +" "+ e.getMessage());
        }
    }
    @RequestMapping(value = "blocked/unblock/{id}", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "unblock user by id"/*, authorizations = {@Authorization(value = "apiKey")} */)
    public boolean unblockUser(@PathVariable Long id){
        LOGGER.info("unblocking user with id: " + id);
        return userService.unblockUser(id);
    }

    @RequestMapping(value = "/blocked", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "Get blocked users"/*, authorizations = {@Authorization(value = "apiKey")} */)
    public Page<UserDTO> getBlockedUsers(@RequestParam(value = "username") String username,
                                            @RequestParam(value = "page", required = false) Integer page,
                                            @RequestParam(value = "pageSize", required = false) @Positive Integer pageSize){
        LOGGER.info("Get blocked users");
        if (username.equals("null"))
            username = null;
        try{
            return userService.getBlockedUsers(username, page, pageSize);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during reading filtered customers", e);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error while looking for events by that artist: " + e.getMessage(), e);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No events are found by that artist:" + e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/password", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "change password of user with id"/*, authorizations = {@Authorization(value = "apiKey")} */)
    public void changePassword(@RequestBody PasswordChangeRequest passwordChangeRequest){
        LOGGER.info("changing password for user " + passwordChangeRequest.getId());
        try {
            userService.changePassword(passwordChangeRequest);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "error changing password for user " + passwordChangeRequest.getUserName());
        }
    }


}
