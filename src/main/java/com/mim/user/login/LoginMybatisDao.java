package com.mim.user.login;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import com.mim.user.User;

/**
 * �α��� Dao
 */
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

	public void insertLoginLog(User user, int status)
	{
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", String.valueOf(user.getId()));
		map.put("status", String.valueOf(status));

		SqlSession sqlSession = session.openSession();
		sqlSession.insert("LoginMapper.loginOutHistory", map);
	}

	public void insertLogoutLog(Long userId, int status)
	{
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", String.valueOf(userId));
		map.put("status", String.valueOf(status));

		SqlSession sqlSession = session.openSession();
		sqlSession.insert("LoginMapper.loginOutHistory", map);
	}

	public void insertUser(User user)
	{
		SqlSession sqlSession = session.openSession();
		sqlSession.insert("LoginMapper.insertUser", user);
	}
}
