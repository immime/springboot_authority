package com.wyjk.admin.dao;

import org.springframework.stereotype.Repository;

import com.wyjk.admin.dao.support.IBaseDao;
import com.wyjk.admin.entity.Banner;

@Repository
public interface IBannerDao extends IBaseDao<Banner, Integer> {

	Banner findByCategory(String username);

}
