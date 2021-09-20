package com.mim.user;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	@RequestMapping(value = "/profile.do", method = RequestMethod.GET)
	public ModelAndView adminIndex(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView("user/profile.tiles");
		for (Cookie cookie : request.getCookies())
		{
			if (cookie.getName().equals(LoginController.KAKAO_TOKEN_NAME))
			{
				String token = cookie.getValue();
				try
				{
					User user = LoginController.getUserInfo(token);
					mv.addObject("user", user);
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}

		return mv;
	}

	/**
	 * 사용자 정보를 업데이트한다.
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/modifyUserInfo.do", method = RequestMethod.POST)
	public ModelAndView modifyUserInfo(User user)
	{
		ModelAndView mv = new ModelAndView("user/profile.tiles");
		User userServer = userService.updateUserInfo(user);
		mv.addObject("user", userServer);

		return mv;
	}
}
