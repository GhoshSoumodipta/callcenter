package at.ac.tuwien.sepm.groupphase.backend.entity;

import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.User;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "translators")
@Inheritance(strategy = InheritanceType.JOINED)
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@Data
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "userId")
public class Translator extends User {

    private Boolean certification;
    private String[] topicKnowledge;
    private String[] locationOnline;
    private String bankData;
    private Boolean SMS_certification;
    private String[] paymentInterval;
    private String linkForVideoCall;
}