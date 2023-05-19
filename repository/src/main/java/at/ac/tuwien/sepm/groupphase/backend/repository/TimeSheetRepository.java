package at.ac.tuwien.sepm.groupphase.backend.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import at.ac.tuwien.sepm.groupphase.backend.entity.TimeSheetEntity;


@Repository
public interface TimeSheetRepository extends JpaRepository<TimeSheetEntity, Long> {


	 Page<TimeSheetEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);

	 TimeSheetEntity findByName(String name);


	    void deleteById(Long id);

	    List<TimeSheetEntity> findAllByName(String name);

}
