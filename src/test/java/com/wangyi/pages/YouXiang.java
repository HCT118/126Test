package com.wangyi.pages;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.wangyi.Driver.WangYiDriver;
import com.wangyi.util.ParseProperties;

public class YouXiang extends BasePage
{
	private Logger logger=LogManager.getLogger(HomePage.class);
	private File filepath = new File(System.getProperty("user.dir"), "data");
	private ParseProperties property=new ParseProperties(filepath.getAbsolutePath()+"\\"+"youxiang.properties");

	public YouXiang(WangYiDriver driver) 
	{
		super(driver);
		logger.info("初始化YouXiang类成功！");
	}
	/**
	 * 
	
	* @Title: getUserName 
	
	* @Description: TODO(进入邮箱界面后获得用户名) 
	* @param @param by
	* @param @return    设定文件 
	
	* @return String    返回类型 
	
	* @throws
	 */
	public String getUserName()
	{
		String value =driver.getText(By.xpath(property.getValue("username")));
		return value;
	}

}
