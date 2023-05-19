package at.ac.tuwien.sepm.groupphase.backend.entity.mapper.user.contacts;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.OrganisationDTO;
import at.ac.tuwien.sepm.groupphase.backend.entity.mapper.MapperBase;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.Organisation;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface OrganisationMapper extends MapperBase<Organisation, OrganisationDTO> {

    @Override
    OrganisationDTO entityToDTO(Organisation entity);

    @Override
    Organisation DTOToEntity(OrganisationDTO dto);
////    /**
////     * Maps User object to UserDTO object
////     * @param user to map
////     * @return the mapped UserDTO object
////     */
//    @ObjectFactory
//    default OrganisationDTO entityToDTO(Organisation entity) {
//        if (entity instanceof Company) {
//            return entityToDTO((Company) entity);
//        } else if (entity instanceof SwitchingCallCenter) {
//            return entityToDTO((SwitchingCallCenter) entity);
//        }
//        throw new IllegalArgumentException("Unknown subtype of Organisation");
//    }
//
//    @Named("companyDTO")
//    CompanyDTO entityToDTO(Company company);
//
//    @Named("switchingCallCenterDTO")
//    SwitchingCallCenterDTO entityToDTO(SwitchingCallCenter switchingCallCenter);
//
//    @ObjectFactory
//    default Organisation DTOToEntity(OrganisationDTO organisationDTO) {
//        if (organisationDTO instanceof CompanyDTO) {
//            return DTOToEntity((CompanyDTO) organisationDTO);
//        }
//
//        if (organisationDTO instanceof SwitchingCallCenterDTO) {
//            return DTOToEntity((SwitchingCallCenterDTO) organisationDTO);
//        }
//
//        throw new IllegalArgumentException("Unknown subtype of OrganisationDTO");
//    }
//
//    @Named("company")
//    Company DTOToEntity(CompanyDTO companyDTO);
//    @Named("switchingCallCenter")
//    SwitchingCallCenter DTOToEntity(SwitchingCallCenterDTO switchingCallCenterDTO);
}
