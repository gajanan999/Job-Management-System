package com.jms.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileCopier extends Job{

	Logger log= LoggerFactory.getLogger(FileCopier.class);
	private String startDirectory;

	public String getStartDirectory() {
		return startDirectory;
	}

	public void setStartDirectory(String startDirectory) {
		this.startDirectory = startDirectory;
	}
	
	public void run() {
		log.info("File Copier is running");
	}
}
