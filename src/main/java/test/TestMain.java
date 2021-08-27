package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestMain
{

	public static void main(String[] args)
	{
		try
		{
			String url = "";
			String id = "";
			String name = "";
			Connection conn = DriverManager.getConnection(url, id, name);
			PreparedStatement pstmt = conn.prepareStatement("");
			pstmt.executeQuery();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}
