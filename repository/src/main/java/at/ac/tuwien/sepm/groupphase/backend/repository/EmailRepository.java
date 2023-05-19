package at.ac.tuwien.sepm.groupphase.backend.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import at.ac.tuwien.sepm.groupphase.backend.entity.EmailsEntity;
import at.ac.tuwien.sepm.groupphase.backend.entity.EmailsEntity;

@Repository
public interface EmailRepository extends JpaRepository<EmailsEntity,Long> {


	Page<EmailsEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);


	 EmailsEntity findByName(String name);


	    void deleteById(Long id);

	    List<EmailsEntity> findAllByName(String name);

}
