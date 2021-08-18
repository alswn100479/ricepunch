package com.mim.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import com.mim.domain.RestRoom;

@Service
public class RestRoomMybatisDao implements RestRoomDao
{
	@Inject
	private SqlSessionFactory session;

	/**
	 * ����� �����ش�.
	 */
	public List<RestRoom> getList(String latitude, String longitude)
	{
		Map<String, String> map = new HashMap<String, String>();
		map.put("latitude", latitude);
		map.put("longitude", longitude);

		SqlSession sqlSession = session.openSession();
		List<RestRoom> list = sqlSession.selectList("RestRoomMapper.list", map);
		return list;
	}

	/**
	 * api �����͸� ����Ѵ�.
	 */
	public void register(List<RestRoom> list)
	{
		SqlSession sqlSession = session.openSession();
		sqlSession.insert("RestRoomMapper.insert", list);
	}

	/**
	 * ���浵 ������Ʈ�� �ʿ��� �����͸� ������� �����ش�.
	 */
	public List<RestRoom> listToGeoUpdate()
	{
		SqlSession sqlSession = session.openSession();
		List<RestRoom> list = sqlSession.selectList("RestRoomMapper.listToGeoUpdate");
		return list;
	}

	/**
	 * ���浵�� ������Ʈ�Ѵ�.
	 */
	public void mergeGeo(List<RestRoom> list)
	{
		SqlSession sqlSession = session.openSession();
		sqlSession.update("RestRoomMapper.mergeGeo", list);
	}

	/**
	 * ���� ���浵�� ������Ʈ�Ѵ�.
	 */
	public void mergeGoogleGeo(List<RestRoom> list)
	{
		SqlSession sqlSession = session.openSession();
		sqlSession.update("RestRoomMapper.mergeGoogleGeo", list);
	}

	public List<RestRoom> listToGoogleGeoUpdate()
	{
		SqlSession sqlSession = session.openSession();
		List<RestRoom> list = sqlSession.selectList("RestRoomMapper.listToGoogleGeoUpdate");
		return list;
	}
}
