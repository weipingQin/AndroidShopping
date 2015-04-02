package com.qinweiping.shopping.common;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.util.Log;

public class DateUtil {

	public static String fullPattern = "yyyy-MM-dd HH:mm:ss";

	public static String datePattern = "yyyy-MM-dd";

	public static String monthPattern = "yyyy-MM";

	public static String yearPattern = "yyyy";

	public static String hourPattern = "yyyy-MM-dd HH";

	public static String zeroDatetime = " 00:00:00";

	public static String nightDatetime = " 23:59:59";

	public static String pubDatePattern = "yyyy-MM-dd HH:mm";

	public static String myPattern = "yyyyMMddHHmm";
	
	/**
	 * function锛氱敤浜庡姞瀵嗚В瀵?1鍒嗛挓
	 * 
	 * @param currentTime
	 * @param format
	 * @return
	 */
	public static String getSystemCurrentTime(long currentTime,String format){
		Date nowTime  =   new Date(currentTime);
		SimpleDateFormat sdFormatter = new SimpleDateFormat(format);
		 String retStrFormatNowDate = sdFormatter.format(nowTime);
		 return retStrFormatNowDate+"0000";
	}
	//获取所对应的时间格式
	public static String getFormatSystime(){
		Date date = new Date();
		SimpleDateFormat sif = new SimpleDateFormat("yyyyMMddHHmm");
		String result = sif.format(date);
		StringBuffer sb = new StringBuffer();
		sb.append(result);
		for(int i = 0 ; i <16-result.length();i++){
			sb.append("0");
		}
		return sb.toString().substring(1,12);
	}

	public static String getDate(Date aDate, String format) {
		if (aDate != null) {
			SimpleDateFormat df = new SimpleDateFormat(format);
			return df.format(aDate);
		}
		return "";
	}

	public static Date getDate(String dateStr, String format) {
		if (dateStr == null || format == null) {
			return null;
		}
		try {
			SimpleDateFormat df = new SimpleDateFormat(format);
			Date date = df.parse(dateStr);
			return date;
		} catch (Exception ex) {
			return null;
		}
	}

	public static final String getZeroDatetime(Date date) {
		return getDate(date, datePattern) + zeroDatetime;
	}
	

	public static String filterDate(String dateStr, String format) {
		return getDate(getDate(dateStr, format), format);
	}

}
