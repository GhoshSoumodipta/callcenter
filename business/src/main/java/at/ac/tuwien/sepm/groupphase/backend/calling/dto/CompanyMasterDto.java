package at.ac.tuwien.sepm.groupphase.backend.calling.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyMasterDto {
    private Long id;
    private String companyName;
    private String email;
    private String address;
    private String contactNo;
    private String contactPerson;
    private Integer status;
}
