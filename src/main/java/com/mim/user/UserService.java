package com.mim.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
	@Autowired
	private UserMybatisDao userDao;

	/**
	 * 사용자 정보를 업데이트한다.
	 * @param user
	 * @return
	 */
	public User updateUserInfo(User user)
	{
		return userDao.updateUserInfo(user);
	}

	/**
	 * 사용자정보를 조회한다.
	 * @param id
	 * @return
	 */
	public User selectUser(Long id)
	{
		return userDao.selectUser(id);
	}
}
