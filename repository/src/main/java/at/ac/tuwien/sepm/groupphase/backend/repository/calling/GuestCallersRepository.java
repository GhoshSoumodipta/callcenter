package at.ac.tuwien.sepm.groupphase.backend.repository.calling;

import at.ac.tuwien.sepm.groupphase.backend.calling.entity.GuestCallers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GuestCallersRepository extends JpaRepository<GuestCallers, Long> {


}
