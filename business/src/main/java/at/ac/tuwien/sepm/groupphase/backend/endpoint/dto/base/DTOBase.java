package at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.base;

import at.ac.tuwien.sepm.groupphase.backend.datatype.UserType;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
//@JsonDeserialize(using = DTOBaseDeserializer.class)
public class DTOBase /*extends Response<T>*/ implements Serializable {

    private String username;
    private UserType userType;
}
