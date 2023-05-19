package at.ac.tuwien.sepm.groupphase.backend.entity.mapper.customer;

import org.mapstruct.Mapper;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.customer.CustomerDTO;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.customer.Customer;
import at.ac.tuwien.sepm.groupphase.backend.entity.mapper.MapperBase;

@Mapper(componentModel = "spring")
public interface CustomerMapper extends MapperBase<Customer, CustomerDTO>{

    /**
     * Maps the CustomerDTO object to Customer object
     * @param customerDTO to map
     * @return the mapped Customer object
     */
    Customer DTOToEntity(CustomerDTO customerDTO);

    /**
     * Maps the Customer object to CustomerDTO object
     * @param customer to map
     * @return the mapped CustomerDTO object
     */
    CustomerDTO entityToDTO(Customer customer);

}
