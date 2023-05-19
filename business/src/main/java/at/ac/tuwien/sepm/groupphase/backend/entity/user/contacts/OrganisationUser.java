package at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Embeddable;

@Data
@EqualsAndHashCode
@SuperBuilder
@AllArgsConstructor
public class OrganisationUser extends User{
}
