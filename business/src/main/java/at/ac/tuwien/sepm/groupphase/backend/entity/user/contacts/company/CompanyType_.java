package at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.company;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CompanyType.class)
public abstract class CompanyType_ {

	public static volatile ListAttribute<CompanyType, Company> companies;
	public static volatile SingularAttribute<CompanyType, String> name;
	public static volatile SingularAttribute<CompanyType, Long> id;

	public static final String COMPANIES = "companies";
	public static final String NAME = "name";
	public static final String ID = "id";

}

