package at.ac.tuwien.sepm.groupphase.backend.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.customer.CustomerDTO;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.customer.Customer;
import at.ac.tuwien.sepm.groupphase.backend.entity.mapper.customer.CustomerMapper;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.User;
import at.ac.tuwien.sepm.groupphase.backend.repository.contacts.UserRepositoryBase;

@Repository
@Transactional(readOnly = false)
public interface CustomerRepository<T extends User, M, DTO extends CustomerDTO> extends UserRepositoryBase<Customer, CustomerMapper, CustomerDTO> {
}
