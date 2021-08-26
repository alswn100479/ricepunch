package com.mim.login;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	public ModelAndView kakaoLogout(HttpSession session) throws IOException
	{
		ModelAndView mv = new ModelAndView("index.tiles");
		URL url = new URL("https://kapi.kakao.com/v2/user/me");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Authorization", "Bearer " + session.getAttribute("accessToken"));

		JSONObject result = HttpUrlConnectionUtil.getResult(conn);
		loginService.insertLogoutLog(ObjectUtil.getLong(result.get("id")), LoginController.LOGOUT_STATUS);
		
		session.invalidate();
		return mv;
	}
}
