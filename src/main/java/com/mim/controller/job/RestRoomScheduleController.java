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

	@RequestMapping(value = "/job.do", method = RequestMethod.GET)
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
				rstr.setType(ObjectUtil.getString(item.get("toiletType")));
				rstr.setName(ObjectUtil.getString(item.get("toiletNm")));
				rstr.setRdnmAdr(ObjectUtil.getString(item.get("rdnmadr")));
				rstr.setLnmAdr(ObjectUtil.getString(item.get("lnmadr")));
				rstr.setLadiesBowlNum(ObjectUtil.getInt(item.get("ladiesToiletBowlNumber")));
				rstr.setLadiesHandicapBowlNum(ObjectUtil.getInt(item.get("ladiesHandicapToiletBowlNumber")));
				rstr.setLadiesChildToiletBowlNum(ObjectUtil.getInt(item.get("ladiesChildrenToiletBowlNumber")));
				rstr.setMenBowlNum(ObjectUtil.getInt(item.get("menToiletBowlNumber")));
				rstr.setMenUrinalNum(ObjectUtil.getInt(item.get("menUrineNumber")));
				rstr.setMenHandicapBowlNum(ObjectUtil.getInt(item.get("menHandicapToiletBowlNumber")));
				rstr.setMenHandicapUrinalNum(ObjectUtil.getInt(item.get("menHandicapUrinalNumber")));
				rstr.setMenChildrenBowlNum(ObjectUtil.getInt(item.get("menChildrenToiletBowlNumber")));
				rstr.setMenChildrenUrinalNum(ObjectUtil.getInt(item.get("menChildrenUrinalNumber")));
				rstr.setInstallationYear(ObjectUtil.getInt(item.get("installationYear")));
				rstr.setInsttCode(ObjectUtil.getInt(item.get("insttCode")));
				rstr.setEmgBellYn(ObjectUtil.getBoolean(item.get("emgBellYn")));
				rstr.setModYear(ObjectUtil.getBoolean(item.get("modYear")));
				rstr.setCctvYn(ObjectUtil.getBoolean(item.get("enterentCctvYn")));
				rstr.setUnisexYn(ObjectUtil.getBoolean(item.get("unisexToiletYn")));
				rstr.setInstitutionName(ObjectUtil.getString(item.get("institutionNm")));
				rstr.setPhonNum(ObjectUtil.getString(item.get("phoneNumber")));
				rstr.setOpenTime(ObjectUtil.getString(item.get("openTime")));
				rstr.setLatitude(ObjectUtil.getString(item.get("latitude")));
				rstr.setLongitude(ObjectUtil.getString(item.get("longitude")));
				rstr.setPossType(ObjectUtil.getString(item.get("toiletPossType")));
				rstr.setPosiType(ObjectUtil.getString(item.get("toiletPosiType")));
				rstr.setDipersExchgPosi(ObjectUtil.getString(item.get("dipersExchgPosi")));
				rstr.setInsttName(ObjectUtil.getString(item.get("institutionNm")));
				rstr.setInstDate(ObjectUtil.getDate(item.get("referenceDate")));

				list.add(rstr);
			}
			service.register(list);

			num -= minusNum;

			System.out.println(i + "ROW END-----");
		}

		return "sss";
	}

	@RequestMapping(value = "/updateGeo.do", method = RequestMethod.GET)
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
			if (StringUtils.isBlank(naver[0]))
			{
				System.out.println(name);
			}
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
