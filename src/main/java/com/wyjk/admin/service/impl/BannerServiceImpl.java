package com.wyjk.admin.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyjk.admin.common.pagination.PageResult;
import com.wyjk.admin.dao.IBannerDao;
import com.wyjk.admin.entity.Banner;
import com.wyjk.admin.service.IBannerService;

@Service
public class BannerServiceImpl implements IBannerService {

	@Autowired
	private IBannerDao dao;

	@Override
	public void saveOrUpdate(Banner banner) {
		if (banner.getId() != null) {
			Banner db = dao.find(banner.getId());
			db.setIconUrl(banner.getIconUrl());
			db.setTargetUrl(banner.getTargetUrl());
			db.setSortOrder(banner.getSortOrder());
			dao.update(db);
		} else {
			banner.setStatus(1);
			dao.save(banner);
		}
	}

	@Override
	public void updateOrder(Integer id, String upOrDown) {
		Banner src = dao.find(id);
		if (src == null) {
			return;
		}
		if (StringUtils.isEmpty(upOrDown)) {
			return;
		}
		Banner target = null;
		Integer sortOrder = src.getSortOrder();
		if (upOrDown.equalsIgnoreCase("down")) {
			target = dao.findNextByOrder(sortOrder);
		}
		if (upOrDown.equalsIgnoreCase("up")) {
			target = dao.findLastByOrder(sortOrder);
		}
		if (target == null) {
			return;
		}
		Integer srcOrder = src.getSortOrder();
		Integer targetOrder = target.getSortOrder();
		
		src.setSortOrder(targetOrder);
		target.setSortOrder(srcOrder);
		
//		dao.update(src);
//		dao.update(target);
		
	}

	@Override
	public Banner find(Integer id) {
		return dao.find(id);
	}

	@Override
	public void delete(Integer id) {
		dao.delete(id);
	}

	@Override
	public PageResult<Banner> findAll(Integer pageNumber, Integer pageSize, String searchText) {
		return dao.findPage(pageNumber, pageSize, searchText);
	}

}
