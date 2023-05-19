package at.ac.tuwien.sepm.groupphase.backend.entity.mapper.user.contacts.company;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.company.CompanyLeaderDTO;
import at.ac.tuwien.sepm.groupphase.backend.entity.mapper.MapperBase;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.company.CompanyLeader;
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
public interface CompanyLeaderMapper extends MapperBase<CompanyLeader, CompanyLeaderDTO> {
	@Override
    CompanyLeader DTOToEntity(CompanyLeaderDTO dto);

	@Override
    CompanyLeaderDTO entityToDTO(CompanyLeader entity);
}
