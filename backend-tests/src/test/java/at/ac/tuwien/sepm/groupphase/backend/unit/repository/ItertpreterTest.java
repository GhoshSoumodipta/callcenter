package at.ac.tuwien.sepm.groupphase.backend.unit.repository;

import java.io.File;
import java.sql.Time;
import java.time.LocalDateTime;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import at.ac.tuwien.sepm.groupphase.backend.datatype.UserType;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.base.DTOBase;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.interpreter.Interpreter;
import at.ac.tuwien.sepm.groupphase.backend.entity.base.EntityBase;
import at.ac.tuwien.sepm.groupphase.backend.entity.mapper.MapperBase;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.company.Company;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.switchingcenter.SwitchingCallCenter;
import at.ac.tuwien.sepm.groupphase.backend.factories.contacts.RepositoryFactoryMain;
import at.ac.tuwien.sepm.groupphase.backend.repository.CustomerRepository;
import at.ac.tuwien.sepm.groupphase.backend.repository.contacts.UserRepositoryBase;

@ContextConfiguration(classes =
{JpaConfiguration.class, SecurityConfiguration.class, ObjectMapperConfiguration.class})
@TestPropertySource(
locations = "classpath:application-integration-test.properties")
@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,
	classes = { CustomerRepository.class,
			JpaConfiguration.class/* , JacksonConfiguration.class, SecurityConfiguration.class */}))
@Import({SecurityConfiguration.class, ObjectMapperConfiguration.class})
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class ItertpreterTest {
    /*
    https://www.baeldung.com/spring-boot-testing
     */

    @Autowired
    private RepositoryFactoryMain repositoryFactoryMain;
    /******************************************************************
 TEST VARIABLES
 ******************************************************************/
    private LocalDateTime TEST_NEWS_PUBLISHEDAT_1 = LocalDateTime.of(2016, 11, 13, 12, 15, 0, 0);
    private LocalDateTime TEST_NEWS_PUBLISHEDAT_2 = LocalDateTime.of(2016, 12, 12, 11, 13, 0, 0);
    private String TEST_USER_USERNAME = "testusername";
    private String TEST_USER_PASSWORD = "testpassword";
    private LocalDateTime TEST_USER_SINCE =
        LocalDateTime.of(2018, 5, 25, 14, 22, 56);
    private LocalDateTime TEST_USER_LAST_LOGIN =
        LocalDateTime.of(2018, 5, 25, 14, 22, 56);
    private Time TEST_USER_COUNTER = new Time(2, 6, 50);
    private LocalDateTime TEST_USER_BIRTHDAY =
        LocalDateTime.of(2070, 5, 25, 14, 22, 56);

    private static SwitchingCallCenter callCenterGlobal;
    private static Company companyGlobal;
//    @Before
    public void before() throws Exception{
    }


    @Test
    @Order(1)
    @Rollback(false)
    public void testCreateInterpreter_Success() throws Exception{
        Interpreter customer = Interpreter.builder()
        		.locationOnline(true)
        		.certificate(true)
        		.linkForVideocall("Interpreter link for video call")
        		.bankData("Bank Data Interpreter")
        		.bankDetails("Bank details Interpreter")
        		.topicKnowledge(new String[] {"some knowlege", "some other knowledge", })
        		.paymentInterval(new String[] {"payment interval 1", "payment interval2"})
            .birthday(TEST_USER_BIRTHDAY)
            .counter(TEST_USER_COUNTER).country("ABC").firstName("kawaman").lastName("mail").lastLogin(TEST_USER_LAST_LOGIN).houseNo("7").location("kkk")
            .onlineStatus(true).zip(1164).phoneNo("+359885828235").pdfs(new String[]{"test.pdf"}).profilePhoto("avatar.gif")
            .street("Пловдивско шосе").username("kawaman@mail.bb").password("password").userType(UserType.INTERPRETER).userSince(TEST_USER_SINCE).build();
        System.out.println(customer.getClass().getSimpleName()+" 1: "+customer);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.writeValue(new File("../bin/interpreter.json"), customer);

        String json = objectMapper.writeValueAsString(customer);

        UserRepositoryBase repositoryBase = repositoryFactoryMain.getRepository(customer.getUserType());
        DTOBase dto = (DTOBase) ((MapperBase)repositoryBase.mapper()).entityToDTO(customer);
        System.out.println(dto.toString());
        EntityBase interpreter2 = (EntityBase)((MapperBase)repositoryBase.mapper()).DTOToEntity(dto);
        EntityBase obj = repositoryBase.create(interpreter2);
        System.out.println(obj.toString());
    }
}
