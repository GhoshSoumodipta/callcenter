package at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.switchingcenter;

import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.User;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "switching_call_center_leaders")
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "userId")
public class SwitchingCallCenterLeader extends User {

    private String leaderLinkForVideoCall;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "switching_call_center_user_id")
    SwitchingCallCenter switchingCallCenter;
}