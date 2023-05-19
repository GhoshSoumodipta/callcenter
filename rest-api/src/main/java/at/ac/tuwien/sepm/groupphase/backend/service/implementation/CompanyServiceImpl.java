package at.ac.tuwien.sepm.groupphase.backend.service.implementation;

import javax.persistence.PersistenceException;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.company.CompanyDTO;
import at.ac.tuwien.sepm.groupphase.backend.repository.contacts.company.CompanyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import at.ac.tuwien.sepm.groupphase.backend.entity.mapper.CalendarMapper;
import at.ac.tuwien.sepm.groupphase.backend.entity.mapper.user.contacts.company.CompanyMapper;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;
import at.ac.tuwien.sepm.groupphase.backend.exception.ServiceException;
import at.ac.tuwien.sepm.groupphase.backend.repository.CalendarRepository;
import at.ac.tuwien.sepm.groupphase.backend.repository.contacts.company.CompanyRepository;
import at.ac.tuwien.sepm.groupphase.backend.repository.EventRepository;
import at.ac.tuwien.sepm.groupphase.backend.service.CompanyService;


@Service
public class CompanyServiceImpl implements CompanyService {


	@Autowired
    private CompanyRepository repository;

	//@Autowired
   // private  EventRepository eventRepository;

	@Autowired(required=false)
    private  CompanyMapper companyMapper;
    private Logger LOGGER = LoggerFactory.getLogger(getClass());



//    public CompanyServiceImpl(CompanyRepository repository, CompanyMapper mapper, EventRepository eventRepository) {
//        this.repository = repository;
//        this.mapper = mapper;
//       // this.eventRepository = eventRepository;
//    }

    @Override
    public Page<CompanyDTO> findByName(String name, Integer page, Integer pageSize) {
        LOGGER.info("CalendarServiceImpl: findByName");



        Pageable pageable = PageRequest.of(page, pageSize);
        Page<CompanyDTO> result = repository.findByFirstNameContainingIgnoreCase(name, pageable).map(companyMapper::entityToDTO);
        if (!result.hasContent()) throw new NotFoundException("No company found");
        if (result.getContent().size() == 0) throw new NotFoundException("No company found");
        return result;
    }

    @Override
    public CompanyDTO update(CompanyDTO dto) throws ServiceException {
        LOGGER.info("CompanyService: updatet");
        try {
            return companyMapper.entityToDTO(repository.save(companyMapper.DTOToEntity(dto)));
        } catch (DataIntegrityViolationException e) {
            throw new ServiceException("comapany name already exists.");
        }
        catch (PersistenceException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public CompanyDTO add(CompanyDTO dto) throws ServiceException {
        LOGGER.info("CompanyService: add");
        try {
            return companyMapper.entityToDTO(repository.save(companyMapper.DTOToEntity(dto)));
        } catch (DataIntegrityViolationException e) {
            throw new ServiceException("Company name already exists.");
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void deleteById(Long id) throws ServiceException, DataIntegrityViolationException {
        LOGGER.info("CompanyService: deleteById " + id);
        try {
//            if (eventRepository.findAllByArtist_Id(id, PageRequest.of(0, 1)).hasContent())
//                throw new DataIntegrityViolationException("Entity is referenced by an event! Deleting it will violate the referential integrity constaint.");
            repository.deleteById(id);
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
