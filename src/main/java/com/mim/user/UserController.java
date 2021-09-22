package com.mim.user;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

import com.mim.user.login.LoginController;

/**
 * Dictionary Controller
 */
@Controller
@RequestMapping(value = "/user")
public class UserController
{
	@Autowired
	UserService userService;
	@Autowired
	private LocaleResolver localeResolver;

	/**
	 * 사용자 정보 화면을 돌려주는 Controller
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/profile.do", method = RequestMethod.GET)
	public ModelAndView adminIndex(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("user/profile.tiles");
		for (Cookie cookie : request.getCookies())
		{
			if (cookie.getName().equals(LoginController.KAKAO_TOKEN_NAME))
			{
				String token = cookie.getValue();
				User user = getUserByKaKaoToken(token);
				mv.addObject("user", user);
			}
		}

		return mv;
	}

	/**
	 * 카카오 토큰으로 사용자 정보를 돌려주는 Controller
	 * @param kakaoToken
	 * @return
	 */
	@RequestMapping(value = "/userInfo.do", method = RequestMethod.GET)
	public ModelAndView getUserInfo(String kakaoToken)
	{
		ModelAndView mv = new ModelAndView();
		User user = getUserByKaKaoToken(kakaoToken);
		mv.addObject("user", user);

		return mv;
	}

	/**
	 * 카카오토큰으로 사용자 정보를 돌려준다.
	 * @param token
	 * @return
	 */
	public User getUserByKaKaoToken(String token)
	{
		User user = null;
		try
		{
			user = LoginController.getUserInfo(token);
			user = userService.selectUser(user.getId());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return user;
	}

	/**
	 * 사용자 정보를 업데이트한다.
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/modifyUserInfo.do", method = RequestMethod.POST)
	public ModelAndView modifyUserInfo(User user, HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView("user/profile.tiles");
		User userServer = userService.updateUserInfo(user);
		mv.addObject("user", userServer);

		Locale locale = new Locale(user.getLanguage());
		localeResolver.setLocale(request, response, locale);

		return mv;
	}
}
