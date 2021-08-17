package com.mim.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.RestController;

/**
 * Naver API - Geocoding
 * @author minju
 */
@RestController
public class GeocodingUtil
{
	public String[] geocoding(String addr)
	{
		StringBuffer sb = null;
		String[] value = new String[2];
		HttpURLConnection conn = null;
		BufferedReader br = null;
		try
		{
			String addrEnc = URLEncoder.encode(addr, "UTF-8");
			String key = "AIzaSyAybOn2KGSusrLBPOFI0uVPSPSE9Mhpgpk";
			String urlStr = "https://maps.googleapis.com/maps/api/geocode/json?key=AIzaSyAybOn2KGSusrLBPOFI0uVPSPSE9Mhpgpk&address="
				+ addrEnc
				+ "&components=country:KR";

			conn = connect(urlStr);

			br = new BufferedReader(new InputStreamReader(new URL(urlStr).openStream(), "UTF-8"));

			sb = new StringBuffer();
			String line = "";
			while ((line = br.readLine()) != null)
			{
				sb.append(line).append("\n"); //StringBuffer�� ������� ������ ���������� ���� �ǽ�
			}
		}
		catch (UnsupportedEncodingException e1)
		{
			e1.printStackTrace();
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}

		JSONParser parser = new JSONParser();
		JSONObject json;
		JSONObject jsonObject;
		JSONObject jsonObject2;

		try
		{
			if (StringUtils.isNotBlank(sb.toString()))
			{
				json = (JSONObject) parser.parse(sb.toString());
				JSONArray results = (JSONArray) json.get("results");
				JSONObject resultsArray = (JSONObject) results.get(0);

				jsonObject = (JSONObject) resultsArray.get("geometry");
				jsonObject2 = (JSONObject) jsonObject.get("location");

				value[0] = jsonObject2.get("lng").toString();
				value[1] = jsonObject2.get("lat").toString();
			}
			else
			{
				value[0] = null;
				value[1] = null;
			}
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}

		conn.disconnect();
		try
		{
			br.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * connect
	 * @param urlStr
	 * @return
	 */
	HttpURLConnection connect(String urlStr)
	{
		HttpURLConnection conn = null;
		try
		{
			URL url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestMethod("GET");
			conn.connect();
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return conn;
	}

}
