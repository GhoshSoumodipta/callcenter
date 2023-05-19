package at.ac.tuwien.sepm.groupphase.backend.repository.contacts.company;

import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.User;
import at.ac.tuwien.sepm.groupphase.backend.repository.contacts.OrganisationUserRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@NoRepositoryBean
public interface CompanyUserRepository<T extends User, M, DTO> extends OrganisationUserRepository<T, M, DTO> {
}
