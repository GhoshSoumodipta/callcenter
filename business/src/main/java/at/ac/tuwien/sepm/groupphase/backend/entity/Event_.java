package at.ac.tuwien.sepm.groupphase.backend.entity;

import at.ac.tuwien.sepm.groupphase.backend.datatype.EventType;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Event.class)
public abstract class Event_ {

	public static volatile SingularAttribute<Event, String> name;
	public static volatile SingularAttribute<Event, Integer> durationInMinutes;
	public static volatile SingularAttribute<Event, String> description;
	public static volatile SingularAttribute<Event, Long> id;
	public static volatile SingularAttribute<Event, EventType> eventType;
	public static volatile SingularAttribute<Event, String> content;

	public static final String NAME = "name";
	public static final String DURATION_IN_MINUTES = "durationInMinutes";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";
	public static final String EVENT_TYPE = "eventType";
	public static final String CONTENT = "content";

}

