package at.ac.tuwien.sepm.groupphase.backend.repository.common;

public interface RepositoryDTO<M> {

    M dto();
    void setDTOClass(Class<M> dtoClass);
    Class<M> getDTOClass();
}
