package at.ac.tuwien.sepm.groupphase.backend.factories.contacts;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.security.crypto.password.PasswordEncoder;

import at.ac.tuwien.sepm.groupphase.backend.datatype.UserType;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.base.DTOBase;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.company.CompanyEmployeeDTO;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.company.CompanyLeaderDTO;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.switchingcallcenter.SwitchingCallCenterEmployeeDTO;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.switchingcallcenter.SwitchingCallCenterLeaderDTO;
import at.ac.tuwien.sepm.groupphase.backend.entity.base.EntityBase;
import at.ac.tuwien.sepm.groupphase.backend.entity.mapper.MapperBase;
import at.ac.tuwien.sepm.groupphase.backend.entity.mapper.user.contacts.company.CompanyEmployeeMapper;
import at.ac.tuwien.sepm.groupphase.backend.entity.mapper.user.contacts.company.CompanyLeaderMapper;
import at.ac.tuwien.sepm.groupphase.backend.entity.mapper.user.contacts.switchingcallcenter.SwitchingCallCenterEmployeeMapper;
import at.ac.tuwien.sepm.groupphase.backend.entity.mapper.user.contacts.switchingcallcenter.SwitchingCallCenterLeaderMapper;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.company.CompanyEmployee;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.company.CompanyLeader;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.switchingcenter.SwitchingCallCenterEmployee;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.switchingcenter.SwitchingCallCenterLeader;
import at.ac.tuwien.sepm.groupphase.backend.factories.repository.MyDefaultRepositoryFactory;
import at.ac.tuwien.sepm.groupphase.backend.repository.contacts.OrganisationUserRepository;
import at.ac.tuwien.sepm.groupphase.backend.repository.contacts.company.CompanyEmployeeRepository;
import at.ac.tuwien.sepm.groupphase.backend.repository.contacts.company.CompanyLeaderRepository;
import at.ac.tuwien.sepm.groupphase.backend.repository.contacts.switchingcenter.SwitchingCallCenterEmployeeRepository;
import at.ac.tuwien.sepm.groupphase.backend.repository.contacts.switchingcenter.SwitchingCallCenterLeaderRepository;

public class OrganizationUserFactory extends AbstractFactory{
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public OrganisationUserRepository getRepository(UserType userType){
        RepositoryFactorySupport factory = new MyDefaultRepositoryFactory(entityManager, passwordEncoder);
        OrganisationUserRepository repositoryBase = switch (userType){
            case COMPANYLEADER -> factory.getRepository(CompanyLeaderRepository.class);
            case SWITCHINGCALLCENTERLEADER -> factory.getRepository(SwitchingCallCenterLeaderRepository.class);
            case COMPANYEMPLOYEE -> factory.getRepository(CompanyEmployeeRepository.class);
            case SWITCHINGCALLCENTEREMPLOYEE -> factory.getRepository(SwitchingCallCenterEmployeeRepository.class);
            default -> throw new IllegalArgumentException("Unknown UserType: "+userType);

        };
        if(repositoryBase.mapper()==null) {
            Class<? extends MapperBase> mapperBase = switch (userType) {
                case COMPANYLEADER -> CompanyLeaderMapper.class;
                case SWITCHINGCALLCENTERLEADER -> SwitchingCallCenterLeaderMapper.class;
                case COMPANYEMPLOYEE -> CompanyEmployeeMapper.class;
                case SWITCHINGCALLCENTEREMPLOYEE -> SwitchingCallCenterEmployeeMapper.class;
                default -> throw new IllegalArgumentException("Unknown UserType: " + userType);
            };
            Class<? extends EntityBase> entityClass = switch (userType) {
                case COMPANYLEADER -> CompanyLeader.class;
                case SWITCHINGCALLCENTERLEADER -> SwitchingCallCenterLeader.class;
                case COMPANYEMPLOYEE -> CompanyEmployee.class;
                case SWITCHINGCALLCENTEREMPLOYEE -> SwitchingCallCenterEmployee.class;
                default -> throw new IllegalArgumentException("Unknown UserType: " + userType);
            };
            Class<? extends DTOBase> dtoClass = switch (userType) {
                case COMPANYLEADER -> CompanyLeaderDTO.class;
                case SWITCHINGCALLCENTERLEADER -> SwitchingCallCenterLeaderDTO.class;
                case COMPANYEMPLOYEE -> CompanyEmployeeDTO.class;
                case SWITCHINGCALLCENTEREMPLOYEE -> SwitchingCallCenterEmployeeDTO.class;
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
