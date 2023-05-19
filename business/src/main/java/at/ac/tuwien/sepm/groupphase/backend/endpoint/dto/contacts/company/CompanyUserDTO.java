package at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.company;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.OrganisationUserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public abstract class CompanyUserDTO extends OrganisationUserDTO {

    private CompanyDTO company;

}
