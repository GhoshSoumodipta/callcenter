package at.ac.tuwien.sepm.groupphase.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import at.ac.tuwien.sepm.groupphase.backend.entity.base.EntityBase;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.User;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "timesheets")
public class TimeSheetEntity extends EntityBase {


	@Id
    @Column(name="id")
    private Long id;

	@Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String sheet;


      @ManyToOne
	  @JoinColumn(name = "user_id")
	  @JsonBackReference
	  private User user;

}
