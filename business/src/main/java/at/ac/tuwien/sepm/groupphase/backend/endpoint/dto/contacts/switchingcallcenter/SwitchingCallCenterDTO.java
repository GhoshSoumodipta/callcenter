package at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.switchingcallcenter;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.UserDTO;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.Organisation;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.OrganisationDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class SwitchingCallCenterDTO extends UserDTO {
    Organisation organisation;
}
