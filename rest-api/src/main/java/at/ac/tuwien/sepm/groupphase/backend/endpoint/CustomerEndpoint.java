package at.ac.tuwien.sepm.groupphase.backend.endpoint;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.customer.CustomerDTO;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;
import at.ac.tuwien.sepm.groupphase.backend.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/api/v1/customers")
@Validated
@Tag(name = "api/v1/customers")
public class CustomerEndpoint {
    private final CustomerService customerService;
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerEndpoint.class);

    public CustomerEndpoint(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public CustomerDTO postCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        LOGGER.info("Post customer " + customerDTO.toString());
        try {
            return customerService.addCustomer(customerDTO);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error during adding of customer: " + e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Operation(summary = "Get detailed information about a specific customer entry"/*, authorizations = {@Authorization(value = "apiKey")} */)
    public CustomerDTO find(@PathVariable Long id) throws Throwable{
        LOGGER.info("Get customer with id " + id);
        return customerService.findOne(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public CustomerDTO adaptCustomer(@Valid @RequestBody CustomerDTO customerDTO, @PathVariable("id") Long id) {
        LOGGER.info("Adapt customer " + customerDTO.toString());
        customerDTO.setUserId(id);
        try {
            return customerService.adaptCustomer(customerDTO);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error during updating customer: " + e.getMessage(), e);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error when reading customer: " + e.getMessage(), e);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    @Operation(summary = "Get list of all customer entries filtered by specified attributes"/*, authorizations = {@Authorization(value = "apiKey")} */)
    public Page<CustomerDTO> findAllFiltered(
        @RequestParam(value = "id", required = false) Long id,
        @RequestParam(value = "name", required = false) String name,
        @RequestParam(value = "firstname", required = false) String firstname,
        @RequestParam(value = "email", required = false) String email,
        @RequestParam(value = "birthday", required = false) String birthday_str,
        @RequestParam(value = "page", required = false) Integer page,
        @RequestParam(value = "pagesize", required = false) @Positive Integer pageSize) {

        boolean filterData = !(id == null && name == null && firstname == null && email == null && birthday_str == null);
        LocalDate birthday = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        if (birthday_str != null) {
            birthday = LocalDate.parse(birthday_str, formatter);
        }
        try {
            Page<CustomerDTO> customerDTOPage;
            if (filterData) {
                LOGGER.info("Get all customers filtered by specified attributes");
                customerDTOPage = customerService.findCustomersFiltered(id, name, firstname, email, birthday, page, pageSize);
            } else {
                LOGGER.info("Get all customers");
                customerDTOPage = customerService.findAll(page);
            }
            return customerDTOPage;
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during reading filtered customers", e);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error while looking for events by that artist: " + e.getMessage(), e);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No events are found by that artist:" + e.getMessage(), e);
        }
    }
}
