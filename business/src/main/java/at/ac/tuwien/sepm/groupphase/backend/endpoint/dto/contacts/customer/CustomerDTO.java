package at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.customer;

import java.math.BigDecimal;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CustomerDTO extends UserDTO {
    private static final long serialVersionUID = -9059906984820235163L;
	private BigDecimal creditPrivate;
    private BigDecimal creditWorking;
    private Boolean SMS_certification;
}
