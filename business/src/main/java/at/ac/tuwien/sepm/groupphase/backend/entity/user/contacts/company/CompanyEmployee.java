package at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.company;

import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.User;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "company_employees")
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "userId")
public class CompanyEmployee extends User {
    private String companyEmployeeLinkForVideoCall;
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_user_id")
    private Company company;
}