package at.ac.tuwien.sepm.groupphase.backend.repository.contacts.company;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.company.CompanyLeaderDTO;
import at.ac.tuwien.sepm.groupphase.backend.entity.mapper.user.contacts.company.CompanyLeaderMapper;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.OrganisationUser;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.company.CompanyLeader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyLeaderRepository extends CompanyUserRepository<CompanyLeader, CompanyLeaderMapper, CompanyLeaderDTO> {
}