package at.ac.tuwien.sepm.groupphase.backend.entity;

import at.ac.tuwien.sepm.groupphase.backend.entity.base.EntityBase;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "login_attempts")
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class LoginAttempts extends EntityBase {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @OneToOne
    @JoinColumn(name = "user_id")
    @MapsId
    private User user;

    @Column(name = "attempts")
    private int numberOfAttempts;

    @Column(name = "blocked")
     private boolean blocked;

    public void setUserSynch(User user) {
        if (user == null) {
            if (this.user != null) {
//                this.user.setLoginAttempts(null);
            }
        }
        else {
            user.setLoginAttempts(this);
        }
        this.user = user;
    }
}
