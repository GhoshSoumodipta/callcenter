package at.ac.tuwien.sepm.groupphase.backend.calling.mapper;

import at.ac.tuwien.sepm.groupphase.backend.calling.dto.GuestCallersDto;
import at.ac.tuwien.sepm.groupphase.backend.calling.entity.GuestCallers;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

@Component
public class GuestCallersMapper {
    public GuestCallers dtoToEntity(GuestCallersDto guestCallersDto){
        GuestCallers guestCallers = new GuestCallers();
        guestCallers.setFullname(guestCallersDto.getFullname());
        guestCallers.setDateOfCall(LocalDateTime.now());
        guestCallers.setMobileNo(guestCallersDto.getMobileNo());
        guestCallers.setStatus(1);
        guestCallers.setCompanyId(guestCallersDto.getCompanyId());
        return guestCallers;
    }

    public GuestCallersDto entityToDto(GuestCallers guestCallers){
        GuestCallersDto dto = new GuestCallersDto();
        //LocalDateTime ldt = guestCallers.getDateOfCall().ofInstant(in.toInstant(), ZoneId.systemDefault());
        dto.setId(guestCallers.getId());
        dto.setFullname(guestCallers.getFullname());
        //dto.setDateOfCall(guestCallers.getDateOfCall());
        dto.setDateOfCall(Date.valueOf(guestCallers.getDateOfCall().toLocalDate()));
        dto.setMobileNo(guestCallers.getMobileNo());
        dto.setStatus(guestCallers.getStatus());
        dto.setCompanyId(guestCallers.getCompanyId());
        dto.setRoomId(guestCallers.getRoomId());
        return dto;
    }
}
