package com.wyjk.admin.service;

import com.wyjk.admin.common.pagination.PageResult;
import com.wyjk.admin.vo.KemuPostVO;

public interface IKemu3Service {

	PageResult<KemuPostVO> findAll(Integer pageNumber, Integer pageSize, String searchText);

	KemuPostVO find(Integer id);

	void saveOrUpdate(KemuPostVO entity);

	void delete(Integer id);

	void updateOrder(Integer id, String upOrDown);

}
