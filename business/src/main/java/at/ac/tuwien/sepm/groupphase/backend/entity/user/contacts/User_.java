package at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts;

import at.ac.tuwien.sepm.groupphase.backend.datatype.UserType;
import at.ac.tuwien.sepm.groupphase.backend.entity.CalendarEntity;
import at.ac.tuwien.sepm.groupphase.backend.entity.EmailsEntity;
import at.ac.tuwien.sepm.groupphase.backend.entity.LoginAttempts;
import at.ac.tuwien.sepm.groupphase.backend.entity.RoleEntity;
import at.ac.tuwien.sepm.groupphase.backend.entity.TimeSheetEntity;
import java.sql.Time;
import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, LocalDateTime> birthday;
	public static volatile SingularAttribute<User, Integer> Zip;
	public static volatile SingularAttribute<User, String> lastName;
	public static volatile SingularAttribute<User, String> country;
	public static volatile SingularAttribute<User, LocalDateTime> userSince;
	public static volatile SingularAttribute<User, LocalDateTime> lastLogin;
	public static volatile ListAttribute<User, CalendarEntity> appointments;
	public static volatile SingularAttribute<User, LoginAttempts> loginAttempts;
	public static volatile SingularAttribute<User, RoleEntity> role;
	public static volatile SingularAttribute<User, Time> counter;
	public static volatile SingularAttribute<User, UserType> type;
	public static volatile SingularAttribute<User, Long> userId;
	public static volatile SingularAttribute<User, String> phoneNo;
	public static volatile ListAttribute<User, EmailsEntity> emails;
	public static volatile SingularAttribute<User, String> firstName;
	public static volatile SingularAttribute<User, String> password;
	public static volatile ListAttribute<User, TimeSheetEntity> sheets;
	public static volatile SingularAttribute<User, String> profilephoto;
	public static volatile SingularAttribute<User, String[]> pdfs;
	public static volatile SingularAttribute<User, String> street;
	public static volatile SingularAttribute<User, Boolean> onlinestatus;
	public static volatile SingularAttribute<User, String> houseNo;
	public static volatile SingularAttribute<User, String> location;
	public static volatile SingularAttribute<User, String> username;

	public static final String BIRTHDAY = "birthday";
	public static final String ZIP = "Zip";
	public static final String LAST_NAME = "lastName";
	public static final String COUNTRY = "country";
	public static final String USER_SINCE = "userSince";
	public static final String LAST_LOGIN = "lastLogin";
	public static final String APPOINTMENTS = "appointments";
	public static final String LOGIN_ATTEMPTS = "loginAttempts";
	public static final String ROLE = "role";
	public static final String COUNTER = "counter";
	public static final String TYPE = "type";
	public static final String USER_ID = "userId";
	public static final String PHONE_NO = "phoneNo";
	public static final String EMAILS = "emails";
	public static final String FIRST_NAME = "firstName";
	public static final String PASSWORD = "password";
	public static final String SHEETS = "sheets";
	public static final String PROFILEPHOTO = "profilephoto";
	public static final String PDFS = "pdfs";
	public static final String STREET = "street";
	public static final String ONLINESTATUS = "onlinestatus";
	public static final String HOUSE_NO = "houseNo";
	public static final String LOCATION = "location";
	public static final String USERNAME = "username";

}

