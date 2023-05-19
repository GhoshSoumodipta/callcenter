package at.ac.tuwien.sepm.groupphase.backend.repository.contacts;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.User;
import at.ac.tuwien.sepm.groupphase.backend.factories.repository.MyJpaRepository;

@NoRepositoryBean
public interface UserRepositoryBase<T extends User, M, DTO> extends MyJpaRepository<T, Long, M, DTO> {



    /**
     * Find a single customer entry by id.
     *
     * @param id id of the customer entry
     * @return Optional containing the found customer entry
     */
    <T extends User> Optional<T> findOneByUserId(Long id);

    /**
     * Find all customers and order the list by the ID ascending.
     *
     * @param pageable special parameter to apply pagination
     * @return a page of the found customers
     */
    <T extends User> Page<T> findAllByOrderByUserIdAsc(Pageable pageable);

    /**
     * Find user entry by username.
     *
     * @param username the username of the desired user
     * @return Optional containing the user
     */

    //TODO check
    //@Query("Select u from #{#entityName} u Where u.username =?1")
    Optional<T> findOneByUsername(String username);

    /**
     * Get all users whose name contains the string 'username'
     *
     * @return list of users
     */
    Page<T> findByUsernameContainingIgnoreCase(String username, Pageable pageable);

    public Page<T> findByUserIdAndFirstNameAndLastNameAndEmailsAndBirthday(Long userId, String firstName, String lastName, String email, LocalDate birthday, Pageable pageable);

    Page<T> findByFirstNameContainingIgnoreCase(String username, Pageable pageable);


//    public List<T> findAllBlocked();

}
