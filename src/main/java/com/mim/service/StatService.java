package com.mim.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mim.dao.StatMybatisDao;

@Service
public class StatService
{
	@Autowired
	private StatMybatisDao dao;

	public List<Map<String, Object>> browser() throws Exception
	{
		return dao.browser();
	}
}
