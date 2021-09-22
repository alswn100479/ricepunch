package com.mim.user.login;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

import com.mim.user.User;
import com.mim.user.UserService;
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
	public final static String REDIRECT_URI = "/login/kakao.do";

	public final static String KAKAO_TOKEN_NAME = "kakao_accessToken";

	public final static int LOGIN_STATUS = 1000;
	public final static int LOGOUT_STATUS = 9000;

	@Autowired
	private LoginService loginService;
	@Autowired
	private LocaleResolver localeResolver;
	@Autowired
	UserService userService;

	@RequestMapping("kakao.do")
	public ModelAndView kakaoLogin(
		@RequestParam("code") String code,
		HttpServletRequest request,
		HttpServletResponse response)
		throws IOException
	{
		ModelAndView mv = new ModelAndView("index.tiles");

		String redirectUrl = request.getScheme()
			+ "://"
			+ request.getServerName()
			+ ":"
			+ request.getServerPort()
			+ REDIRECT_URI;

		// access token 발급
		String accessToken = getAccessToken(code, redirectUrl);
		Cookie tokenCookie = new Cookie(KAKAO_TOKEN_NAME, accessToken);
		tokenCookie.setPath("/");
		response.addCookie(tokenCookie);

		// 로그인 사용자 저장
		User user = getUserInfo(accessToken);
		loginService.insertUser(user);
		loginService.insertLoginLog(user, LOGIN_STATUS);

		User dbUser = userService.selectUser(user.getId());

		Locale locale = new Locale(dbUser.getLanguage());
		localeResolver.setLocale(request, response, locale);

		return mv;
	}

	/**
	 * access token을 받는다.
	 * @param code
	 * @return
	 * @throws IOException
	 */
	public String getAccessToken(String code, String redirectUrl) throws IOException
	{
		URL url = new URL("https://kauth.kakao.com/oauth/token");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);

		StringBuffer sb = new StringBuffer();
		sb.append("grant_type=authorization_code");
		sb.append("&client_id=" + REST_API_KEY);
		sb.append("&redirect_uri=" + redirectUrl);
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
	public static User getUserInfo(String accessToken) throws IOException
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
