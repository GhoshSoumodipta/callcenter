package at.ac.tuwien.sepm.groupphase.backend.repository.common;

import at.ac.tuwien.sepm.groupphase.backend.entity.mapper.MapperBase;
import org.mapstruct.factory.Mappers;
import org.springframework.data.annotation.Transient;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface RepositoryMapper<M> {

    public M mapper();

    public void setMapperClass(Class<M> clazz);
}
