package at.ac.tuwien.sepm.groupphase.backend.calling.service;

import at.ac.tuwien.sepm.groupphase.backend.calling.dto.ResponseDto;
import at.ac.tuwien.sepm.groupphase.backend.repository.calling.GuestCallersRepository;
import at.ac.tuwien.sepm.groupphase.backend.calling.dto.GuestCallersDto;
import at.ac.tuwien.sepm.groupphase.backend.calling.entity.GuestCallers;
import at.ac.tuwien.sepm.groupphase.backend.calling.exceptions.DataSaveException;
import at.ac.tuwien.sepm.groupphase.backend.calling.mapper.GuestCallersMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GuestCallersService {

    private static final Logger logger = LoggerFactory.getLogger(GuestCallersService.class);

    @Autowired
    private GuestCallersRepository guestCallersRepository;

    @Autowired(required = false)
    private GuestCallersMapper guestCallersMapper;

/*    @Autowired
    private ResponseDto responseDto;*/

    public ResponseDto saveGuestCaller(GuestCallersDto guestCallersDto){
        logger.info("Inside service class and saveGuestCaller............ ");
        ResponseDto responseDto = new ResponseDto();
        Map<String, String> response = new HashMap<>();
        GuestCallers guestCallers = null;
        guestCallers = guestCallersMapper.dtoToEntity(guestCallersDto);
        Random r = new Random(System.currentTimeMillis());
        guestCallers.setRoomId(r.nextLong(100000000));
        guestCallersRepository.save(guestCallers);
        if(guestCallers.getId() != null) {
            logger.info("Guest Caller saved successfully............");
            responseDto.setStatus("success");
            responseDto.setData(guestCallersMapper.entityToDto(guestCallers));
            responseDto.setMessage("Guest Caller saved successfully");
        }else{
            logger.error("Some error occurred while saving guest callers..........");
            responseDto.setStatus("failed");
            responseDto.setData(null);
            responseDto.setMessage("Some error occurred while saving guest callers");
        }
        return responseDto;
    }

    public ResponseDto getAllGuestCallers(){
        logger.info("Inside getAllGuestCallers method of GuestCallerService...... ");
        ResponseDto responseDto = new ResponseDto();
        List<GuestCallersDto> dtoList = new ArrayList<>();
        List<GuestCallers> guestCallers = guestCallersRepository.findAll();
        guestCallers.stream().forEach(callers -> {
            GuestCallersDto dto = guestCallersMapper.entityToDto(callers);
            dtoList.add(dto);
        });
        responseDto.setStatus("success");
        responseDto.setData(dtoList);
        responseDto.setMessage("Data Fetched Successfully");
        return responseDto;
    }
}
