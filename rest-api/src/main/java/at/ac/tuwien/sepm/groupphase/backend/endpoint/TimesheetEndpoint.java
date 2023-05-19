package at.ac.tuwien.sepm.groupphase.backend.endpoint;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.TimeSheetDTO;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;
import at.ac.tuwien.sepm.groupphase.backend.exception.ServiceException;
import at.ac.tuwien.sepm.groupphase.backend.service.TimesheetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1/timesheet")
@Tag(name = "api/v1/timesheet")
public class TimesheetEndpoint {

	@Autowired
    private  TimesheetService service;
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	    public TimesheetEndpoint(TimesheetService service) {
	        this.service = service;
	    }


	    @GetMapping
//	    @Operation(summary = "Get timesheet with 'Name' as part of their name"/*, authorizations = {@Authorization(value = "apiKey")} */)
	    public Page<TimeSheetDTO> findByName(@RequestParam(value = "name") String name,
	                                             @RequestParam(value = "page",defaultValue = "0") Integer page,
	                                             @RequestParam(value = "pageSize",defaultValue = "10") @Positive Integer pageSize) {
	        LOGGER.info("TimesheetEndpoint: findByName");
	        return service.findByName(name, page, pageSize);
	    }

	    @PostMapping
	    @PreAuthorize("hasRole('ADMIN')")
	    @Operation(summary = "Add an timesheet by id"/*, authorizations = {@Authorization(value = "apiKey")} */)
	    public TimeSheetDTO add(@Valid @RequestBody TimeSheetDTO dto) {
	        LOGGER.info("TimesheetEndpoint: Add an timesheet " + dto.toString());
	        try {
	            return service.add(dto);
	        } catch (ServiceException e) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error during adding a timesheet: " + e.getMessage(), e);
	        } catch (NotFoundException e) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error when reading timesheet: " + e.getMessage(), e);
	        }
	    }

	    @PutMapping(value = "/{id}")
	    @PreAuthorize("hasRole('ADMIN')")
	    @Operation(summary = "Update an timesheet by id"/*, authorizations = {@Authorization(value = "apiKey")} */)
	    public TimeSheetDTO update(@Valid @RequestBody TimeSheetDTO dto, @PathVariable("id") Long id) {
	        LOGGER.info("TimesheetEndpoint: update");
	        dto.setId(id);
	        try {
	            return service.update(dto);
	        } catch (ServiceException e) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error during adding a timesheet: " + e.getMessage(), e);
	        } catch (NotFoundException e) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error when reading timesheet: " + e.getMessage(), e);
	        }
	    }

	    @DeleteMapping(value = "/{id}")
	    @PreAuthorize("hasRole('ADMIN')")
	    @Operation(summary = "Delete an timesheet by id"/*, authorizations = {@Authorization(value = "apiKey")} */)
	    public void deleteById(@PathVariable Long id) {
	        LOGGER.info("TimesheetEndpoint: deleteById " + id);
	        try {
	            service.deleteById(id);
	        } catch (ServiceException e) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
	        }
	    }

}
