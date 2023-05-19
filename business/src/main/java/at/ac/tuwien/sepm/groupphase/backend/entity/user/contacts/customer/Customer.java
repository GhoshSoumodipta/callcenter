package at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.customer;

import javax.persistence.*;

import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.User;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="customers")
@Data
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer extends User {

	@Column(nullable = false)
    private BigDecimal creditPrivate;
    private BigDecimal creditWorking;
    private Boolean SMS_certification;
}
