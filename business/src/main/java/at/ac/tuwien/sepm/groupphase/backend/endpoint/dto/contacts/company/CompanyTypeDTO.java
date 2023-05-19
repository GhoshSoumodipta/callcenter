package at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.company;


import lombok.Data;

import java.util.List;

@Data
public class CompanyTypeDTO {
    private Long id;

    private String name;

    private List<CompanyDTO> companies;
}
