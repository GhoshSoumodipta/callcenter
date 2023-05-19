package at.ac.tuwien.sepm.groupphase.backend.repository.common;

public interface RepositoryEntity<M> {
    M entity();
    void setEntityClass(Class<M> entityClass);
    Class<M> getEntityClass();
}
