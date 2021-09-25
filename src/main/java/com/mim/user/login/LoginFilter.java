package com.mim.user.login;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mim.user.User;

public class LoginFilter implements Filter
{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException,
			ServletException
	{
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession httpSession = req.getSession();

		boolean isLogout = false;
		String accessToken = null;
		String refreshToken = null;
		for (Cookie cookie : req.getCookies())
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

		// cookie�� ��ū�� ���� ��
		if (null != accessToken)
		{
			boolean isExpire = checkTokenExpire(accessToken);
			if (isExpire)
			{
				boolean isRefresh = refreshAccessToken(httpSession, res, accessToken, refreshToken);
				isLogout = isRefresh ? false : true;
			}
		}
		else
		{
			isLogout = true;
		}

		// session �ٽ� set
		if (null == httpSession.getAttribute("user") && !isLogout)
		{
			User user = LoginController.getUserInfo(accessToken);
			httpSession.setAttribute("user", user);
		}

		chain.doFilter(request, response);
	}

	/**
	 * accessToken ��ȿ���� üũ�Ѵ�.
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
	 * accessToken�� ��ȿ���� üũ�ϰ� ����Ǹ� �����Ѵ�.
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

		// accessToken �����
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
				tokenCookie.setMaxAge(24 * 60 * 60 * 30); //30��
				tokenCookie.setPath("/");
				response.addCookie(tokenCookie);

				isRefresh = true;
			}
		}
		return isRefresh;
	}

	@Override
	public void destroy()
	{
	}
}
