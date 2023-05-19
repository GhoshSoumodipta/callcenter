package at.ac.tuwien.sepm.groupphase.backend.endpoint;

import java.util.List;

import javax.validation.constraints.Positive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.location.LocationDTO;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;
import at.ac.tuwien.sepm.groupphase.backend.exception.ServiceException;
import at.ac.tuwien.sepm.groupphase.backend.repository.projections.SimpleLocation;
import at.ac.tuwien.sepm.groupphase.backend.service.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/api/v1/locations")
@Tag(name = "/api/v1/locations")
public class LocationEndpoint {

    private LocationService locationService;
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    LocationEndpoint(LocationService locationService) {
        this.locationService = locationService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Operation(summary = "Get one location by its id"/*, authorizations = {@Authorization(value = "apiKey")} */)
    public LocationDTO findOneById(@PathVariable("id") Long id){
        LOGGER.info("Location Endpoint: GET location with id " + id);
        return locationService.findOneById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    @Operation(summary = "Get all shows filtered by location"/*, authorizations = {@Authorization(value = "apiKey")} */)
    public Page<LocationDTO> findLocationsFiltered(
        @RequestParam(value = "name", required = false) String name,
        @RequestParam(value = "country", required = false) String country,
        @RequestParam(value = "city", required = false) String city,
        @RequestParam(value = "street", required = false) String street,
        @RequestParam(value = "postalCode", required = false) String postalCode,
        @RequestParam(value = "description", required = false) String description,
        @RequestParam(value = "page", required = false) Integer page,
        @RequestParam(value = "pageSize", required = false) @Positive Integer pageSize
    ) {
        try {
            if (page != null && name == null && country == null && city == null && postalCode == null && street == null && description == null) {
                LOGGER.info("Location Endpoint: Get all locations");
                return locationService.findAll(page);
            } else {
                LOGGER.info("Location Endpoint: Get all locations filtered by some parameters");
                return locationService.findLocationsFiltered(name, country, city, street, postalCode, description, page, pageSize);
            }
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error while looking for locations with those parameters: " + e.getMessage(), e);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while looking for locations with those parameters", e);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No locations are found for the given parameters:" + e.getMessage(), e);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/countries")
    @Operation(summary = "Get all countries filtered alphabetically"/*, authorizations = {@Authorization(value = "apiKey")} */)
    public List<String> getCountriesOrderedByName() {
        try {
            LOGGER.info("Location Endpoint: Get all countries ordered alphabetically");
            return locationService.getCountriesOrderedByName();
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while trying to get all countries ordered: ", e);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No locations are found: " + e.getMessage(), e);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/suggestions")
    @Operation(summary = "Get search result suggestions for locations by returning only location name and id"/*, authorizations = {@Authorization(value = "apiKey")} */)
    public List<SimpleLocation> getSearchResultSuggestions(@RequestParam String name){
        LOGGER.info("GET location");
        return locationService.findSearchResultSuggestions(name);
    }

    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Add a location by id"/*, authorizations = {@Authorization(value = "apiKey")} */)
    public LocationDTO updateLocation(@RequestBody LocationDTO locationDTO) {
        LOGGER.info("LocationEndpoint: Add a location " + locationDTO.toString());
        try {
            return locationService.addLocation(locationDTO);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error during adding a location: " + e.getMessage(), e);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error when reading location: " + e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update a location by id"/*, authorizations = {@Authorization(value = "apiKey")} */)
    public LocationDTO updateLocation(@RequestBody LocationDTO locationDTO, @PathVariable("id") Long id) {
        LOGGER.info("LocationEndpoint: Update location " + locationDTO.toString());
        locationDTO.setId(id);
        try {
            return locationService.updateLocation(locationDTO);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error during updating location: " + e.getMessage(), e);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error when reading location: " + e.getMessage(), e);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete a location by id"/*, authorizations = {@Authorization(value = "apiKey")} */)
    public void deleteById(@PathVariable Long id) {
        LOGGER.info("LocationEndpoint: deleteById " + id);
        try {
            locationService.deleteById(id);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
