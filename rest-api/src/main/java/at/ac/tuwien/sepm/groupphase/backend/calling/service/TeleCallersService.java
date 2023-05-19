package at.ac.tuwien.sepm.groupphase.backend.calling.service;

import at.ac.tuwien.sepm.groupphase.backend.calling.dto.ResponseDto;
import at.ac.tuwien.sepm.groupphase.backend.calling.dto.TelecallersDto;
import at.ac.tuwien.sepm.groupphase.backend.calling.entity.Telecallers;
import at.ac.tuwien.sepm.groupphase.backend.calling.exceptions.DataNotPresentException;
import at.ac.tuwien.sepm.groupphase.backend.calling.exceptions.DataSaveException;
import at.ac.tuwien.sepm.groupphase.backend.calling.mapper.TeleCallersMapper;
import at.ac.tuwien.sepm.groupphase.backend.repository.calling.TeleCallersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeleCallersService {

    private static final Logger logger = LoggerFactory.getLogger(TeleCallersService.class);

    @Autowired
    private TeleCallersRepository repository;

    @Autowired
    private TeleCallersMapper mapper;

    public ResponseDto saveTeleCaller(TelecallersDto dto){
        logger.info("Inside saveTeleCaller method of TeleCallersService........");
        ResponseDto responseDto = new ResponseDto();
        Telecallers entity = mapper.dtoToEntity(dto);
        repository.save(entity);
        if(entity.getId() != null){
            logger.info("Tele Caller saved successfully........");
            responseDto.setStatus("success");
            responseDto.setData(mapper.entityToDto(entity));
            responseDto.setMessage("Tele Caller saved successfully");
        }else{
            logger.error("Some error occurred while saving Tele Caller....");
            responseDto.setStatus("failed");
            responseDto.setData(null);
            responseDto.setMessage("Some error occurred while saving a Tele Caller");
        }
        return responseDto;
    }

    public ResponseDto getAllTeleCallers(){
        logger.info("Inside getAllTeleCallers method of TeleCallersService........");
        ResponseDto responseDto = new ResponseDto();
        List<TelecallersDto> dtoList = new ArrayList<>();
        List<Telecallers> telecallersList = repository.findAll();
        telecallersList.stream().forEach(caller -> {
            TelecallersDto dto = mapper.entityToDto(caller);
            dtoList.add(dto);
        });
        responseDto.setStatus("success");
        responseDto.setData(dtoList);
        responseDto.setMessage("Data Fetched Successfully");
        return responseDto;
    }

    public ResponseDto getTeleCallerById(Long id){
        logger.info("Inside getTeleCallerById method of TeleCallersService........");
        ResponseDto responseDto = new ResponseDto();
        TelecallersDto dto = null;
        Optional<Telecallers> optionalEntity = null;
        optionalEntity = repository.findById(id);
        if(optionalEntity.isPresent()){
            logger.info("TeleCaller exist for id : {}", id);
            dto = mapper.entityToDto(optionalEntity.get());
            responseDto.setStatus("success");
            responseDto.setData(dto);
            responseDto.setMessage("Tele caller fetched successfully");
        }else{
            logger.error("Tele caller not present for id : {}", id);
            responseDto.setStatus("failed");
            responseDto.setData(null);
            responseDto.setMessage("Tele caller not present for the given id");
        }
        return responseDto;
    }

    public ResponseDto updateTeleCaller(TelecallersDto dto, Long id){
        logger.info("Inside updateTeleCaller method of TeleCallersService........");
        ResponseDto responseDto = new ResponseDto();
        Integer updateStatus =repository.updateTelecallerById(id, dto.getFirstname(), dto.getLastname(), dto.getMobileNo(), dto.getEmailAddress(), LocalDateTime.now(), dto.getStatus());
        if(updateStatus > 0){
            logger.info("Telecaller data updated successfully.........");
            responseDto = getTeleCallerById(id);
        }else{
            logger.error("Some error occured while updating Telecaller data......");
            responseDto.setStatus("failed");
            responseDto.setData(null);
            responseDto.setMessage("Some error occurred while updating the Telecaller data");
        }
        return responseDto;
    }
}
