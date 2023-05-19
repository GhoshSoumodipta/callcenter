package at.ac.tuwien.sepm.groupphase.backend.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import at.ac.tuwien.sepm.groupphase.backend.entity.File;

@Repository
public interface FilesRepository extends JpaRepository<File,Long> {

	Page<File> findByNameContainingIgnoreCase(String name, Pageable pageable);


	 File findByName(String name);


	    void deleteById(Long id);

	    List<File> findAllByName(String name);
}
