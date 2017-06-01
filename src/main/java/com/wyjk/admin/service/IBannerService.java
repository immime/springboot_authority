package com.wyjk.admin.service;

import com.wyjk.admin.common.pagination.PageResult;
import com.wyjk.admin.entity.Banner;

public interface IBannerService {

	void saveOrUpdate(Banner user);

	void updateOrder(Integer id, String upOrDown);

	Banner find(Integer id);

	void delete(Integer id);

	PageResult<Banner> findAll(Integer pageNumber, Integer pageSize, String searchText);

}
