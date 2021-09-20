package com.mim.user;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

@Service
public class UserMybatisDao
{
	@Inject
	private SqlSessionFactory session;

	/**
	 * api 데이터를 등록한다.
	 * @return 
	 */
	public User updateUserInfo(User user)
	{
		SqlSession sqlSession = session.openSession();
		sqlSession.update("UserMapper.updateUserInfo", user);
		User userServer = sqlSession.selectOne("UserMapper.selectUser", user.getId());
		return userServer;
	}
}
