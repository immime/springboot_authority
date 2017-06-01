package com.wyjk.admin.dao;

import org.springframework.stereotype.Repository;

import com.wyjk.admin.common.pagination.PageResult;
import com.wyjk.admin.entity.Banner;

@Repository
public interface IBannerDao {

	Banner findNextByOrder(Integer sortOrder);

	Banner findLastByOrder(Integer sortOrder);

	Banner find(Integer id);

	void update(Banner db);

	void save(Banner banner);

	void delete(Integer id);

	PageResult<Banner> findPage(Integer pageNumber, Integer pageSize, String searchText);

}
