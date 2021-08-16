package com.mim.domain;

import java.util.Date;

public class EmgBell
{
	private int id;
	private int instPrpsCode; //설치목적
	private int itlpcTypeCode; //설치장소유형
	private String name; //설치위치
	private String rdnmadr; //소재지도로명주소
	private String lnmadr; //소재지지번주소
	private String latitude; //위도
	private String longitude; //경도
	private String naverLatitude;
	private String naverLongitude;
	private int cntcMthdCode; //연계방식
	private int polcCntcYn;
	private int gurdCntcYn;
	private int officeCntcYn;
	private int safechkTypeYn;
	private String adiFnct;
	private int instYear;
	private Date safechkDate;
	private String institutionNm;
	private String phoneNum;
	private Date instDate;
	private int insttCode;
	private int insttName;

	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public int getInstPrpsCode()
	{
		return instPrpsCode;
	}
	public void setInstPrpsCode(int instPrpsCode)
	{
		this.instPrpsCode = instPrpsCode;
	}
	public int getItlpcTypeCode()
	{
		return itlpcTypeCode;
	}
	public void setItlpcTypeCode(int itlpcTypeCode)
	{
		this.itlpcTypeCode = itlpcTypeCode;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getRdnmadr()
	{
		return rdnmadr;
	}
	public void setRdnmadr(String rdnmadr)
	{
		this.rdnmadr = rdnmadr;
	}
	public String getLnmadr()
	{
		return lnmadr;
	}
	public void setLnmadr(String lnmadr)
	{
		this.lnmadr = lnmadr;
	}
	public String getLatitude()
	{
		return latitude;
	}
	public void setLatitude(String latitude)
	{
		this.latitude = latitude;
	}
	public String getLongitude()
	{
		return longitude;
	}
	public void setLongitude(String longitude)
	{
		this.longitude = longitude;
	}
	public String getNaverLatitude()
	{
		return naverLatitude;
	}
	public void setNaverLatitude(String naverLatitude)
	{
		this.naverLatitude = naverLatitude;
	}
	public String getNaverLongitude()
	{
		return naverLongitude;
	}
	public void setNaverLongitude(String naverLongitude)
	{
		this.naverLongitude = naverLongitude;
	}
	public int getCntcMthdCode()
	{
		return cntcMthdCode;
	}
	public void setCntcMthdCode(int cntcMthdCode)
	{
		this.cntcMthdCode = cntcMthdCode;
	}
	public int getPolcCntcYn()
	{
		return polcCntcYn;
	}
	public void setPolcCntcYn(int polcCntcYn)
	{
		this.polcCntcYn = polcCntcYn;
	}
	public int getGurdCntcYn()
	{
		return gurdCntcYn;
	}
	public void setGurdCntcYn(int gurdCntcYn)
	{
		this.gurdCntcYn = gurdCntcYn;
	}
	public int getOfficeCntcYn()
	{
		return officeCntcYn;
	}
	public void setOfficeCntcYn(int officeCntcYn)
	{
		this.officeCntcYn = officeCntcYn;
	}
	
	public int getSafechkTypeYn()
	{
		return safechkTypeYn;
	}
	public void setSafechkTypeYn(int safechkTypeYn)
	{
		this.safechkTypeYn = safechkTypeYn;
	}
	public String getAdiFnct()
	{
		return adiFnct;
	}
	public void setAdiFnct(String adiFnct)
	{
		this.adiFnct = adiFnct;
	}
	public int getInstYear()
	{
		return instYear;
	}
	public void setInstYear(int instYear)
	{
		this.instYear = instYear;
	}
	public Date getSafechkDate()
	{
		return safechkDate;
	}
	public void setSafechkDate(Date safechkDate)
	{
		this.safechkDate = safechkDate;
	}
	public String getInstitutionNm()
	{
		return institutionNm;
	}
	public void setInstitutionNm(String institutionNm)
	{
		this.institutionNm = institutionNm;
	}
	public String getPhoneNum()
	{
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum)
	{
		this.phoneNum = phoneNum;
	}
	public Date getInstDate()
	{
		return instDate;
	}
	public void setInstDate(Date instDate)
	{
		this.instDate = instDate;
	}
	
	public int getInsttCode()
	{
		return insttCode;
	}
	public void setInsttCode(int insttCode)
	{
		this.insttCode = insttCode;
	}
	public int getInsttName()
	{
		return insttName;
	}
	public void setInsttName(int insttName)
	{
		this.insttName = insttName;
	}
}
