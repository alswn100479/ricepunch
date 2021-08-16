package com.mim.controller.job;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mim.domain.EmgBell;
import com.mim.domain.RestRoom;
import com.mim.service.RestRoomService;
import com.mim.util.ApiUtil;
import com.mim.util.GeocoderUtil;
import com.mim.util.ObjectUtil;

/**
 * 스케쥴을 실행한다.
 */
@RestController
@RequestMapping(value = "/bell")
public class EmgBellScheduleController
{
	@Autowired
	private RestRoomService service;
	@Autowired
	private GeocoderUtil geocoder;

	@RequestMapping(value = "/job", method = RequestMethod.GET)
	public String register(Model model, String option, String search) throws IOException
	{
		String urlStr = "http://api.data.go.kr/openapi/tn_pubr_public_safety_emergency_bell_position_api?serviceKey=GBEvp69IWg7y2Yao9dtDKME3VgxqJaOuETuMOaWHcVbCh0sMqXgOeL%2BkFEKMLFK3amNCU43p24qRUpMjaKJpng%3D%3D&type=json";

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
			List<EmgBell> list = new ArrayList<EmgBell>();
			for (j = 0; j < items.size(); j++)
			{
				JSONObject item = (JSONObject) items.get(j);
				
				EmgBell bell = new EmgBell();
				bell.setId(ObjectUtil.setInt("safeBellManageNo"));
				bell.setInstPrpsCode(ObjectUtil.setInt("installationPurps"));
				bell.setItlpcTypeCode(ObjectUtil.setInt("itlpcType"));
				bell.setName(ObjectUtil.setString("itlpc"));
				bell.setRdnmadr(ObjectUtil.setString("rdnmadr"));
				bell.setLnmadr(ObjectUtil.setString("lnmadr"));
				bell.setLatitude(ObjectUtil.setString("latitude"));
				bell.setLongitude(ObjectUtil.setString("longitude"));
				bell.setCntcMthdCode(ObjectUtil.setInt("cntcMthd"));
				bell.setPolcCntcYn(ObjectUtil.setInt("polcCntcYn"));
				bell.setOfficeCntcYn(ObjectUtil.setInt("officeCntcYn"));
				bell.setAdiFnct(ObjectUtil.setString("adiFnct"));
				bell.setInstYear(ObjectUtil.setInt("installationYear"));
				bell.setSafechkDate(ObjectUtil.setDate("safechkDate"));
				bell.setSafechkTypeYn(ObjectUtil.setInt("safechkType"));
				bell.setInstitutionNm(ObjectUtil.setString("institutionNm"));
				bell.setPhoneNum(ObjectUtil.setString("phoneNumber"));
				bell.setInstDate(ObjectUtil.setDate("referenceDate"));
				bell.setInsttCode(ObjectUtil.setInt("insttCode"));
				

				list.add(bell);
			}
			//service.register(list);

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
