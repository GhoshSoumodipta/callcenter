package at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts;

import at.ac.tuwien.sepm.groupphase.backend.entity.base.EntityBase;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@EqualsAndHashCode
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Embeddable
public class Organisation {

    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "url")
    private String url;

    @Column(nullable = false)
    private String linkForVideoCall;
}
