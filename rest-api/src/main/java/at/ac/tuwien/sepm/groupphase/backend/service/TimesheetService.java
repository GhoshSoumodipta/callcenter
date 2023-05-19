package at.ac.tuwien.sepm.groupphase.backend.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.TimeSheetDTO;
import at.ac.tuwien.sepm.groupphase.backend.exception.ServiceException;

public interface TimesheetService {


Page<TimeSheetDTO> findByName(String name, Integer page, Integer pageSize);


    void deleteById(Long id) throws ServiceException, DataIntegrityViolationException;


    TimeSheetDTO update(TimeSheetDTO dto) throws ServiceException;


    TimeSheetDTO add(TimeSheetDTO dto) throws ServiceException;
}
