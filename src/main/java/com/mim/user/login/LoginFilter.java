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
		HttpSession session = req.getSession();

		// 로그인 세션이 남아있을 때
		if (null != session.getAttribute("user"))
		{
			User user = (User) session.getAttribute("user");
			KaKaoToken sessionKt = user.getKakaoToken();

			// 토큰 유효성 체크
			if (null != user && null != sessionKt)
			{
				KaKaoToken kt = LoginController.getTokenInfo(sessionKt.getAccessToken());

				// 에러
				if (null != kt.getErrCode())
				{
					// accessToken만료 -> 갱신 필요
					if (kt.getErrCode().equals("-401"))
					{
						KaKaoToken refreshKt = LoginController.tokenRefresh(sessionKt.getRefreshToken());
						// refreshToken도 만료
						if (null != refreshKt.getErrCode())
						{
							session.invalidate();
							new Cookie(LoginController.KAKAO_TOKEN_NAME, null);
						}
						// 갱신 성공
						else
						{
							user.setKakaoToken(refreshKt);
							session.setAttribute("user", user);

							Cookie tokenCookie = new Cookie(
								LoginController.KAKAO_TOKEN_NAME, refreshKt.getAccessToken());
							tokenCookie.setMaxAge(24 * 60 * 60 * 30); //30일
							tokenCookie.setPath("/");
							res.addCookie(tokenCookie);
						}
					}
				}
			}
		}

		chain.doFilter(request, response);
	}

	@Override
	public void destroy()
	{
	}
}
