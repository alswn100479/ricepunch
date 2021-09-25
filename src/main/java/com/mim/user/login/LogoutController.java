package com.mim.user.login;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mim.util.HttpUrlConnectionUtil;
import com.mim.util.ObjectUtil;

/**
 * Logout Controller
 */
@Controller
@RequestMapping("/logout")
public class LogoutController
{
	@Autowired
	private LoginService loginService;

	@RequestMapping("kakao.do")
	public ModelAndView kakaoLogout(
		@CookieValue(name = LoginController.KAKAO_TOKEN_NAME, required = false) String accessToken,
		HttpServletResponse response,
		HttpSession session)
		throws IOException
	{
		ModelAndView mv = new ModelAndView("index.tiles");
		URL url = new URL("https://kapi.kakao.com/v2/user/me");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Authorization", "Bearer " + accessToken);

		JSONObject result = HttpUrlConnectionUtil.getResult(conn);
		loginService.insertLogoutLog(ObjectUtil.getLong(result.get("id")), LoginController.LOGOUT_STATUS);

		loginInfoExpire(session, response);

		return mv;
	}

	/**
	 * 로그인 정보 expire (쿠키,세션)
	 * @param session
	 * @param response
	 */
	public static void loginInfoExpire(HttpSession session, HttpServletResponse response)
	{
		Cookie cookie = new Cookie(LoginController.KAKAO_TOKEN_NAME, null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);

		Cookie reCookie = new Cookie(LoginController.KAKAO_REFRESH_TOKEN_NAME, null);
		reCookie.setMaxAge(0);
		reCookie.setPath("/");
		response.addCookie(reCookie);

		session.invalidate();
	}
}
