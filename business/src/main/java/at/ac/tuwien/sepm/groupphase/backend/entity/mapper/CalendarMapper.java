package at.ac.tuwien.sepm.groupphase.backend.entity.mapper;

import org.mapstruct.Mapper;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.CalendarDTO;
import at.ac.tuwien.sepm.groupphase.backend.entity.CalendarEntity;

@Mapper(componentModel = "spring")
public interface CalendarMapper extends MapperBase<CalendarEntity,CalendarDTO> {



    @Override
    CalendarEntity DTOToEntity(CalendarDTO dto);


    @Override
    CalendarDTO entityToDTO(CalendarEntity entity);
}
