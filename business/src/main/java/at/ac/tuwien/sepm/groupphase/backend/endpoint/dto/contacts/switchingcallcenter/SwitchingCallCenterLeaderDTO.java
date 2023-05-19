package at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.switchingcallcenter;

import lombok.*;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class SwitchingCallCenterLeaderDTO extends SwitchingCallCenterUserDTO {

    private String linkForVideoCall;
}