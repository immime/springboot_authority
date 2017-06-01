package com.wyjk.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wyjk.admin.common.pagination.PageResult;
import com.wyjk.admin.dao.IKnowledgeDao;
import com.wyjk.admin.dao.ISecretDao;
import com.wyjk.admin.entity.Knowledge;
import com.wyjk.admin.service.ISecretService;
import com.wyjk.admin.vo.SecretVO;

@Service
public class SecretServiceImpl implements ISecretService {
	
	@Autowired
	private ISecretDao dao;
	@Autowired
	private IKnowledgeDao knowledgeDao;

	@Override
	public PageResult<SecretVO> findAll(Integer pageNumber, Integer pageSize, String searchText) {
		return dao.findAll(pageNumber, pageSize, searchText);
	}

	@Override
	public SecretVO find(Integer id) {
		return dao.find(id);
	}

	@Transactional
	@Override
	public void saveOrUpdate(SecretVO vo) {
		Integer id = vo.getId();
		if (id != null) {
			Knowledge db = knowledgeDao.find(id);
			db.setContent(vo.getContent());
			db.setTitle(vo.getTitle());
			knowledgeDao.update(db);
		} else {
			Knowledge knowledge = new Knowledge();
			knowledge.setCategoryId(2); // 秘籍的栏目id
			knowledge.setContent(vo.getContent());
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
