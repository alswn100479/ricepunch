package com.mim.home;

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

import com.mim.user.login.LoginController;
import com.mim.user.login.LoginService;
import com.mim.user.stat.StatService;

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

	public static final String IS_MOBILE = "MOBILE";
	private static final String IS_PHONE = "PHONE";
	public static final String IS_TABLET = "TABLET";
	public static final String IS_PC = "PC";

	/**
	 * ����ȭ�� ����
	 * @param locale
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		request.getRequestURI();
		ModelAndView mv = new ModelAndView("index.tiles");

		String redirectUrl = request.getScheme()
			+ "://"
			+ request.getServerName()
			+ ":"
			+ request.getServerPort()
			+ LoginController.REDIRECT_URI;

		String val = LoginController.KAKAO_HOST
			+ "/oauth/authorize?client_id="
			+ LoginController.REST_API_KEY
			+ "&redirect_uri="
			+ redirectUrl
			+ "&response_type=code";
		mv.addObject("kakao_url", val);

		// �����̷� �����
		if ((request.getRemoteAddr() != request.getLocalAddr()))
		{
			String agentStr = request.getHeader("User-Agent");
			UserAgent agent = UserAgent.parseUserAgentString(agentStr);
			Map<String, String> map = new HashMap<String, String>();
			map.put("userid", null);
			map.put("ip", request.getRemoteAddr());
			map.put("browser", agent.getBrowser().getName());
			map.put("operatingSystem", agent.getOperatingSystem().getName());

			// ��ⱸ��
			if (agentStr.indexOf(IS_MOBILE) > -1)
			{
				if (agentStr.indexOf(IS_PHONE) == -1)
				{
					map.put("device", IS_MOBILE);
				}
				else
				{
					map.put("device", IS_TABLET);
				}
			}
			else
			{
				map.put("device", IS_PC);
			}

			loginService.access(map);
		}

		// ������ ���
		mv.addObject("browser", statService.browser().get(0));
		mv.addObject("accessCnt", statService.accessCnt().get(0));

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
