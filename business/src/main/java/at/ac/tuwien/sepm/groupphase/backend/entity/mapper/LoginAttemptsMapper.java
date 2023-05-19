package at.ac.tuwien.sepm.groupphase.backend.entity.mapper;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.LoginAttemptsDTO;
import at.ac.tuwien.sepm.groupphase.backend.entity.LoginAttempts;
import at.ac.tuwien.sepm.groupphase.backend.entity.mapper.customer.CustomerMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LoginAttemptsMapper extends MapperBase<LoginAttempts, LoginAttemptsDTO> {

    CustomerMapper INSTANCE = Mappers.getMapper( CustomerMapper.class );

    @Override
    LoginAttempts DTOToEntity(LoginAttemptsDTO dto);


    @Override
    LoginAttemptsDTO entityToDTO(LoginAttempts entity);
}
