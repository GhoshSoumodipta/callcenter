package at.ac.tuwien.sepm.groupphase.backend.entity.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import at.ac.tuwien.sepm.groupphase.backend.endpoint.dto.TimeSheetDTO;
import at.ac.tuwien.sepm.groupphase.backend.entity.TimeSheetEntity;

@Mapper(componentModel = "spring")
public interface TimeSheetMapper extends MapperBase<TimeSheetEntity, TimeSheetDTO> {
	@Override
    TimeSheetDTO entityToDTO(TimeSheetEntity entity);

    @Override
    TimeSheetEntity DTOToEntity(TimeSheetDTO dto);
}
