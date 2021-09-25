package com.mim.emgbell;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

@Service
public class EmgBellMybatisDao
{
	@Inject
	private SqlSessionFactory session;

	/**
	 * api 데이터를 등록한다.
	 */
	public void register(List<EmgBell> list)
	{
		SqlSession sqlSession = session.openSession();
		sqlSession.insert("EmgBellMapper.insert", list);
	}

	/**
	 * 위경도 업데이트가 필요한 데이터를 목록으로 돌려준다.
	 */
	public List<EmgBell> listToGeoUpdate()
	{
		SqlSession sqlSession = session.openSession();
		List<EmgBell> list = sqlSession.selectList("EmgBellMapper.listToGeoUpdate");
		return list;
	}

	/**
	 * 위경도를 업데이트한다.
	 */
	public void mergeGeo(List<EmgBell> list)
	{
		SqlSession sqlSession = session.openSession();
		sqlSession.update("EmgBellMapper.mergeGeo", list);
	}
}
