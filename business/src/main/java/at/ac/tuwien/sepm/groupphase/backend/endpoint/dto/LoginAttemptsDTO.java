package at.ac.tuwien.sepm.groupphase.backend.endpoint.dto;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.base.DTOBase;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class LoginAttemptsDTO extends DTOBase {

    private User user;
    private int numberOfAttempts;
    private boolean blocked;
}
