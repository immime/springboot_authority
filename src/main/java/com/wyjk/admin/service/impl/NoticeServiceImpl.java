package com.wyjk.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wyjk.admin.common.pagination.PageResult;
import com.wyjk.admin.dao.IKnowledgeDao;
import com.wyjk.admin.dao.INoticeDao;
import com.wyjk.admin.entity.Banner;
import com.wyjk.admin.entity.Knowledge;
import com.wyjk.admin.service.INoticeService;
import com.wyjk.admin.vo.NoticeVO;

@Service
public class NoticeServiceImpl implements INoticeService {
	
	@Autowired
	private INoticeDao dao;
	@Autowired
	private IKnowledgeDao knowledgeDao;

	@Override
	public PageResult<NoticeVO> findAll(Integer pageNumber, Integer pageSize, String searchText) {
		return dao.findAll(pageNumber, pageSize, searchText);
	}

	@Override
	public NoticeVO find(Integer id) {
		return dao.find(id);
	}

	@Transactional
	@Override
	public void saveOrUpdate(NoticeVO entity) {
		Integer id = entity.getId();
		if (id != null) {
			Knowledge db = knowledgeDao.find(id);
			db.setContent(entity.getContent());
			db.setIconUrl(entity.getIconUrl());
			db.setTitle(entity.getTitle());
			knowledgeDao.update(db);
		} else {
			Knowledge knowledge = new Knowledge();
			knowledge.setContent(entity.getContent());
			knowledge.setIconUrl(entity.getIconUrl());
			knowledge.setTitle(entity.getTitle());
			knowledgeDao.add(knowledge);
		}
	}

	@Transactional
	@Override
	public void delete(Integer id) {
		knowledgeDao.delete(id);
	}

	@Transactional
	@Override
	public void updateOrder(Integer id, String upOrDown) {
		// TODO Auto-generated method stub
		
	}

}
