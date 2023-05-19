package at.ac.tuwien.sepm.groupphase.backend.unit.service;

import java.io.File;
import java.math.BigDecimal;
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
import at.ac.tuwien.sepm.groupphase.backend.entity.mapper.MapperBase;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.Organisation;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.company.Company;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.company.CompanyEmployee;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.company.CompanyLeader;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.customer.Customer;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.switchingcenter.SwitchingCallCenter;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.switchingcenter.SwitchingCallCenterEmployee;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.switchingcenter.SwitchingCallCenterLeader;
import at.ac.tuwien.sepm.groupphase.backend.factories.contacts.RepositoryFactoryMain;
import at.ac.tuwien.sepm.groupphase.backend.repository.CustomerRepository;
import at.ac.tuwien.sepm.groupphase.backend.repository.contacts.UserRepositoryBase;
import at.ac.tuwien.sepm.groupphase.backend.service.BusinessObjectService;
import at.ac.tuwien.sepm.groupphase.backend.service.implementation.BusinessObjectServiceImpl;
import at.ac.tuwien.sepm.groupphase.backend.unit.repository.JpaConfiguration;
import at.ac.tuwien.sepm.groupphase.backend.unit.repository.ObjectMapperConfiguration;
import at.ac.tuwien.sepm.groupphase.backend.unit.repository.SecurityConfiguration;

@ContextConfiguration(classes =
{JpaConfiguration.class, SecurityConfiguration.class, ObjectMapperConfiguration.class})
@TestPropertySource(
locations = "classpath:application-integration-test.properties")
@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,
	classes = { CustomerRepository.class, 
			at.ac.tuwien.sepm.groupphase.backend.unit.repository.JpaConfiguration.class/* , JacksonConfiguration.class, SecurityConfiguration.class */}))
