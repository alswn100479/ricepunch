package com.mim.user;

public class User
{
	private Long id;
	private String accessToken;
	private int ageRange;
	private String name;

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
}
