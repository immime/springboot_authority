package com.wyjk.admin.service;

import com.wyjk.admin.common.pagination.PageResult;
import com.wyjk.admin.vo.NoticeVO;

public interface INoticeService {

	PageResult<NoticeVO> findAll(Integer pageNumber, Integer pageSize, String searchText);

	NoticeVO find(Integer id);

	void saveOrUpdate(NoticeVO entity);

	void delete(Integer id);

	void updateOrder(Integer id, String upOrDown);

}
