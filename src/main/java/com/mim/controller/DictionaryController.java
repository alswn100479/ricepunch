package com.mim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mim.domain.Dictionary;
import com.mim.service.DictionaryService;

/**
 * Dictionary Controller
 */
@Controller
@RequestMapping(value = "/dictionary")
public class DictionaryController
{
	@Autowired
	DictionaryService service;

	@RequestMapping(value = "/adminIndex.do", method = RequestMethod.GET)
	public ModelAndView adminIndex()
	{
		ModelAndView mv = new ModelAndView("dictionary/adm.index.tiles");
		return mv;
	}

	@RequestMapping(value = "/write.do", method = RequestMethod.POST)
	public ModelAndView register(Dictionary dict)
	{
		System.out.println("controller");
		service.register(dict);

		ModelAndView mv = new ModelAndView("dictionary/adm.index.tiles");
		return mv;
	}
}
