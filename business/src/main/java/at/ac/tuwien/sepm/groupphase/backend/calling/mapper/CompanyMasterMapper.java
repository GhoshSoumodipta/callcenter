package at.ac.tuwien.sepm.groupphase.backend.calling.mapper;

import at.ac.tuwien.sepm.groupphase.backend.calling.dto.CompanyMasterDto;
import at.ac.tuwien.sepm.groupphase.backend.calling.entity.CompanyMaster;
import org.springframework.stereotype.Component;

@Component
public class CompanyMasterMapper {

    public CompanyMaster dtoToEntity(CompanyMasterDto dto){
        CompanyMaster entity = new CompanyMaster();
        entity.setCompanyName(dto.getCompanyName());
        entity.setEmail(dto.getEmail());
        entity.setAddress(dto.getAddress());
        entity.setContactNo(dto.getContactNo());
        entity.setContactPerson(dto.getContactPerson());
        entity.setStatus(dto.getStatus());
        return entity;
    }

    public CompanyMasterDto entityToDto(CompanyMaster entity){
        CompanyMasterDto dto = new CompanyMasterDto();
        dto.setId(entity.getId());
        dto.setCompanyName(entity.getCompanyName());
        dto.setEmail(entity.getEmail());
        dto.setAddress(entity.getAddress());
        dto.setContactNo(entity.getContactNo());
        dto.setContactPerson(entity.getContactPerson());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
