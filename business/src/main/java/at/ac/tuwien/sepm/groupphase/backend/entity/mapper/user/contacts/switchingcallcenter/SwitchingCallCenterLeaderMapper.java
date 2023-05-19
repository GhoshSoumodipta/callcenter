
package at.ac.tuwien.sepm.groupphase.backend.entity.mapper.user.contacts.switchingcallcenter;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.switchingcallcenter.SwitchingCallCenterLeaderDTO;
import at.ac.tuwien.sepm.groupphase.backend.entity.mapper.MapperBase;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.switchingcenter.SwitchingCallCenterLeader;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SwitchingCallCenterLeaderMapper extends MapperBase<SwitchingCallCenterLeader, SwitchingCallCenterLeaderDTO> {

	@Override
    SwitchingCallCenterLeader DTOToEntity(SwitchingCallCenterLeaderDTO dto);

	@Override
    SwitchingCallCenterLeaderDTO entityToDTO(SwitchingCallCenterLeader entity);
}