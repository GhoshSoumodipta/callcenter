package at.ac.tuwien.sepm.groupphase.backend.calling.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuestCallersDto {
    private Long id;
    private String fullname;

    private Date dateOfCall;
    private String mobileNo;
    private Integer status;
    private Long companyId;
    private Long roomId;
}
