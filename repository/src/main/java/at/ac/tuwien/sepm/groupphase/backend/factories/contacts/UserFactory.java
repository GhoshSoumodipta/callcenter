package at.ac.tuwien.sepm.groupphase.backend.factories.contacts;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import at.ac.tuwien.sepm.groupphase.backend.entity.mapper.user.contacts.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import at.ac.tuwien.sepm.groupphase.backend.datatype.UserType;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.base.DTOBase;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.UserDTO;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.customer.CustomerDTO;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.interpreter.InterpreterDTO;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.interpreter.Interpreter;
import at.ac.tuwien.sepm.groupphase.backend.entity.base.EntityBase;
import at.ac.tuwien.sepm.groupphase.backend.entity.mapper.InterpreterMapper;
import at.ac.tuwien.sepm.groupphase.backend.entity.mapper.MapperBase;
import at.ac.tuwien.sepm.groupphase.backend.entity.mapper.customer.CustomerMapper;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.User;
import at.ac.tuwien.sepm.groupphase.backend.factories.repository.MyDefaultRepositoryFactory;
import at.ac.tuwien.sepm.groupphase.backend.factories.repository.MyJpaRepository;
import at.ac.tuwien.sepm.groupphase.backend.repository.CustomerRepository;
import at.ac.tuwien.sepm.groupphase.backend.repository.InterpreterRepository;
import at.ac.tuwien.sepm.groupphase.backend.repository.contacts.UserRepository;


@Transactional
public class UserFactory extends AbstractFactory {
    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public MyJpaRepository getRepository(UserType userType){
        RepositoryFactorySupport factory = new MyDefaultRepositoryFactory(entityManager, passwordEncoder);
        MyJpaRepository repositoryBase = switch (userType){
            case CAPTIONER, COMMUNICATIONASSISTENCE, CUSTOMER -> factory.getRepository(CustomerRepository.class);
//            case COMMUNICATIONASSISTENCE:
//            case TRANSLATOR:
//            case CUSTOMER:

            case INTERPRETER -> factory.getRepository(InterpreterRepository.class);
            case USER -> factory.getRepository(UserRepository.class);
            default -> throw new IllegalArgumentException("Unknown UserType: "+userType);
        };
        if(repositoryBase.mapper()==null) {
            Class<? extends MapperBase> mapperBase = switch (userType) {
                case CAPTIONER, COMMUNICATIONASSISTENCE, CUSTOMER -> CustomerMapper.class;
//            case COMMUNICATIONASSISTENCE:
//            case TRANSLATOR:
//            case CUSTOMER:
                case INTERPRETER -> InterpreterMapper.class;
                case USER -> UserMapper.class;
                default -> throw new IllegalArgumentException("Unknown UserType: " + userType);
            };
            Class<? extends EntityBase> entityClass = switch (userType) {
                case CAPTIONER, COMMUNICATIONASSISTENCE, CUSTOMER -> Interpreter.class;
//            case COMMUNICATIONASSISTENCE:
//            case TRANSLATOR:
//            case CUSTOMER:
                case INTERPRETER -> Interpreter.class;
                case USER -> User.class;
                default -> throw new IllegalArgumentException("Unknown UserType: " + userType);
            };
            Class<? extends DTOBase> dtoClass = switch (userType) {
                case CAPTIONER, COMMUNICATIONASSISTENCE, CUSTOMER -> CustomerDTO.class;
//            case COMMUNICATIONASSISTENCE:
//            case TRANSLATOR:
//            case CUSTOMER:
                case INTERPRETER -> InterpreterDTO.class;
                case USER -> UserDTO.class;
                default -> throw new IllegalArgumentException("Unknown UserType: " + userType);
            };
            repositoryBase.setMapperClass(mapperBase);
            repositoryBase.setDTOClass(dtoClass);
            repositoryBase.setEntityClass(entityClass);
            repositoryBase.setRepositoryClass();
        }
        return repositoryBase;
    }
}
