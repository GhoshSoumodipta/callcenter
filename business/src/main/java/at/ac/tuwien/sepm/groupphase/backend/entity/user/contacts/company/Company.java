package at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.company;

import javax.persistence.*;

import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.Organisation;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.User;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@Table(name = "companies")
@Inheritance(strategy = InheritanceType.JOINED)
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@PrimaryKeyJoinColumn(name = "userId")
public class Company extends User {
    @Column
    private String invoice;

    @Embedded
    private Organisation organisation;
}
