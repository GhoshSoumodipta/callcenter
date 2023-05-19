package at.ac.tuwien.sepm.groupphase.backend.entity.mapper.user.contacts;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.OrganisationUserDTO;
import at.ac.tuwien.sepm.groupphase.backend.entity.mapper.MapperBase;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.OrganisationUser;

//@Mapper(componentModel = "spring")
public interface OrganisationUserMapper extends MapperBase<OrganisationUser, OrganisationUserDTO> {

    @Override
    OrganisationUser DTOToEntity(OrganisationUserDTO dto);


    @Override
    OrganisationUserDTO entityToDTO(OrganisationUser entity);

//    /**
//     * Maps User object to UserDTO object
//     * @param user to map
//     * @return the mapped UserDTO object
//     */
//    @ObjectFactory
//    default OrganisationUserDTO createOrganisationDTO(OrganisationUser organisationUser) {
//        if (organisationUser instanceof CompanyUser) {
//            return companyUserDTOToCompanyUser((CompanyUser) organisationUser);
//        } else if (organisationUser instanceof SwitchingCallCenterUser) {
//            return switchingCallCenterUserToSwitchingCallCenterUserDTO((SwitchingCallCenterUser) organisationUser);
//        }
//
//        throw new IllegalArgumentException("Unknown subtype of OrganisationUser");
//    }
//
//    @Named("companyUser")
//    default OrganisationUserDTO companyUserDTOToCompanyUser(CompanyUser companyUser){
//        return new CompanyUserDTO(){};
//    }
//
//    @Named("switchingCallCenterUser")
//    default OrganisationUserDTO switchingCallCenterUserToSwitchingCallCenterUserDTO(SwitchingCallCenterUser switchingCallCenter){
//        return new SwitchingCallCenterUserDTO(){};
//    }
//
//
//    @ObjectFactory
//    default OrganisationUser organisationDTOToOrganisation(OrganisationUserDTO organisationUserDTO) {
//        if (organisationUserDTO instanceof CompanyUserDTO) {
//            return companyUserDTOToCompanyUser((CompanyUserDTO) organisationUserDTO);
//        } else if (organisationUserDTO instanceof SwitchingCallCenterUserDTO) {
//            return switchingCenterUserDTOToSwitchingCenterUser((SwitchingCallCenterUserDTO) organisationUserDTO);
//        }
//        throw new IllegalArgumentException("Unknown subtype of OrganisationUserDTO");
//    }
//
//    @Named("companyDTO")
//    default OrganisationUser companyUserDTOToCompanyUser(CompanyUserDTO companyUserDTO){
//        if(companyUserDTO instanceof CompanyEmployeeDTO){
//            return new CompanyEmployee();
//        } else if (companyUserDTO instanceof CompanyLeaderDTO) {
//            return new CompanyLeader();
//        }
//        throw new IllegalArgumentException("Unknown subtype of CompanyUserDTO");
//    }
//    @Named("switchingCallCenterDTO")
//    default OrganisationUser switchingCenterUserDTOToSwitchingCenterUser(SwitchingCallCenterUserDTO switchingCallCenterUserDTO){
//        if(switchingCallCenterUserDTO instanceof SwitchingCallCenterEmployeeDTO){
//            return new SwitchingCallCenterEmployee();
//        } else if (switchingCallCenterUserDTO instanceof SwitchingCallCenterLeaderDTO) {
//            return new SwitchingCallCenterLeader();
//        }
//        throw new IllegalArgumentException("Unknown subtype of SwitchingCallCenterUserDTO");
//    }
}
