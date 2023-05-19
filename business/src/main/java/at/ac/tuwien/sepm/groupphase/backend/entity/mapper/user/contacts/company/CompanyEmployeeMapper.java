package at.ac.tuwien.sepm.groupphase.backend.entity.mapper.user.contacts.company;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.company.CompanyEmployeeDTO;
import at.ac.tuwien.sepm.groupphase.backend.entity.mapper.MapperBase;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.company.CompanyEmployee;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;



@Mapper(componentModel = "spring")
public interface CompanyEmployeeMapper extends MapperBase<CompanyEmployee, CompanyEmployeeDTO> {

	@Override
    CompanyEmployee DTOToEntity(CompanyEmployeeDTO dto);

	@Override
    CompanyEmployeeDTO entityToDTO(CompanyEmployee entity);
}