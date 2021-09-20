package com.mim.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
	@Autowired
	private UserMybatisDao userDao;

	public User updateUserInfo(User user)
	{
		return userDao.updateUserInfo(user);
	}
}
