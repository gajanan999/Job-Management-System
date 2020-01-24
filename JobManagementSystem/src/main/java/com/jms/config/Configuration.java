package com.jms.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jms.model.Job;

@Component
public class Configuration {

	Map<String,Job> jobs=new HashMap<>();

	public Map<String, Job> getJobs() {
		return jobs;
	}

	public void setJobs(Map<String, Job> jobs) {
		this.jobs = jobs;
	}
	
	
}
