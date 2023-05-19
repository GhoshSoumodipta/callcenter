package at.ac.tuwien.sepm.groupphase.backend.repository.contacts.switchingcenter;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.switchingcallcenter.SwitchingCallCenterDTO;
import at.ac.tuwien.sepm.groupphase.backend.entity.mapper.user.contacts.switchingcallcenter.SwitchingCallCenterMapper;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.switchingcenter.SwitchingCallCenter;
import at.ac.tuwien.sepm.groupphase.backend.repository.contacts.OrganisationRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SwitchingCallCenterRepository extends OrganisationRepository<SwitchingCallCenter, SwitchingCallCenterMapper, SwitchingCallCenterDTO> {
    @Override
    SwitchingCallCenterMapper mapper();
}
