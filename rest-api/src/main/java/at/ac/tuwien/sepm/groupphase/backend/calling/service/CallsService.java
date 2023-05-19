package at.ac.tuwien.sepm.groupphase.backend.calling.service;

import at.ac.tuwien.sepm.groupphase.backend.calling.constant.WaitingLoungeConstants;
import at.ac.tuwien.sepm.groupphase.backend.calling.dto.CallsDto;
import at.ac.tuwien.sepm.groupphase.backend.calling.dto.ResponseDto;
import at.ac.tuwien.sepm.groupphase.backend.calling.entity.Calls;
import at.ac.tuwien.sepm.groupphase.backend.calling.exceptions.DataNotPresentException;
import at.ac.tuwien.sepm.groupphase.backend.calling.exceptions.DataSaveException;
import at.ac.tuwien.sepm.groupphase.backend.calling.mapper.CallsMapper;
import at.ac.tuwien.sepm.groupphase.backend.repository.calling.CallsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CallsService {

    private static final Logger logger = LoggerFactory.getLogger(CallsService.class);

    @Autowired
    private CallsRepository repository;

    @Autowired
    private CallsMapper mapper;

    public ResponseDto saveCall(CallsDto dto){
        logger.info("Inside saveCall method of CallsService...........");
        ResponseDto responseDto = new ResponseDto();
        Calls entity = mapper.dtoToEntity(dto);
        repository.save(entity);
        if(entity.getId() != null){
            logger.info("Call saved successfully............");
            responseDto.setStatus("success");
            responseDto.setData(mapper.entityToDto(entity));
            responseDto.setMessage("Call saved successfully");
        }else{
            logger.error("Some error occurred while saving a call......");
            responseDto.setStatus("success");
            responseDto.setData(null);
            responseDto.setMessage("Some error occurred while saving a call");
        }
        return responseDto;
    }

    public ResponseDto getAllCalls(){
        logger.info("Inside getAllCalls method of CallsService...........");
        ResponseDto responseDto = new ResponseDto();
        List<CallsDto> dtoList = new ArrayList<>();
        List<Calls> callsList = repository.findAll();
        callsList.stream().forEach(call -> {
            CallsDto dto = new CallsDto();
            dto = mapper.entityToDto(call);
            dtoList.add(dto);
        });
        responseDto.setStatus("success");
        responseDto.setData(dtoList);
        responseDto.setMessage("Data Fetched Successfully");
        return responseDto;
    }

    public ResponseDto getAllCallsByLoungeType(String LoungeType){
        logger.info("Inside getAllCallsByLoungeType method of CallsService...........");
        ResponseDto responseDto = new ResponseDto();
        List<CallsDto> dtoList = new ArrayList<>();
        List<Calls> callsList = repository.findByLoungeType(LoungeType);
        callsList.stream().forEach(call -> {
            CallsDto dto = new CallsDto();
            dto = mapper.entityToDto(call);
            dtoList.add(dto);
        });
        responseDto.setStatus("success");
        responseDto.setData(dtoList);
        responseDto.setMessage("Data Fetched Successfully");
        return responseDto;
    }

    public ResponseDto getCallById(Long id){
        logger.info("Inside getCallById method of CallsService...........");
        ResponseDto responseDto = new ResponseDto();
        CallsDto dto = null;
        Optional<Calls> optionalCall = repository.findById(id);
        if(optionalCall.isPresent()){
            logger.info("Call data present for id : {}", id);
            dto = mapper.entityToDto(optionalCall.get());
            responseDto.setStatus("success");
            responseDto.setData(dto);
            responseDto.setMessage("Call data Fetched Successfully");
        }else{
            logger.error("Call data does not exist for the given id : {}", id);
            responseDto.setStatus("failed");
            responseDto.setData(null);
            responseDto.setMessage("Call data does not exist for the requested id");
        }
        return responseDto;
    }

    public ResponseDto updateACall(CallsDto dto, Long id){
        logger.info("Inside getCallById method of CallsService...........");
        ResponseDto responseDto = new ResponseDto();
        Integer updateStatus = 0;
        if(dto.getLoungeType().equalsIgnoreCase(WaitingLoungeConstants.RUNNING)) {
            updateStatus  = repository.updateACallByIdToRunning(id, LocalDateTime.now(), dto.getCalledBy(), dto.getAttendedBy(), dto.getCallerId(), dto.getLoungeType());
        }
        if(dto.getLoungeType().equalsIgnoreCase(WaitingLoungeConstants.ENDED)) {
            updateStatus = repository.updateACallByIdToEnded(id, LocalDateTime.now(), dto.getCalledBy(), dto.getAttendedBy(), dto.getCallerId(), dto.getLoungeType());
        }
        if (updateStatus > 0){
            logger.info("Call data Updated successfully............");
            responseDto = getCallById(id);
        }else{
            logger.error("Some error occurred while updating the call............");
            responseDto.setStatus("failed");
            responseDto.setData(null);
            responseDto.setMessage("Some error occurred while updating the call");
        }
        return responseDto;
    }
}
