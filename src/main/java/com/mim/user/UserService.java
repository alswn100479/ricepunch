package com.mim.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
	@Autowired
	private UserMybatisDao userDao;

	/**
	 * ����� ������ ������Ʈ�Ѵ�.
	 * @param user
	 * @return
	 */
	public User updateUserInfo(User user)
	{
		return userDao.updateUserInfo(user);
	}

	/**
	 * ����������� ��ȸ�Ѵ�.
	 * @param id
	 * @return
	 */
	public User selectUser(Long id)
	{
		return userDao.selectUser(id);
	}
}
