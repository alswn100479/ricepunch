package com.mim.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Table Specification
 * @author minju
 */
@Controller
public class TableSpcfcController
{
	@RequestMapping(value = "/tableSpcfc", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws InvalidFormatException, IOException
	{
		File f = new File("D:\\test\\createTable.xlsx");

		XSSFWorkbook workbook = new XSSFWorkbook(f);
		Sheet sheet = workbook.getSheetAt(0);

		HashMap<String, ArrayList<String>> columnMap = new HashMap<String, ArrayList<String>>();
		HashMap<String, ArrayList<String>> pkMap = new HashMap<String, ArrayList<String>>();
		StringBuffer returnVal = new StringBuffer();

		int rowCnt = sheet.getPhysicalNumberOfRows();
		for (int i = 0; i < rowCnt + 1; i++)
		{
			XSSFRow row = (XSSFRow) sheet.getRow(i);
			if (null != row)
			{
				String cell0 = getCellValue(row.getCell(0));
				if (StringUtils.isNotBlank(cell0) && StringUtils.equals(cell0, "Y"))
				{
					StringBuffer str = new StringBuffer();
					String tableName = getCellValue(row.getCell(1));
					String columnName = getCellValue(row.getCell(2));
					String dataType = getCellValue(row.getCell(3));
					String dataLength = getCellValue(row.getCell(4));
					boolean nullable = StringUtils.equalsIgnoreCase(getCellValue(row.getCell(5)), "Y"); // null Çã¿ë
					String pkfk = getCellValue(row.getCell(6));
					boolean isPk = StringUtils.equalsIgnoreCase(pkfk, "PK");
					boolean isFK = StringUtils.equalsIgnoreCase(pkfk, "FK");
					String constraint = getCellValue(row.getCell(7));
					String comment = getCellValue(row.getCell(8));

					// add column
					str.append(columnName + " ");
					str.append(dataType);
					if (StringUtils.isNotBlank(dataLength) && dataType.indexOf("(") < 0)
					{
						str.append("(" + dataLength + ")");
					}
					if (!nullable)
					{
						str.append(" NOT NULL ");
					}

					if (!columnMap.containsKey(tableName))
					{
						columnMap.put(tableName, new ArrayList<String>());
					}
					columnMap.get(tableName).add(str.toString());

					// add constraint 
					if (isPk && StringUtils.isNotBlank(constraint))
					{
						String pkKey = tableName + "," + constraint;
						if (!pkMap.containsKey(pkKey))
						{
							pkMap.put(pkKey, new ArrayList<String>());
						}
						pkMap.get(pkKey).add(columnName);
					}

				}
			}
		}

		// make query
		Iterator<String> columnIter = columnMap.keySet().iterator();
		while (columnIter.hasNext())
		{
			String key = columnIter.next();
			ArrayList<String> value = columnMap.get(key);
			StringBuffer queryStr = new StringBuffer();
			queryStr.append("CREATE TABLE " + key + " ( \n");

			for (int i = 0; i < value.size(); i++)
			{
				queryStr.append("\t");
				queryStr.append(i != value.size() - 1 ? value.get(i) + "," : value.get(i));
				queryStr.append("\n");
			}

			queryStr.append(" ); ");
			
			returnVal.append(queryStr.toString() + "\n");
		}

		Iterator<String> pkIter = pkMap.keySet().iterator();
		while (pkIter.hasNext())
		{
			String key = pkIter.next();
			String[] keyArr = key.split(",");
			ArrayList<String> value = pkMap.get(key);

			StringBuffer queryStr = new StringBuffer();
			queryStr.append("ALTER TABLE " + keyArr[0] + " ADD CONSTRAINT " + keyArr[1] + "PRIMARY KEY (");
			for (int i = 0; i < value.size(); i++)
			{
				queryStr.append(i != value.size() - 1 ? value.get(i) + ", " : value.get(i));
			}
			queryStr.append("); ");

			returnVal.append(queryStr.toString() + "\n");
		}
		
		System.out.println(returnVal.toString());
		
		model.addAttribute("query", returnVal);
		return "util/tableSpcfc";
	}

	String getCellValue(Cell cell)
	{
		String cellVal = "";
		if (null != cell)
		{
			switch (cell.getCellType())
			{
				case NUMERIC :
					cellVal = new DataFormatter().formatCellValue(cell);
					break;
				case STRING :
					cellVal = cell.getStringCellValue().trim();
					break;
				case FORMULA :
					cellVal = cell.getCellFormula();
					break;
				case BLANK :
					break;
				case BOOLEAN :
					cellVal = String.valueOf(cell.getBooleanCellValue());
					break;
				case ERROR :
					cellVal = String.valueOf(cell.getErrorCellValue());
					break;
			}
		}
		return cellVal;
	}
}
