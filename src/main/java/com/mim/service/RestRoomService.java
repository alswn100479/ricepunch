package com.mim.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mim.dao.RestRoomDao;
import com.mim.domain.RestRoom;

@Service
public class RestRoomService
{
	@Autowired
	private RestRoomDao rstrDao;

	public List<RestRoom> list(String column, String type) throws Exception
	{
		return rstrDao.getList(column, type);
	}

	public List<RestRoom> listToGeoUpdate() throws Exception
	{
		return rstrDao.listToGeoUpdate();
	}

	public void register(List<RestRoom> list)
	{
		rstrDao.register(list);
	}

	public void mergeGeo(List<RestRoom> list)
	{
		rstrDao.mergeGeo(list);
	}
}
