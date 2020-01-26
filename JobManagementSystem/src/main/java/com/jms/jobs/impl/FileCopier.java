package com.jms.jobs.impl;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jms.constants.Constants;
import com.jms.jobs.Job;
import com.jms.utils.JMSUtils;

public class FileCopier extends Job{

	Logger log= LoggerFactory.getLogger(FileCopier.class);
	private String inputDirectory;
	private String outputDirectory;
	
	public void run() {
		this.setStatus(Constants.RUNNING);
		log.info("File Copier is started at", LocalDateTime.now());
		log.info(this.inputDirectory);
		log.info(this.outputDirectory);
		try {
			File[] files= JMSUtils.getListofFile(new File(this.inputDirectory));
			
			for(File file:files) {
				File f=new File( this.outputDirectory.concat("/").concat(file.getName()));
				if(!f.exists())
					f.createNewFile();
				JMSUtils.copyFileUsingApache(file.getAbsoluteFile(),f);
			}
			int a= 9/0;
			Thread.sleep(10000);
			log.info("Files copied from "+ this.inputDirectory +" to "+  this.outputDirectory +" done");
			log.info("File Copier is ended at", LocalDateTime.now());
			this.setStatus(Constants.SUCCESS);
		}
		catch(IOException | InterruptedException e) {
			this.setStatus(Constants.FAILED);
			log.error(e.getMessage(),e);
		}catch(Exception e) {
			this.setStatus(Constants.FAILED);
			log.error(e.getMessage(),e);
		}
	}

	public String getInputDirectory() {
		return inputDirectory;
	}

	public void setInputDirectory(String inputDirectory) {
		this.inputDirectory = inputDirectory;
	}

	public String getOutputDirectory() {
		return outputDirectory;
	}

	public void setOutputDirectory(String outputDirectory) {
		this.outputDirectory = outputDirectory;
	}
	
}
