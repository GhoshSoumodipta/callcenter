package at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.switchingcallcenter;


import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.OrganisationUserDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public abstract class SwitchingCallCenterUserDTO extends OrganisationUserDTO {

    private SwitchingCallCenterDTO switchingCallCenter;
}
