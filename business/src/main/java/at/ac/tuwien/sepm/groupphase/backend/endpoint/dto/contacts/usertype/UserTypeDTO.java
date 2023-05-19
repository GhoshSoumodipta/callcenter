package at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.usertype;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(name = "UserTypeDTO", description = "A DTO for userType entries via rest")
public class UserTypeDTO {

    @Schema(name = "The name of the user type.")
    private String name;
}
