package com.jms.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jms.exceptions.ConfigurationException;
import com.jms.service.impl.ScheduleTaskService;

@Service
public class ConfigurationLoader {

	Logger log= LoggerFactory.getLogger(ConfigurationLoader.class);
	
	@Autowired
	ConfigurationFactory configFactory;
	

	
	@Value("${configuration.directory}")
	private String confDir;
	
	public void loadConfiguration() {
		try {
			configFactory.loadJobs(getConfFolderPath());
		} catch (ConfigurationException e) {
			log.error("Exception Occured"+e.getErrorCode()+" "+ e.getMessage());
			log.error(e.getMessage(), e);
		}
	}

	private String getConfFolderPath() throws ConfigurationException {
		if(null == confDir) {
			throw new ConfigurationException("Either configuration directory is not specified or null", "1000");
		}
		return confDir;
	}
}
