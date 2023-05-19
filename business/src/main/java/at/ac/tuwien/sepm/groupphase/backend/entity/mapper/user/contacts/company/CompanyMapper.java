package at.ac.tuwien.sepm.groupphase.backend.entity.mapper.user.contacts.company;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.company.CompanyDTO;
import at.ac.tuwien.sepm.groupphase.backend.entity.mapper.MapperBase;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.company.Company;
import at.ac.tuwien.sepm.groupphase.backend.entity.mapper.user.contacts.OrganisationMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CompanyMapper extends MapperBase<Company, CompanyDTO> {

    @Override
    Company DTOToEntity(CompanyDTO dto);


    @Override
    CompanyDTO entityToDTO(Company entity);
}
