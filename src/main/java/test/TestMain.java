package test;

import java.io.IOException;
import java.text.ParseException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.mim.controller.job.RestRoomScheduleController;

public class TestMain
{

	public static void main(String[] args) throws InvalidFormatException, IOException, ParseException
	{
		RestRoomScheduleController c = new RestRoomScheduleController();
		c.excel();
	}
}
