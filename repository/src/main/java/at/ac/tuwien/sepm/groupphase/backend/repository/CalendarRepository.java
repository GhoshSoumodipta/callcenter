package at.ac.tuwien.sepm.groupphase.backend.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import at.ac.tuwien.sepm.groupphase.backend.entity.CalendarEntity;

@Repository
public interface CalendarRepository extends JpaRepository<CalendarEntity, Long> {


	 Page<CalendarEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);


	 CalendarEntity findByName(String name);


	    void deleteById(Long id);

	    List<CalendarEntity> findAllByName(String name);
}
