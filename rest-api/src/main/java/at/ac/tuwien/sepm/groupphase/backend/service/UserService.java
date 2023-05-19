package at.ac.tuwien.sepm.groupphase.backend.service;

import java.util.List;

import org.springframework.data.domain.Page;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.UserDTO;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.usertype.UserTypeDTO;
import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.requestparameter.PasswordChangeRequest;
import at.ac.tuwien.sepm.groupphase.backend.exception.NotFoundException;
import at.ac.tuwien.sepm.groupphase.backend.exception.ServiceException;

public interface UserService {

    /**
     * Get all users or search by username, depending on whether username == null
     *
     * @param username string to search by
     * @param page number of the requested page
     * @param pageSize size of the requested Page
     * @return page of the found users
     */
    Page<UserDTO> getUsers(String username, Integer page, Integer pageSize) throws ServiceException;

    /**
     * Find a single user entry by id.
     *
     * @param id the is of the user entry
     * @return the user entry
     */
    UserDTO findOne(Long id, String userTypeString) throws NotFoundException;

    /**
     * finds a user by (unique) name
     *
     * @param name the name of the user
     * @return an dto of the user
     */
    UserDTO findUserByName(String name);

    /**
     * Create a user
     *
     * @param UserDTO to add
     * @return created userDTO
     */
    UserDTO createUser(UserDTO userPostDTO) throws ServiceException;

    /**
     * Delete a user by id
     *
     * @param userId id of user to delete
     */
    void deleteUser(Long userId) throws ServiceException;

    /**
     * Find user by username.
     *
     * @param username name of user
     * @return found user
     */
    UserDTO findOneByUsername(String username);

    /**
     * Unblock a user by id
     *
     * @param userId id of the user that is to be unblocked
     * @return boolean if the operation was successful
     */
    boolean unblockUser(Long userId);

    /**
     * Block a user by id
     *
     * @param userId the id of the user that is to be blocked
     * @return boolean of the success of the operation
     */
    boolean blockUser(Long userId) throws ServiceException;


    /**
     * changes the password of a user (request is from an admin)
     * @param passwordChangeRequest contains the id and the new password
     */
    void changePassword(PasswordChangeRequest passwordChangeRequest) throws ServiceException;

    /**
     * Get all blocked users or search by username, depending on whether username == null
     * @param username string to search by
     * @param page number of the requested page
     * @param pageSize size of the requested page
     * @return a page with users that are currently blocked
     */
    Page<UserDTO> getBlockedUsers(String username, Integer page, Integer pageSize) throws ServiceException;

    List<UserTypeDTO> getUserTypes() throws ServiceException;
}
