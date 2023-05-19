package at.ac.tuwien.sepm.groupphase.backend.entity.mapper;

import org.mapstruct.Mapper;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.EmailDTO;
import at.ac.tuwien.sepm.groupphase.backend.entity.EmailsEntity;

@Mapper(componentModel = "spring")
public interface EmailMapper extends MapperBase<EmailsEntity,EmailDTO> {

    @Override
	EmailsEntity DTOToEntity(EmailDTO dto);


    @Override
	EmailDTO entityToDTO(EmailsEntity entity);

}
