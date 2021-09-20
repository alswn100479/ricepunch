package com.mim.user;

public class User
{
	private Long id;
	private String accessToken;
	private int ageRange;
	private String name;
	private String userDesc;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getAccessToken()
	{
		return accessToken;
	}

	public void setAccessToken(String accessToken)
	{
		this.accessToken = accessToken;
	}

	public int getAgeRange()
	{
		return ageRange;
	}

	public void setAgeRange(int ageRange)
	{
		this.ageRange = ageRange;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the userDesc
	 */
	public String getUserDesc()
	{
		return userDesc;
	}

	/**
	 * @param userDesc the userDesc to set
	 */
	public void setUserDesc(String userDesc)
	{
		this.userDesc = userDesc;
	}
}
