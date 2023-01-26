package com.dfg.icon.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.dfg.icon.core.exception.IconCode;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * IRC 관련 헬퍼 
 * @author hslee
 *
 */
public class IRCUtil {

	public static JsonArray IRC2_FORMAT = null;
	public static JsonArray IRC2_eventLogInputsArray = null;
	public static JsonArray IRC3_FORMAT = null;
	public static JsonArray IRC3_TRANSFER_eventLogInputsArray = null;
	public static JsonArray IRC3_APPROVAL_eventLogInputsArray = null;
	public static JsonObject fileParse(String filePath) throws Exception{
		File file = new File(filePath);

		BufferedReader br = new BufferedReader(new FileReader(file));

		String line;
		StringBuffer sb = new StringBuffer();

		while ((line = br.readLine()) != null) {
			sb.append(line);
		}

		String jsonStr = sb.toString();

		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(jsonStr);
		return element.getAsJsonObject();
	}

	public static void initIRC2(String filePath) throws Exception{
		JsonObject rootObject = fileParse(filePath + "IRC2.json");
		IRC2_FORMAT = rootObject.get("result").getAsJsonArray();

		// eventlog 일경우는 name 항목이 상이하여도 같은것으로 처리 해야함
		for(JsonElement je : IRC2_FORMAT) {
			String type = je.getAsJsonObject().get("type").getAsString();

			if(type.equals("eventlog")) {
				IRC2_eventLogInputsArray = je.getAsJsonObject().get("inputs").getAsJsonArray();
			}
		}
	}

	public static void initIRC3(String filePath) throws Exception{
		JsonObject rootObject = fileParse(filePath + "IRC3.json");

		IRC3_FORMAT = rootObject.get("result").getAsJsonArray();

		// eventlog 일경우는 name 항목이 상이하여도 같은것으로 처리 해야함
		for(JsonElement je : IRC3_FORMAT) {
			String type = je.getAsJsonObject().get("type").getAsString();
			String name = je.getAsJsonObject().get("name").getAsString();
			if(type.equals("eventlog")){
				if(name.equals("Transfer")) {
					IRC3_TRANSFER_eventLogInputsArray = je.getAsJsonObject().get("inputs").getAsJsonArray();
				}else if(name.equals("Approval")){
					IRC3_APPROVAL_eventLogInputsArray = je.getAsJsonObject().get("inputs").getAsJsonArray();
				}
			}
		}
	}




	/** 해당 JSON String의 IRC 버젼을 조회
	 * @param jArray
	 * @return
	 * @throws Exception
	 */

	public static String checkIRCVersion(JsonArray jArray, String filePath) throws Exception {
		if(IRC2_FORMAT == null) {
			initIRC2(filePath);
		}
		if(IRC3_FORMAT == null) {
			initIRC3(filePath);
		}

		String IrcFormat = "-";

		if(checkIrc2(jArray) ) {
			IrcFormat = IconCode.IRC2_TOKEN.getCode();
		}else if(checkIrc3(jArray) ) {
			IrcFormat = IconCode.IRC3_TOKEN.getCode();
		}

		return IrcFormat;
	}


	private static boolean checkIrc2(JsonArray jArray) throws Exception {
		int okCount = 0 ;
		JsonElement transferLog = null;

		for(JsonElement je : jArray) {
			String type = je.getAsJsonObject().get("type").getAsString();
			String name = je.getAsJsonObject().get("name").getAsString();
			if(type.equals("eventlog") && name.equals("Transfer")) {
				transferLog = je;
				continue;
			}

			if(type.equals("fallback") && name.equals("fallback")) {
				okCount++;
				continue;
			}

			for(JsonElement je2 : IRC2_FORMAT) {
				if(je.equals(je2)) {
					okCount++;
					break;
				}
			}
		}

		// eventlog 인 경우는 별도 처리를 해야함 (Name 값이 없거나 틀려도 같다고 판단)
		if(okCount == IRC2_FORMAT.size() - 1 && transferLog != null) {

			JsonArray inputsArray = transferLog.getAsJsonObject().get("inputs").getAsJsonArray();
			int checkCount = 0;
			for(int i = 0 ; i < inputsArray.size() ; i++) {
				JsonObject removeNameObject 	= inputsArray.get(i).getAsJsonObject();
				JsonObject  targetObject	 = IRC2_eventLogInputsArray.get(i).getAsJsonObject();
				if(removeNameObject.get("type").equals(targetObject.get("type")) ) {
					if(removeNameObject.get("type").getAsString().equals("bytes")) {
						checkCount++;
					}else {
						// 20.05.27
						// transfer log내에 indexed가 없이 들어오는 경우가 발생하여 예외처리
						if(removeNameObject.has("indexed") && removeNameObject.get("indexed") != null) {
							if(removeNameObject.get("indexed").equals(targetObject.get("indexed")) ) {
								checkCount++;
							}
						}
					}
				}
			}

			if(checkCount == IRC2_eventLogInputsArray.size()) {
				okCount++;
			}
		}

		if(okCount == IRC2_FORMAT.size()) {
			return true;
		}

		return false;
	}


	private static boolean checkIrc3(JsonArray jArray) throws Exception {
		int okCount = 0 ;

		for(JsonElement je : jArray) {
			for(JsonElement je2 : IRC3_FORMAT) {
				if(je.equals(je2)) {
					okCount++;
					break;
				}
			}
		}

		if(okCount == IRC3_FORMAT.size()) {
			return true;
		}

		return false;
	}

}
