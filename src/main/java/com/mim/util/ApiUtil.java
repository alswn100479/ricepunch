package com.mim.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ApiUtil
{
	/**
	 * api에 연결 후, 응답 결과를 JSONOBJECT로 돌려준다. @param urlData @return @throws
	 */
	public static JSONObject getApiResult(String urlData) throws IOException
	{
		JSONObject response = null;
		URL url = new URL(urlData);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300)
		{
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		}
		else
		{
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null)
		{
			sb.append(line);
		}
		rd.close();
		conn.disconnect();

		JSONParser parser = new JSONParser();
		JSONObject obj = null;

		try
		{
			obj = (JSONObject) parser.parse(sb.toString());
			response = (JSONObject) obj.get("response");
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		return response;
	}
}
