package com.mim.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mim.dao.LoginMybatisDao;

@Service
public class LoginService
{
	@Autowired
	private LoginMybatisDao loginDao;

	public void access(Map<String, String> map)
	{
		loginDao.accessRegist(map);
	}
}