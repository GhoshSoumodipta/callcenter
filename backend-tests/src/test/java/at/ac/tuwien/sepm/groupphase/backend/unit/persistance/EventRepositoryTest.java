package at.ac.tuwien.sepm.groupphase.backend.unit.persistance;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import at.ac.tuwien.sepm.groupphase.backend.datatype.EventType;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.searchParameters.EventSearchParametersDTO;
import at.ac.tuwien.sepm.groupphase.backend.entity.Event;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;
import at.ac.tuwien.sepm.groupphase.backend.repository.EventRepository;

@DataJpaTest
public class EventRepositoryTest {

    @Autowired
    private EventRepository eventRepository;

    private boolean initiated = false;

    Page<Event> eventPage;
    private Event event1 = Event.builder().name("Birthday").eventType(EventType.FESTIVAL).content("feiern").description("Its my Birthday").build();
    private Event event2 = Event.builder().name("NoFind").eventType(EventType.CONCERT).content("feiern und warten").description("nicht da").build();
    private Event event3 = Event.builder().name("Birthday").eventType(EventType.FESTIVAL).content("feiern").description("Its my Birthday").build();
    private Event eventTheatre = Event.builder().name("NOTAASDLKSAKDJl").eventType(EventType.THEATRE).content("SAÖLDKASKDAÖLSKD").description("SADKÖSADKLASJDKL").build();

    @Before
    public void before(){
        if(!initiated){
            event1 = eventRepository.save(event1);
            event2 = eventRepository.save(event2);
            event3 = eventRepository.save(event3);
            eventTheatre = eventRepository.save(eventTheatre);
            initiated = true;
        }
    }

    @Test
    public void searchByMultipleParameters(){
        EventSearchParametersDTO parameters = EventSearchParametersDTO.builder().setDurationInMinutes(160).setContent(event1.getContent()).setName("Birth").build();
        eventPage = eventRepository.findAllEventsFiltered(parameters, PageRequest.of(0, 10));
        Assert.assertTrue(eventPage.getContent().contains(event1));
        Assert.assertEquals(1, eventPage.getContent().size());
        parameters = EventSearchParametersDTO.builder().setContent("feie").setName("ind").build();
        eventPage = eventRepository.findAllEventsFiltered(parameters, PageRequest.of(0, 10));
        Assert.assertTrue(eventPage.getContent().contains(event2));
    }

    @Test
    public void searchByEventName_FindsCorrectAmountOfShows_AndResultsMatchExpected(){
        EventSearchParametersDTO parameters = EventSearchParametersDTO.builder().setName("Birthday").build();
        eventPage = eventRepository.findAllEventsFiltered(parameters, PageRequest.of(0, 10));
        Assert.assertEquals(2, eventPage.getContent().size());
        Assert.assertTrue(eventPage.getContent().contains(event1));
        Assert.assertTrue(eventPage.getContent().contains(event3));
    }

    @Test(expected = NotFoundException.class)
    public void searchByEventName_(){
        EventSearchParametersDTO parameters = EventSearchParametersDTO.builder().setName("Rihanna").build();
        eventRepository.findAllEventsFiltered(parameters, PageRequest.of(0, 10));
    }

    @Test
    public void searchByContent_FindsCorrectAmountOfShows_AndResultsMatchExpected(){
        EventSearchParametersDTO parameters = EventSearchParametersDTO.builder().setContent("feiern").build();
        eventPage = eventRepository.findAllEventsFiltered(parameters, PageRequest.of(0, 10));
        Assert.assertEquals(3, eventPage.getContent().size());
        //Assert.assertTrue(eventList.contains(event1) && eventList.contains(event2) && eventList.contains(event3));
        parameters = EventSearchParametersDTO.builder().setContent("warten").build();
        eventPage = eventRepository.findAllEventsFiltered(parameters, PageRequest.of(0, 10));
        Assert.assertEquals(1, eventPage.getContent().size());
        Assert.assertTrue(eventPage.getContent().contains(event2));
    }
    @Test
    public void searchByDuration_FindsCorrectAmountOfShows_AndResultsMatchExpected(){
        EventSearchParametersDTO parameters = EventSearchParametersDTO.builder().setDurationInMinutes(420).build();
        eventPage = eventRepository.findAllEventsFiltered(parameters,PageRequest.of(0, 10));
        Assert.assertEquals(2, eventPage.getContent().size());
        Assert.assertTrue(eventPage.getContent().contains(event2));
        Assert.assertTrue(eventPage.getContent().contains(event3));
        parameters = EventSearchParametersDTO.builder().setDurationInMinutes(160).build();
        eventPage = eventRepository.findAllEventsFiltered(parameters,PageRequest.of(0, 10));
        Assert.assertEquals(1, eventPage.getContent().size());
        Assert.assertTrue(eventPage.getContent().contains(event1));
    }
    @Test
    public void searchByArtist_FindsCorrectAmountOfShows_AndResultsMatchExpected(){
        EventSearchParametersDTO parameters = EventSearchParametersDTO.builder().build();
        eventPage = eventRepository.findAllEventsFiltered(parameters,PageRequest.of(0, 10));
        Assert.assertEquals(2, eventPage.getContent().size());
        Assert.assertTrue(eventPage.getContent().contains(event1));
        Assert.assertTrue(eventPage.getContent().contains(event2));
        parameters = EventSearchParametersDTO.builder().build();
        eventPage = eventRepository.findAllEventsFiltered(parameters,PageRequest.of(0, 10));
        Assert.assertEquals(2, eventPage.getContent().size());
        Assert.assertTrue(eventPage.getContent().contains(event3));
    }

    @Test
    public void searchByEventType_FindsCorrectAmountOfShows_AndResultsMatchExpected(){
        EventSearchParametersDTO parameters = EventSearchParametersDTO.builder().setEventType(event3.getEventType()).build();
        eventPage = eventRepository.findAllEventsFiltered(parameters,PageRequest.of(0, 10));
        Assert.assertEquals(2, eventPage.getContent().size());
        Assert.assertTrue(eventPage.getContent().contains(event1));
        Assert.assertTrue(eventPage.getContent().contains(event3));
        parameters = EventSearchParametersDTO.builder().setEventType(eventTheatre.getEventType()).build();
        eventPage = eventRepository.findAllEventsFiltered(parameters,PageRequest.of(0, 10));
        Assert.assertEquals(1, eventPage.getContent().size());
        Assert.assertTrue(eventPage.getContent().contains(eventTheatre));
    }



}
