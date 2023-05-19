package at.ac.tuwien.sepm.groupphase.backend.service.implementation;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import at.ac.tuwien.sepm.groupphase.backend.datatype.UserType;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.base.DTOBase;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.UserDTO;
import at.ac.tuwien.sepm.groupphase.backend.entity.base.EntityBase;
import at.ac.tuwien.sepm.groupphase.backend.entity.mapper.MapperBase;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.User;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;
import at.ac.tuwien.sepm.groupphase.backend.exception.ServiceException;
import at.ac.tuwien.sepm.groupphase.backend.factories.contacts.RepositoryFactoryMain;
import at.ac.tuwien.sepm.groupphase.backend.repository.contacts.UserRepositoryBase;
import at.ac.tuwien.sepm.groupphase.backend.service.BusinessObjectService;

@Service
public class BusinessObjectServiceImpl implements BusinessObjectService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessObjectServiceImpl.class);

    @Autowired
    private RepositoryFactoryMain repositoryFactoryMain;

    
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public UserDTO findOne(Long id, String userTypeString) throws NotFoundException {
        LOGGER.info("Find user with id " + id);

        UserRepositoryBase repository = repositoryFactoryMain.getRepository(UserType.valueOf(userTypeString.toUpperCase()));
        MapperBase mapper = (MapperBase)repository.mapper();

        Optional<User> usr = repository.findOneByUserId(id);

        UserDTO dto = null;
        if(usr.isPresent()) {
        	dto = (UserDTO) mapper.entityToDTO(usr.get());
        } 

//        TODO
//        dto.setEmails(usr.getEmails());
//        dto.setAppointments(usr.getAppointments());
//        dto.setSheets(usr.getSheets());

        return dto;
    }

	@SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    @Transactional
    public DTOBase createObject(String objectJSONString)  throws ServiceException {
        try {

            // TODO Dynamic validation of dto

            ObjectMapper objectMapper = new ObjectMapper();

            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            Map<String, String> map = objectMapper.readValue(objectJSONString, Map.class);
            String userTypeStr = map.get("userType");
            LOGGER.info("user type: " + userTypeStr);
            UserType userType = UserType.valueOf(userTypeStr.toUpperCase());

            UserRepositoryBase repository = repositoryFactoryMain.getRepository(userType);
            MapperBase mapper = (MapperBase) repository.mapper();
            //Now serialize
            DTOBase objectDTO = (DTOBase) (objectMapper.readValue(objectJSONString, repository.getDTOClass()));

            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<DTOBase>> constraintViolations =
                    validator.validate( objectDTO );

            if(constraintViolations.size()>0) {
            	LOGGER.error("Problems while validating dto");
                throw new ServiceException("Problems while validating dto");
            }

            LOGGER.info("Object DTO: " + objectDTO);
//            Object o = ((UserDTO) objectDTO).getUserType();
//            LOGGER.info("user type: " + o);
            LOGGER.debug("mapper class: " + mapper.getClass().getSimpleName());
            Optional<EntityBase> entityBaseOptional = repository.findOneByUsername(objectDTO.getUsername());
            if (entityBaseOptional.isEmpty()) {
//                ((UserPostDTO) objectDTO).setPassword(passwordEncoder.encode(((UserPostDTO) objectDTO).getPassword()));
//                LOGGER.info("Setting password: " + ((UserPostDTO) objectDTO).getPassword());
                // User usr = userMapper.userDTOToUser(userDTO);
                EntityBase obj = (EntityBase) mapper.DTOToEntity(objectDTO);
                LOGGER.info("Create " + obj.getClass().getSimpleName() + " with name: " + objectDTO.getUsername());
//                LOGGER.info("User Dto mapped to user "+ usr.getUsername());
                LOGGER.debug("Creating object: " + obj);
                EntityBase obj2 = (EntityBase)repository.create(obj);
                User usr2 = (User) obj2;



//
                LOGGER.info("created user2 " + usr2.getUsername());
                // return userMapper.userToUserDTO(userRepository.createUser(userMapper.userDTOToUser(userDTO)));
                DTOBase dto = (DTOBase) mapper.entityToDTO(obj2);
                LOGGER.debug("returned dto: " + dto);
                return dto;
            } else {
                LOGGER.info("OBJECT ALREADY EXISTS: " + objectDTO.getUsername());
                return UserDTO.builder().userId(((User)entityBaseOptional.get()).getUserId()).build();
            }
        }catch (Exception e){
            LOGGER.error("Problems while creating user" + objectJSONString);
            throw new ServiceException(e.getMessage(), e);
        }
    }

	@SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public Page<DTOBase> getObjects(String object, String username, Integer page, Integer pageSize) throws ServiceException {
        if (username == null)
            LOGGER.info("Get all users");
        else
            LOGGER.info("Search users with " + username + " as a part of their username");

        try {
            if(page == null){
                page = 0;
            }
            if(pageSize == null){
                pageSize = 10;
            }
            if(page < 0) {
                throw new IllegalArgumentException("Not a valid page.");
            }
            Pageable pageable = PageRequest.of(page, pageSize);
            UserRepositoryBase repository = repositoryFactoryMain.getRepository(UserType.valueOf(object.toUpperCase()));
            MapperBase mapper = (MapperBase)repository.mapper();
            if (username == null)
                return repository.findAll(pageable).map(o->mapper.entityToDTO((EntityBase) o));
            else
                return ((UserRepositoryBase)repository).findByUsernameContainingIgnoreCase(username, pageable).map(o->mapper.entityToDTO((EntityBase) o));
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage());
        }
    }

}
