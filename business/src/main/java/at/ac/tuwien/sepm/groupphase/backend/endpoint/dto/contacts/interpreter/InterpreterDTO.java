package at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.interpreter;



import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class InterpreterDTO extends UserDTO {

    private static final long serialVersionUID = 5799389536837660633L;

	private boolean locationOnline;

    private Boolean certificate;

    private String bankDetails;

    private String[] topicKnowledge;

    private String bankData;

    private String[] paymentInterval;

    private String linkForVideocall;
}
