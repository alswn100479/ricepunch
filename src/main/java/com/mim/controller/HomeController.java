package com.mim.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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

import com.mim.service.LoginService;

/**
 * http://localhost:8080/restroom/
 */
@Controller
public class HomeController
{

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	MessageSource messageSource;
	@Autowired
	LoginService loginService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("index.tiles");

		// �����̷� �����
		if (request.getRemoteAddr() != request.getLocalAddr())
		{
			Map<String, String> map = new HashMap<String, String>();
			map.put("userid", null);
			map.put("ip", request.getRemoteAddr());
			map.put("browser", null);
			map.put("operatingSystem", null);
			loginService.access(map);
		}

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
