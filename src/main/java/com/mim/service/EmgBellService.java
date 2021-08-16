package com.mim.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mim.dao.EmgBellMybatisDao;
import com.mim.domain.EmgBell;

@Service
public class EmgBellService
{
	@Autowired
	private EmgBellMybatisDao emgBellDao;

	public void register(List<EmgBell> list)
	{
		emgBellDao.register(list);
	}

	public List<EmgBell> listToGeoUpdate() throws Exception
	{
		return emgBellDao.listToGeoUpdate();
	}

	public void mergeGeo(List<EmgBell> list)
	{
		emgBellDao.mergeGeo(list);
	}
}
