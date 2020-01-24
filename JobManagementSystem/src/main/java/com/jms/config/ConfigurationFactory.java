package com.jms.config;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.jms.exceptions.ConfigurationException;
import com.jms.model.Job;

@Service
public class ConfigurationFactory {
	
	Logger log= LoggerFactory.getLogger(ConfigurationFactory.class);
	
	@Autowired
	Configuration configuration;
	

	public void loadJobs(String confDir) throws ConfigurationException {
		// TODO Auto-generated method stub
		String currentDirectory = System.getProperty("user.dir");
		log.info("The current working directory is " + currentDirectory);
		File dir = new File(currentDirectory.concat(confDir).concat("\\jobs"));
		ObjectMapper mapper = new ObjectMapper();
		for(File file:getListofFile(dir)) {
			 try {
				 
				 final ObjectNode node = new ObjectMapper().readValue(file, ObjectNode.class);
				 if (node.has("className") && "" != node.get("className").asText()) {
					    Class cls = Class.forName(node.get("className").asText());
					    Object obj = mapper.readValue(file, cls);
			            if(obj instanceof Job) {
			            	Job job= (Job) obj;
			            	configuration.getJobs().put(job.getName(), job);
			            }else {
			            	throw new Exception("Json file is not compatible with Job class");
			            }
					}  
		            
		        } catch (Exception e) {
		           log.error("Configuration failed for file { "+ file.getName()+" }");
		           log.error(e.getMessage(),e);
		        }  
		}
		
		for(Map.Entry<String, Job> entry: configuration.getJobs().entrySet()) {
			Job newJob= entry.getValue();
			log.info("Configuration successful for job { "+ entry.getKey()+" }");
		}
	}

	
	public File[] getListofFile(File dir) {
		File[] files = dir.listFiles(new FilenameFilter()
		{
		  public boolean accept(File dir, String name)
		  {
		     return name.endsWith(".json");
		  }
		});
		return files;
	}
}
