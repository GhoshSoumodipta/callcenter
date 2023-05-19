package at.ac.tuwien.sepm.groupphase.backend.repository.contacts.switchingcenter;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.switchingcallcenter.SwitchingCallCenterEmployeeDTO;
import at.ac.tuwien.sepm.groupphase.backend.entity.mapper.user.contacts.switchingcallcenter.SwitchingCallCenterEmployeeMapper;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.User;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.switchingcenter.SwitchingCallCenterEmployee;

import org.springframework.stereotype.Repository;

@Repository
public interface SwitchingCallCenterEmployeeRepository extends SwitchingCallCenterUserRepository<SwitchingCallCenterEmployee, SwitchingCallCenterEmployeeMapper, SwitchingCallCenterEmployeeDTO> {

}