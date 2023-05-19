package at.ac.tuwien.sepm.groupphase.backend.calling.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "company_master")
public class CompanyMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "company_name")
    private String companyName;
    private String email;
    private String address;
    @Column(name = "contact_no")
    private String contactNo;
    @Column(name = "contact_person")
    private String contactPerson;
    private Integer status;
}
