package at.ac.tuwien.sepm.groupphase.backend.service.implementation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import at.ac.tuwien.sepm.groupphase.backend.datatype.UserType;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.UserDTO;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.usertype.UserTypeDTO;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.requestparameter.PasswordChangeRequest;
import at.ac.tuwien.sepm.groupphase.backend.entity.LoginAttempts;
import at.ac.tuwien.sepm.groupphase.backend.entity.base.EntityBase;
import at.ac.tuwien.sepm.groupphase.backend.entity.mapper.MapperBase;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.User;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;
import at.ac.tuwien.sepm.groupphase.backend.exception.ServiceException;
import at.ac.tuwien.sepm.groupphase.backend.factories.contacts.UserFactory;
import at.ac.tuwien.sepm.groupphase.backend.factories.mapper.MapperFactory;
import at.ac.tuwien.sepm.groupphase.backend.factories.repository.MyJpaRepository;
import at.ac.tuwien.sepm.groupphase.backend.repository.LoginAttemptsRepository;
import at.ac.tuwien.sepm.groupphase.backend.repository.contacts.UserRepository;
import at.ac.tuwien.sepm.groupphase.backend.repository.contacts.UserRepositoryBase;
import at.ac.tuwien.sepm.groupphase.backend.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final Integer MAX_NUMBER_OF_ATTEMPTS = 4;
//    @Autowired
//    private UserRepository userRepository;
    @Autowired
    private UserFactory userFactory;
    @Autowired
    MapperFactory mapperFactory;
