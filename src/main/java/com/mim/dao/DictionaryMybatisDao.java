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
	 * api �����͸� ����Ѵ�.
	 */
	public void register(Dictionary dict)
	{
		System.out.println("dao");
		SqlSession sqlSession = session.openSession();
		sqlSession.insert("DictionaryMapper.insert", dict);
	}
}
