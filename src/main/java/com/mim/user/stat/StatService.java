package com.mim.user.stat;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ������� Service
 * @author minju
 *
 */
@Service
public class StatService
{
	@Autowired
	private StatMybatisDao dao;

	/**
	 * ������ ���ӷ��� ��ȸ�Ѵ�.
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> browser() throws Exception
	{
		return dao.browser();
	}
	
	/**
	 * ���Ӽ��� ��ȸ�Ѵ�.
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> accessCnt() throws Exception
	{
		return dao.accessCnt();
	}
}
