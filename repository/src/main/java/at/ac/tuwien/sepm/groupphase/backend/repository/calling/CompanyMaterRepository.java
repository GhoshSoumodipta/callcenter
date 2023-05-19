package at.ac.tuwien.sepm.groupphase.backend.repository.calling;

import at.ac.tuwien.sepm.groupphase.backend.calling.entity.CompanyMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyMaterRepository extends JpaRepository<CompanyMaster, Long> {

}
