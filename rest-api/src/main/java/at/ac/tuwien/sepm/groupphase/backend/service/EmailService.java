package at.ac.tuwien.sepm.groupphase.backend.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.EmailDTO;
import at.ac.tuwien.sepm.groupphase.backend.exception.ServiceException;

public interface EmailService {


Page<EmailDTO> findByName(String name, Integer page, Integer pageSize);


    void deleteById(Long id) throws ServiceException, DataIntegrityViolationException;


    EmailDTO update(EmailDTO dto) throws ServiceException;


    EmailDTO add(EmailDTO dto) throws ServiceException;

}