//    @Autowired(required=false)
//    private UserGetMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private  LoginAttemptsRepository loginAttemptsRepository;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        }


    @Override
    public Page<UserDTO> getUsers(String username, Integer page, Integer pageSize) throws ServiceException {
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
            MyJpaRepository repository = (MyJpaRepository)userFactory.getRepository(UserType.valueOf("USER"));
            MapperBase mapper = mapperFactory.getMapper(repository);
            if (username == null)
                return repository.findAll(pageable).map(o->mapper.entityToDTO((EntityBase) o));
            else
                return ((UserRepositoryBase)repository).findByUsernameContainingIgnoreCase(username, pageable).map(o->mapper.entityToDTO((EntityBase) o));
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public UserDTO findOne(Long id, String userTypeString) throws NotFoundException {
        LOGGER.info("Find user with id " + id);

        UserType userType = UserType.valueOf(userTypeString); 
        UserRepository repository = (UserRepository) userFactory.getRepository(userType);
        MapperBase mapper = mapperFactory.getMapper(repository);

        Optional<User> usr = repository.findOneByUserId(id);

        UserDTO dto = (UserDTO) mapper.entityToDTO(usr.get());

//        TODO
//        dto.setEmails(usr.getEmails());
//        dto.setAppointments(usr.getAppointments());
//        dto.setSheets(usr.getSheets());

        return dto;
    }

    @Override
    @Transactional
    public UserDTO createUser(UserDTO userDTO) throws ServiceException {
        try {
            LOGGER.info(userDTO.toString());
            MyJpaRepository repository = (MyJpaRepository) userFactory.getRepository(userDTO.getUserType());
            MapperBase mapper = mapperFactory.getMapper(repository);
            Optional<User> userOptional = ((UserRepositoryBase)repository).findOneByUsername(userDTO.getUsername());
            if(userOptional.isEmpty()){
               // User usr = userMapper.userDTOToUser(userDTO);
                User usr = (User)mapper.DTOToEntity(userDTO);
                LOGGER.info("User Dto mapped to user "+ usr.getUsername());
                 User usr2 = (User) repository.create(usr);
//
                 LOGGER.info("created user2 "+ usr2.getUsername());
               // return userMapper.userToUserDTO(userRepository.createUser(userMapper.userDTOToUser(userDTO)));
                UserDTO dto = (UserDTO) mapper.entityToDTO(usr2);
               return dto;
            }else {
                return new UserDTO();
            }
        }catch (DataIntegrityViolationException e) {
            LOGGER.error("Problems while creating user" + userDTO.toString());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public UserDTO findUserByName(String userName) throws NotFoundException{
        LOGGER.info("Finding user with username: " + userName);
        MyJpaRepository repository = (MyJpaRepository) userFactory.getRepository(UserType.valueOf("USER"));
        MapperBase mapper = mapperFactory.getMapper(repository);

        Optional<User> found = ((UserRepositoryBase)repository).findOneByUsername(userName);

        LOGGER.info("found"+found.toString());
        if (found.isPresent()){
            return (UserDTO) mapper.entityToDTO(found.get());
        }else {
            throw new NotFoundException("Could not find User with username: " + userName);
        }

    }

    @Override
    public void deleteUser(Long id) throws ServiceException {
        LOGGER.info("Remove user with id " + id);
        try {
            MyJpaRepository repository = (MyJpaRepository)userFactory.getRepository(UserType.valueOf("USER"));
            repository.deleteById(id);
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public UserDTO findOneByUsername(String username) {
        MyJpaRepository repository = (MyJpaRepository) userFactory.getRepository(UserType.valueOf("USER"));
        MapperBase mapper = mapperFactory.getMapper(repository);

        return (UserDTO) mapper.entityToDTO(((Optional<User>)((UserRepositoryBase)repository).findOneByUsername(username)).orElseThrow(NotFoundException::new));
    }

    @Override
    public boolean unblockUser(Long userId) throws NotFoundException {
        LOGGER.info("Unblocking User with id: "+ userId);
        Optional<LoginAttempts> attemptsFound = loginAttemptsRepository.findById(userId);
        if(attemptsFound.isPresent()){
            LoginAttempts loginAttempts = attemptsFound.get();
            loginAttempts.setNumberOfAttempts(0);
            loginAttempts.setBlocked(false);
            loginAttempts.setNumberOfAttempts(0);
            loginAttemptsRepository.save(loginAttempts);
            LOGGER.info("unblocked User with id: " + userId);
            return true;
        }else{
            throw new NotFoundException("could not find user with id: "+ userId);
        }
    }

    @Override
    public boolean blockUser(Long userId) throws ServiceException {
        LOGGER.info("Blocking user with id: " + userId);
        Optional<LoginAttempts> loginAttemptsFound = loginAttemptsRepository.findById(userId);
        if(loginAttemptsFound.isPresent()){
            if(loginAttemptsFound.get().getUser().getUserType().equals(UserType.ADMIN)){
                throw new ServiceException("Admin can't be blocked");
            }
            LoginAttempts loginAttempts = loginAttemptsFound.get();
            if (loginAttempts.isBlocked()) {
                throw new ServiceException("User already blocked");
            }
            loginAttempts.setBlocked(true);
            loginAttemptsRepository.save(loginAttempts);
            LOGGER.info("Blocked user with id: " + userId);
            return true;
        }else
            throw new NotFoundException("Could not find user with id "+ userId);
    }

    @Override
    public Page<UserDTO> getBlockedUsers(String username, Integer page, Integer pageSize) throws ServiceException {
        if (username == null)
            LOGGER.info("Getting all blocked users");
        else
            LOGGER.info("Search blocked users with " + username + " as a part of their username");

        try {
            if(pageSize == null){
                pageSize = 10;
            }
            if(page < 0) {
                throw new IllegalArgumentException("Not a valid page.");
            }
            Pageable pageable = PageRequest.of(page, pageSize);
            List<LoginAttempts> blockedUserAttempts = loginAttemptsRepository.getAllByBlockedTrue();
            List<UserDTO> users = new ArrayList<>();
            Comparator<LoginAttempts> comparator = Comparator.comparing(la -> la.getUser().getUserId());
            MyJpaRepository repository = (MyJpaRepository)userFactory.getRepository(UserType.valueOf("USER"));
            MapperBase mapper = mapperFactory.getMapper(repository);
            blockedUserAttempts.stream()
                .sorted(comparator)
                .forEach(loginAttempts -> {
                    if (username == null) {
                        users.add((UserDTO) mapper.entityToDTO(loginAttempts.getUser()));
                    } else {
                        if (loginAttempts.getUser().getUsername().contains(username))
                            users.add((UserDTO) mapper.entityToDTO(loginAttempts.getUser()));
                    }
                });
            int totalElements = users.size();
            int from = page * pageSize;
            int offset = page * pageSize + pageSize > totalElements ? (totalElements - page * pageSize) : pageSize;
            List<UserDTO> sublist = users.subList(from, from + offset);
            Page<UserDTO> result = new PageImpl<>(sublist, pageable, totalElements);
            LOGGER.debug(result.getContent().toString());
            LOGGER.debug("totalElem: " + result.getTotalElements());
            LOGGER.debug("totalPages: " + result.getTotalPages());
            return result;
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void changePassword(PasswordChangeRequest passwordChangeRequest) throws ServiceException {
        LOGGER.info("changing password for user " + passwordChangeRequest.getId());
        UserRepository repository = (UserRepository) userFactory.getRepository(UserType.valueOf("USER"));
        Optional<User> userOptional = repository.findOneByUserId(passwordChangeRequest.getId());
        if(userOptional.isPresent()){
            User user = userOptional.get();
            if(user.getUserType().equals(UserType.ADMIN)){
                throw new ServiceException("admins cant be blocked");
            }else{
                user.setPassword(passwordEncoder.encode(passwordChangeRequest.getPassword()));
                user = (User)repository.save(user);
                unblockUser(user.getUserId());
            }
        }else{
            throw new NotFoundException("could not find user with id: " + passwordChangeRequest.getId());
        }
    }

    @Override
    public List<UserTypeDTO> getUserTypes() throws ServiceException {
        List<UserTypeDTO> userTypes = new ArrayList<>();
        for(UserType userType: UserType.values()){
            if(UserType.ADMIN.equals(userType)){
                continue;
            }
            userTypes.add(UserTypeDTO.builder().name(userType.name()).build());
        }

        return userTypes;
    }
}
