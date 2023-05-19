package at.ac.tuwien.sepm.groupphase.backend.endpoint;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.usertype.UserTypeDTO;
import at.ac.tuwien.sepm.groupphase.backend.exception.ServiceException;
import at.ac.tuwien.sepm.groupphase.backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/api/v1/types")
@Tag(name = "/api/v1/types")
public class UserTypesEndpoint {

    private final UserService userService;
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    public UserTypesEndpoint(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @Operation(summary = "Get users"/*, authorizations = {@Authorization(value = "apiKey")} */)
    public List<UserTypeDTO> getUserTypes() {
        LOGGER.info("Get userTypes");
        try {
            return userService.getUserTypes();
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
