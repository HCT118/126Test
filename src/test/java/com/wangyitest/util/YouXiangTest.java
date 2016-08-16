package com.wangyitest.util;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wangyi.Driver.WangYiDriver;
import com.wangyi.pages.HomePage;
import com.wangyi.pages.WebdriverBaseCase;
import com.wangyi.pages.YouXiang;
import com.wangyi.util.BrowerType;
import com.wangyi.util.Browsers;
import com.wangyi.util.ParseProperties;
import com.wangyi.util.SleepTime;

@Test
public class YouXiangTest extends WebdriverBaseCase
{
	private WebDriver driver;
	private  WangYiDriver webdriver;
	private HomePage hp;
	private YouXiang yx;
	private File filepath = new File(System.getProperty("user.dir"), "data");
	private ParseProperties property=new ParseProperties(filepath.getAbsolutePath()+"\\"+"shoye.properties");
	@BeforeClass
	 public void Init()
	 {
	  beforeClass();
	  driver=Browsers.Browser(BrowerType.ie);
      webdriver=new WangYiDriver(driver);
      hp=new HomePage(webdriver);
      webdriver.get(property.getValue("url"));
      
	 }
	 @Test(description="用户名错误，密码正确",priority=1)
     public void youXiangShouye1()
     {
    	boolean flag =false; 
    	beforeTest("youXiangShouye1");
    	try {
    		hp.sendUserName("nature");
    		hp.sendPassWord("850926");
    		yx=hp.goYouXiang();
    		SleepTime.sleepSeconds(5);
    		Assert.assertEquals(hp.getCuoWu(), "帐号或密码错误");
    		flag=true;

		} catch (Exception e) {
			e.printStackTrace();
		}
        afterTest("youXiangShouye1", flag,webdriver); 	
     }
	 @Test(description="用户名正确，密码错误",priority=2)
     public void youXiangShouye2()
     {
    	boolean flag =false; 
    	beforeTest("youXiangShouye2");
    	try {
    		hp.sendUserName("nature0805");
    		hp.sendPassWord("850936");
    		yx=hp.goYouXiang();
    		SleepTime.sleepSeconds(5);
    		Assert.assertEquals(hp.getCuoWu(), "帐号或密码错误");
    		flag=true;

		} catch (Exception e) {
			e.printStackTrace();
		}
        afterTest("youXiangShouye2", flag,webdriver); 	
     }
	 @Test(description="用户、密码都为空",priority=3)
     public void youXiangShouye3()
     {
    	boolean flag =false; 
    	beforeTest("youXiangShouye3");
    	try {
    		hp.sendUserName("");
    		hp.sendPassWord("");
    		yx=hp.goYouXiang();
    		SleepTime.sleepSeconds(5);
    		Assert.assertEquals(hp.getCuoWu(), "请先输入您的邮箱帐号");
    		flag=true;

		} catch (Exception e) {
			e.printStackTrace();
		}
        afterTest("youXiangShouye3", flag,webdriver); 	
     }
	 @Test(description="用户为空、密码正确",priority=4)
     public void youXiangShouye4()
     {
    	boolean flag =false; 
    	beforeTest("youXiangShouye4");
    	try {
    		hp.sendUserName("nature0805");
    		hp.sendPassWord("");
    		yx=hp.goYouXiang();
    		SleepTime.sleepSeconds(5);
    		Assert.assertEquals(hp.getCuoWu(), "请输入您的密码");
    		flag=true;

		} catch (Exception e) {
			e.printStackTrace();
		}
        afterTest("youXiangShouye4", flag,webdriver); 	
     }
	 @Test(description="用户名为空，密码正确",priority=5)
     public void youXiangShouye5()
     {
    	boolean flag =false; 
    	beforeTest("youXiangShouye5");
    	try {
    		hp.sendUserName("");
    		hp.sendPassWord("850926");
    		yx=hp.goYouXiang();
    		SleepTime.sleepSeconds(5);
    		Assert.assertEquals(hp.getCuoWu(), "请先输入您的邮箱帐号");
    		flag=true;

		} catch (Exception e) {
			e.printStackTrace();
		}
        afterTest("youXiangShouye5", flag,webdriver); 	
     }
     @Test(description="用户名、密码正确",priority=6)
     public void youXiangShouye()
     {
    	boolean flag =false; 
    	beforeTest("youXiangShouye");
    	try {
    		hp.sendUserName("nature0805");
    		hp.sendPassWord("850926");
    		yx=hp.goYouXiang();
    		SleepTime.sleepSeconds(5);
    		String value=yx.getUserName();
    		Assert.assertEquals(value, "nature0805@126.com");
    		flag=true;

		} catch (Exception e) {
			e.printStackTrace();
		}
        afterTest("youXiangShouye", flag,webdriver); 	
     }
     
     @AfterClass
     public void CloseWindow()
     {
    	 afterClass();
    	 driver.close();
    	 driver.quit();
     }
}
