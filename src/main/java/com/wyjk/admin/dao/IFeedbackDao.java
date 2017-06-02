package com.wyjk.admin.dao;

import com.wyjk.admin.common.pagination.PageResult;
import com.wyjk.admin.vo.FeedbackVO;

public interface IFeedbackDao {

	PageResult<FeedbackVO> findAll(Integer pageNumber, Integer pageSize, String searchText);

	FeedbackVO find(Integer id);

	void updateStatus(Integer id, Integer targetStatus);

}
