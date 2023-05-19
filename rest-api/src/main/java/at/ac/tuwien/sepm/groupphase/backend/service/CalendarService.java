package at.ac.tuwien.sepm.groupphase.backend.service;




import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.CalendarDTO;
import at.ac.tuwien.sepm.groupphase.backend.exception.ServiceException;

public interface CalendarService {


    Page<CalendarDTO> findByName(String artistName, Integer page, Integer pageSize);


    void deleteById(Long id) throws ServiceException, DataIntegrityViolationException;


    CalendarDTO update(CalendarDTO dto) throws ServiceException;


    CalendarDTO add(CalendarDTO dto) throws ServiceException;

}
