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
	 * api �����͸� ����Ѵ�.
	 */
	public void register(List<EmgBell> list)
	{
		SqlSession sqlSession = session.openSession();
		sqlSession.insert("EmgBellMapper.insert", list);
	}

	/**
	 * ���浵 ������Ʈ�� �ʿ��� �����͸� ������� �����ش�.
	 */
	public List<EmgBell> listToGeoUpdate()
	{
		SqlSession sqlSession = session.openSession();
		List<EmgBell> list = sqlSession.selectList("EmgBellMapper.listToGeoUpdate");
		return list;
	}

	/**
	 * ���浵�� ������Ʈ�Ѵ�.
	 */
	public void mergeGeo(List<EmgBell> list)
	{
		SqlSession sqlSession = session.openSession();
		sqlSession.update("EmgBellMapper.mergeGeo", list);
	}
}
