package at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.company;

import lombok.*;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class CompanyEmployeeDTO extends CompanyUserDTO {
    private String companyEmployeeLinkForVideoCall;
}