package at.ac.tuwien.sepm.groupphase.backend.repository;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.LoginAttemptsDTO;
import at.ac.tuwien.sepm.groupphase.backend.entity.LoginAttempts;
import at.ac.tuwien.sepm.groupphase.backend.entity.mapper.LoginAttemptsMapper;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.User;
import at.ac.tuwien.sepm.groupphase.backend.factories.repository.MyJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface LoginAttemptsRepository extends MyJpaRepository<LoginAttempts, Long, LoginAttemptsMapper, LoginAttemptsDTO> {

    /**
     *
     * @param id
     * @return
     */
    Optional<LoginAttempts> findByUserId(Long id);



    /**
     *
     * @return returns a list of all blocked user
     */
    List<LoginAttempts> getAllByBlockedTrue();

    /**
     *
     * @param user the user whose attempts are queried
     * @return the LoginAttempts of the user
     */
    LoginAttempts getByUser(User user);

    LoginAttempts getLoginAttemptsByUserId(Long id);

    LoginAttempts findByUser(User user);
}
