package com.mim.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class HttpUrlConnectionUtil
{
	/**
	 * HttpUrlConnection response를 json으로 돌려준다.
	 * @param conn
	 * @return
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("unchecked")
	public static JSONObject getResult(HttpURLConnection conn)
	{
		JSONObject response = null;
		StringBuilder sb = new StringBuilder();
		JSONParser parser = new JSONParser();

		try
		{
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			String line;
			while ((line = rd.readLine()) != null)
			{
				sb.append(line);
			}

			if (conn.getResponseCode() == 200)
			{
				try
				{
					response = (JSONObject) parser.parse(sb.toString());
				}
				catch (ParseException e)
				{
					e.printStackTrace();
				}
			}
		}
		catch (IOException e1)
		{
			try
			{
				response = new JSONObject();
				response.put("errorCode", conn.getResponseCode());
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		return response;
	}
}
