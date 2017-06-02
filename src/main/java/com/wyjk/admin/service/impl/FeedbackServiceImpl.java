package com.wyjk.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyjk.admin.common.pagination.PageResult;
import com.wyjk.admin.dao.IFeedbackDao;
import com.wyjk.admin.service.IFeedbackService;
import com.wyjk.admin.vo.FeedbackVO;

@Service
public class FeedbackServiceImpl implements IFeedbackService {
	
	@Autowired
	private IFeedbackDao dao;

	@Override
	public PageResult<FeedbackVO> findAll(Integer pageNumber, Integer pageSize, String searchText) {
		return dao.findAll(pageNumber, pageSize, searchText);
	}

	@Override
	public FeedbackVO find(Integer id) {
		return dao.find(id);
	}

	@Override
	public void updateStatus(Integer id, Integer targetStatus) {
		dao.updateStatus(id, targetStatus);
	}

}
