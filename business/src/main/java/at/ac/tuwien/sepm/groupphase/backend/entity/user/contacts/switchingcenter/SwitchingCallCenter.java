package at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.switchingcenter;

import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.Organisation;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.User;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@Entity
@Table(name = "switching_centers")
@Inheritance(strategy = InheritanceType.JOINED)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder
@ToString
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "userId")
public class SwitchingCallCenter extends User {
    @Embedded
    Organisation organisation;
}
