package com.mim.user.login;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mim.user.User;

/**
 * ·Î±×ÀÎ Service
 */
@Service
public class LoginService
{
	@Autowired
	private LoginMybatisDao loginDao;

	public void access(Map<String, String> map)
	{
		loginDao.accessRegist(map);
	}

	public void insertUser(User user)
	{
		loginDao.insertUser(user);
	}

	public void insertLoginLog(User user, int status)
	{
		loginDao.insertLoginLog(user, status);
	}

	public void insertLogoutLog(Long userId, int status)
	{
		loginDao.insertLogoutLog(userId, status);
	}
}