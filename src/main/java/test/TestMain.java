package test;

import java.io.IOException;
import java.text.ParseException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import com.google.code.geocoder.model.GeocoderStatus;
import com.google.code.geocoder.model.LatLng;
import com.mim.util.GeocoderUtil;
import com.mim.util.GeocodingUtil;

public class TestMain
{

	public static void main(String[] args) throws InvalidFormatException, IOException, ParseException
	{
//		String addr = "��⵵ ���� �ϻ꼭�� �ϻ굿 600-47";
		String addr = "����Ư���� ��õ�� ���μ�ȯ�� 1314";
		
		GeocoderUtil nUtil = new GeocoderUtil();
		String[] nVal = nUtil.geocoding(addr);
		
		System.out.println(nVal[0]);
		System.out.println(nVal[1]);

		GeocodingUtil util = new GeocodingUtil();
		
		
		String[] val = util.geocoding(addr);
		
		System.out.println(val[0]);
		System.out.println(val[1]);
		
		/*Float[] a = geoCoding(addr);
		System.out.println(a[0]);
		System.out.println(a[1]);*/
	}
	
	public static Float[] geoCoding(String location) {

		if (location == null)  

		return null;

				       

		Geocoder geocoder = new Geocoder();

		// setAddress : ��ȯ�Ϸ��� �ּ� (��⵵ ������ �д籸 ��)

		// setLanguate : ���ڵ� ����

		GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress(location).setLanguage("ko").getGeocoderRequest();

		GeocodeResponse geocoderResponse;



		try {

		geocoderResponse = geocoder.geocode(geocoderRequest);

		if (geocoderResponse.getStatus() == GeocoderStatus.OK & !geocoderResponse.getResults().isEmpty()) {


		 


		GeocoderResult geocoderResult=geocoderResponse.getResults().iterator().next();


		 
		LatLng latitudeLongitude = geocoderResult.getGeometry().getLocation();

						  

		Float[] coords = new Float[2];

		coords[0] = latitudeLongitude.getLat().floatValue();

		coords[1] = latitudeLongitude.getLng().floatValue();

		return coords;

		}

		} catch (IOException ex) {

		ex.printStackTrace();

		}

		return null;

		}
}
