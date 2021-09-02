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
import com.mim.service.StatService;

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
	@Autowired
	StatService statService;

	/**
	 * 메인화면 진입
	 * @param locale
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ModelAndView mv = new ModelAndView("index.tiles");

		String val = LoginController.KAKAO_HOST
			+ "/oauth/authorize?client_id="
			+ LoginController.REST_API_KEY
			+ "&redirect_uri="
			+ LoginController.REDIRECT_URI
			+ "&response_type=code";
		mv.addObject("kakao_url", val);

		// 젒근이력 남기기
		if ((request.getRemoteAddr() != request.getLocalAddr()))
		{
			UserAgent agent = UserAgent.parseUserAgentString((String) request.getHeader("User-Agent"));
			Map<String, String> map = new HashMap<String, String>();
			map.put("userid", null);
			map.put("ip", request.getRemoteAddr());
			map.put("browser", agent.getBrowser().getName());
			map.put("operatingSystem", agent.getOperatingSystem().getName());
			loginService.access(map);
		}
		
		// 브라우저 통계
		mv.addObject("browser", statService.browser().get(0));
		mv.addObject("accessCnt", statService.accessCnt().get(0));

		return mv;
	}

	/**
	 * 언어 설정을 변경한다.
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
		// 넘어온 파라미터에 ko가 있으면 Locale의 언어를 한국어로 바꿔준다.
		// 그렇지 않을 경우 영어로 설정한다.
		if (language.matches("ko"))
		{
			locales = Locale.KOREAN;
		}
		else
		{
			locales = Locale.ENGLISH;
		}

		// 세션에 존재하는 Locale을 새로운 언어로 변경해준다.
		session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locales);

		return mv;
	}
}
