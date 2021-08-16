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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Naver API - Geocoding
 * @author minju
 */
@RestController
public class GeocoderUtil
{
	@RequestMapping(value = "/geo", method = RequestMethod.GET)
	public void test()
	{
		String[] value = geocoding("경기도 고양시 일산서구 일산동 600-47");
		System.out.println(value[0]);
		System.out.println(value[1]);
	}

	public String[] geocoding(String addr)
	{
		StringBuffer sb = null;
		String[] value = new String[2];
		HttpURLConnection conn = null;
		BufferedReader br = null;
		try
		{
			String addrEnc = URLEncoder.encode(addr, "UTF-8");
			String urlStr = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=" + addrEnc;

			conn = connect(urlStr);

			br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

			sb = new StringBuffer();
			String line = "";
			while ((line = br.readLine()) != null)
			{
				sb.append(line).append("\n"); //StringBuffer에 응답받은 데이터 순차적으로 저장 실시
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
		JSONObject jsonObject;
		JSONObject jsonObject2;
		JSONArray jsonArray;
		String x = "";
		String y = "";

		try
		{
			if (StringUtils.isNotBlank(sb.toString()))
			{
				jsonObject = (JSONObject) parser.parse(sb.toString());
				JSONObject meta = (JSONObject) jsonObject.get("meta");
				int totalCount = Integer.parseInt(meta.get("totalCount").toString());
				jsonArray = (JSONArray) jsonObject.get("addresses");

				for (int i = 0; i < jsonArray.size(); i++)
				{
					jsonObject2 = (JSONObject) jsonArray.get(i);
					if (null != jsonObject2.get("x"))
					{
						x = (String) jsonObject2.get("x").toString(); //longitude
						value[0] = x;
					}
					if (null != jsonObject2.get("y"))
					{
						y = (String) jsonObject2.get("y").toString(); // latitude
						value[1] = y;
					}
				}
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
			conn.setRequestProperty("X-NCP-APIGW-API-KEY-ID", "lb07yh2rsh");
			conn.setRequestProperty("X-NCP-APIGW-API-KEY", "aMXUSuCvbjJquFO5z6Wqb4zo7HeoyantiJb8dUy1");
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
