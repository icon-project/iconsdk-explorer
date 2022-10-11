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
	public static JsonArray eventLogInputsArray2 = null;
	
	 static  String ircFilePath = "";

	public static void initIRC2() throws Exception{


		File projectPath = new File("");
		String path = projectPath.getAbsolutePath()+"/IRC/";
		File file = new File(path + "IRC2.json");

		BufferedReader br = new BufferedReader(new FileReader(file));

		String line;
		StringBuffer sb = new StringBuffer();

		while ((line = br.readLine()) != null) {
			sb.append(line);
		}

		String jsonStr = sb.toString();

		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(jsonStr);
		JsonObject rootObject =  element.getAsJsonObject();
		IRC2_FORMAT = rootObject.get("result").getAsJsonArray();

		// eventlog 일경우는 name 항목이 상이하여도 같은것으로 처리 해야함
		for(JsonElement je : IRC2_FORMAT) {
			String type = je.getAsJsonObject().get("type").getAsString();

			if(type.equals("eventlog")) {
				eventLogInputsArray2  = je.getAsJsonObject().get("inputs").getAsJsonArray();
			}
		}
	}


	/** 해당 JSON String의 IRC 버젼을 조회
	 * @param jArray
	 * @return
	 * @throws Exception
	 */

	public static String checkIRCVersion(JsonArray jArray, String filePath) throws Exception {
		ircFilePath = filePath;

		String IrcFormat = "-";

		if(checkIrc2(jArray) ) {
			IrcFormat = IconCode.IRC2_TOKEN.getCode();
		}

		return IrcFormat;
	}


	private static boolean checkIrc2(JsonArray jArray) throws Exception {
		if(IRC2_FORMAT == null) {
			initIRC2();
		}

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
				JsonObject  targetObject	 = eventLogInputsArray2.get(i).getAsJsonObject();
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

			if(checkCount == eventLogInputsArray2.size()) {
				okCount++;
			}
		}

		if(okCount == IRC2_FORMAT.size()) {
			return true;
		}

		return false;
	}
}
