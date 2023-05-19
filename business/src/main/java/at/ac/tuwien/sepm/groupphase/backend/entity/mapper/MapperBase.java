package at.ac.tuwien.sepm.groupphase.backend.entity.mapper;

import java.io.Serializable;

import org.mapstruct.BeanMapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.annotation.Transient;


public interface MapperBase<E,D> extends Serializable {
    @Transient
    @BeanMapping(ignoreByDefault = true)
    default MapperBase getInstance(){
        Class cls = (Class)getClass().getInterfaces()[0];
        System.out.println("Mapper for: "+getClass().getSimpleName()+" is: "+cls.getSimpleName());
        return (MapperBase) Mappers.getMapper( cls );
    }
    @Transient
    @BeanMapping(ignoreByDefault = true)
//    EntityBase DTOToEntity(DTOBase dto);
    default E DTOToEntity(D dto){
        MapperBase mapper = getInstance();
        E entity = (E)mapper.getClass().cast(mapper).DTOToEntity(dto);
        return entity;
    }

    @Transient
    @BeanMapping(ignoreByDefault = true)
    default D entityToDTO(E entity){
        return (D)getInstance().entityToDTO(entity);
    }

}
