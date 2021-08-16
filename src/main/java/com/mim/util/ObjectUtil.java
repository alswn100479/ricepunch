package com.mim.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

public class ObjectUtil
{
	/**
	 * Object에서 String 값을 돌려준다.
	 * @param obj
	 * @return
	 */
	public static String setString(Object obj)
	{
		String value = null;
		if (ObjectUtils.isNotEmpty(obj))
		{
			value = obj.toString();
		}
		return value;
	}

	/**
	 * Object에서 Int 값을 돌려준다.
	 * @param obj
	 * @return
	 */
	public static int setInt(Object obj)
	{
		int value = 0;
		if (ObjectUtils.isNotEmpty(obj))
		{
			String str = obj.toString();
			if (StringUtils.isNotBlank(str) && !StringUtils.equals(str, "null"))
			{
				try
				{
					value = Integer.parseInt(str);
				}
				catch (NumberFormatException e)
				{
				}
			}
		}
		return value;
	}

	/**
	 * Object에서 Boolean값을 돌려준다.
	 * @param obj
	 * @return
	 */
	public static int setBoolean(Object obj)
	{
		int value = 0;
		if (ObjectUtils.isNotEmpty(obj))
		{
			String str = obj.toString();
			if (StringUtils.isNotBlank(str) && StringUtils.equals(str, "Y"))
			{
				value = 1;
			}
		}
		return value;
	}

	/**
	 * Object에서 Date 값을 돌려준다.
	 * @param obj
	 * @return
	 */
	public static Date setDate(Object obj)
	{
		Date value = null;
		if (ObjectUtils.isNotEmpty(obj))
		{
			String str = obj.toString();
			if (StringUtils.isNoneBlank(str))
			{
				SimpleDateFormat sf = new SimpleDateFormat("YYYY-MM-DD");
				try
				{
					value = sf.parse(str);
				}
				catch (java.text.ParseException e)
				{
				}
			}
		}
		return value;
	}
}
