package at.ac.tuwien.sepm.groupphase.backend.repository.common;

import at.ac.tuwien.sepm.groupphase.backend.factories.repository.MyJpaRepository;

public interface RepositoryRepository {

    Class<MyJpaRepository> repository();
    void setRepositoryClass();
}
