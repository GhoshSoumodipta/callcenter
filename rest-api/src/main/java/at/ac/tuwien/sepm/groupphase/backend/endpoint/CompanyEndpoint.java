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

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.company.CompanyDTO;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;
import at.ac.tuwien.sepm.groupphase.backend.exception.ServiceException;
import at.ac.tuwien.sepm.groupphase.backend.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1/company")
@Tag(name = "api/v1/company")
public class CompanyEndpoint {


	@Autowired
    private  CompanyService service;
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	    public CompanyEndpoint(CompanyService service) {
	        this.service = service;
	    }


	    @GetMapping
//	    @Operation(summary = "Get company with 'Name' as part of their name"/*, authorizations = {@Authorization(value = "apiKey")} */)
	    public Page<CompanyDTO> findByName(@RequestParam(value = "name") String name,
                                           @RequestParam(value = "page",defaultValue = "0") Integer page,
                                           @RequestParam(value = "pageSize",defaultValue = "10") @Positive Integer pageSize) {
	        LOGGER.info("CompanyEndpoint: findByName");
	        return service.findByName(name, page, pageSize);
	    }

	    @PostMapping
	    @PreAuthorize("hasRole('ADMIN')")
	    @Operation(summary = "Add an company by id"/*, authorizations = {@Authorization(value = "apiKey")} */)
	    public CompanyDTO add(@Valid @RequestBody CompanyDTO dto) {
	        LOGGER.info("CompanyEndpoint: Add an company " + dto.toString());
	        try {
	            return service.add(dto);
	        } catch (ServiceException e) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error during adding a company: " + e.getMessage(), e);
	        } catch (NotFoundException e) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error when reading company: " + e.getMessage(), e);
	        }
	    }

	    @PutMapping(value = "/{id}")
	    @PreAuthorize("hasRole('ADMIN')")
	    @Operation(summary = "Update an company by id"/*, authorizations = {@Authorization(value = "apiKey")} */)
	    public CompanyDTO update(@Valid @RequestBody CompanyDTO dto, @PathVariable("id") Long id) {
	        LOGGER.info("CompanyEndpoint: update");
            //TODO
//	        dto.setId(id);
	        try {
	            return service.update(dto);
	        } catch (ServiceException e) {
	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error during adding a company: " + e.getMessage(), e);
	        } catch (NotFoundException e) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error when reading company: " + e.getMessage(), e);
	        }
	    }

	    @DeleteMapping(value = "/{id}")
	    @PreAuthorize("hasRole('ADMIN')")
		@Operation(summary = "Delete an company by id" /*, authorizations = {@Authorization(value = "apiKey")} */ )
	    public void deleteById(@PathVariable Long id) {
	        LOGGER.info("CompanyEndpoint: deleteById " + id);
	        try {
	            service.deleteById(id);
	        } catch (ServiceException e) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
	        }
	    }
}
