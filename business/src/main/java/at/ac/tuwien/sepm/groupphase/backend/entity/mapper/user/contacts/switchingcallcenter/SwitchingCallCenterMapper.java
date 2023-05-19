package at.ac.tuwien.sepm.groupphase.backend.entity.mapper.user.contacts.switchingcallcenter;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.switchingcallcenter.SwitchingCallCenterDTO;
import at.ac.tuwien.sepm.groupphase.backend.entity.mapper.MapperBase;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.switchingcenter.SwitchingCallCenter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SwitchingCallCenterMapper extends MapperBase<SwitchingCallCenter, SwitchingCallCenterDTO> {

	@Override
    SwitchingCallCenter DTOToEntity(SwitchingCallCenterDTO dto);

	@Override
    SwitchingCallCenterDTO entityToDTO(SwitchingCallCenter entity);
}
