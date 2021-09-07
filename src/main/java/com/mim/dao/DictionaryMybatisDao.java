package com.mim.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import com.mim.domain.Dictionary;

@Service
public class DictionaryMybatisDao
{
	@Inject
	private SqlSessionFactory session;

	/**
	 * api 데이터를 등록한다.
	 */
	public void register(Dictionary dict)
	{
		System.out.println("dao");
		SqlSession sqlSession = session.openSession();
		sqlSession.insert("DictionaryMapper.insert", dict);
	}
}
