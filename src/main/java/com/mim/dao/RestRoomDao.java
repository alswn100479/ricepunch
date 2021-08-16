package com.mim.dao;

import java.util.List;

import com.mim.domain.RestRoom;

public interface RestRoomDao
{
	public List<RestRoom> getList(String column, String type);

	public void register(List<RestRoom> list);

	public List<RestRoom> listToGeoUpdate();
	
	public void mergeGeo(List<RestRoom> list);
}
