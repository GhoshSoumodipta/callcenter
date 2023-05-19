package at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.authentication;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.LoginUserResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "AuthenticationToken", description = "Data Transfer Objects for AuthenticationTokens")
public class AuthenticationToken {

    @Schema(requiredMode = RequiredMode.REQUIRED, accessMode = AccessMode.READ_ONLY, name = "Current authentication token")
    private String currentToken;

    @Schema(requiredMode = RequiredMode.REQUIRED, accessMode = AccessMode.READ_ONLY, name = "Future authentication token")
    private String futureToken;

     private LoginUserResponseDTO user;
}
