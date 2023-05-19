package at.ac.tuwien.sepm.groupphase.backend.service;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.UserDTO;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.UserDTO;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.company.CompanyDTO;
import at.ac.tuwien.sepm.groupphase.backend.exception.ServiceException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;

public interface OrganisationAndUserService {
    Page<UserDTO> findByName(String name, Integer page, Integer pageSize);


    void deleteById(Long id) throws ServiceException, DataIntegrityViolationException;


    UserDTO update(UserDTO dto) throws ServiceException;


    UserDTO add(UserDTO dto) throws ServiceException;
}
