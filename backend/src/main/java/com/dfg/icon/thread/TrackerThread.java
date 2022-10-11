package com.dfg.icon.thread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TrackerThread extends Thread{


	private static String serverUrl;


	public TrackerThread (String serverUrl){
		this.serverUrl = serverUrl;
	}


	@Override
	public void run() {
		while(true){
			try {
				

				String url = serverUrl + "/v0/exchange/updateExchageInfo";

				URL obj = new URL(url);
				HttpURLConnection con = (HttpURLConnection) obj.openConnection();

				// optional default is GET
				con.setRequestMethod("GET");

				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

				Thread.sleep(600000);
				
			} catch (Exception e) {

				e.printStackTrace();
			}
		}


	}
}
