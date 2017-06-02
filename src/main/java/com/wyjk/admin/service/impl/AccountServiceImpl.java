package com.wyjk.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyjk.admin.common.pagination.PageResult;
import com.wyjk.admin.dao.IAccountDao;
import com.wyjk.admin.service.IAccountService;
import com.wyjk.admin.vo.AccountVO;

@Service
public class AccountServiceImpl implements IAccountService {

	@Autowired
	private IAccountDao dao;
	
	@Override
	public PageResult<AccountVO> findAll(Integer pageNumber, Integer pageSize, String searchText) {
		return dao.findAll(pageNumber, pageSize, searchText);
	}

	@Override
	public AccountVO find(Integer id) {
		return dao.find(id);
	}

	@Override
	public void updateStatus(Integer id, int targetStatus) {
		dao.updateStatus(id, targetStatus);
	}

}
