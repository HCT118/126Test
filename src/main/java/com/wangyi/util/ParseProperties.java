package com.wangyi.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ParseProperties 
{
	private String filepath;
	private Properties property=new Properties();
	private Logger logger = LogManager.getLogger(ParseProperties.class);

	public ParseProperties(String filepath) 
	{
		this.filepath = filepath;
		loadFile(filepath);
	}

	private void loadFile(String filepath)
	{
		try {
			FileInputStream file = new FileInputStream(filepath);
			property.load(file);
			logger.info("加载    "+filepath+"成功");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.info("加载"+filepath+"失败");
			e.printStackTrace();
		}
		
	}
	
	public String getValue(String key)
	{
		return property.getProperty(key);
		
	}
	
	
	public static void main(String[] args)
	{
		File filepath = new File(System.getProperty("user.dir"), "data");
		ParseProperties property=new ParseProperties(filepath.getAbsolutePath()+"\\"+"shoye.properties");
		System.out.println(filepath);
		System.out.println(property.getValue("username"));
	}

}
