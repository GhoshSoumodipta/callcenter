package at.ac.tuwien.sepm.groupphase.backend.calling.service;



import at.ac.tuwien.sepm.groupphase.backend.calling.dto.CompanyMasterDto;
import at.ac.tuwien.sepm.groupphase.backend.calling.dto.ResponseDto;
import at.ac.tuwien.sepm.groupphase.backend.calling.entity.CompanyMaster;
import at.ac.tuwien.sepm.groupphase.backend.calling.exceptions.DataSaveException;
import at.ac.tuwien.sepm.groupphase.backend.calling.mapper.CompanyMasterMapper;
import at.ac.tuwien.sepm.groupphase.backend.repository.calling.CompanyMaterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyMasterService {

    public static final Logger logger = LoggerFactory.getLogger(CompanyMasterService.class);

    @Autowired
    private CompanyMasterMapper mapper;

    @Autowired
    private CompanyMaterRepository repository;

    public ResponseDto saveCompanyMaster(CompanyMasterDto dto){
        logger.info("Inside saveCompanyMaster method of CompanyMasterService...........");
        ResponseDto responseDto = new ResponseDto();
        CompanyMaster entity = mapper.dtoToEntity(dto);
        repository.save(entity);
        if(entity.getId() != null){
            logger.info("CompanyMaster saved successfully............");
            responseDto.setStatus("success");
            responseDto.setData(mapper.entityToDto(entity));
            responseDto.setMessage("CompanyMaster saved successfully");
        }else{
            logger.error("Some error occurred while saving a CompanyMaster......");
            responseDto.setStatus("failed");
            responseDto.setData(null);
            responseDto.setMessage("Some error occurred while saving a CompanyMaster");
        }
        return responseDto;
    }

    public ResponseDto getAllCalls(){
        logger.info("Inside getAllCalls method of CompanyMasterService...........");
        ResponseDto responseDto = new ResponseDto();
        List<CompanyMasterDto> dtoList = new ArrayList<>();
        List<CompanyMaster> callsList = repository.findAll();
        callsList.stream().forEach(call -> {
            CompanyMasterDto dto = new CompanyMasterDto();
            dto = mapper.entityToDto(call);
            dtoList.add(dto);
        });
        responseDto.setStatus("success");
        responseDto.setData(dtoList);
        responseDto.setMessage("Data Fetched Successfully");
        return responseDto;
    }
}
