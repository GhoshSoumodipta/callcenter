package at.ac.tuwien.sepm.groupphase.backend.entity;

import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(LoginAttempts.class)
public abstract class LoginAttempts_ {

	public static volatile SingularAttribute<LoginAttempts, Boolean> blocked;
	public static volatile SingularAttribute<LoginAttempts, Long> userId;
	public static volatile SingularAttribute<LoginAttempts, User> user;
	public static volatile SingularAttribute<LoginAttempts, Integer> attempts;

	public static final String BLOCKED = "blocked";
	public static final String USER_ID = "userId";
	public static final String USER = "user";
	public static final String ATTEMPTS = "attempts";

}

