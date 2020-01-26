package com.jms;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import com.jms.utils.JMSUtils;


@SpringBootTest
class JobManagementSystemApplicationTests {

	Logger log= LoggerFactory.getLogger(JobManagementSystemApplicationTests.class);
	@Test
	void contextLoads() {
	}
	
	@Test
	void copyFiles() {
		log.info("Running a test for JMSUtils.copyFileUsingApache()");
		try {
			File f=new File("test.txt");
			if(!f.exists())
				f.createNewFile();
			
			String fileContent = "Hello this is test file";
		     
		    BufferedWriter writer = new BufferedWriter(new FileWriter(f));
		    writer.write(fileContent);
		    writer.close();
		    
			File output = new File("output");
			boolean bool=false;
			if(!output.exists())
				output.mkdir();
			
			if(bool) {
				JMSUtils.copyFileUsingApache(f, new File(output.getAbsoluteFile()+"/"+f.getName()));
			}else {
				Assert.assertTrue(false);
			}
			
			File files[]= output.listFiles();
			for(File file:files) {
				if(file.getName().equals(f.getName()))
					 Assert.assertTrue(true);
			}
			
		}
		catch(Exception e) {
			Assert.assertFalse(true);
		}
		log.info("Running a test for JMSUtils.copyFileUsingApache() is Successfull");
	}
}
