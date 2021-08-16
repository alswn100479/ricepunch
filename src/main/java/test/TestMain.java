package test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import com.google.code.geocoder.model.GeocoderStatus;
import com.google.code.geocoder.model.LatLng;

public class TestMain
{

	public static void main(String[] args) throws InvalidFormatException, IOException, ParseException
	{
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		list.add("6");
		list.add("7");
		list.add("8");
		list.add("9");
		list.add("10");
		
		List<String> nList=  new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			nList.add(list.get(i));
			if ((i+1) %3 == 0) {
				System.out.println(i + " " + nList.toString());
				nList = new ArrayList<String>();
			}
		}
		System.out.println("end "+ nList.toString());
	}

	public static Float[] geoCoding(String location)
	{

		if (location == null)

			return null;

		Geocoder geocoder = new Geocoder();

		// setAddress : 변환하려는 주소 (경기도 성남시 분당구 등)

		// setLanguate : 인코딩 설정

		GeocoderRequest geocoderRequest = new GeocoderRequestBuilder()
			.setAddress(location).setLanguage("ko").getGeocoderRequest();

		GeocodeResponse geocoderResponse;

		try
		{

			geocoderResponse = geocoder.geocode(geocoderRequest);

			if (geocoderResponse.getStatus() == GeocoderStatus.OK & !geocoderResponse.getResults().isEmpty())
			{
				GeocoderResult geocoderResult = geocoderResponse.getResults().iterator().next();

				LatLng latitudeLongitude = geocoderResult.getGeometry().getLocation();

				Float[] coords = new Float[2];

				coords[0] = latitudeLongitude.getLat().floatValue();

				coords[1] = latitudeLongitude.getLng().floatValue();

				return coords;

			}

		}
		catch (IOException ex)
		{

			ex.printStackTrace();

		}

		return null;

	}

}
