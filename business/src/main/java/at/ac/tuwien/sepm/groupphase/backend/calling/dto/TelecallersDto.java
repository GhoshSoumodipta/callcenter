package at.ac.tuwien.sepm.groupphase.backend.calling.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TelecallersDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String mobileNo;
    private String emailAddress;
    private Date createdAt;
    private Date updatedOn;
    private Integer status;
    private Long companyId;
}
