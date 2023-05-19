package at.ac.tuwien.sepm.groupphase.backend.integrationtest;

import at.ac.tuwien.sepm.groupphase.backend.datatype.EventType;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.event.EventDTO;
import at.ac.tuwien.sepm.groupphase.backend.entity.Event;
import at.ac.tuwien.sepm.groupphase.backend.integrationtest.base.BaseIntegrationTest;
import at.ac.tuwien.sepm.groupphase.backend.repository.EventRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.hamcrest.core.Is.is;

public class EventIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private EventRepository eventRepository;

    private static final String EVENTS_SEARCH_ENDPOINT = "/events/?";
    private boolean initiated = false;

    private Event event1 = Event.builder().name("Birthday").eventType(EventType.FESTIVAL).content("feiern").description("Its my Birthday").build();
    private Event event2 = Event.builder().name("NoFind").eventType(EventType.CONCERT).content("feiern und warten").description("nicht da").build();
    private Event event3 = Event.builder().name("Birthday").eventType(EventType.FESTIVAL).content("feiern").description("Its my Birthday").build();
    private Event eventTheatre = Event.builder().name("NOTAASDLKSAKDJl").eventType(EventType.THEATRE).content("SAÖLDKASKDAÖLSKD").description("SADKÖSADKLASJDKL").build();
    String EVENT_NAME_SEARCH =  EVENTS_SEARCH_ENDPOINT +"eventName=";
    String CONTENT_SEARCH =  EVENTS_SEARCH_ENDPOINT +"content=";
    String DURATION_SEARCH =  EVENTS_SEARCH_ENDPOINT +"duration=";
    String ARTIST_NAME_SEARCH =  EVENTS_SEARCH_ENDPOINT +"artistName=";
    String EVENT_TYPE_SEARCH =  EVENTS_SEARCH_ENDPOINT +"eventType=";

    @Before
    public void before(){
        if(!initiated){
            this.eventRepository.deleteAll();

            event1 = eventRepository.save(event1);
            event2 = eventRepository.save(event2);
            event3 = eventRepository.save(event3);
            eventTheatre = eventRepository.save(eventTheatre);
            initiated = true;
        }
    }
    @After
    public void after(){
        this.eventRepository.deleteAll();
        this.initiated = false;
    }


      @Test
    public void findEventsByName_findsCorrectNumberOfFittingEvents(){
        Response response = RestAssured
            .given()
            .contentType(ContentType.JSON)
            .header(HttpHeaders.AUTHORIZATION, validUserTokenWithPrefix)
            .when().get(EVENT_NAME_SEARCH + "Birthday" + "&page=0")
            .then().extract().response();
        Assert.assertThat(response.getStatusCode(), is(HttpStatus.OK.value()));
        List<EventDTO> eventDTOS = response.jsonPath().getList("content");
        Assert.assertEquals(2, eventDTOS.size()  );
    }
    @Test
    public void findEventsByContent_findsCorrectNumberOfFittingEvents(){
        Response response = RestAssured
            .given()
            .contentType(ContentType.JSON)
            .header(HttpHeaders.AUTHORIZATION, validUserTokenWithPrefix)
            .when().get(CONTENT_SEARCH + "feiern" + "&page=0")
            .then().extract().response();
        response.print();
        Assert.assertThat(response.getStatusCode(), is(HttpStatus.OK.value()));
        List<EventDTO> events = response.jsonPath().getList("content");
        Assert.assertEquals(events.size(), 3);
        response = RestAssured
            .given()
            .contentType(ContentType.JSON)
            .header(HttpHeaders.AUTHORIZATION, validUserTokenWithPrefix)
            .when().get(CONTENT_SEARCH + "feiern" + "&page=0")
            .then().extract().response();

        Assert.assertThat(response.getStatusCode(), is(HttpStatus.OK.value()));
        events = response.jsonPath().getList("content");
        Assert.assertEquals(3, events.size() );
    }

    @Test
    public void findEventsByDuration_findsCorrectNumberOfFittingEvents(){
        Response response = RestAssured
            .given()
            .contentType(ContentType.JSON)
            .header(HttpHeaders.AUTHORIZATION, validUserTokenWithPrefix)
            .when().get(DURATION_SEARCH + "160" + "&page=0")
            .then().extract().response();
        response.print();
        Assert.assertThat(response.getStatusCode(), is(HttpStatus.OK.value()));
        List<EventDTO> events = response.jsonPath().getList("content");
        Assert.assertEquals( events.size(), 1);
        response = RestAssured
            .given()
            .contentType(ContentType.JSON)
            .header(HttpHeaders.AUTHORIZATION, validUserTokenWithPrefix)
            .when().get(DURATION_SEARCH + "420" + "&page=0")
            .then().extract().response();

        Assert.assertThat(response.getStatusCode(), is(HttpStatus.OK.value()));
        events = response.jsonPath().getList("content");
        Assert.assertEquals(2 ,events.size());
    }


    public void findEventsByEventType_findsCorrectNumberOfFittingEvents(){
        Response response = RestAssured
            .given()
            .contentType(ContentType.JSON)
            .header(HttpHeaders.AUTHORIZATION, validUserTokenWithPrefix)
            .when().get(EVENT_TYPE_SEARCH + event3.getEventType() + "&page=0")
            .then().extract().response();
        response.print();
        Assert.assertThat(response.getStatusCode(), is(HttpStatus.OK.value()));
        List<EventDTO> events = response.jsonPath().getList("content");
        Assert.assertEquals(events.size(), 2);
        response = RestAssured
            .given()
            .contentType(ContentType.JSON)
            .header(HttpHeaders.AUTHORIZATION, validUserTokenWithPrefix)
            .when().get(EVENT_TYPE_SEARCH + eventTheatre.getEventType() + "&page=0")
            .then().extract().response();
        Assert.assertThat(response.getStatusCode(), is(HttpStatus.OK.value()));
        events = response.jsonPath().getList("content");
        Assert.assertEquals(events.size(), 2);
    }

    @Test
    public void searchByEventName_Throws_Not_Found_Status_When_Not_Found(){
        Response response = RestAssured
            .given()
            .contentType(ContentType.JSON)
            .header(HttpHeaders.AUTHORIZATION, validUserTokenWithPrefix)
            .when().get(EVENT_NAME_SEARCH + "Rihanna" + "&page=0")
            .then().extract().response();
        Assert.assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND.value()));
    }


}
