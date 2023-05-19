package at.ac.tuwien.sepm.groupphase.backend.calling.controller;

import at.ac.tuwien.sepm.groupphase.backend.calling.dto.CallsDto;
import at.ac.tuwien.sepm.groupphase.backend.calling.entity.Calls;
import at.ac.tuwien.sepm.groupphase.backend.calling.service.CallsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/calls")
public class CallsController {

    private static final Logger logger = LoggerFactory.getLogger(CallsController.class);

    @Autowired
    private CallsService callsService;

    @PostMapping("/savecall")
    public ResponseEntity<?> saveACall(@RequestBody CallsDto callsDto){
        logger.info("Inside saveACall method of Calls controller.....");
        return new ResponseEntity<>(callsService.saveCall(callsDto), HttpStatus.OK);
    }

    @GetMapping("/getallcalls")
    public ResponseEntity<?> getAllCalls(){
        logger.info("Inside getAllCalls method of Calls controller.....");
        return new ResponseEntity<>(callsService.getAllCalls(), HttpStatus.OK);
    }

    @GetMapping("/getallwaitingcalls/{type}")
    public ResponseEntity<?> getAllWaitingCalls(@PathVariable("type") String loungeType){
        logger.info("Inside getAllWaitingCalls method of Calls controller.....");
        return new ResponseEntity<>(callsService.getAllCallsByLoungeType(loungeType), HttpStatus.OK);
    }

    @GetMapping("/getcallbyid/{id}")
    public ResponseEntity<?> getCallByCallId(@PathVariable("id") Long id){
        logger.info("Inside getCallByCallId method of Calls controller.....");
        return new ResponseEntity<>(callsService.getCallById(id), HttpStatus.OK);
    }

    @PutMapping("/updatecall")
    public ResponseEntity<?> updateACall(@RequestBody CallsDto callsDto){
        logger.info("Inside updateACall method of Calls controller.....");
        return new ResponseEntity<>(callsService.updateACall(callsDto, callsDto.getId()), HttpStatus.OK);
    }

    /**
     *  anoter api required to start a call
     */
}
