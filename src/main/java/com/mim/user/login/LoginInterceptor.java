package com.mim.user.login;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.mim.user.User;
import com.mim.user.UserService;

/**
 * Login Interceptor
 */
public class LoginInterceptor implements HandlerInterceptor
{
	@Autowired
	private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		HttpSession httpSession = request.getSession();

		boolean isLogout = false;
		String accessToken = null;
		String refreshToken = null;
		if (null != request.getCookies())
		{
			for (Cookie cookie : request.getCookies())
			{
				if (cookie.getName().equals(LoginController.KAKAO_TOKEN_NAME))
				{
					accessToken = cookie.getValue();
				}
				if (cookie.getName().equals(LoginController.KAKAO_REFRESH_TOKEN_NAME))
				{
					refreshToken = cookie.getValue();
				}
			}

		}

		// cookie에 토큰이 있을 때
		if (null != accessToken)
		{
			boolean isExpire = checkTokenExpire(accessToken);
			if (isExpire)
			{
				boolean isRefresh = refreshAccessToken(httpSession, response, accessToken, refreshToken);
				isLogout = isRefresh ? false : true;
			}
		}
		else
		{
			isLogout = true;
		}

		// session 다시 set
		if (null == httpSession.getAttribute("user") && !isLogout)
		{
			User user = LoginController.getUserInfo(accessToken);
			user = userService.selectUser(user.getId());
			httpSession.setAttribute("user", user);
		}

		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	/**
	 * accessToken 유효성을 체크한다.
	 * @param accessToken
	 * @return
	 * @throws IOException
	 */
	public boolean checkTokenExpire(String accessToken) throws IOException
	{
		KaKaoToken kt = LoginController.getTokenInfo(accessToken);
		if (null != kt.getErrCode() && kt.getErrCode().equals("-401"))
		{
			return true;
		}
		return false;
	}

	/**
	 * accessToken이 유효한지 체크하고 만료되면 갱신한다.
	 * @return
	 * @throws IOException
	 */
	public boolean refreshAccessToken(
		HttpSession httpSession,
		HttpServletResponse response,
		String accessToken,
		String refreshToken)
		throws IOException
	{
		KaKaoToken kt = LoginController.getTokenInfo(accessToken);
		boolean isRefresh = false;

		// accessToken 만료시
		if (kt.getErrCode().equals("-401"))
		{
			kt = LoginController.tokenRefresh(refreshToken);
			if (null != kt.getErrCode())
			{
				LogoutController.loginInfoExpire(httpSession, response);
			}
			else
			{
				Cookie tokenCookie = new Cookie(LoginController.KAKAO_TOKEN_NAME, accessToken);
				tokenCookie.setMaxAge(24 * 60 * 60 * 30); //30일
				tokenCookie.setPath("/");
				response.addCookie(tokenCookie);

				isRefresh = true;
			}
		}
		return isRefresh;
	}

	@Override
	public void postHandle(
		HttpServletRequest request,
		HttpServletResponse response,
		Object handler,
		ModelAndView modelAndView)
		throws Exception
	{
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
		throws Exception
	{
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

}
