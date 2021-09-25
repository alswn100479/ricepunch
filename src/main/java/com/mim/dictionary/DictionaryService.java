package com.mim.dictionary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
