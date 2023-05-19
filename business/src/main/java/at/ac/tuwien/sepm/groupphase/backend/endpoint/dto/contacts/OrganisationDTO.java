package at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.base.DTOBase;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class OrganisationDTO extends DTOBase {

    private String name;

    private String url;

    private String linkForVideoCall;
}
