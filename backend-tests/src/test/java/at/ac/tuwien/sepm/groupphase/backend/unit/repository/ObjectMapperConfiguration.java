package at.ac.tuwien.sepm.groupphase.backend.unit.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

//@TestConfiguration
public class
ObjectMapperConfiguration {


    @Bean(name = "mvcHandlerMappingIntrospector")
    public HandlerMappingIntrospector mvcHandlerMappingIntrospector() {
        return new HandlerMappingIntrospector();
    }
    @Bean
    @Primary
    public ObjectMapper scmsObjectMapper() {
        com.fasterxml.jackson.databind.ObjectMapper responseMapper = new com.fasterxml.jackson.databind.ObjectMapper();
        return responseMapper;
    }
}
