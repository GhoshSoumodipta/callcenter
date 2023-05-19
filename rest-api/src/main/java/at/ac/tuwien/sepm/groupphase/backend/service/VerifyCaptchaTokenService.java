package at.ac.tuwien.sepm.groupphase.backend.service;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.UserDTO;
import at.ac.tuwien.sepm.groupphase.backend.exception.ServiceException;

public interface VerifyCaptchaTokenService {

    public boolean verifyToken(UserDTO UserDTO) throws ServiceException;
}
