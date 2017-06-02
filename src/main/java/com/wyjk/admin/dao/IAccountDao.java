package com.wyjk.admin.dao;

import com.wyjk.admin.common.pagination.PageResult;
import com.wyjk.admin.vo.AccountVO;

public interface IAccountDao {

	PageResult<AccountVO> findAll(Integer pageNumber, Integer pageSize, String searchText);

	AccountVO find(Integer id);

	void updateStatus(Integer id, int targetStatus);

}
