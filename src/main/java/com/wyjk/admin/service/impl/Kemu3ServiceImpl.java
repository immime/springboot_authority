package com.wyjk.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wyjk.admin.common.pagination.PageResult;
import com.wyjk.admin.dao.IKemu2Dao;
import com.wyjk.admin.dao.IKnowledgeDao;
import com.wyjk.admin.entity.Knowledge;
import com.wyjk.admin.service.IKemu3Service;
import com.wyjk.admin.vo.KemuPostVO;

@Service
public class Kemu3ServiceImpl implements IKemu3Service {

	@Autowired
	private IKemu2Dao dao;
	@Autowired
	private IKnowledgeDao knowledgeDao;
	
	@Override
	public PageResult<KemuPostVO> findAll(Integer pageNumber, Integer pageSize, String searchText) {
		return dao.findAll(pageNumber, pageSize, searchText);
	}

	@Override
	public KemuPostVO find(Integer id) {
		return dao.find(id);
	}

	@Transactional
	@Override
	public void saveOrUpdate(KemuPostVO vo) {
		Integer id = vo.getId();
		if (id != null) {
			Knowledge db = knowledgeDao.find(id);
			db.setContent(vo.getContent());
			db.setDescription(vo.getDescription());
			db.setThumbUrl(vo.getThumbUrl());
			db.setVideoUrl(vo.getVideoUrl());
			db.setTitle(vo.getTitle());
			knowledgeDao.update(db);
		} else {
			Knowledge knowledge = new Knowledge();
			knowledge.setCategoryId(4); // 科目三文案的栏目id
			knowledge.setContent(vo.getContent());
			knowledge.setDescription(vo.getDescription());
			knowledge.setThumbUrl(vo.getThumbUrl());
			knowledge.setVideoUrl(vo.getVideoUrl());
			knowledge.setTitle(vo.getTitle());
			knowledge.setStatus(1);
			knowledgeDao.add(knowledge);
		}
	}

	@Transactional
	@Override
	public void delete(Integer id) {
		knowledgeDao.updateStatus(id, 0);
	}

	@Transactional
	@Override
	public void updateOrder(Integer id, String upOrDown) {
		// TODO Auto-generated method stub

	}

}
