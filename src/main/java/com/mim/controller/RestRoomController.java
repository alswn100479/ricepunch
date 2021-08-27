package com.mim.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mim.domain.RestRoom;
import com.mim.service.RestRoomService;

/**
 * ����ȭ��� Controller Class
 */
@Controller
@RequestMapping(value = "/rstr")
public class RestRoomController
{
	@Autowired
	private RestRoomService service;

	/**
	 * ����ȭ��� ����ȭ��
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index()
	{
		ModelAndView mav = new ModelAndView("rstr/index.tiles");
		return mav;
	}

	/**
	 * ��ġ ����� ����ȭ��� ����� �����´�.
	 * @param latitude
	 * @param longitude
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public ModelAndView table(String latitude, String longitude, String search)
	{
		ModelAndView mav = new ModelAndView("rstr/list.table");
		try
		{
			latitude = StringUtils.isBlank(latitude) ? "37.579887" : latitude;
			longitude = StringUtils.isBlank(longitude) ? "126.976870" : longitude;
			
			System.out.println("search = " + search);

			List<RestRoom> list = service.list(latitude, longitude, search);
			mav.addObject("list", list);

			List<String[]> locations = new ArrayList<String[]>();
			for (RestRoom r : list)
			{
				String[] location = {r.getLatitude(), r.getLongitude()};
				locations.add(location);
			}
			mav.addObject("locations", locations);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return mav;
	}
}
