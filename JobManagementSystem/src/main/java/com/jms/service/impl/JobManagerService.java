package com.jms.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jms.config.Configuration;
import com.jms.jobs.Job;

@Service
public class JobManagerService {
	
	Logger log= LoggerFactory.getLogger(JobManagerService.class);
	
	@Autowired
	Configuration configuration;

	public Map<String,String> getAllJobStatus() {
		Map<String,Job> jobs= configuration.getJobs();
		Map<String,String> jobStatusMap = new HashMap<>();
		for(Map.Entry<String, Job> entry:jobs.entrySet()) {
			jobStatusMap.put(entry.getKey(), entry.getValue().getStatus());
			
		}
		return jobStatusMap;
	}

}
