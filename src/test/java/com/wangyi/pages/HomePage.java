package com.wangyi.pages;


import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.wangyi.Driver.WangYiDriver;
import com.wangyi.util.ParseProperties;

public class HomePage extends BasePage
{
	private Logger logger=LogManager.getLogger(HomePage.class);
	private File filepath = new File(System.getProperty("user.dir"), "data");
	private ParseProperties property=new ParseProperties(filepath.getAbsolutePath()+"\\"+"shoye.properties");
	public HomePage(WangYiDriver driver) {
		super(driver);
		logger.info("初始化HomePage类成功！");
	}
	/**
	 * 
	
	* @Title: sendUserName 
	
	* @Description: TODO(126邮箱首页，输入用户名和密码) 
	
	* @param @param by
	* @param @param value    设定文件 
	
	* @return void    返回类型 
	
	* @throws
	 */

	public void sendUserName(String username)
	{
		driver.clear(By.xpath(property.getValue("username")));
		driver.sendKeys(By.xpath(property.getValue("username")),username);	
	}
	/**
	 * 
	
	* @Title: sendPassWord 
	
	* @Description: TODO(126邮箱首页，输入密码) 
	
	* @param @param by
	* @param @param value    设定文件 
	
	* @return void    返回类型 
	
	* @throws
	 */

	public void sendPassWord(String password)
	{
		driver.clear(By.xpath(property.getValue("password")));
		driver.sendKeys(By.xpath(property.getValue("password")),password);	
	}
	/**
	 * 
	
	* @Title: goYouXiang 
	
	* @Description: TODO(输入用户名和密码后，获得进入邮箱界面类) 
	
	* @param @param by
	* @param @return    设定文件 
	
	* @return YouXiang    返回类型 
	
	* @throws
	 */
	public YouXiang goYouXiang()
	{
		driver.click(By.xpath(property.getValue("denglubutton")));
		return new YouXiang(driver);
	}
	/**
	 * 
	
	* @Title: getCuoWu 
	
	* @Description: TODO(输入错误的用户名或者密码后，获得错误提示信息) 
	
	* @param @param by
	* @param @return    设定文件 
	
	* @return String    返回类型 
	
	* @throws
	 */
	public String getCuoWu()
	{
		
		String value = driver.getText(By.xpath(property.getValue("error")));
		return value;
	}

}
