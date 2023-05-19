package at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.interpreter;

import javax.persistence.*;

import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.User;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@Table(name = "interpreters")
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "userId")
public class Interpreter extends User {


	    @Column(nullable = false, name = "location_online")
	    private boolean locationOnline;

	    @Column(nullable = false)
	    private Boolean certificate;

	    @Column(nullable = false, unique = true)
	    private String bankDetails;


	    @Column(nullable = false, name = "topic_knowledge")
	    private String[] topicKnowledge;

	    @Column(nullable = false)
	    private String bankData;

	    @Column(nullable = false)
	    private String[] paymentInterval;

	    @Column(nullable = false)
	    private String linkForVideocall;
}
