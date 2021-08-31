package com.mim.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

@Service
public class StatMybatisDao
{
	@Inject
	private SqlSessionFactory session;

	public List<Map<String, Object>> browser()
	{
		SqlSession sqlSession = session.openSession();
		List<Map<String, Object>> result = sqlSession.selectList("statMapper.browser");
		return result;
	}
}
