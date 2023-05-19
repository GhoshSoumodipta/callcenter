package at.ac.tuwien.sepm.groupphase.backend.calling.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "calls")
public class Calls {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "date_of_call")
    private LocalDateTime dateOfCall;
    @Column(name = "call_started")
    private LocalDateTime callStarted;
    @Column(name = "call_ended")
    private LocalDateTime callEnded;
    @Column(name = "attended_by")
    private String attendedBy;
    @Column(name = "called_by")
    private String calledBy;
    @Column(name = "caller_id")
    private String callerId;
    @Column(name = "lounge_type")
    private String loungeType;
    @Column(name = "room_id", updatable = false)
    private Long roomId;
}
