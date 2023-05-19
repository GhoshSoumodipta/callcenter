package at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.base.DTOBase;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.sql.Time;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class UserDTO extends DTOBase {
	private static final long serialVersionUID = -8579023698105353730L;

    private Long userId;

    private String firstName;

    private String lastName;

    private LocalDateTime birthday;

    private String street;

    private String location;

    private String country;

    private Integer Zip;

    private String houseNo;

    private String phoneNo;

    private String[] pdfs;

    private String profilePhoto;

    private Boolean onlineStatus;

    private Time counter;

    private LocalDateTime userSince;

    private LocalDateTime lastLogin;

    private String captcha;

    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;
}