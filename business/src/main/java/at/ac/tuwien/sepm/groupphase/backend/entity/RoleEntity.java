package at.ac.tuwien.sepm.groupphase.backend.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Table(name = "roles")
@Data
public class RoleEntity {


	    @Id
	    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	    private Long id;

	    @Column(nullable = false, unique = true)
	    @Size(max = 255)
	    private String name;


	    @OneToMany(mappedBy = "role",cascade = { CascadeType.ALL})
		 @JsonManagedReference
		 private List<User> users;

}
