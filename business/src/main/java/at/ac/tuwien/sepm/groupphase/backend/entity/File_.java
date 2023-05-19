package at.ac.tuwien.sepm.groupphase.backend.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(File.class)
public abstract class File_ {

	public static volatile SingularAttribute<File, byte[]> data;
	public static volatile SingularAttribute<File, String> name;
	public static volatile SingularAttribute<File, Long> id;
	public static volatile SingularAttribute<File, String> fileType;

	public static final String DATA = "data";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String FILE_TYPE = "fileType";

}