@Import({at.ac.tuwien.sepm.groupphase.backend.unit.repository.SecurityConfiguration.class, ObjectMapperConfiguration.class, BusinessObjectServiceImpl.class})
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class BusinessObjectServiceTest {
    /*
    https://www.baeldung.com/spring-boot-testing
     */
    @Autowired
    private BusinessObjectService businessObjectService;

//    @MockBean
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
//    @BeforeEach
    void checkPreConditions() {
    }


    public SwitchingCallCenter createSwitchingCallCenter() throws Exception{
        SwitchingCallCenter center = SwitchingCallCenter.builder().country("Bulgaria").zip(1000).counter(TEST_USER_COUNTER)
            .organisation(Organisation.builder().url("Call Center URL").linkForVideoCall("Call Center Link for Video Call").name("Bulgarian Call Center").build())
            .birthday(TEST_USER_BIRTHDAY).firstName("Call").lastName("Center").houseNo("5").lastLogin(TEST_USER_LAST_LOGIN)
            .location("Abra Cadabra").onlineStatus(true).phoneNo("+359885828235").password("password")
            .username("callcenter@mail.tr").userType(UserType.valueOf("SWITCHINGCENTER")).build();
        System.out.println(center.getClass().getSimpleName()+": "+center);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
//        objectMapper.writeValue(new File("../bin/customer.json"), customer);

        String json = objectMapper.writeValueAsString(center);

        UserRepositoryBase repositoryBase = repositoryFactoryMain.getRepository(center.getUserType());
        DTOBase dto = (DTOBase) ((MapperBase)repositoryBase.mapper()).entityToDTO(center);
        System.out.println(dto.toString());
        DTOBase obj = businessObjectService.createObject(json);
        System.out.println(obj.toString());
        return center;
    }
    public Company createCompany() throws Exception{
        Company center = Company.builder().country("Bulgaria").zip(1000).counter(TEST_USER_COUNTER)
            .organisation(Organisation.builder().url("Company URL").linkForVideoCall("Company Link for Video Call").name("Bulgarian Company").build())
            .birthday(TEST_USER_BIRTHDAY).firstName("Call").lastName("Center").houseNo("5").lastLogin(TEST_USER_LAST_LOGIN)
            .location("Abra Cadabra").onlineStatus(true).phoneNo("+359885828235").password("password")
            .invoice("Company Invoice")
            .username("callcenter@abv.se").userType(UserType.COMPANY).build();
        System.out.println(center.getClass().getSimpleName()+": "+center);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
//        objectMapper.writeValue(new File("../bin/customer.json"), customer);

        String json = objectMapper.writeValueAsString(center);

        UserRepositoryBase repositoryBase = repositoryFactoryMain.getRepository(center.getUserType());
        DTOBase dto = (DTOBase) ((MapperBase)repositoryBase.mapper()).entityToDTO(center);
        System.out.println(dto.toString());
        DTOBase obj = businessObjectService.createObject(json);
        System.out.println(obj.toString());
        return center;
    }
    @Test
    @Order(1)
    @Rollback(false)
    public void testCreateCustomer_Success() throws Exception{
        Customer customer = Customer.builder().creditPrivate(new BigDecimal(12.34)).SMS_certification(true)
            .creditWorking(new BigDecimal(12.34)).birthday(TEST_USER_BIRTHDAY)
            .counter(TEST_USER_COUNTER).country("ABC").firstName("kawaman").lastName("mail").lastLogin(TEST_USER_LAST_LOGIN).houseNo("7").location("kkk")
            .onlineStatus(true).zip(1164).phoneNo("+359885828235").pdfs(new String[]{"test.pdf"}).profilePhoto("avatar.gif")
            .street("Пловдивско шосе").username("kawaman@mail.tr").password("password").userType(UserType.CUSTOMER).userSince(TEST_USER_SINCE).build();
        System.out.println(customer.getClass().getSimpleName()+" 1: "+customer);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.writeValue(new File("../bin/customer.json"), customer);

        String json = objectMapper.writeValueAsString(customer);

        UserRepositoryBase repositoryBase = repositoryFactoryMain.getRepository(customer.getUserType());
        DTOBase dto = (DTOBase) ((MapperBase)repositoryBase.mapper()).entityToDTO(customer);
        System.out.println(dto.toString());
        DTOBase obj = businessObjectService.createObject(json);
        System.out.println(obj.toString());
    }

    @Test
    @Order(2)
    @Rollback(false)
    public void testCreateCompany_Success() throws Exception{
//        CompanyType type = CompanyType.builder().name("")
        Company company = Company.builder().invoice("invoice").organisation(Organisation.builder().name("My Company").linkForVideoCall("Link For Video Call").url("URL").build()).birthday(TEST_USER_BIRTHDAY)
            .counter(TEST_USER_COUNTER).country("ABC").firstName("kawaman").lastName("mail").lastLogin(TEST_USER_LAST_LOGIN).houseNo("7").location("kkk")
            .onlineStatus(true).zip(1164).phoneNo("+359885828235").pdfs(new String[]{"test.pdf"}).profilePhoto("avatar.gif")
            .street("Пловдивско шосе").username("kawaman@mail.sb").password("password").userType(UserType.COMPANY).userSince(TEST_USER_SINCE).build();
        System.out.println(company.getClass().getSimpleName()+" 2: "+company);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.writeValue(new File("../bin/company.json"), company);

        String json = objectMapper.writeValueAsString(company);

        UserRepositoryBase repositoryBase = repositoryFactoryMain.getRepository(company.getUserType());
        DTOBase dto = (DTOBase) ((MapperBase)repositoryBase.mapper()).entityToDTO(company);
        System.out.println(dto.toString());
        DTOBase obj = businessObjectService.createObject(json);
        System.out.println(obj.toString());
        companyGlobal =  company;
    }
    @Test
    @Rollback(false)
    @Order(3)
    public void testCreateSwitchingCallCenter_Success() throws Exception{
//        CompanyType type = CompanyType.builder().name("")

        SwitchingCallCenter company = SwitchingCallCenter.builder().organisation(Organisation.builder().name("My Call Center").linkForVideoCall("Call Center Link For Video Call").url("Call Center URL").build()).birthday(TEST_USER_BIRTHDAY)
            .counter(TEST_USER_COUNTER).country("ABC").firstName("kawaman").lastName("mail").lastLogin(TEST_USER_LAST_LOGIN).houseNo("7").location("kkk")
            .onlineStatus(true).zip(1164).phoneNo("+359885828235").pdfs(new String[]{"test.pdf"}).profilePhoto("avatar.gif")
            .street("Пловдивско шосе").username("kawaman@mail.ar").password("password").userType(UserType.SWITCHINGCALLCENTER).userSince(TEST_USER_SINCE).build();
        System.out.println(company.getClass().getSimpleName()+" 3: "+company);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.writeValue(new File("../bin/switching-call-center.json"), company);

        String json = objectMapper.writeValueAsString(company);

        UserRepositoryBase repositoryBase = repositoryFactoryMain.getRepository(company.getUserType());
        DTOBase dto = (DTOBase) ((MapperBase)repositoryBase.mapper()).entityToDTO(company);
        System.out.println(dto.toString());
        DTOBase obj = businessObjectService.createObject(json);
        System.out.println(obj.toString());
        callCenterGlobal =  company;
    }
    @Test
    @Rollback(false)
    @Order(4)
    public void testCreateSwitchingCallCenterEmployee_Success() throws Exception{

//        SwitchingCallCenter callCenterGlobal = createSwitchingCallCenter();
        System.err.println("callCenterGlobal 4: ".toUpperCase()+callCenterGlobal);
        SwitchingCallCenterEmployee company = SwitchingCallCenterEmployee.builder().switchingCallCenter(callCenterGlobal).employeeLinkForVideoCall("Link For Video Call").birthday(TEST_USER_BIRTHDAY)
            .counter(TEST_USER_COUNTER).country("ABC").firstName("kawaman").lastName("mail").lastLogin(TEST_USER_LAST_LOGIN).houseNo("7").location("kkk")
            .onlineStatus(true).zip(1164).phoneNo("+359885828235").pdfs(new String[]{"test.pdf"}).profilePhoto("avatar.gif")
            .street("Пловдивско шосе").username("kawaman@mail.mk").password("password").userType(UserType.SWITCHINGCALLCENTEREMPLOYEE).userSince(TEST_USER_SINCE).build();
        System.out.println(company.getClass().getSimpleName().toUpperCase()+"4: "+company);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.writeValue(new File("../bin/switching-call-center-employee.json"), company);

        String json = objectMapper.writeValueAsString(company);

        UserRepositoryBase repositoryBase = repositoryFactoryMain.getRepository(company.getUserType());
        DTOBase dto = (DTOBase) ((MapperBase)repositoryBase.mapper()).entityToDTO(company);
        System.out.println(dto.toString());
        DTOBase obj = businessObjectService.createObject(json);
        System.out.println(obj.toString());
    }
    @Test
    @Rollback(false)
    @Order(5)
    public void testCreateSwitchingCallCenterLeader_Success() throws Exception{
//        CompanyType type = CompanyType.builder().name("")
        System.err.println("callCenterGlobal 5: ".toUpperCase()+callCenterGlobal);
        SwitchingCallCenterLeader company = SwitchingCallCenterLeader.builder().switchingCallCenter(callCenterGlobal)
            .leaderLinkForVideoCall("Leader Link For Video Call").birthday(TEST_USER_BIRTHDAY)
            .counter(TEST_USER_COUNTER).country("ABC").firstName("kawaman").lastName("mail").lastLogin(TEST_USER_LAST_LOGIN).houseNo("7").location("kkk")
            .onlineStatus(true).zip(1164).phoneNo("+359885828235").pdfs(new String[]{"test.pdf"}).profilePhoto("avatar.gif")
            .street("Пловдивско шосе").username("kawaman@mail.kk").password("password").userType(UserType.SWITCHINGCALLCENTERLEADER).userSince(TEST_USER_SINCE).build();
        System.out.println("Customer: "+company);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.writeValue(new File("../bin/switching-call-center-leader.json"), company);

        String json = objectMapper.writeValueAsString(company);

        UserRepositoryBase repositoryBase = repositoryFactoryMain.getRepository(company.getUserType());
        DTOBase dto = (DTOBase) ((MapperBase)repositoryBase.mapper()).entityToDTO(company);
        System.out.println(dto.toString());
        DTOBase obj = businessObjectService.createObject(json);
        System.out.println(obj.toString());
    }

    @Test
    @Rollback(false)
    @Order(6)
    public void testCreateCompanyEmployee_Success() throws Exception{
//        Company companyGlobal = createCompany();
        System.err.println("companyGlobal 6: ".toUpperCase()+companyGlobal);
        CompanyEmployee companyEmployee = CompanyEmployee.builder().company(companyGlobal).companyEmployeeLinkForVideoCall("Link For Video Call").birthday(TEST_USER_BIRTHDAY)
            .counter(TEST_USER_COUNTER).country("ABC").firstName("kawaman").lastName("mail").lastLogin(TEST_USER_LAST_LOGIN).houseNo("7").location("kkk")
            .onlineStatus(true).zip(1164).phoneNo("+359885828235").pdfs(new String[]{"test.pdf"}).profilePhoto("avatar.gif")
            .street("Пловдивско шосе").username("kawaman@abv.mk").password("password").userType(UserType.COMPANYEMPLOYEE).userSince(TEST_USER_SINCE).build();
        System.out.println("Customer: "+companyEmployee);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.writeValue(new File("../bin/company-employee.json"), companyEmployee);

        objectMapper.writeValue(new File("../bin/company.json"), companyGlobal);


        String json = objectMapper.writeValueAsString(companyEmployee);

        UserRepositoryBase repositoryBase = repositoryFactoryMain.getRepository(companyEmployee.getUserType());
        DTOBase dto = (DTOBase) ((MapperBase)repositoryBase.mapper()).entityToDTO(companyEmployee);
        System.out.println(dto.toString());
        DTOBase obj = businessObjectService.createObject(json);
        System.out.println(obj.toString());
    }

    @Test
    @Rollback(false)
    @Order(7)
    public void testCreateCompanyLeader_Success() throws Exception{
//        CompanyType type = CompanyType.builder().name("")
//        Company companyGlobal = createCompany();

        System.err.println("companyGlobal 7: ".toUpperCase()+companyGlobal);
        CompanyLeader companyLeader = CompanyLeader.builder().company(companyGlobal)
            .companyLeaderLinkForVideoCall("Leader Link For Video Call").birthday(TEST_USER_BIRTHDAY)
            .counter(TEST_USER_COUNTER).country("ABC").firstName("kawaman").lastName("mail").lastLogin(TEST_USER_LAST_LOGIN).houseNo("7").location("kkk")
            .onlineStatus(true).zip(1164).phoneNo("+359885828235").pdfs(new String[]{"test.pdf"}).profilePhoto("avatar.gif")
            .street("Пловдивско шосе").username("kawaman@abv.tr").password("password").userType(UserType.COMPANYLEADER).userSince(TEST_USER_SINCE).build();
        System.out.println("Customer: "+companyLeader);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.writeValue(new File("../bin/company-leader.json"), companyLeader);

        objectMapper.writeValue(new File("../bin/company.json"), companyGlobal);

        String json = objectMapper.writeValueAsString(companyLeader);

        UserRepositoryBase repositoryBase = repositoryFactoryMain.getRepository(companyLeader.getUserType());
        DTOBase dto = (DTOBase) ((MapperBase)repositoryBase.mapper()).entityToDTO(companyLeader);
        System.out.println(dto.toString());
        DTOBase obj = businessObjectService.createObject(json);
        System.out.println(obj.toString());
    }

    @Test
    @Rollback(false)
    @Order(8)
    public void testCreateSwitchingCallCenterEmployee2_Success() throws Exception{

//        SwitchingCallCenter callCenterGlobal = createSwitchingCallCenter();
        System.err.println("callCenterGlobal 8: ".toUpperCase()+callCenterGlobal);
        SwitchingCallCenterEmployee company = SwitchingCallCenterEmployee.builder().switchingCallCenter(callCenterGlobal).employeeLinkForVideoCall("Link For Video Call").birthday(TEST_USER_BIRTHDAY)
            .counter(TEST_USER_COUNTER).country("ABC").firstName("kawaman").lastName("mail").lastLogin(TEST_USER_LAST_LOGIN).houseNo("7").location("kkk")
            .onlineStatus(true).zip(1164).phoneNo("+359885828235").pdfs(new String[]{"test.pdf"}).profilePhoto("avatar.gif")
            .street("Пловдивско шосе").username("kawaman@mail.rk").password("password").userType(UserType.SWITCHINGCALLCENTEREMPLOYEE).userSince(TEST_USER_SINCE).build();
        String companyTrace = company.getClass().getSimpleName().toUpperCase()+" 8: "+company;
        System.err.println(companyTrace);
        System.out.println(companyTrace);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.writeValue(new File("../bin/switching-call-center-employee2.json"), company);

        String json = objectMapper.writeValueAsString(company);

        UserRepositoryBase repositoryBase = repositoryFactoryMain.getRepository(company.getUserType());
        DTOBase dto = (DTOBase) ((MapperBase)repositoryBase.mapper()).entityToDTO(company);
        System.out.println(dto.toString());
        DTOBase obj = businessObjectService.createObject(json);
        System.out.println(obj.toString());
    }
    @Test
    @Rollback(false)
    @Order(9)
    public void testCreateSwitchingCallCenterLeader2_Success() throws Exception{
//        CompanyType type = CompanyType.builder().name("")
        System.err.println("callCenterGlobal 9: ".toUpperCase()+callCenterGlobal);
        SwitchingCallCenterLeader company = SwitchingCallCenterLeader.builder().switchingCallCenter(callCenterGlobal)
            .leaderLinkForVideoCall("Leader Link For Video Call").birthday(TEST_USER_BIRTHDAY)
            .counter(TEST_USER_COUNTER).country("ABC").firstName("kawaman").lastName("mail").lastLogin(TEST_USER_LAST_LOGIN).houseNo("7").location("kkk")
            .onlineStatus(true).zip(1164).phoneNo("+359885828235").pdfs(new String[]{"test.pdf"}).profilePhoto("avatar.gif")
            .street("Пловдивско шосе").username("kawaman@mail.sk").password("password").userType(UserType.SWITCHINGCALLCENTERLEADER).userSince(TEST_USER_SINCE).build();
        System.out.println("Customer: "+company);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.writeValue(new File("../bin/switching-call-center-leader2.json"), company);

        String json = objectMapper.writeValueAsString(company);

        UserRepositoryBase repositoryBase = repositoryFactoryMain.getRepository(company.getUserType());
        DTOBase dto = (DTOBase) ((MapperBase)repositoryBase.mapper()).entityToDTO(company);
        System.out.println(dto.toString());
        DTOBase obj = businessObjectService.createObject(json);
        System.out.println(obj.toString());
    }
}
