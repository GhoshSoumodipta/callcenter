package at.ac.tuwien.sepm.groupphase.backend.factories.repository;

import at.ac.tuwien.sepm.groupphase.backend.entity.base.EntityBase;
import at.ac.tuwien.sepm.groupphase.backend.entity.mapper.MapperBase;
import at.ac.tuwien.sepm.groupphase.backend.repository.common.RepositoryDTO;
import at.ac.tuwien.sepm.groupphase.backend.repository.common.RepositoryEntity;
import at.ac.tuwien.sepm.groupphase.backend.repository.common.RepositoryMapper;
import at.ac.tuwien.sepm.groupphase.backend.repository.common.RepositoryRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface MyJpaRepository<T, I, M, DTO> extends JpaRepository<T, I>, RepositoryMapper<M>, RepositoryEntity<T>, RepositoryDTO<DTO>, RepositoryRepository {
    public EntityBase create(EntityBase entityBase);
}
