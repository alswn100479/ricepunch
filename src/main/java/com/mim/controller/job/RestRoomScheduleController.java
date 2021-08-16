package com.mim.controller.job;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mim.domain.RestRoom;
import com.mim.service.RestRoomService;
import com.mim.util.ApiUtil;
import com.mim.util.GeocoderUtil;
import com.mim.util.ObjectUtil;

/**
 * 스케쥴을 실행한다.
 */
@RestController
@RequestMapping(value = "/rstr")
public class RestRoomScheduleController
{
	@Autowired
	private RestRoomService service;
	@Autowired
	private GeocoderUtil geocoder;

	@RequestMapping(value = "/job", method = RequestMethod.GET)
	public String register(Model model, String option, String search) throws IOException
	{
		String urlStr = "http://api.data.go.kr/openapi/tn_pubr_public_toilet_api?serviceKey=GBEvp69IWg7y2Yao9dtDKME3VgxqJaOuETuMOaWHcVbCh0sMqXgOeL%2BkFEKMLFK3amNCU43p24qRUpMjaKJpng%3D%3D&type=json";

		// 카운트 조회
		String urlData = urlStr + "&numOfRows=10&pageNo=1";
		JSONObject countResponse = ApiUtil.getApiResult(urlData); //connect
		JSONObject countBody = (JSONObject) countResponse.get("body");
		int totalCount = Integer.parseInt(countBody.get("totalCount").toString());

		// 데이터 조회
		int minusNum = 1000;
		int num = totalCount;
		int size = (totalCount / minusNum) + 1;
		for (int i = 1; i < size + 1; i++)
		{
			int j = 0;
			int numOfRows = num >= minusNum ? minusNum : num;
			urlData = urlStr;
			urlData += "&pageNo=" + i;
			urlData += "&numOfRows=" + numOfRows;

			System.out.println(i + " / " + numOfRows);

			JSONObject response = ApiUtil.getApiResult(urlData); //connect
			JSONObject body = (JSONObject) response.get("body");

			// 데이터 입력
			JSONArray items = (JSONArray) body.get("items");
			List<RestRoom> list = new ArrayList<RestRoom>();
			for (j = 0; j < items.size(); j++)
			{
				JSONObject item = (JSONObject) items.get(j);

				RestRoom rstr = new RestRoom();
				rstr.setType(ObjectUtil.setString(item.get("toiletType")));
				rstr.setName(ObjectUtil.setString(item.get("toiletNm")));
				rstr.setRdnmAdr(ObjectUtil.setString(item.get("rdnmadr")));
				rstr.setLnmAdr(ObjectUtil.setString(item.get("lnmadr")));
				rstr.setLadiesBowlNum(ObjectUtil.setInt(item.get("ladiesToiletBowlNumber")));
				rstr.setLadiesHandicapBowlNum(ObjectUtil.setInt(item.get("ladiesHandicapToiletBowlNumber")));
				rstr.setLadiesChildToiletBowlNum(ObjectUtil.setInt(item.get("ladiesChildrenToiletBowlNumber")));
				rstr.setMenBowlNum(ObjectUtil.setInt(item.get("menToiletBowlNumber")));
				rstr.setMenUrinalNum(ObjectUtil.setInt(item.get("menUrineNumber")));
				rstr.setMenHandicapBowlNum(ObjectUtil.setInt(item.get("menHandicapToiletBowlNumber")));
				rstr.setMenHandicapUrinalNum(ObjectUtil.setInt(item.get("menHandicapUrinalNumber")));
				rstr.setMenChildrenBowlNum(ObjectUtil.setInt(item.get("menChildrenToiletBowlNumber")));
				rstr.setMenChildrenUrinalNum(ObjectUtil.setInt(item.get("menChildrenUrinalNumber")));
				rstr.setInstallationYear(ObjectUtil.setInt(item.get("installationYear")));
				rstr.setInsttCode(ObjectUtil.setInt(item.get("insttCode")));
				rstr.setEmgBellYn(ObjectUtil.setBoolean(item.get("emgBellYn")));
				rstr.setModYear(ObjectUtil.setBoolean(item.get("modYear")));
				rstr.setCctvYn(ObjectUtil.setBoolean(item.get("enterentCctvYn")));
				rstr.setUnisexYn(ObjectUtil.setBoolean(item.get("unisexToiletYn")));
				rstr.setInstitutionName(ObjectUtil.setString(item.get("institutionNm")));
				rstr.setPhonNum(ObjectUtil.setString(item.get("phoneNumber")));
				rstr.setOpenTime(ObjectUtil.setString(item.get("openTime")));
				rstr.setLatitude(ObjectUtil.setString(item.get("latitude")));
				rstr.setLongitude(ObjectUtil.setString(item.get("longitude")));
				rstr.setPossType(ObjectUtil.setString(item.get("toiletPossType")));
				rstr.setPosiType(ObjectUtil.setString(item.get("toiletPosiType")));
				rstr.setDipersExchgPosi(ObjectUtil.setString(item.get("dipersExchgPosi")));
				rstr.setInsttName(ObjectUtil.setString(item.get("institutionNm")));
				rstr.setInstDate(ObjectUtil.setDate(item.get("referenceDate")));

				list.add(rstr);
			}
			service.register(list);

			num -= minusNum;

			System.out.println(i + "ROW END-----");
		}

		return "sss";
	}

	@RequestMapping(value = "/updateGeo", method = RequestMethod.GET)
	public void updateGeo() throws Exception
	{
		List<RestRoom> list = service.listToGeoUpdate();
		int totalCount = list.size();
		System.out.println("totalCount = " + totalCount);
		List<RestRoom> nList = new ArrayList<RestRoom>();
		for (int i = 0; i < list.size(); i++)
		{
			RestRoom rstr = list.get(i);
			String name = StringUtils.isNotBlank(rstr.getRdnmAdr()) ? rstr.getRdnmAdr() : rstr.getLnmAdr();
			String[] naver = geocoder.geocoding(name);
			rstr.setNaverLongitude(naver[0]);
			rstr.setNaverLatitude(naver[1]);
			nList.add(rstr);
			if ((i + 1) % 1000 == 0)
			{
				service.mergeGeo(nList);
				nList = new ArrayList<RestRoom>();
			}
		}
		service.mergeGeo(nList);
		System.out.println("MergeGeo End");
	}
}
