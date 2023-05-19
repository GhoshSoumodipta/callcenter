package at.ac.tuwien.sepm.groupphase.backend.repository.contacts.switchingcenter;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.switchingcallcenter.SwitchingCallCenterLeaderDTO;
import at.ac.tuwien.sepm.groupphase.backend.entity.mapper.user.contacts.switchingcallcenter.SwitchingCallCenterLeaderMapper;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.switchingcenter.SwitchingCallCenterLeader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SwitchingCallCenterLeaderRepository extends SwitchingCallCenterUserRepository<SwitchingCallCenterLeader, SwitchingCallCenterLeaderMapper, SwitchingCallCenterLeaderDTO> {
    @Override
    SwitchingCallCenterLeaderMapper mapper();

    /**
     * Find a single customer entry by id.
     *
     * @param id id of the customer entry
     * @return Optional containing the found customer entry
     */
    Optional<SwitchingCallCenterLeader> findOneByUserId(Long id);

    /**
     * Find all customers and order the list by the ID ascending.
     *
     * @param pageable special parameter to apply pagination
     * @return a page of the found customers
     */
    Page<SwitchingCallCenterLeader> findAllByOrderByUserIdAsc(Pageable pageable);

    /**
     * Find user entry by username.
     *
     * @param username the username of the desired user
     * @return Optional containing the user
     */

    @Query("Select u From User u Where u.username =?1")
    Optional<SwitchingCallCenterLeader> findOneByUsername(String username);

    /**
     * Get all users whose name contains the string 'username'
     *
     * @return list of users
     */
    Page<SwitchingCallCenterLeader> findByUsernameContainingIgnoreCase(String username, Pageable pageable);
}