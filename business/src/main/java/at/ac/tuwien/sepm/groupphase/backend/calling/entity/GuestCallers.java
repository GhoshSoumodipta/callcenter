package at.ac.tuwien.sepm.groupphase.backend.calling.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "guest_callers")
public class GuestCallers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullname;
    @Column(name = "date_of_call")
    private LocalDateTime dateOfCall;
    @Column(name = "mobile_no")
    private String mobileNo;
    private Integer status;
    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "room_id", updatable = false)
    private Long roomId;

}
