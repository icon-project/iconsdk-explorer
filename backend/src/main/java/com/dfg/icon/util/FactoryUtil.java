package com.dfg.icon.util;

import java.io.FileOutputStream;
import java.math.BigInteger;
import java.util.Date;

import com.dfg.icon.core.exception.IconCode;
import com.google.gson.JsonObject;

public class FactoryUtil {


	/** python timestap 형태를 자바형태로 변환
	 * @param timestamp
	 * @return
	 */
	public static Date changeTimeStampFormat(String timestamp) {

		if(timestamp != null && timestamp.length() > 3) {
			return new Date(Long.parseLong(timestamp.substring(0, timestamp.length() - 3)));
		}
		return null;
	}


	/** python Hex Type timestap 형태를 자바형태로 변환
	 * @param hexString
	 * @return
	 */
	public static Date changeHexTimeStampFormat(String hexString) {
		if(hexString.startsWith("0x")) {
    		hexString = hexString.substring(2);
    	}
		BigInteger bi = new BigInteger(hexString, 16);
		String timestamp = bi.toString();
		return new Date(Long.parseLong(timestamp.substring(0, timestamp.length() - 3)));
	}

	public static Date changeStringTimeStampFormat(String timeString) {
		if(timeString.startsWith("0x")) {
			timeString = timeString.substring(2);
			BigInteger bi = new BigInteger(timeString, 16);
			String timestamp = bi.toString();
			return new Date(Long.parseLong(timestamp.substring(0, timestamp.length() - 3)));
		} else {
			if(timeString != null && timeString.length() > 3) {
				return new Date(Long.parseLong(timeString.substring(0, timeString.length() - 3)));
			}
			return null;
		}
	}


	/** python timestap 형태를 자바형태로 변환
	 * @param timestamp
	 * @return
	 */
	public static Date changeHexStringTimeStampFormat(String timestamp) {

		timestamp = String.valueOf(HexUtil.changeHexToInteger(timestamp));

		if(timestamp != null && timestamp.length() > 3) {
			return new Date(Long.parseLong(timestamp.substring(0, timestamp.length() - 3)));
		}
		return null;
	}


	/** 트랜잭션 버젼을 조회
	 */
	public static String getTxVersion(JsonObject txObject) {
		if(txObject.get("version") == null) {
			return IconCode.TXNVERSION_2.getCode();
		}else {
			return txObject.get("version").getAsString();
		}
	}


	/**
	 * String 형태의 byte[] 를 받아서 zip 파일로 저장
	 */
	public static void createZipFileFromByteArrayString(String filepath , String contextAddr , String hexString ) throws Exception {

		// 최초의 0x 제거
		if(hexString != null && hexString.length() > 1 && hexString.startsWith("0x")) {
			hexString = hexString.substring(2, hexString.length());
		}

		byte[] bData = null;
		try {
			bData = new BigInteger(hexString,16).toByteArray();
		} catch(Exception e) {
			e.printStackTrace();
			bData = new BigInteger("0", 16).toByteArray();
		}

		FileOutputStream stream = new FileOutputStream(filepath + contextAddr);
		try {
			stream.write(bData);
		} finally {
			stream.close();
		}
	}
}
