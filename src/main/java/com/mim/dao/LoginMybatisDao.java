package com.mim.dao;

import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

@Service
public class LoginMybatisDao
{
	@Inject
	private SqlSessionFactory session;

	/**
	 * �����̷��� �����Ѵ�.
	 */
	public void accessRegist(Map<String, String> map)
	{
		SqlSession sqlSession = session.openSession();
		sqlSession.insert("LoginMapper.access", map);
	}
}
