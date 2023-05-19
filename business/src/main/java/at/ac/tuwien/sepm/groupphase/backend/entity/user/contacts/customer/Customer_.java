package at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.customer;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Customer.class)
public abstract class Customer_ extends at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.User_ {

	public static volatile SingularAttribute<Customer, BigDecimal> creditWorking;
	public static volatile SingularAttribute<Customer, Boolean> SMS_certification;
	public static volatile SingularAttribute<Customer, BigDecimal> creditPrivate;

	public static final String CREDIT_WORKING = "creditWorking";
	public static final String S_MS_CERTIFICATION = "SMS_certification";
	public static final String CREDIT_PRIVATE = "creditPrivate";

}

