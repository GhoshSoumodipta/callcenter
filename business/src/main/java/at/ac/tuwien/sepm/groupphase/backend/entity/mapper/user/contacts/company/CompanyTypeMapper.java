package at.ac.tuwien.sepm.groupphase.backend.entity.mapper.user.contacts.company;


import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.company.CompanyTypeDTO;
import at.ac.tuwien.sepm.groupphase.backend.entity.mapper.MapperBase;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.company.CompanyType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CompanyTypeMapper extends MapperBase<CompanyType, CompanyTypeDTO> {

    CompanyType DTOToEntity(CompanyTypeDTO dto);


    CompanyTypeDTO entityToDTO(at.ac.tuwien.sepm.groupphase.backend.datatype.CompanyType entity);
}
