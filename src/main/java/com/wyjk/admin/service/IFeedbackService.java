package com.wyjk.admin.service;

import com.wyjk.admin.common.pagination.PageResult;
import com.wyjk.admin.vo.FeedbackVO;

public interface IFeedbackService {

	PageResult<FeedbackVO> findAll(Integer pageNumber, Integer pageSize, String searchText);

	FeedbackVO find(Integer id);
	
	void updateStatus(Integer id, Integer targetStatus);
	
}
