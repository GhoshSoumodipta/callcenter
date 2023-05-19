package at.ac.tuwien.sepm.groupphase.backend.calling.mapper;

import at.ac.tuwien.sepm.groupphase.backend.calling.dto.CallsDto;
import at.ac.tuwien.sepm.groupphase.backend.calling.entity.Calls;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDateTime;

@Component
public class CallsMapper {

    public Calls dtoToEntity(CallsDto dto){
        Calls call = new Calls();
        call.setCallStarted(LocalDateTime.now());
        call.setCallEnded(LocalDateTime.now());
        call.setDateOfCall(LocalDateTime.now());
        call.setCalledBy(dto.getCalledBy());
        call.setAttendedBy(dto.getAttendedBy());
        call.setCallerId(dto.getCallerId());
        call.setLoungeType(dto.getLoungeType());
        call.setRoomId(dto.getRoomId());
        return call;
    }

    public CallsDto entityToDto(Calls entity){
        CallsDto dto = new CallsDto();
        dto.setId(entity.getId());
        dto.setCalledBy(entity.getCalledBy());
        dto.setCallStarted(Date.valueOf(entity.getCallStarted().toLocalDate()));
        dto.setCallEnded(Date.valueOf(entity.getCallEnded().toLocalDate()));
        dto.setAttendedBy(entity.getAttendedBy());
        dto.setCallerId(entity.getCallerId());
        dto.setDateOfCall(Date.valueOf(entity.getDateOfCall().toLocalDate()));
        dto.setLoungeType(entity.getLoungeType());
        dto.setRoomId(entity.getRoomId());
        return dto;
    }
}
