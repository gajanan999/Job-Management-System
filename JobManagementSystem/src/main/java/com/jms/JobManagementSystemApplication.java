package com.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jms.config.ConfigurationFactory;
import com.jms.config.ConfigurationLoader;
import com.jms.service.impl.ScheduleTaskService;

@SpringBootApplication
public class JobManagementSystemApplication implements CommandLineRunner{

	@Autowired
	ConfigurationLoader loder;
	
	@Autowired
	ScheduleTaskService scheduleTaskService;
	
	
	public static void main(String[] args) {
		SpringApplication.run(JobManagementSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		loder.loadConfiguration();
		scheduleTaskService.scheduleAllJobs();
	}

}
