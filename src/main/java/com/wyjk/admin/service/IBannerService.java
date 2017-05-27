package com.wyjk.admin.service;

import com.wyjk.admin.entity.Banner;
import com.wyjk.admin.service.support.IBaseService;

public interface IBannerService extends IBaseService<Banner, Integer> {

	void saveOrUpdate(Banner user);

}
