package com.jms.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class JMSUtils {

	
	public static File[] getListofFile(File dir) {
		File[] files = dir.listFiles(new FilenameFilter()
		{
		  public boolean accept(File dir, String name)
		  {
		     return name.endsWith(".json");
		  }
		});
		return files;
	}
	
	 /**
     * Apache commons IO FileUtils provides easy way to copy files in Java
     * It internally uses File
     * @param from
     * @param string
     * @throws IOException
     */
    public static void copyFileUsingApache(File from, File to) throws IOException{
        FileUtils.copyFile(from, to);
    }
}
