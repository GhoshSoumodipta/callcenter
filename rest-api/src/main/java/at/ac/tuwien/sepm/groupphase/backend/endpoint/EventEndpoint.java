package at.ac.tuwien.sepm.groupphase.backend.endpoint;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.ac.tuwien.sepm.groupphase.backend.service.EventService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/api/v1/events")
@Tag(name = "api/v1/events")
public class EventEndpoint {

    private final EventService eventService;
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    public EventEndpoint(EventService eventService) {
        this.eventService = eventService;
    }

/*
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create event", authorizations = {@Authorization(value = "apiKey")} )
    public EventDTO createEvent(@Valid @RequestBody EventDTO eventDTO) {
        LOGGER.info("Event Endpoint: createEvent");
        try {
            return eventService.createEvent(eventDTO);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/topten")
    @Operation(summary = "Get top 10 events"/*, authorizations = {@Authorization(value = "apiKey")} )
    public List<EventDTO> findTopTenEvents(@RequestBody TopTenDetailsDTO details) {
        LOGGER.info("Event Endpoint: findTopTenEvents");
        Set<String> monthsSet = new HashSet<>(details.getMonths());
        Set<EventType> categoriesSet = new HashSet<>();

        for (String s: details.getCategories()) {
            categoriesSet.add(EventType.valueOf(s));
        }

        try {
            return new ArrayList<>(eventService.findTopTenEvents(monthsSet, categoriesSet));
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }


    @RequestMapping(method = RequestMethod.GET)
    @Operation(summary = "Get all events"/*, authorizations = {@Authorization(value = "apiKey")} )
    public Page<EventDTO> findEventsFilteredByAttributes(@RequestParam(value = "eventName", required = false) String eventName,
                                                         @RequestParam(value = "locationName", required = false) String locationName,
                                                         @RequestParam(value = "eventType", required = false) String eventType,
                                                         @RequestParam(value = "content", required = false) String content,
                                                         @RequestParam(value = "description", required = false) String description,
                                                         @RequestParam(value = "duration", required = false) Integer duration,

                                                         @RequestParam(value = "page", required = false) Integer page,
                                                         @RequestParam(value = "pageSize", required = false) @Positive Integer pageSize){
        if (page!= null && eventName == null && eventType == null && content == null && description == null && duration == null) {
            LOGGER.info("Event Endpoint: findAll");
            try {
                return eventService.findAll(page, pageSize);
            } catch (ServiceException e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
            }
        } else if(eventName != null && eventName.equals("getAllEventsNotPaginated")) {
            LOGGER.info("Event Endpoint: findAll Not Paginated");
            try {
                return eventService.findAll(page, -1);
            } catch (ServiceException e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
            }
        } else {
            LOGGER.info("Event Endpoint: findEventsFilteredByAttributes");
            try{
                EventType eventTypeConv = null;
                if (eventType != null) {
                    for (EventType type : EventType.values()
                    ) {
                        if (eventType.equalsIgnoreCase(type.name())) {
                            eventTypeConv = type;
                        }

                    }
                }
                EventSearchParametersDTO parameters = EventSearchParametersDTO.builder()
                    .setName(eventName)
                    .setContent(content)
                    .setDurationInMinutes(duration)
                    .setDescription(description)
                    .setEventType(eventTypeConv)
                    .setLocationName(locationName)
                    .build();
                LOGGER.info("Event Endpoint: findEventsFilteredByAttributes" + parameters.toString());
                Page<EventDTO> page1 = eventService.findAllFiltered(parameters, page, pageSize);
                LOGGER.debug("Event Endpoint: " + page1.getContent().toString());
                return page1;
            }catch (IllegalArgumentException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error while looking for events with those parameters: " + e.getMessage(), e);
            }catch (ServiceException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error while looking for events with those parameters", e);
            } catch (NotFoundException e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No events are found for the given parameters:" + e.getMessage(), e);
            }
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Operation(summary = "Delete event by id"/*, authorizations = {@Authorization(value = "apiKey")} )
    public void delete(@PathVariable Long id) {
        LOGGER.info("Delete event with id " + id);
        try {
            eventService.deleteEvent(id);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @Operation(summary = "Update event"/*, authorizations = {@Authorization(value = "apiKey")} )
    public EventDTO updateEvent(@Valid @RequestBody EventDTO eventDTO, @PathVariable("id") Long id) {
        LOGGER.info("Update event " + eventDTO.toString());
        eventDTO.setId(id);
        try {
            return eventService.updateEvent(eventDTO);
        } catch (org.hibernate.service.spi.ServiceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error during updating event: " + e.getMessage(), e);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error when reading event: " + e.getMessage(), e);
        }
    }
*/
}
