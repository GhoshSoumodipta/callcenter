package at.ac.tuwien.sepm.groupphase.backend.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.interpreter.InterpreterDTO;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.interpreter.Interpreter;
import at.ac.tuwien.sepm.groupphase.backend.entity.mapper.InterpreterMapper;
import at.ac.tuwien.sepm.groupphase.backend.repository.contacts.UserRepositoryBase;

@Repository
public interface InterpreterRepository extends UserRepositoryBase<Interpreter, InterpreterMapper, InterpreterDTO> {


	Page<Interpreter> findByFirstNameContainingIgnoreCase(String name, Pageable pageable);


	 Interpreter findByfirstName(String name);


	 void deleteById(Long id);

	 List<Interpreter> findAllByFirstName(String name);

}
