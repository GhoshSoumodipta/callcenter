package at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts;

import at.ac.tuwien.sepm.groupphase.backend.datatype.UserType;
import at.ac.tuwien.sepm.groupphase.backend.entity.*;
import at.ac.tuwien.sepm.groupphase.backend.entity.base.EntityBase;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@AttributeOverrides({
    @AttributeOverride(name = "lastLogin", column = @Column(name = "last_login")),
    @AttributeOverride(name = "firstName", column = @Column(name = "first_name")),
    @AttributeOverride(name = "houseNo", column = @Column(name = "house_no")),
    @AttributeOverride(name = "lastName", column = @Column(name = "last_name")),
    @AttributeOverride(name = "onlinestatus", column = @Column(name = "online_status")),
    @AttributeOverride(name = "phoneNo", column = @Column(name = "phone_no")),
    @AttributeOverride(name = "userSince", column = @Column(name = "user_since"))
})
public class User extends EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonProperty(access = Access.READ_ONLY)
    private Long userId;

    @Column(nullable = true)
    @Size(max = 255)
    private String firstName;

    @Column(nullable = true)
    @Size(max = 255)
    private String lastName;

    @Column(name = "birthday")
    private LocalDateTime birthday;

    @Column(nullable = true)
    @Size(max = 255)
    private String street;

    @Column(nullable = true)
    @Size(max = 255)
    private String location;


    @Column(nullable = true)
    @Size(max = 255)
    private String country;

    @Column(nullable = false, unique = true)
    private String username;


    @Column(nullable = true)
    private Integer zip;


    @Column(nullable = true)
    @Size(max = 255)
    private String houseNo;

    @Column(nullable = true)
    private String phoneNo;

    @Column(nullable = true)
    private String[] pdfs;

    @Column(nullable = true)
    private String profilePhoto;


    @Column(nullable = true)
    private Boolean onlineStatus;

    @Column(nullable = true)
    private Time counter;

    @Column(nullable = false)
    @Size(max = 255)
    private String password;

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column(nullable = true, name = "user_since")
    private LocalDateTime userSince;

    @Column(name = "lastlogin")
    private LocalDateTime lastLogin;

    @OneToOne(mappedBy =  "user",cascade = { CascadeType.ALL}, orphanRemoval = true, optional = false)
    private LoginAttempts loginAttempts;

     @OneToMany(mappedBy = "user",cascade = { CascadeType.ALL})
	 @JsonManagedReference
	 private List<EmailsEntity> emails;


     @OneToMany(mappedBy = "user",cascade = { CascadeType.ALL})
	 @JsonManagedReference
	 private List<CalendarEntity> appointments;



     @OneToMany(mappedBy = "user",cascade = { CascadeType.ALL})
	 @JsonManagedReference
	 private List<TimeSheetEntity> sheets;


     @ManyToOne
	  @JoinColumn(name = "role_id")
	  @JsonBackReference
	  private RoleEntity role;

}