package at.ac.tuwien.sepm.groupphase.backend.entity;

import javax.persistence.*;

import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.User;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Data
@Entity
@Table(name = "admins")
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "userId")
public class AdminEntity extends User{

	    @Column(nullable = false, unique = true)
	    private Boolean SMS_Certification;
}
