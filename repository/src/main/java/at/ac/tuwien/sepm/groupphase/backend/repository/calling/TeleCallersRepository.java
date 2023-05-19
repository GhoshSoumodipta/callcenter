package at.ac.tuwien.sepm.groupphase.backend.repository.calling;

import at.ac.tuwien.sepm.groupphase.backend.calling.entity.Telecallers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

public interface TeleCallersRepository extends JpaRepository<Telecallers, Long> {

    Optional<Telecallers> findById(Long id);

    @Modifying
    @Transactional
    @Query(value = "update telecallers set firstname = :firstname,lastname = :lastname,mobile_no = :mobileNo,email_address  = :emailAddress,updated_on = :updatedOn,status = :status where id = :id", nativeQuery = true)
    Integer updateTelecallerById(@Param("id") Long id,@Param("firstname") String firstname,@Param("lastname") String lastname,@Param("mobileNo") String mobileNo,@Param("emailAddress") String emailAddress,@Param("updatedOn") LocalDateTime updatedOn,@Param("status") Integer status);
}
