package com.mim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mim.dao.DictionaryMybatisDao;
import com.mim.domain.Dictionary;

@Service
public class DictionaryService
{
	@Autowired
	private DictionaryMybatisDao dictDao;

	public void register(Dictionary dic)
	{
		System.out.println("service");
		dictDao.register(dic);
	}
}
