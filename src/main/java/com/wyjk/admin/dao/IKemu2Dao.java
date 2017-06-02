package com.wyjk.admin.dao;

import com.wyjk.admin.common.pagination.PageResult;
import com.wyjk.admin.vo.KemuPostVO;

public interface IKemu2Dao {

	PageResult<KemuPostVO> findAll(Integer pageNumber, Integer pageSize, String searchText);

	KemuPostVO find(Integer id);
	
}
