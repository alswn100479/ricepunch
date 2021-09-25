package com.mim.user.login;

/**
 * 카카오로그인 토큰 정보 Class
 */
public class KaKaoToken
{
	private Long id;
	private Long appId;
	private String accessToken;
	private String refreshToken;
	private Long refreshTokenExpireIn;
	private Long expireIn;
	private String tokenType;
	private String errCode;

	/**
	 * @return the id
	 */
	public Long getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id)
	{
		this.id = id;
	}

	/**
	 * @return the appId
	 */
	public Long getAppId()
	{
		return appId;
	}

	/**
	 * @param appId the appId to set
	 */
	public void setAppId(Long appId)
	{
		this.appId = appId;
	}

	public String getAccessToken()
	{
		return accessToken;
	}

	public void setAccessToken(String accessToken)
	{
		this.accessToken = accessToken;
	}

	/**
	 * @return the refreshToken
	 */
	public String getRefreshToken()
	{
		return refreshToken;
	}

	/**
	 * @param refreshToken the refreshToken to set
	 */
	public void setRefreshToken(String refreshToken)
	{
		this.refreshToken = refreshToken;
	}

	/**
	 * @return the refreshTokenExpireIn
	 */
	public Long getRefreshTokenExpireIn()
	{
		return refreshTokenExpireIn;
	}

	/**
	 * @param refreshTokenExpireIn the refreshTokenExpireIn to set
	 */
	public void setRefreshTokenExpireIn(Long refreshTokenExpireIn)
	{
		this.refreshTokenExpireIn = refreshTokenExpireIn;
	}

	/**
	 * @return the expireIn
	 */
	public Long getExpireIn()
	{
		return expireIn;
	}

	/**
	 * @param expireIn the expireIn to set
	 */
	public void setExpireIn(Long expireIn)
	{
		this.expireIn = expireIn;
	}

	/**
	 * @return the tokenType
	 */
	public String getTokenType()
	{
		return tokenType;
	}

	/**
	 * @param tokenType the tokenType to set
	 */
	public void setTokenType(String tokenType)
	{
		this.tokenType = tokenType;
	}

	/**
	 * @return the errCode
	 */
	public String getErrCode()
	{
		return errCode;
	}

	/**
	 * @param errCode the errCode to set
	 */
	public void setErrCode(String errCode)
	{
		this.errCode = errCode;
	}
}
