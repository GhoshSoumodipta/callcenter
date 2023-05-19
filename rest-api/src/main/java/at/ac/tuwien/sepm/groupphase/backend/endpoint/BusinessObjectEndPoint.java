package at.ac.tuwien.sepm.groupphase.backend.endpoint;

import java.util.List;

import javax.validation.constraints.Positive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.base.DTOBase;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.UserDTO;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;
import at.ac.tuwien.sepm.groupphase.backend.exception.ServiceException;
import at.ac.tuwien.sepm.groupphase.backend.service.BusinessObjectService;
import at.ac.tuwien.sepm.groupphase.backend.service.VerifyCaptchaTokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/api/v1/objects")
@Tag(name = "/api/v1/objects")
public class BusinessObjectEndPoint {
    private final Logger LOGGER = LoggerFactory.getLogger(BusinessObjectEndPoint.class);

    @Autowired
    private VerifyCaptchaTokenService verifyCaptchaTokenService;

    @Autowired
    private BusinessObjectService businessObjectService;

    @RequestMapping(value = "/{id}/{usertype}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CUSTOMER', 'ROLE_COMPANY', 'ROLE_COMPANYEMPLOYEE', 'ROLE_COMPANYLEADER', 'ROLE_SWITCHINGCALLCENTER', 'ROLE_SWITCHINGCALLCENTERLEADER', 'ROLE_SWITCHINGCALLCENTEREMPLOYEE')")
	@Operation(summary = "Get a user by id"/*, authorizations = {@Authorization(value = "apiKey")} */)
	public UserDTO find(@PathVariable(name = "id", required = true) Long id, @PathVariable(name = "usertype", required = true) String userType) {
		LOGGER.info("Get user with id " + id);
		try {
			return businessObjectService.findOne(id, userType);
		} catch (NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CUSTOMER', 'ROLE_COMPANY', 'ROLE_COMPANYEMPLOYEE', 'ROLE_COMPANYLEADER', 'ROLE_SWITCHINGCALLCENTER', 'ROLE_SWITCHINGCALLCENTERLEADER', 'ROLE_SWITCHINGCALLCENTEREMPLOYEE')")
	@Operation(summary = "Get users"/* , authorizations = {@Authorization(value = "apiKey")} */)
    public Page<DTOBase> getUsers(   @RequestParam(value = "object", required = true) String object,
                                        @RequestParam(value = "username", required = false) String username,
                                        @RequestParam(value = "page", required = false) Integer page,
                                        @RequestParam(value = "pageSize", required = false) @Positive Integer pageSize) {
        LOGGER.info("Get users");
        if (username==null || username.isBlank() || username.equals("null"))
            username = null;
        try {
            return businessObjectService.getObjects(object, username, page, pageSize);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_COMPANY', 'ROLE_COMPANYEMPLOYEE', 'ROLE_COMPANYLEADER', 'ROLE_SWITCHINGCALLCENTER', 'ROLE_SWITCHINGCALLCENTERLEADER', 'ROLE_SWITCHINGCALLCENTEREMPLOYEE')")
    @Operation(summary = "Create a user"/*, authorizations = {@Authorization(value = "apiKey")}*/)
    public DTOBase create(@RequestBody String json, BindingResult result) {
        List<FieldError> errors = result.getFieldErrors();
        for (FieldError error : errors ) {
            LOGGER.error(error.getDefaultMessage());
        }
        try {

//            boolean captchaResult = verifyCaptchaTokenService.verifyToken(dtoBase);
            boolean captchaResult = true;
            if(captchaResult) {
                return businessObjectService.createObject(json);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unauthorized");
            }
        } catch (Exception e) {
//            if (e.getCause() instanceof DataIntegrityViolationException) {
//                return new UserGetDTO();
//            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


}
