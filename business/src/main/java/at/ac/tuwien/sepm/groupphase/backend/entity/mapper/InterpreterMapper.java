package at.ac.tuwien.sepm.groupphase.backend.entity.mapper;

import org.mapstruct.Mapper;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.contacts.interpreter.InterpreterDTO;
import at.ac.tuwien.sepm.groupphase.backend.entity.user.contacts.interpreter.Interpreter;

@Mapper(componentModel = "spring")
public interface InterpreterMapper extends MapperBase<Interpreter, InterpreterDTO> {

    @Override
    Interpreter DTOToEntity(InterpreterDTO dto);


    @Override
	InterpreterDTO entityToDTO(Interpreter entity);
}
