package at.ac.tuwien.sepm.groupphase.backend.configuration;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import at.ac.tuwien.sepm.groupphase.backend.configuration.properties.ApplicationConfigurationProperties;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
//@EnableSwagger
public class SwaggerConfiguration {

    private final ApplicationConfigurationProperties acp;

    public SwaggerConfiguration(ApplicationConfigurationProperties applicationConfigurationProperties) {
        acp = applicationConfigurationProperties;
    }

//    @Bean
//    public Docket backendApiDocket() {
//        return new Docket(DocumentationType.SWAGGER_2)
//            .select()
//            .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
//            .paths(PathSelectors.any())
//            .build()
//            .pathMapping("/")
//            .apiInfo(new ApiInfo(
//                "Backend",
//                "Interactive API documentation for the backend",
//                acp.getVersion(),
//                null,
//                null,
//                null,
//                null,
//                Collections.emptyList()
//            ))
//            .genericModelSubstitutes(ResponseEntity.class)
//            .securitySchemes(Arrays.asList(apiKey()))
//            .useDefaultResponseMessages(false)
//            .globalResponseMessage(RequestMethod.GET,
//                newArrayList(
//                    new ResponseMessageBuilder()
//                        .code(HttpStatus.OK.value())
//                        .message("Success")
//                        .build(),
//                    new ResponseMessageBuilder()
//                        .code(HttpStatus.UNAUTHORIZED.value())
//                        .message("Unauthorized request, login first")
//                        .build()))
//            .globalResponseMessage(RequestMethod.POST,
//                newArrayList(
//                    new ResponseMessageBuilder()
//                        .code(HttpStatus.OK.value())
//                        .message("Success")
//                        .build(),
//                    new ResponseMessageBuilder()
//                        .code(HttpStatus.UNAUTHORIZED.value())
//                        .message("Unauthorized request, login first")
//                        .build()))
//            .consumes(new HashSet<>(Collections.singletonList(MediaType.APPLICATION_JSON_VALUE)))
//            .produces(new HashSet<>(Collections.singletonList(MediaType.APPLICATION_JSON_VALUE)))
//            ;
//    }
//
//    private ApiKey apiKey() {
//        return new ApiKey("apiKey", "Authorization", "header"); //`apiKey` is the name of the APIKey, `Authorization` is the key in the request header
//    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("SpringShop API")
                .description("Spring shop sample application")
                .version("v0.0.1")
                .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                .description("SpringShop Wiki Documentation")
                .url("https://springshop.wiki.github.org/docs"));
    }
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("springshop-public")
                .pathsToMatch("/**")
                .build();
    }
    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder()
                .group("springshop-admin")
                .pathsToMatch("/admin/**")
                .build();
    }
}
