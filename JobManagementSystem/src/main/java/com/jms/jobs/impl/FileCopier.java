package com.jms.jobs.impl;

import java.io.File;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jms.jobs.Job;
import com.jms.utils.JMSUtils;

public class FileCopier extends Job{

	Logger log= LoggerFactory.getLogger(FileCopier.class);
	private String inputDirectory;
	private String outputDirectory;
	
	public void run() {
		log.info("File Copier is running");
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
		}
		catch(IOException e) {
			log.error(e.getMessage(),e);
		}
		log.info("Files copied from "+ this.inputDirectory +" to "+  this.outputDirectory +" done");
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
