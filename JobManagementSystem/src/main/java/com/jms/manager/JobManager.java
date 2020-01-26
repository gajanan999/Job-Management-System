package com.jms.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jms.service.impl.JobManagerService;

@RestController
public class JobManager {
	
	Logger log= LoggerFactory.getLogger(JobManager.class);
	
	@Autowired
	JobManagerService jobManagerService;


    @GetMapping("/getAllJobStatus")
    public ResponseEntity isValidUserCredentials() {
        try {
            return new ResponseEntity<>(jobManagerService.getAllJobStatus(), HttpStatus.OK);
        } catch (Exception ex) {
        	log.error("Invalid inputs to login", ex);
            return new ResponseEntity<>("FAILURE", HttpStatus.INTERNAL_SERVER_ERROR);
        } 
    }

}
