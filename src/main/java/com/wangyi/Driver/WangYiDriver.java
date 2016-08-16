package com.wangyi.Driver;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.wangyi.util.DateTimeUtil;
import com.wangyi.util.SleepTime;

public class WangYiDriver
{
	private WebDriver driver;
	private Actions actionDriver;
	private Logger logger = LogManager.getFormatterLogger(WangYiDriver.class);

	public WangYiDriver(WebDriver driver) 
	{
		this.driver = driver;
		actionDriver = new Actions(this.driver);
	}
	/**
	 * 
	
	* @Title: takeScreen 
	
	* @Description: TODO(截取屏幕图片) 
	
	* @param     设定文件 
	
	* @return void    返回类型 
	
	* @throws
	 */
	public void takeScreen()
	{
		String screenname=String.valueOf(DateTimeUtil.getDateTime()+".jpg");
		File pic=new File(System.getProperty("user.dir"));
		File picpath=new File(pic,"pic");
		if(!picpath.exists())
		{
			picpath.mkdir();
		}
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
	/**
	 * 
	
	* @Title: operationCheck 
	
	* @Description: TODO(用于判断要执行的方法是否执行了) 
	
	* @param @param methodName
	* @param @param isSucceed    设定文件 
	
	* @return void    返回类型 
	
	* @throws
	 */
	public void operationCheck(String methodName, boolean isSucceed) {
		if (isSucceed) {
			logger.info("方法 【" + methodName + "】 运行通过！");
		} else {
			String dateTime = DateTimeUtil.formatedTime("-yyyyMMdd-HHmmssSSS");
			StringBuffer sb = new StringBuffer();
			File pic=new File(System.getProperty("user.dir"));
			File picpath=new File(pic,"pic");
			String captureName = sb.append(picpath.getAbsolutePath()).append("\\").append(methodName)
					.append(dateTime).append(".jpg").toString();
			TakeScreenShot(captureName);
			logger.error("方法 【" + methodName + "】 运行失败，请查看截图快照："
					+ captureName);
		}
	}
		
	/**
	 * 
	
	* @Title: get 
	
	* @Description: TODO(用于界面跳转) 
	
	* @param @param url
	* @param @param count    设定文件 
	
	* @return void    返回类型 
	
	* @throws
	 */
	public void get(String url)
	{
		boolean flag=false;
		
		driver.get(url);
		logger.info("跳转到URL："+url+"成功");
		flag=true;
		operationCheck("get", flag);
	}
	
	
	public void navigationForward()
	{
		driver.navigate().forward();
		logger.info("往前跳转成功");
	}
	public void navigationBack()
	{
		driver.navigate().back();
		logger.info("往后跳转成功");
	}
	/**
	 * 
	
	* @Title: isAlertExists 
	
	* @Description: TODO(用于判断是否出现警告框) 
	
	* @param @param timeout
	* @param @return    设定文件 
	
	* @return boolean    返回类型 
	
	* @throws
	 */
	public boolean isAlertExists(long timeout) {
		boolean isSucceed = false;
		long timeBegins = System.currentTimeMillis();
		do {
			try {
				driver.switchTo().alert();
				isSucceed = true;
				break;
			} catch (Exception e) {
				logger.error(e);
			}
			SleepTime.sleepSeconds(timeout);
		} while (System.currentTimeMillis() - timeBegins <= timeout * 1000);
		operationCheck("isAlertExists", isSucceed);
		return isSucceed;
	}

	public boolean isAlertExists() {
		return isAlertExists(0);
	}
	/**
	 * 
	
	* @Title: isElementPresent 
	
	* @Description: TODO(用于判定元素是否显示) 
	
	* @param @param by
	* @param @return    设定文件 
	
	* @return boolean    返回类型 
	
	* @throws
	 */

	public boolean isElementPresent(By by)
	{
		boolean flag=false;
		try {
			driver.findElement(by);
			flag = true;
			logger.debug("定位控件 [" + by.toString()+ "] 成功");
		} catch (ElementNotFoundException e) {
			logger.info("元素控件："+by.toString()+"不存在");
		}
		operationCheck("isElementPresent", flag);
		return flag;
	}
	
	/**
	 * 
	
	* @Title: isElementDisplayed 
	
	* @Description: TODO(用于判定元素是否显示) 
	
	* @param @param by
	* @param @return    设定文件 
	
	* @return boolean    返回类型 
	
	* @throws
	 */
	public boolean isElementDisplayed(By by)
	{
		boolean flag=false;
		try {
			flag=driver.findElement(by).isDisplayed();
			logger.debug("定位控件 [" + by.toString()+ "] 成功!");
		} catch (ElementNotFoundException e) {
			logger.error(e);
		}
		operationCheck("isElementDisplayed", flag);
		return false;
		
	}
	
	/**
	 * 
	
	* @Title: findElement 
	
	* @Description: TODO(按指定方法搜索元素) 
	
	* @param @param by
	* @param @return    设定文件 
	
	* @return WebElement    返回类型 
	
	* @throws
	 */
	public WebElement findElement(By by)
	{
		boolean flag=false;
		WebElement element = null;
		try {
			element=driver.findElement(by);
			flag=true;
			logger.info("查找控件："+element.getText()+"成功");
		} catch (ElementNotFoundException e) {
			logger.info("查找控件失败："+by.toString()+"原因："+e);
		}
		operationCheck("findElement", flag);
		return element;
	}
	
	
	public List<WebElement> findElements(By by)
	{
		boolean flag=false;
		List<WebElement> elements =null;
		try {
			elements= driver.findElements(by);
			flag=true;
			for(int i=0;i<elements.size();i++)
			{
				System.out.println("第"+i+"个元素是："+elements.get(i).getText());
			}
		} catch (ElementNotFoundException e) {
			
		}
		operationCheck("findElements", flag);
		return elements;
	}
	
	/**
	 * 
	
	* @Title: getWindowTitle 
	
	* @Description: TODO(获取当前窗口的title) 
	
	* @param @return    设定文件 
	
	* @return String    返回类型 
	
	* @throws
	 */
	public String getWindowTitle() {
		String title = driver.getTitle();
		logger.debug("当前窗口Title是 :" + title);
		return title;
	}
	/**
	 * 
	
	* @Title: getCurrentUrl 
	
	* @Description: TODO(获取当前窗口的url地址) 
	
	* @param @return    设定文件 
	
	* @return String    返回类型 
	
	* @throws
	 */
	public String getCurrentUrl() {
		String url = driver.getCurrentUrl();
		logger.debug("当前URL地址是 :" + url);
		return url;
	}
	/**
	 * 
	
	* @Title: getPageSource 
	
	* @Description: TODO(获取当前页面的source) 
	
	* @param @return    设定文件 
	
	* @return String    返回类型 
	
	* @throws
	 */
	public String getPageSource() {
		String source = driver.getPageSource();
		logger.info("获取当前页面的source成功");
		return source;
	}
	
	/**
	 * 
	
	* @Title: getWindowHandles 
	
	* @Description: TODO(获取当前浏览器打开的所有窗体) 
	
	* @param @return    设定文件 
	
	* @return Set<String>    返回类型 
	
	* @throws
	 */
	public Set<String> getWindowHandles() {
		Set<String> handles = driver.getWindowHandles();
		logger.debug("window handles count are:" + handles.size());
		logger.debug("window handles are: " + handles.toString());
		return handles;
	}
	/**
	 * 
	
	* @Title: getWindowHandle 
	
	* @Description: TODO(获取当前窗体) 
	
	* @param @return    设定文件 
	
	* @return String    返回类型 
	
	* @throws
	 */

	public String getWindowHandle() {
		String handle = driver.getWindowHandle();
		logger.debug("current window handle is:" + handle);
		return handle;
	}
	/**
	 * 
	
	* @Title: getTagName 
	
	* @Description: TODO(根据webelement获取tagname) 
	
	* @param @param element
	* @param @return    设定文件 
	
	* @return String    返回类型 
	
	* @throws
	 */
	public String getTagName(WebElement element)
	{
		String tagName=element.getTagName();
		logger.info("获取tagname："+tagName+"成功！");
		return tagName;
		
	}
	
	/**
	 * 
	
	* @Title: getTagName 
	
	* @Description: TODO(根据webdriver获取控件的tagname) 
	
	* @param @param by
	* @param @return    设定文件 
	
	* @return String    返回类型 
	
	* @throws
	 */
	public String getTagName(By by)
	{
		String tagName=driver.findElement(by).getTagName();
		logger.info("获取控件："+by.toString()+"的tagname"+tagName+"成功！");
		return tagName;
		
	}
	/**
	 * 
	
	* @Title: getAttribute 
	
	* @Description: TODO(根据webelemeent来获取某个属性的值) 
	
	* @param @param element
	* @param @param attributeName
	* @param @return    设定文件 
	
	* @return String    返回类型 
	
	* @throws
	 */
	public String getAttribute(WebElement element,String attributeName)
	{
		String attributeValue=element.getAttribute(attributeName);
		logger.info("元素的属性是： " + attributeName + "值是: " + attributeValue);
		return attributeValue;
		
	}
	/**
	 * 
	
	* @Title: getAttribute 
	
	* @Description: TODO(根据webdriver获取某个属性的属性值) 
	
	* @param @param by
	* @param @param attributename
	* @param @return    设定文件 
	
	* @return String    返回类型 
	
	* @throws
	 */
	public String getAttribute(By by,String attributename)
	{
		String attributevalue=driver.findElement(by).getAttribute(attributename);
		logger.info("控件："+by.toString()+"的属性："+attributename+"值是："+attributevalue);
		return attributevalue;
	}
	
	
	/**
	 * rewrite the clear method, adding user defined log</BR>
	 * 与工具原生API作用完全一致，只是增加了操作结果检查和日志记录。
	 * 
	 * @param element
	 *            the webelement you want to operate
	 */
	public void clear(WebElement element) {
		element.clear();
		logger.debug("控件元素 [ " + element + " ] 清空成功");
	}
	
	public void clear(By by)
	{
		driver.findElement(by).clear();
		logger.info("清空控件："+by.toString()+"内容成功");
	}

	/**
	 * rewrite the click method, adding user defined log</BR>
	 * 与工具原生API作用完全一致，只是增加了操作结果检查和日志记录。
	 * 
	 * @param element
	 *            the webelement you want to operate
	 */
	public void click(WebElement element) {
		element.click();
		logger.debug("点击元素 [ " + element + " ]成功 ");
	}
	
	public void click(By by)
	{
		driver.findElement(by).click();
		logger.info("点击元素："+by.toString()+"成功！");
	}

	/**
	 * rewrite the sendKeys method, adding user defined log</BR>
	 * 与工具原生API作用完全一致，只是增加了操作结果检查和日志记录。
	 * 
	 * @param element
	 *            the webelement you want to operate
	 */
	public void sendKeys(WebElement element, String text) {
		element.sendKeys(text);
		logger.debug("输入值 [ " + text + " ] 到控件元素 [ " + element
				+ " ]成功");
	}
	public void sendKeys(By by,String value)
	{
		driver.findElement(by).sendKeys(value);
		logger.info("输入值"+value+"成功");
	}

	/**
	 * rewrite the isSelected method, adding user defined log</BR>
	 * 与工具原生API作用完全一致，只是增加了操作结果检查和日志记录。
	 * 
	 * @param element
	 *            the webelement you want to operate
	 * @return the bool value of whether is the WebElement selected
	 */
	public boolean isSelected(WebElement element) {
		boolean isSelected = element.isSelected();
		logger.debug("元素是否选中? " + String.valueOf(isSelected));
		return isSelected;
	}

	/**
	 * rewrite the isSelected method, the element to be find by By</BR>
	 * 与工具原生API作用完全一致，只是增加了操作结果检查和日志记录。
	 * 
	 * @param by
	 *            the locator you want to find the element
	 * @return the bool value of whether is the WebElement selected
	 */
	public boolean isSelected(By by) {
		boolean isSelected = driver.findElement(by).isSelected();
		logger.debug("element [ " + by.toString() + " ] selected? "
				+ String.valueOf(isSelected));
		return isSelected;
	}

	/**
	 * rewrite the isEnabled method, adding user defined log</BR>
	 * 与工具原生API作用完全一致，只是增加了操作结果检查和日志记录。
	 * 
	 * @param element
	 *            the webelement you want to operate
	 * @return the bool value of whether is the WebElement enabled
	 */
	public boolean isEnabled(WebElement element) {
		boolean isEnabled = element.isEnabled();
		logger.debug("元素是否可用? " + String.valueOf(isEnabled));
		return isEnabled;
	}

	/**
	 * rewrite the isEnabled method, the element to be find by By</BR>
	 * 与工具原生API作用完全一致，只是增加了操作结果检查和日志记录。
	 * 
	 * @param by
	 *            the locator you want to find the element
	 * @return the bool value of whether is the WebElement enabled
	 */
	public boolean isEnabled(By by) {
		boolean isEnabled = driver.findElement(by).isEnabled();
		logger.debug("element [ " + by.toString() + " ] enabled? "
				+ String.valueOf(isEnabled));
		return isEnabled;
	}

	/**
	 * rewrite the getText method, adding user defined log</BR>
	 * 与工具原生API作用完全一致，只是增加了操作结果检查和日志记录。
	 * 
	 * @param element
	 *            the webelement you want to operate
	 */
	public String getText(WebElement element) {
		String text = element.getText();
		logger.debug("控件元素的值是:" + text);
		return text;
	}

	/**
	 * rewrite the getText method, find the element by By and get its own
	 * text</BR> 与工具原生API作用完全一致，只是增加了操作结果检查和日志记录。
	 * 
	 * @param by
	 *            the locator you want to find the element
	 * @return the text string
	 */
	public String getText(By by) {
		String text = driver.findElement(by).getText();
		logger.debug("控件 [ " + by.toString() + " ]的 内容是: " + text);
		return text;
	}

	/**
	 * rewrite the isDisplayed method, adding user defined log</BR>
	 * 与工具原生API作用完全一致，只是增加了操作结果检查和日志记录。
	 * 
	 * @param element
	 *            the webelement you want to operate
	 * @return the bool value of whether is the WebElement displayed
	 */
	protected boolean isDisplayed(WebElement element) {
		boolean isDisplayed = element.isDisplayed();
		logger.debug("元素是否已经显示? " + String.valueOf(isDisplayed));
		return isDisplayed;
	}

	/**
	 * rewrite the isDisplayed method, the element to be find by By</BR>
	 * 与工具原生API作用完全一致，只是增加了操作结果检查和日志记录。
	 * 
	 * @param by
	 *            the locator you want to find the element
	 * @return the bool value of whether is the WebElement displayed
	 */
	public boolean isDisplayed(By by) {
		boolean isDisplayed = driver.findElement(by).isDisplayed();
		logger.debug("element [ " + by.toString() + " ] displayed? "
				+ String.valueOf(isDisplayed));
		return isDisplayed;
	}

	/**
	 * get its css property value</BR> 与工具原生API作用完全一致，只是增加了操作结果检查和日志记录。
	 * 
	 * @param element
	 *            the webelement you want to operate
	 * @param propertyName
	 *            the name of the property you want to get
	 * @return the css property value string
	 */
	public String getCssValue(WebElement element, String propertyName) {
		String cssValue = element.getCssValue(propertyName);
		logger.debug("element's css [" + propertyName + "] value is:"
				+ cssValue);
		return cssValue;
	}

	/**
	 * find the element by By and get its css property value</BR>
	 * 与工具原生API作用完全一致，只是增加了操作结果检查和日志记录。
	 * 
	 * @param by
	 *            the locator you want to find the element
	 * @param propertyName
	 *            the name of the property you want to get
	 * @return the css property value string
	 */
	public String getCssValue(By by, String propertyName) {
		String cssValue = driver.findElement(by).getCssValue(propertyName);
		logger.debug("element [ " + by.toString() + " ]'s css[" + propertyName
				+ "] value is: " + cssValue);
		return cssValue;
	}
	/**
	 * 
	
	* @Title: submitForm 
	
	* @Description: TODO(等待对象出来后做提交) 
	
	* @param @param by
	* @param @param timeout    设定文件 
	
	* @return void    返回类型 
	
	* @throws
	 */
	public void submitForm(By by, long timeout) {
		boolean isSucceed = false;
		try {
			if (isElementPresent(by)) {
				driver.findElement(by).submit();
				logger.debug("submit on element [ " + by.toString() + " ]");
				isSucceed = true;
			}
		} catch (Exception e) {
			logger.error(e);
		}
		operationCheck("submit", isSucceed);
	}
	/**
	 * choose OK/Cancel button's OK on alerts</BR> 在弹出的对话框（Dialog）上点击确认/是等接受性按钮。
	 * 
	 * @param timeout
	 *            超时时间，单位：秒
	 */
	public void chooseOKOnAlert(long timeout) {
		boolean isSucceed = false;
		try {
			if (isAlertExists(timeout)) {
				driver.switchTo().alert().accept();
				logger.debug("click OK button on alert");
				isSucceed = true;
			}
		} catch (Exception e) {
			logger.error(e);
		}
		operationCheck("chooseOKOnAlert", isSucceed);
	}

	/**
	 * choose OK/Cancel button's OK on alerts</BR> 在弹出的对话框（Dialog）上点击确认/是等接受性按钮。
	 * 
	 */
	public void chooseOKOnAlert() {
		chooseOKOnAlert(0);
	}

	/**
	 * choose Cancel on alerts</BR> 在弹出的对话框（Dialog）上点击取消/否等拒绝性按钮。
	 * 
	 * @param timeout
	 *            超时时间，单位：秒
	 */
	public void chooseCancelOnAlert(long timeout) {
		boolean isSucceed = false;
		try {
			if (isAlertExists(timeout)) {
				driver.switchTo().alert().dismiss();
				logger.debug("click Cancel on alert dialog");
				isSucceed = true;
			}
		} catch (Exception e) {
			logger.error(e);
		}
		operationCheck("chooseCancelOnAlert", isSucceed);
	}

	/**
	 * choose Cancel on alerts</BR> 在弹出的对话框（Dialog）上点击取消/否等拒绝性按钮。
	 * 
	 */
	public void chooseCancelOnAlert() {
		chooseCancelOnAlert(0);
	}

	/**
	 * get the text of the alerts</BR> 返回对话框（Dialog）上的提示信息文本内容。
	 * 
	 * @param timeout
	 *            超时时间，单位：秒
	 * 
	 * @return alert text string
	 */
	public String getTextOfAlert(long timeout) {
		boolean isSucceed = false;
		String alerts = "";
		try {
			if (isAlertExists(timeout)) {
				alerts = driver.switchTo().alert().getText();
				logger.debug("the text of the alert is: " + alerts);
				isSucceed = true;
			}
		} catch (Exception e) {
			logger.error(e);
		}
		operationCheck("getTextOfAlert", isSucceed);
		return alerts;
	}

	/**
	 * get the text of the alerts</BR> 返回对话框（Dialog）上的提示信息文本内容。
	 * 
	 * @return alert text string
	 */
	public String getTextOfAlert() {
		return getTextOfAlert(0);
	}

	/**
	 * set text on alerts</BR> 向对话框（InputBox）中输入文本。
	 * 
	 * @param text
	 *            the text string you want to input on alerts
	 * @param timeout
	 *            超时时间，单位：秒
	 */
	public void setTextOnAlert(String text, long timeout) {
		boolean isSucceed = false;
		try {
			if (isAlertExists(timeout)) {
				driver.switchTo().alert().sendKeys(text);
				logger.debug("输入内容 [ " + text + " ] 到 alert中成功");
				isSucceed = true;
			}
		} catch (Exception e) {
			logger.error(e);
		}
		operationCheck("setTextOnAlert", isSucceed);
	}

	/**
	 * set text on alerts</BR> 向对话框（InputBox）中输入文本。
	 * 
	 * @param text
	 *            the text string you want to input on alerts
	 */
	public void setTextOnAlert(String text) {
		setTextOnAlert(text, 0);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	



	
	
	
	
	

}
