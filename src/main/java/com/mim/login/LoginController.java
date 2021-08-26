package com.mim.login;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mim.user.User;
import com.mim.user.UserSession;
import com.mim.util.HttpUrlConnectionUtil;
import com.mim.util.ObjectUtil;

/**
 * 로그인 컨트롤러
 */
@Controller
@RequestMapping("/login")
public class LoginController
{
	public final static String KAKAO_HOST = "https://kauth.kakao.com";
	public final static String REST_API_KEY = "7b71f5d4a64438b4fb2ce2f31a0a9be8";
	public final static String REDIRECT_URI = "https://localhost:8443/login/kakao.do";

	public final static int LOGIN_STATUS = 1000;
	public final static int LOGOUT_STATUS = 9000;

	@Autowired
	private LoginService loginService;

	@RequestMapping("kakao.do")
	public ModelAndView kakaoLogin(@RequestParam("code") String code, HttpSession session) throws IOException
	{
		ModelAndView mv = new ModelAndView("index.tiles");

		// access token 발급
		String accessToken = getAccessToken(code);
		session.setAttribute("accessToken", accessToken);

		// 로그인 사용자 저장
		User user = getUserInfo(accessToken);
		loginService.insertUser(user);
		loginService.insertLoginLog(user, LOGIN_STATUS);
		
		UserSession us = new UserSession();
		session.setAttribute("userSession", us);

		return mv;
	}

	/**
	 * access token을 받는다.
	 * @param code
	 * @return
	 * @throws IOException
	 */
	public String getAccessToken(String code) throws IOException
	{
		URL url = new URL("https://kauth.kakao.com/oauth/token");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);

		StringBuffer sb = new StringBuffer();
		sb.append("grant_type=authorization_code");
		sb.append("&client_id=" + REST_API_KEY);
		sb.append("&redirect_uri=" + REDIRECT_URI);
		sb.append("&code=" + code);

		OutputStreamWriter outStream = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
		PrintWriter writer = new PrintWriter(outStream);
		writer.write(sb.toString());
		writer.flush();

		JSONObject result = HttpUrlConnectionUtil.getResult(conn);
		String accessToken = (String) result.get("access_token");
		/*String refreshTokenExpireIn = (String) result.get("refresh_token_expires_in");
		String refreshToken = (String) result.get("refresh_token");
		String tokenType = (String) result.get("token_type");
		String expireIn = (String) result.get("expires_in");*/

		return accessToken;
	}

	/**
	 * token의 정보를 받는다.
	 * @param accessToken
	 * @return
	 * @throws IOException
	 */
	public String getTokenInfo(String accessToken) throws IOException
	{
		URL url = new URL("https://kapi.kakao.com/v1/user/access_token_info");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Authorization", "Bearer " + accessToken);

		//JSONObject result = HttpUrlConnectionUtil.getResult(conn);
		return null;
	}

	/**
	 * 토큰으로 사용자의 정보를 받는다.
	 * @param accessToken
	 * @return
	 * @throws IOException
	 */
	public User getUserInfo(String accessToken) throws IOException
	{
		URL url = new URL("https://kapi.kakao.com/v2/user/me");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Authorization", "Bearer " + accessToken);

		JSONObject result = HttpUrlConnectionUtil.getResult(conn);

		/*		login accessToken = qXkj-R0EJXQpIaAa59RX9VDKe4GekXctCtM64Ao9dGgAAAF7fXtSvg
		{"id":1865365598,"connected_at":"2021-08-25T12:14:08Z","kakao_account":{"age_range":"20~29","age_range_needs_agreement":false,"has_age_range":true}}
		logout
		{"id":1865365598,"connected_at":"2021-08-25T12:14:08Z","kakao_account":{"age_range":"20~29","age_range_needs_agreement":false,"has_age_range":true}}
		*/
		User user = new User();
		user.setAccessToken(ObjectUtil.getString(result.get("accessToken")));
		user.setId(ObjectUtil.getLong(result.get("id")));

		JSONObject kakaoAccount = (JSONObject) result.get("kakao_account");
		String ageRange = ObjectUtil.getString(kakaoAccount.get("age_range"));
		if (StringUtils.isNotBlank(ageRange) && ageRange.indexOf("~") > -1)
		{
			user.setAgeRange(Integer.parseInt(ageRange.split("~")[0]));
		}

		return user;
	}
}
