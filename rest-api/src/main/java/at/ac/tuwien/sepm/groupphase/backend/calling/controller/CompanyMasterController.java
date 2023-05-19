package at.ac.tuwien.sepm.groupphase.backend.calling.controller;

import at.ac.tuwien.sepm.groupphase.backend.calling.dto.CompanyMasterDto;
import at.ac.tuwien.sepm.groupphase.backend.calling.service.CompanyMasterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/companymaster")
public class CompanyMasterController {

    public static final Logger logger = LoggerFactory.getLogger(CompanyMasterController.class);
    @Autowired
    private CompanyMasterService service;

    @PostMapping("/savecompanymaster")
    public ResponseEntity<?> saveCompanyMaster(@RequestBody CompanyMasterDto dto){
        logger.info("Inside saveCompanyMaster method of CompanyMasterController.....");
        return new ResponseEntity<>(service.saveCompanyMaster(dto), HttpStatus.OK);
    }

    @GetMapping("/allcomapnymasters")
    public ResponseEntity<?> getAllCompanyMaster(){
        logger.info("Inside getAllCompanyMaster method of CompanyMasterController.....");
        return new ResponseEntity<>(service.getAllCalls(), HttpStatus.OK);
    }
}
