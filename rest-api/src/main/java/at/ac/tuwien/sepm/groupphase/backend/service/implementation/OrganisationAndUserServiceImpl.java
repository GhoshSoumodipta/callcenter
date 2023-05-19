package at.ac.tuwien.sepm.groupphase.backend.service.implementation;

import at.ac.tuwien.sepm.groupphase.backend.datatype.UserType;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.UserDTO;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.UserDTO;
import at.ac.tuwien.sepm.groupphase.backend.entity.base.EntityBase;
import at.ac.tuwien.sepm.groupphase.backend.entity.mapper.MapperBase;
import at.ac.tuwien.sepm.groupphase.backend.exception.ServiceException;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;
import at.ac.tuwien.sepm.groupphase.backend.factories.contacts.RepositoryFactoryMain;
import at.ac.tuwien.sepm.groupphase.backend.factories.mapper.MapperFactory;
import at.ac.tuwien.sepm.groupphase.backend.repository.contacts.UserRepositoryBase;
import at.ac.tuwien.sepm.groupphase.backend.service.OrganisationAndUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.persistence.PersistenceException;

public class OrganisationAndUserServiceImpl implements OrganisationAndUserService {
    private Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private RepositoryFactoryMain repositoryFactoryMain;

    @Autowired
    private MapperFactory mapperFactory;

//    public CompanyServiceImpl(CompanyRepository repository, CompanyMapper mapper, EventRepository eventRepository) {
//        this.repository = repository;
//        this.mapper = mapper;
//       // this.eventRepository = eventRepository;
//    }

    @Override
    public Page<UserDTO> findByName(String name, Integer page, Integer pageSize) {
        LOGGER.info("CalendarServiceImpl: findByName");

        UserRepositoryBase repositoryBase = repositoryFactoryMain.getRepository(UserType.valueOf("USER"));
        MapperBase mapper = mapperFactory.getMapper(repositoryBase);

        Pageable pageable = PageRequest.of(page, pageSize);
        Page<UserDTO> result = repositoryBase.findByFirstNameContainingIgnoreCase(name, pageable).map(o->mapper.entityToDTO((EntityBase) o));
        if (!result.hasContent()) throw new NotFoundException("No company found");
        if (result.getContent().size() == 0) throw new NotFoundException("No company found");
        return result;
    }

    @Override
    public UserDTO update(UserDTO dto) throws ServiceException {
        LOGGER.info("CompanyService: updatet");
        try {
            UserRepositoryBase repositoryBase = repositoryFactoryMain.getRepository(dto.getUserType());
            MapperBase mapper = mapperFactory.getMapper(repositoryBase);
            return (UserDTO)mapper.entityToDTO((EntityBase) repositoryBase.save(mapper.DTOToEntity(dto)));
        } catch (DataIntegrityViolationException e) {
            throw new ServiceException("comapany name already exists.");
        }
        catch (PersistenceException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public UserDTO add(UserDTO dto) throws ServiceException {
        LOGGER.info("CompanyService: add");
        try {
            UserRepositoryBase repositoryBase = repositoryFactoryMain.getRepository(dto.getUserType());
            MapperBase mapper = mapperFactory.getMapper(repositoryBase);
            return (UserDTO)mapper.entityToDTO((EntityBase) repositoryBase.save(mapper.DTOToEntity(dto)));
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
            repositoryFactoryMain.getRepository(UserType.valueOf("USER")).deleteById(id);
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
