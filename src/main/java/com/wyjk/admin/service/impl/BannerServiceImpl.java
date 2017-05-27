package com.wyjk.admin.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyjk.admin.common.utils.MD5Utils;
import com.wyjk.admin.dao.IBannerDao;
import com.wyjk.admin.dao.support.IBaseDao;
import com.wyjk.admin.entity.Banner;
import com.wyjk.admin.entity.User;
import com.wyjk.admin.service.IBannerService;
import com.wyjk.admin.service.support.impl.BaseServiceImpl;

@Service
public class BannerServiceImpl extends BaseServiceImpl<Banner, Integer> implements IBannerService {

	@Autowired
	private IBannerDao dao;

	@Override
	public void saveOrUpdate(Banner banner) {
		if (banner.getId() != null) {
			Banner db = find(banner.getId());
			db.setIconUrl(banner.getIconUrl());
			db.setTargetUrl(banner.getTargetUrl());
			db.setSortOrder(banner.getSortOrder());
			update(db);
		} else {
			banner.setStatus(1);
			save(banner);
		}
	}

	@Override
	public IBaseDao<Banner, Integer> getBaseDao() {
		return this.dao;
	}

}
