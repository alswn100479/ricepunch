package com.mim.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * http://localhost:8080/restroom/
 */
@Controller
public class HomeController
{

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired 
	MessageSource messageSource;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/index.do", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, HttpServletRequest request)
	{
		logger.info("Welcome home! The client locale is {}.", locale);

		ModelAndView mv = new ModelAndView("index.tiles");

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		return mv;
	}

	@RequestMapping(value = "/changeLanguage.do", method = RequestMethod.GET)
	public ModelAndView changeLanguage(HttpServletResponse response, HttpServletRequest request, String language)
	{
		ModelAndView mv = new ModelAndView("index.tiles");

		Cookie cookie = new Cookie("language", language);
		cookie.setMaxAge(60 * 60 * 24);
		response.addCookie(cookie);

		HttpSession session = request.getSession();
		Locale locales = null;
		// �Ѿ�� �Ķ���Ϳ� ko�� ������ Locale�� �� �ѱ���� �ٲ��ش�.
		// �׷��� ���� ��� ����� �����Ѵ�.
		if (language.matches("ko"))
		{
			locales = Locale.KOREAN;
		}
		else
		{
			locales = Locale.ENGLISH;
		}

		// ���ǿ� �����ϴ� Locale�� ���ο� ���� �������ش�.
		session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locales);

		return mv;
	}
}
