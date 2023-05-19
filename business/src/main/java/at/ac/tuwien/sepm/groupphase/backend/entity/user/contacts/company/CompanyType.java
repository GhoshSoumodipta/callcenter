package at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.company;


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

import at.ac.tuwien.sepm.groupphase.backend.entity.base.EntityBase;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "company_types")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyType extends EntityBase {


	    @Id
	    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	    private Long id;

	    @Column(nullable = false, unique = true)
	    @Size(max = 255)
	    private String name;


//	    @OneToMany(mappedBy = "companyType",cascade = { CascadeType.ALL})
//		 @JsonManagedReference
//		 private List<Company> companies;

}
