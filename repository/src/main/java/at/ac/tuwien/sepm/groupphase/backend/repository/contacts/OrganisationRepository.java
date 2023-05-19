package at.ac.tuwien.sepm.groupphase.backend.repository.contacts;

import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;


@NoRepositoryBean
public interface OrganisationRepository<T extends User, M, DTO> extends UserRepositoryBase<T, M, DTO> {
}
