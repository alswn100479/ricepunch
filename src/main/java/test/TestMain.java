package test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TestMain
{

	public static void main(String[] args) throws SQLException
	{
		java.sql.Connection conn = java.sql.DriverManager.getConnection("");
		java.sql.PreparedStatement pstmt = conn.prepareStatement("");
		ResultSet rs = pstmt.executeQuery();

		java.sql.ResultSetMetaData rsmd = rs.getMetaData();

		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
		ArrayList<String> columnNames = new ArrayList<String>();
		
		while (rs.next())
		{
			// column name
			if (rs.first())
			{
				for (int i = 0; i < rsmd.getColumnCount(); i++)
				{
					columnNames.add(rsmd.getColumnName(i));
				}
			}

			ArrayList<String> rowData = new ArrayList<String>();
			for (int i = 0; i < rsmd.getColumnCount(); i++)
			{
				rowData.add(rs.getString(i));
			}
			data.add(rowData);
		}
	}
}
