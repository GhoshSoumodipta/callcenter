package at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.switchingcallcenter;

import lombok.*;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class SwitchingCallCenterEmployeeDTO extends SwitchingCallCenterUserDTO {

    private String linkForVideoCall;
}