package at.ac.tuwien.sepm.groupphase.backend.repository.contacts.switchingcenter;

import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.User;
import at.ac.tuwien.sepm.groupphase.backend.repository.contacts.OrganisationUserRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@NoRepositoryBean
public interface SwitchingCallCenterUserRepository<T extends User, M, DTO> extends OrganisationUserRepository<T, M, DTO> {
}
