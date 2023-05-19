package at.ac.tuwien.sepm.groupphase.backend.endpoint.dto;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.base.DTOBase;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CalendarDTO extends DTOBase {



	    private Long id;


	    private String title;

	    private UserDTO user;

}
