package at.ac.tuwien.sepm.groupphase.backend.calling.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {

    private String status;
    private Object data;
    private String message;
}
