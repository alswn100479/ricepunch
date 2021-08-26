package com.mim.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;

public class XlsxUtil
{
	public static String getCellValue(XSSFCell cell, boolean stringOnly)
	{
		String value = null;
		CellType type = cell.getCellType();
		
		if (stringOnly)
		{
			value = cell.getStringCellValue();
		}
		else
		{
			switch (type)
			{
				case FORMULA :
					value = cell.getCellFormula();
				case NUMERIC :
					value = cell.getNumericCellValue() + "";
				case STRING :
					value = cell.getStringCellValue();
				case BOOLEAN :
					value = cell.getBooleanCellValue() + "";
				case ERROR :
					value = cell.getErrorCellValue() + "";
				default:
					break;
			}
		}
		return value;
	}
}
