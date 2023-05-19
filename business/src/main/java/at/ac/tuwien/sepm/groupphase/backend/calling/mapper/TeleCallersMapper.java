package at.ac.tuwien.sepm.groupphase.backend.calling.mapper;

import at.ac.tuwien.sepm.groupphase.backend.calling.dto.TelecallersDto;
import at.ac.tuwien.sepm.groupphase.backend.calling.entity.Telecallers;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDateTime;

@Component
public class TeleCallersMapper {

    public Telecallers dtoToEntity(TelecallersDto dto){
        Telecallers entity = new Telecallers();
        entity.setFirstname(dto.getFirstname());
        entity.setLastname(dto.getLastname());
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedOn(LocalDateTime.now());
        entity.setMobileNo(dto.getMobileNo());
        entity.setEmailAddress(dto.getEmailAddress());
        entity.setStatus(dto.getStatus());
        entity.setCompanyId(dto.getCompanyId());
        return entity;
    }

    public TelecallersDto entityToDto(Telecallers entity){
        TelecallersDto dto = new TelecallersDto();
        dto.setId(entity.getId());
        dto.setFirstname(entity.getFirstname());
        dto.setLastname(entity.getLastname());
        dto.setCreatedAt(Date.valueOf(entity.getCreatedAt().toLocalDate()));
        dto.setUpdatedOn(Date.valueOf(entity.getUpdatedOn().toLocalDate()));
        dto.setMobileNo(entity.getMobileNo());
        dto.setEmailAddress(entity.getEmailAddress());
        dto.setStatus(entity.getStatus());
        dto.setCompanyId(entity.getCompanyId());
        return dto;
    }
}
