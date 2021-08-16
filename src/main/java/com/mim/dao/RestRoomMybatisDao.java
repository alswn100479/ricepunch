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
	 * 목록을 돌려준다.
	 */
	public List<RestRoom> getList(String column, String type)
	{
		Map<String, String> map = new HashMap<String, String>();
		map.put("option", column);

		SqlSession sqlSession = session.openSession();
		List<RestRoom> list = sqlSession.selectList("RestRoomMapper.list", map);
		return list;
	}

	/**
	 * api 데이터를 등록한다.
	 */
	public void register(List<RestRoom> list)
	{
		SqlSession sqlSession = session.openSession();
		sqlSession.insert("RestRoomMapper.insert", list);
	}

	/**
	 * 위경도 업데이트가 필요한 데이터를 목록으로 돌려준다.
	 */
	public List<RestRoom> listToGeoUpdate()
	{
		SqlSession sqlSession = session.openSession();
		List<RestRoom> list = sqlSession.selectList("RestRoomMapper.listToGeoUpdate");
		return list;
	}

	/**
	 * 위경도를 업데이트한다.
	 */
	public void mergeGeo(List<RestRoom> list)
	{
		SqlSession sqlSession = session.openSession();
		sqlSession.update("RestRoomMapper.mergeGeo", list);
	}
}
