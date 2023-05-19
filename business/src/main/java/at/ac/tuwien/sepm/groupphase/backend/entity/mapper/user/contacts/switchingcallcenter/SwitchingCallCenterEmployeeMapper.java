package at.ac.tuwien.sepm.groupphase.backend.entity.mapper.user.contacts.switchingcallcenter;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.switchingcallcenter.SwitchingCallCenterEmployeeDTO;
import at.ac.tuwien.sepm.groupphase.backend.entity.mapper.MapperBase;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.switchingcenter.SwitchingCallCenterEmployee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SwitchingCallCenterEmployeeMapper extends MapperBase<SwitchingCallCenterEmployee, SwitchingCallCenterEmployeeDTO> {

	@Override
    SwitchingCallCenterEmployee DTOToEntity(SwitchingCallCenterEmployeeDTO dto);

	@Override
    SwitchingCallCenterEmployeeDTO entityToDTO(SwitchingCallCenterEmployee entity);
}