package at.ac.tuwien.sepm.groupphase.backend.repository.contacts.company;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.company.CompanyEmployeeDTO;
import at.ac.tuwien.sepm.groupphase.backend.entity.base.EntityBase;
import at.ac.tuwien.sepm.groupphase.backend.entity.mapper.user.contacts.company.CompanyEmployeeMapper;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.OrganisationUser;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.User;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.company.Company;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.company.CompanyEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@NoRepositoryBean
public interface CompanyEmployeeRepository extends CompanyUserRepository<CompanyEmployee, CompanyEmployeeMapper, CompanyEmployeeDTO> {

}