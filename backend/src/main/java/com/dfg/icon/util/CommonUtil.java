package com.dfg.icon.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;

/**
 * CommonUtil 
 * @author hslee
 *
 */
public class CommonUtil {

	/** 0이하의 값이거나 null일경우 1으로 처리
	 * @param i
	 * @return
	 */
	public static Integer changeUnderZero(Integer i) {
		if(i == null || i < 1) {
			i = 1;
		}
		return i;
	}

	/** 시간에 00:00:00 추가 
	 * @param date
	 * @return
	 */
	public static String appendZeroTime(String date) {
		if(date != null && !"".equals(date)) {
			date = date + " 00:00:00";
		}
		return date;
	}

	/** 시간에  23:59:59 추가 
	 * @param date
	 * @return
	 */
	public static String appendMaxTime(String date) {
		if(date != null && !"".equals(date)) {
			date = date + " 23:59:59";
		}
		return date;
	}


	/** Exception 의 내용을 상세히 표현 
	 * @param e
	 */
	public static void printException(Logger logger , String message ,  Exception e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		logger.error(message, sw);
	}
	
	/**  주소의 자리수가 40이고 16진수의 데이터일 경우 앞에 hx를추가함  
	 * @return
	 */
	public static String checkAddress(String addr) {

		Pattern p = Pattern.compile("^[A-Fa-f0-9]{40}$");
		Matcher m =  p.matcher(addr);
		if(m.find()) {
			return "hx" + addr;
		}
		return addr;
	}
	
	public static String invisibleAddress(String addr){
		
		if("cx0000000000000000000000000000000000000000".equals(addr)){
			addr = "-";
		}
		return addr;
	}
}
