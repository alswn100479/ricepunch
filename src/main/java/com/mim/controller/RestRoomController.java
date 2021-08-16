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
		ModelAndView mav = new ModelAndView("common/table");
		try
		{
			List<RestRoom> list = service.list(option, search);
			mav.addObject("list", list);
			mav.addObject("title", title);
			mav.addObject("fields", fields);
			System.out.println(title);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return mav;
	}
}
