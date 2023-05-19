package at.ac.tuwien.sepm.groupphase.backend.service;



import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.company.CompanyDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import at.ac.tuwien.sepm.groupphase.backend.exception.ServiceException;

public interface CompanyService {



	 Page<CompanyDTO> findByName(String name, Integer page, Integer pageSize);


	    void deleteById(Long id) throws ServiceException, DataIntegrityViolationException;


	    CompanyDTO update(CompanyDTO dto) throws ServiceException;


	    CompanyDTO add(CompanyDTO dto) throws ServiceException;
}
