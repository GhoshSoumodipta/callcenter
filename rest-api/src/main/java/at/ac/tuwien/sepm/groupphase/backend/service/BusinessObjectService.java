package at.ac.tuwien.sepm.groupphase.backend.service;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.base.DTOBase;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.UserDTO;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;
import at.ac.tuwien.sepm.groupphase.backend.exception.ServiceException;
import org.springframework.data.domain.Page;

public interface BusinessObjectService {
    public DTOBase createObject(String objectJSONString)  throws ServiceException;
    public Page<DTOBase> getObjects(String object, String username, Integer page, Integer pageSize) throws ServiceException;
    public UserDTO findOne(Long id, String userTypeString) throws NotFoundException;
}
