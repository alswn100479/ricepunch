package com.mim.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mim.domain.RestRoom;
import com.mim.service.RestRoomService;

/**
 * api를 받아온다. http://localhost:8080/restroom/rstr
 */
@Controller
@RequestMapping(value = "/rstr")
public class RestRoomController
{
	@Autowired
	private RestRoomService service;

	@RequestMapping(value = "/index.do", method = RequestMethod.GET)
	public String index(ModelAndView model)
	{
		return "rstr/list.tiles";
	}

	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public ModelAndView list(String option, String search, String fields, String title)
	{
		ModelAndView mav = new ModelAndView("rstr/list.tiles");
		try
		{
			List<RestRoom> list = service.list(option, search);
			mav.addObject("list", list);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return mav;
	}
	
	@RequestMapping(value = "/list.table.do", method = RequestMethod.GET)
	public ModelAndView table(String latitude, String longitude, String fields, String title)
	{
		ModelAndView mav = new ModelAndView("rstr/list.table");
		try
		{
			List<RestRoom> list = service.list(latitude, longitude);
			mav.addObject("list", list);
			System.out.println(latitude);
			System.out.println(longitude);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return mav;
	}
}
