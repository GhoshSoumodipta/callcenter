package at.ac.tuwien.sepm.groupphase.backend.factories.contacts;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import at.ac.tuwien.sepm.groupphase.backend.datatype.UserType;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.base.DTOBase;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.company.CompanyDTO;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.switchingcallcenter.SwitchingCallCenterDTO;
import at.ac.tuwien.sepm.groupphase.backend.entity.base.EntityBase;
import at.ac.tuwien.sepm.groupphase.backend.entity.mapper.MapperBase;
import at.ac.tuwien.sepm.groupphase.backend.entity.mapper.user.contacts.company.CompanyMapper;
import at.ac.tuwien.sepm.groupphase.backend.entity.mapper.user.contacts.switchingcallcenter.SwitchingCallCenterMapper;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.company.Company;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.switchingcenter.SwitchingCallCenter;
import at.ac.tuwien.sepm.groupphase.backend.factories.repository.MyDefaultRepositoryFactory;
import at.ac.tuwien.sepm.groupphase.backend.factories.repository.MyJpaRepository;
import at.ac.tuwien.sepm.groupphase.backend.repository.contacts.company.CompanyRepository;
import at.ac.tuwien.sepm.groupphase.backend.repository.contacts.switchingcenter.SwitchingCallCenterRepository;


public class OrganizationFactory extends AbstractFactory{

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public MyJpaRepository getRepository(UserType userType){
        RepositoryFactorySupport factory = new MyDefaultRepositoryFactory(entityManager, passwordEncoder);
        MyJpaRepository repositoryBase = switch (userType){
            case COMPANY -> factory.getRepository(CompanyRepository.class);
            case SWITCHINGCALLCENTER -> factory.getRepository(SwitchingCallCenterRepository.class);
            default -> throw new IllegalArgumentException("Unknown UserType: "+userType);
        };
        if(repositoryBase.mapper()==null) {
            Class<? extends MapperBase> mapperBase = switch (userType) {
                case COMPANY -> CompanyMapper.class;
                case SWITCHINGCALLCENTER -> SwitchingCallCenterMapper.class;
                default -> throw new IllegalArgumentException("Unknown UserType: " + userType);
            };
            Class<? extends EntityBase> entityClass = switch (userType) {
                case COMPANY -> Company.class;
                case SWITCHINGCALLCENTER -> SwitchingCallCenter.class;
                default -> throw new IllegalArgumentException("Unknown UserType: " + userType);
            };
            Class<? extends DTOBase> dtoClass = switch (userType) {
                case COMPANY -> CompanyDTO.class;
                case SWITCHINGCALLCENTER -> SwitchingCallCenterDTO.class;
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
