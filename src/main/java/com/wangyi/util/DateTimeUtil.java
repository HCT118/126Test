package com.wangyi.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtil 
{
	public static String getCurrentDate()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(new Date());
		
	}
	public static String getCurrentTime()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
		return sdf.format(new Date());
		
	}
	public static String getTime()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmssSSS");
		return sdf.format(new Date());
		
	}
	public static String formatedTime(String format) 
	{
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}
	public static String getCurrentDateTime() 
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(new Date());
	}
	public static String getDateTime()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return sdf.format(new Date());
	}

}
