package at.ac.tuwien.sepm.groupphase.backend.factories.repository;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import at.ac.tuwien.sepm.groupphase.backend.entity.LoginAttempts;
import at.ac.tuwien.sepm.groupphase.backend.entity.base.EntityBase;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.User;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.company.Company;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.company.CompanyEmployee;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.company.CompanyLeader;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.switchingcenter.SwitchingCallCenter;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.switchingcenter.SwitchingCallCenterEmployee;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.switchingcenter.SwitchingCallCenterLeader;
import lombok.Getter;


@Transactional
public class MySimpleRepositoryImpl<T, ID, M, DTO extends Serializable> extends SimpleJpaRepository<T, ID> implements MyJpaRepository<T, ID, M, DTO> {
    
    private final static Logger LOGGER = LoggerFactory.getLogger(MySimpleRepositoryImpl.class);

    @Getter
    private EntityManager entityManager;

    private Class<M> mapperClass;

    private Class<DTO> dtoClass;
    private Class<T> entityClass;

    private Class<MyJpaRepository> repositoryClass;
    private M mapper;
    private T entity;
    private DTO dto;

    private MyJpaRepository repository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void setRepositoryClass() {
        this.repositoryClass = (Class<MyJpaRepository>) this.getClass().getInterfaces()[0];
    }
    public Class<MyJpaRepository> repository(){
        return repositoryClass;
    }

    public void setRepository(MyJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void setMapperClass(Class<M> mapperClass) {
        this.mapperClass = mapperClass;
        mapper = Mappers.getMapper(mapperClass);
    }

    public MySimpleRepositoryImpl(JpaEntityInformation<T, ?> entityInformation,
                                  EntityManager entityManager, PasswordEncoder passwordEncoder){
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public M mapper(){
        return mapper;
    }

    @Override
    public T entity() {
        return entity;
    }

    @Override
    public DTO dto() {
        return dto;
    }


    @Override
    public Class<DTO> getDTOClass() {
        return dtoClass;
    }

    @Override
    public Class<T> getEntityClass() {
        return entityClass;
    }

    public Class<MyJpaRepository> getRepositoryClass() {
        return repositoryClass;
    }


    @Override
    public void setDTOClass(Class<DTO> dtoClass) {
        this.dtoClass = dtoClass;
        try {
            dto = dtoClass.getConstructor().newInstance();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
        try {
            entity = entityClass.getConstructor().newInstance();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    
    <S extends User> Optional<S> getExistsInDb(S user){
		ExampleMatcher customExampleMatcher = ExampleMatcher.matchingAny()
			      .withMatcher("username", ExampleMatcher.GenericPropertyMatchers.exact());
		try {
			S usr = (S)user.getClass().getDeclaredConstructor().newInstance();
			usr.setUsername(user.getUsername());
			Example<S> userExample = Example.of(usr, customExampleMatcher);
			LOGGER.debug("example: "+userExample);
			Optional<S> userOptional = (Optional<S>) findOne((Example<T>)  userExample);
			return userOptional;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage(), e);;
			return Optional.empty();
		}
    }

    public EntityBase create(EntityBase entityBase){
    	if(entityBase instanceof User) {
    		User user = (User)entityBase;
    		Optional<User> userOptional = getExistsInDb(user);
    		if(userOptional.isPresent()) {
    			LOGGER.debug("merging: "+entityBase);
    			getEntityManager().merge(entityBase);
    		} else {
    	        if (entityBase instanceof CompanyEmployee) {
    	            CompanyEmployee companyEmployee = (CompanyEmployee) entityBase;
    	            Company company = companyEmployee.getCompany();
    	            if (company != null) {
    	            	Optional<Company> companyOptional = getExistsInDb(company);
    	                if (companyOptional.isPresent()) {
    	                    companyEmployee.setCompany(companyOptional.get());
    	                } else {
    	                    company = (Company) create(company);
    	                    companyEmployee.setCompany(company);
    	                }
    	            }
    	        }else if(entityBase instanceof CompanyLeader){
    	            CompanyLeader companyEmployee = (CompanyLeader) entityBase;
    	            Company company = companyEmployee.getCompany();
    	            if (company != null) {
    	            	Optional<Company> companyOptional = getExistsInDb(company);
    	                if (companyOptional.isPresent()) {
    	                    companyEmployee.setCompany(companyOptional.get());
    	                } else {
    	                    company = (Company) create(company);
    	                    companyEmployee.setCompany(company);
    	                }
    	            }
    	        } else if (entityBase instanceof SwitchingCallCenterEmployee) {
    	            SwitchingCallCenterEmployee callCenterEmployee = (SwitchingCallCenterEmployee) entityBase;
    	            SwitchingCallCenter callCenter = callCenterEmployee.getSwitchingCallCenter();
    	            if (callCenter != null) {
    	                Optional<SwitchingCallCenter> callCenterOptional = getExistsInDb(callCenter);
    	                if (callCenterOptional.isPresent()) {
    	                    callCenterEmployee.setSwitchingCallCenter(callCenterOptional.get());
    	                } else {
    	                    callCenter = (SwitchingCallCenter) create(callCenter);
    	                    callCenterEmployee.setSwitchingCallCenter(callCenter);
    	                }
    	            }
    	        }else if (entityBase instanceof SwitchingCallCenterLeader) {
    	            SwitchingCallCenterLeader companyEmployee = (SwitchingCallCenterLeader) entityBase;
    	            SwitchingCallCenter company = companyEmployee.getSwitchingCallCenter();
    	            if (company != null) {
    	                Optional<SwitchingCallCenter> companyOptional = getExistsInDb(company);
    	                if (companyOptional.isPresent()) {
    	                    companyEmployee.setSwitchingCallCenter(companyOptional.get());
    	                } else {
    	                    company = (SwitchingCallCenter) create(company);
    	                    companyEmployee.setSwitchingCallCenter(company);
    	                }
    	            }
    	        }

    	        LoginAttempts loginAttempts = null;
                LOGGER.info("Create user with name: " + user.getUsername());
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                LOGGER.info("Setting password "+user.getPassword());

	            loginAttempts = LoginAttempts.builder().numberOfAttempts(0).blocked(false).user(user).build();
	            loginAttempts.setUserSynch(user);

    			LOGGER.debug("persisting: "+user.getUsername());
    	        getEntityManager().persist(entityBase);
    	        LOGGER.debug("persisting loginAttempts for user id: "+loginAttempts.getUserId());
   	            getEntityManager().persist(loginAttempts);
    		}
    	} else {
    		getEntityManager().persist(entityBase);
    	}
        return entityBase;
    }
}
