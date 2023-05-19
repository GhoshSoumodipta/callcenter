package at.ac.tuwien.sepm.groupphase.backend.service.implementation;

import at.ac.tuwien.sepm.groupphase.backend.datatype.UserType;
import at.ac.tuwien.sepm.groupphase.backend.entity.LoginAttempts;
import at.ac.tuwien.sepm.groupphase.backend.entity.mapper.user.contacts.UserMapper;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.User;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;
import at.ac.tuwien.sepm.groupphase.backend.repository.LoginAttemptsRepository;
import at.ac.tuwien.sepm.groupphase.backend.repository.contacts.UserRepository;
import at.ac.tuwien.sepm.groupphase.backend.service.LoginAttemptService;
import at.ac.tuwien.sepm.groupphase.backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginAttemptServiceImpl implements LoginAttemptService {

    private final Integer MAX_NUMBER_OF_ATTEMPTS = 4;
    @Autowired
    private  UserService userService;
    @Autowired
    private  LoginAttemptsRepository loginAttemptsRepository;
    @Autowired(required=false)
    private UserMapper userMapper;
    @Autowired
    private UserRepository userRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginAttemptService.class);

//    public LoginAttemptServiceImpl(UserService userService, LoginAttemptsRepository loginAttemptsRepository, UserMapper userMapper, UserRepository userRepository) {
//        this.userService = userService; this.loginAttemptsRepository = loginAttemptsRepository;
//        this.userMapper = userMapper;
//        this.userRepository = userRepository;
//    }

    @Override
    public void failedLogin(String username) throws NotFoundException {
    	LOGGER.info("In LogginAttemptService 1");
            Optional<User> found = userRepository.findOneByUsername(username);
            if (found.isPresent()){
                if(!found.get().getUserType().equals(UserType.ADMIN)){
                    User user = found.get();
                    LoginAttempts attempts = loginAttemptsRepository.getByUser(user);
                    LOGGER.info("Failed Login Attempt number: "+ attempts +"by user: "+ user.getUsername());
                    attempts.setNumberOfAttempts(attempts.getNumberOfAttempts() + 1);
                    if(attempts.getNumberOfAttempts() > MAX_NUMBER_OF_ATTEMPTS){
                        attempts.setBlocked(true);
                    }
                    loginAttemptsRepository.save(attempts);
                }

            }else{
                throw new NotFoundException("could not find user with username: " + username);
            }

    }

    @Override
    public void successfulLogin(String username) {
    	LOGGER.info("In LogginAttemptService 2: "+username);
        Optional<User> opt = userRepository.findOneByUsername(username);
        LOGGER.info("In LogginAttemptService 3: "+opt.get().getFirstName());
        if (opt.isPresent()) {
        	LOGGER.info("In LogginAttemptService 4: "+opt.get().getFirstName());
//            UserDTO user = userMapper.userToUserDTO(opt.get());
        	User user = opt.get();
            LOGGER.info("In LogginAttemptService 5: "+opt.get().getFirstName());
            if (!user.getUserType().equals(UserType.ADMIN)){
                LoginAttempts attempts = loginAttemptsRepository.getLoginAttemptsByUserId(user.getUserId());
                attempts.setNumberOfAttempts(0);
                loginAttemptsRepository.save(attempts);
            }
        }

    }




}
