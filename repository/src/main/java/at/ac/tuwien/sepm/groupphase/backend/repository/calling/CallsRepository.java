package at.ac.tuwien.sepm.groupphase.backend.repository.calling;

import at.ac.tuwien.sepm.groupphase.backend.calling.entity.Calls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

public interface CallsRepository  extends JpaRepository<Calls, Long> {

    List<Calls> findByLoungeType(String loungeType);

    @Modifying
    @Transactional
    @Query(value = "update calls set call_started = :callStarted, attended_by = :attendedBy, called_by = :calledBy, caller_id = :callerId, lounge_type = :loungeType where id = :id", nativeQuery = true)
    Integer updateACallByIdToRunning(@Param("id") Long id,@Param("callStarted") LocalDateTime callStarted,@Param("attendedBy") String attendedBy,@Param("calledBy") String calledBy,@Param("callerId") String callerId,@Param("loungeType") String loungeType);

    @Modifying
    @Transactional
    @Query(value = "update calls set call_ended = :callEnded, attended_by = :attendedBy, called_by = :calledBy, caller_id = :callerId, lounge_type = :loungeType where id = :id", nativeQuery = true)
    Integer updateACallByIdToEnded(@Param("id") Long id,@Param("callEnded") LocalDateTime callEnded,@Param("attendedBy") String attendedBy,@Param("calledBy") String calledBy,@Param("callerId") String callerId,@Param("loungeType") String loungeType);


}
