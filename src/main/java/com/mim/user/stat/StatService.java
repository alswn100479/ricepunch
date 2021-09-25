package com.mim.user.stat;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 접속통계 Service
 * @author minju
 *
 */
@Service
public class StatService
{
	@Autowired
	private StatMybatisDao dao;

	/**
	 * 브라우저 접속률을 조회한다.
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> browser() throws Exception
	{
		return dao.browser();
	}
	
	/**
	 * 접속수를 조회한다.
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> accessCnt() throws Exception
	{
		return dao.accessCnt();
	}
}
