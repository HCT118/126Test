package com.wangyi.util;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Browsers 
{
	private static WebDriver driver;
	private static FirefoxProfile firefoxfile=null;
	private static DesiredCapabilities cpas=null;
	private static File classpathRoot=new File(System.getProperty("user.dir"));
	private static File tool = new File(classpathRoot,"tool");
	public static WebDriver Browser(BrowerType browserstype)
	{
		
		switch (browserstype) {
		case firefox:
			File firebug=new File(tool.getAbsolutePath()+"/firebug-1.12.1-fx.xpi");
			File firexpath=new File(tool.getAbsolutePath()+"/firepath-0.9.7-fx.xpi");
			firefoxfile=new FirefoxProfile();
			try {
				firefoxfile.addExtension(firexpath);
				firefoxfile.addExtension(firebug);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			driver=new FirefoxDriver(firefoxfile);
			driver.manage().window().maximize();
			break;
		case chrome:
			System.setProperty("webdriver.chrome.driver", tool.getAbsolutePath()+"/chromedriver.exe");
			cpas = DesiredCapabilities.chrome();
			cpas.setCapability("chrome.switches",Arrays.asList("--start-maximized"));  //���browser
			//capabilities.setCapability("chrome.switches", Arrays.asList("--proxy-server=http://your-proxy-domain:4443")); //���ô���
			driver = new ChromeDriver(cpas);
			driver.manage().window().maximize();
			break;
		case ie:
			System.setProperty("webdriver.ie.driver", tool.getAbsolutePath()+"/IEDriverServer64.exe");
			cpas = DesiredCapabilities.internetExplorer();
			cpas.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, false);
			cpas.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);   
			cpas.setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");		
			cpas.setCapability("ignoreZoomSetting", true);
	        driver = new InternetExplorerDriver(cpas);
	        driver.manage().window().maximize();
			break;
		}
		return driver;
	}

}
