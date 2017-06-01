package com.wyjk.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyjk.admin.common.pagination.PageResult;
import com.wyjk.admin.dao.IKnowledgeDao;
import com.wyjk.admin.dao.ISecretDao;
import com.wyjk.admin.service.ISecretService;
import com.wyjk.admin.vo.SecretVO;

@Service
public class SecretServiceImpl implements ISecretService {
	
	@Autowired
	private ISecretDao dao;
	@Autowired
	private IKnowledgeDao knowledgeDao;

	@Override
	public PageResult<SecretVO> findAll(Integer pageNumber, Integer pageSize, String searchText) {
		return dao.findAll(pageNumber, pageSize, searchText);
	}

	@Override
	public SecretVO find(Integer id) {
		return dao.find(id);
	}

	@Override
	public void saveOrUpdate(SecretVO entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer id) {
		knowledgeDao.delete(id);
	}

	@Override
	public void updateOrder(Integer id, String upOrDown) {
		// TODO Auto-generated method stub

	}

}
