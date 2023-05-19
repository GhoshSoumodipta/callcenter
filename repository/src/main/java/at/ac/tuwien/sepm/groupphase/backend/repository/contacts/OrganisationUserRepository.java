package at.ac.tuwien.sepm.groupphase.backend.repository.contacts;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.User;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface OrganisationUserRepository<T extends User, M, DTO> extends UserRepositoryBase<T,  M, DTO> {

}
