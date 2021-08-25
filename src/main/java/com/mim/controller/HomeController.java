package com.mim.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.mim.login.LoginController;
import com.mim.login.LoginService;

import eu.bitwalker.useragentutils.UserAgent;

/**
 * HomeController
 */
@Controller
public class HomeController
{
	@Autowired
	MessageSource messageSource;
	@Autowired
	LoginService loginService;

	/**
	 * ����ȭ�� ����
	 * @param locale
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("index.tiles");

		// �����̷� �����
		if (request.getRemoteAddr() != request.getLocalAddr())
		{
			UserAgent agent = UserAgent.parseUserAgentString((String) request.getAttribute("User-Agent"));
			Map<String, String> map = new HashMap<String, String>();
			map.put("userid", null);
			map.put("ip", request.getRemoteAddr());
			map.put("browser", agent.getBrowser().getName());
			map.put("operatingSystem", agent.getOperatingSystem().getName());
			loginService.access(map);
		}

		String val = LoginController.KAKAO_HOST
			+ "/oauth/authorize?client_id="
			+ LoginController.REST_API_KEY
			+ "&redirect_uri="
			+ LoginController.REDIRECT_URI
			+ "&response_type=code";
		mv.addObject("kakao_url", val);

		return mv;
	}

	/**
	 * ��� ������ �����Ѵ�.
	 * @param response
	 * @param request
	 * @param language
	 * @return
	 */
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
