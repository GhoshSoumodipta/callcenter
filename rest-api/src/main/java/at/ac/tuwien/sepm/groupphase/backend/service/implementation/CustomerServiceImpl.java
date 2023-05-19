package at.ac.tuwien.sepm.groupphase.backend.service.implementation;

import java.time.LocalDate;

import javax.persistence.PersistenceException;

import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.customer.CustomerDTO;
import at.ac.tuwien.sepm.groupphase.backend.entity.mapper.MapperBase;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;
import at.ac.tuwien.sepm.groupphase.backend.repository.CustomerRepository;
import at.ac.tuwien.sepm.groupphase.backend.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
    private  CustomerRepository customerRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

//    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
//        this.customerRepository = customerRepository;
//        this.customerMapper = customerMapper;
//    }

    @Override
    public CustomerDTO addCustomer(CustomerDTO customerDTO) {
        if (customerDTO != null) {
            LOGGER.info("Add customer " + customerDTO.toString());
            // VALIDATION START
            if (customerDTO.getUsername() == null || customerDTO.getUsername().isBlank())
                throw new ServiceException("Customer " + customerDTO.toString() + "could not be added: username must not be empty");
            if (customerDTO.getFirstName() == null || customerDTO.getFirstName().isBlank())
                throw new ServiceException("Customer " + customerDTO.toString() + "could not be added: first username must not be empty");
            if (customerDTO.getBirthday() == null)
                throw new ServiceException("Customer " + customerDTO.toString() + "could not be added: birthday must not be empty");
            //VALIDATION END
            return (CustomerDTO) ((MapperBase)customerRepository.mapper()).entityToDTO(customerRepository.save(((MapperBase)customerRepository.mapper()).DTOToEntity(customerDTO)));
        }
        else {
            LOGGER.info("Add customer failed");
            throw new ServiceException("Customer could not be added " + customerDTO.toString());
        }
    }

    @Override
    public CustomerDTO findOne(Long id) throws Throwable{
        LOGGER.info("Find one customer by ID");
        return (CustomerDTO) ((MapperBase)customerRepository.mapper()).entityToDTO(customerRepository.findOneByUserId(id).orElseThrow(NotFoundException::new));
    }

    @Override
    public CustomerDTO adaptCustomer(CustomerDTO customerDTO) {
        LOGGER.info("Adapt customer: " + customerDTO.toString());
        return (CustomerDTO) ((MapperBase)customerRepository.mapper()).entityToDTO(customerRepository.save(((MapperBase)customerRepository.mapper()).DTOToEntity(customerDTO)));
    }

    @Override
    public Page<CustomerDTO> findAll(Integer page) {
        LOGGER.info("Find all customers ordered by ID");
        try{
            int pageSize = 10;
            if(page < 0) {
                throw new IllegalArgumentException("Not a valid page.");
            }
            Pageable pageable = PageRequest.of(page, pageSize);
            return customerRepository.findAllByOrderByUserIdAsc(pageable).map(o->((MapperBase)customerRepository.mapper()).entityToDTO(o));
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public Page<CustomerDTO> findCustomersFiltered(Long id, String name, String firstname, String email, LocalDate birthday, Integer page, Integer pageSize) {
        LOGGER.info("Find customers filtered");
        try{
            if(pageSize == null){
                pageSize = 10;
            }
            if(page < 0) {
                throw new IllegalArgumentException("Not a valid page.");
            }
            Pageable pageable = PageRequest.of(page, pageSize);
            return customerRepository.findByUserIdAndFirstNameAndLastNameAndEmailsAndBirthday(id, name, firstname, email, birthday, pageable).map(o->((MapperBase)customerRepository.mapper()).entityToDTO(o));
        }catch (PersistenceException e){
            throw new ServiceException(e.getMessage());
        }
    }
 }
