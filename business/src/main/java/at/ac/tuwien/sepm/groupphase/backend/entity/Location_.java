package at.ac.tuwien.sepm.groupphase.backend.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Location.class)
public abstract class Location_ {

	public static volatile SingularAttribute<Location, String> country;
	public static volatile SingularAttribute<Location, String> locationName;
	public static volatile SingularAttribute<Location, String> city;
	public static volatile SingularAttribute<Location, String> street;
	public static volatile SingularAttribute<Location, String> postalCode;
	public static volatile SingularAttribute<Location, String> description;
	public static volatile SingularAttribute<Location, Long> id;

	public static final String COUNTRY = "country";
	public static final String LOCATION_NAME = "locationName";
	public static final String CITY = "city";
	public static final String STREET = "street";
	public static final String POSTAL_CODE = "postalCode";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";

}

