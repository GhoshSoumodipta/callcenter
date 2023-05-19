package at.ac.tuwien.sepm.groupphase.backend.calling.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CallsDto {
    private Long id;
    private Date dateOfCall;
    private Date callStarted;
    private Date callEnded;
    private String attendedBy;
    private String calledBy;
    private String callerId;
    private String loungeType;
    private Long roomId;
}
