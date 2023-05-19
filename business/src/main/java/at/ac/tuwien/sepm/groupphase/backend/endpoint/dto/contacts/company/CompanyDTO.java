package at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.company;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.UserDTO;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.Organisation;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class CompanyDTO extends UserDTO {
    private String invoice;
    //private CompanyTypeDTO companyType;
    Organisation organisation;
}
