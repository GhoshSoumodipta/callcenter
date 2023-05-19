package at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.company;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class CompanyLeaderDTO extends CompanyUserDTO {
    private String companyLeaderLinkForVideoCall;
}