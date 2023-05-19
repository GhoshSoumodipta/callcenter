package at.ac.tuwien.sepm.groupphase.backend.calling.controller;

import at.ac.tuwien.sepm.groupphase.backend.calling.dto.GuestCallersDto;
import at.ac.tuwien.sepm.groupphase.backend.calling.dto.TelecallersDto;
import at.ac.tuwien.sepm.groupphase.backend.calling.entity.Telecallers;
import at.ac.tuwien.sepm.groupphase.backend.calling.service.GuestCallersService;
import at.ac.tuwien.sepm.groupphase.backend.calling.service.TeleCallersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/caller")
public class CallerController {

    private static final Logger logger = LoggerFactory.getLogger(CallerController.class);

    @Autowired
    private GuestCallersService guestCallersService;

    @Autowired
    private TeleCallersService teleCallersService;

    @PostMapping("/addguestcaller")
    public ResponseEntity<?> addGuestCaller(@RequestBody GuestCallersDto guestCallersDto){
        logger.info("Inside addGuestCaller method of Caller controller.........");
        return new ResponseEntity<>(guestCallersService.saveGuestCaller(guestCallersDto), HttpStatus.OK);
    }

    @PostMapping("/addtelecaller")
    public ResponseEntity<?> addTeleCaller(@RequestBody TelecallersDto telecallersDto){
        logger.info("Inside addTeleCaller method of Caller controller.........");
        return new ResponseEntity<>(teleCallersService.saveTeleCaller(telecallersDto), HttpStatus.OK);
    }

    @PostMapping("/updatetelecaller")
    public ResponseEntity<?> updateTeleCaller(@RequestBody TelecallersDto telecallersDto){
        logger.info("Inside updateTeleCaller method of Caller controller.....");
        return new ResponseEntity<>(teleCallersService.updateTeleCaller(telecallersDto, telecallersDto.getId()), HttpStatus.OK);
    }

    @GetMapping("/getalltelecallers")
    public ResponseEntity<?> getAllTeleCallers(){
        logger.info("Inside getAllTeleCallers method of Caller controller.....");
        return new ResponseEntity<>(teleCallersService.getAllTeleCallers(), HttpStatus.OK);
    }

    @GetMapping("/allguestcallers")
    public ResponseEntity<?> getAllGuestCallers(){
        logger.info("Inside getAllGuestCallers method of Caller controller.....");
        return new ResponseEntity<>(guestCallersService.getAllGuestCallers(), HttpStatus.OK);
    }

    @GetMapping("/gettelecallerbyid/{id}")
    public ResponseEntity<?> getTelecallerById(@PathVariable("id") Long id){
        logger.info("Inside getTelecallerById method of Caller controller.....");
        return new ResponseEntity<>(teleCallersService.getTeleCallerById(id), HttpStatus.OK);
    }

}
