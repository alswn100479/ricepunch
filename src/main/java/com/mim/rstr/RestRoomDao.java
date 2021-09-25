package com.mim.rstr;

import java.util.List;

public interface RestRoomDao
{
	public List<RestRoom> getList(String latitude, String longitude, String search);

	public void register(List<RestRoom> list);

	public List<RestRoom> listToGeoUpdate();

	public void mergeGeo(List<RestRoom> list);

	public List<RestRoom> listToGoogleGeoUpdate();

	public void mergeGoogleGeo(List<RestRoom> list);
}
