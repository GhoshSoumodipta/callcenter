package at.ac.tuwien.sepm.groupphase.backend.calling.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "telecallers")
public class Telecallers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    @Column(name = "mobile_no")
    private String mobileNo;
    @Column(name = "email_address")
    private String emailAddress;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_on")
    private LocalDateTime updatedOn;
    private Integer status;
    @Column(name = "company_id")
    private Long companyId;
}
