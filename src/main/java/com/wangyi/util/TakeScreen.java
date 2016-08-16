package com.wangyi.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TakeScreen 
{
    private WebDriver driver;
    private Logger logger = LogManager.getLogger(TakeScreen.class);
	public TakeScreen(WebDriver driver) 
	{
		this.driver = driver;
	}
	public void takeScreen()
	{
		String screenname=String.valueOf(DateTimeUtil.getDateTime()+".jpg");
		File pic=new File(System.getProperty("user.dir"));
		File picpath=new File(pic,"pic");
		String screenpath = picpath.getAbsolutePath()+"/"+screenname;
		TakeScreenShot(screenpath);
	}
	public void TakeScreenShot(String screenpath) 
	{
		try 
		{
			File file=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(file, new File(screenpath));
			logger.info("截图成功");
		} catch (IOException e) {
			logger.error("截图失败"+e);
		}
		
	}
   
}
