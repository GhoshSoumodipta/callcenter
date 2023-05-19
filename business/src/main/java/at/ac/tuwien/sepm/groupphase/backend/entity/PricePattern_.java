package at.ac.tuwien.sepm.groupphase.backend.entity;

import at.ac.tuwien.sepm.groupphase.backend.datatype.PriceCategory;
import javax.annotation.Generated;
import javax.persistence.metamodel.MapAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PricePattern.class)
public abstract class PricePattern_ {

	public static volatile MapAttribute<PricePattern, PriceCategory, Double> priceMapping;
	public static volatile SingularAttribute<PricePattern, String> name;
	public static volatile SingularAttribute<PricePattern, Long> id;

	public static final String PRICE_MAPPING = "priceMapping";
	public static final String NAME = "name";
	public static final String ID = "id";

}

