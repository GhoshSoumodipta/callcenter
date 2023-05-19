package at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.company;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Company.class)
public abstract class Company_ extends at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.User_ {

	public static volatile SingularAttribute<Company, Long> companyId;
	public static volatile SingularAttribute<Company, CompanyType> companyType;
	public static volatile SingularAttribute<Company, String> invoice;

	public static final String COMPANY_ID = "companyId";
	public static final String COMPANY_TYPE = "companyType";
	public static final String INVOICE = "invoice";

}

