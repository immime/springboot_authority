package com.wyjk.admin.dao;

import com.wyjk.admin.common.pagination.PageResult;
import com.wyjk.admin.vo.NoticeVO;

public interface INoticeDao {

	PageResult<NoticeVO> findAll(Integer pageNumber, Integer pageSize, String searchText);

	NoticeVO find(Integer id);

}
