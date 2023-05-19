package at.ac.tuwien.sepm.groupphase.backend.endpoint.dto;

import java.sql.Time;

import at.ac.tuwien.sepm.groupphase.backend.datatype.UserType;
import lombok.Data;


@Data
public class LoginUserResponseDTO {


	private Long id;


    private String firstName;


    private String lastNname;


    private String username;


    private String profilephoto;


    private Boolean online_status;


    private Time counter;
    
    private UserType userType;

    private String role;

}
