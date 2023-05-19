package at.ac.tuwien.sepm.groupphase.backend.service;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.interpreter.InterpreterDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;

import at.ac.tuwien.sepm.groupphase.backend.exception.ServiceException;

public interface InterpreterService {


Page<InterpreterDTO> findByName(String artistName, Integer page, Integer pageSize);


    void deleteById(Long id) throws ServiceException, DataIntegrityViolationException;


    InterpreterDTO update(InterpreterDTO dto) throws ServiceException;


    InterpreterDTO add(InterpreterDTO dto) throws ServiceException;
}
