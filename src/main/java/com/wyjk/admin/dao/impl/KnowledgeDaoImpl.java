package com.wyjk.admin.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.wyjk.admin.dao.IKnowledgeDao;
import com.wyjk.admin.entity.Knowledge;

@Repository
public class KnowledgeDaoImpl implements IKnowledgeDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void add(Knowledge knowledge) {
		em.persist(knowledge);
	}

	@Override
	public void update(Knowledge knowledge) {
		em.merge(knowledge);
	}

	@Override
	public void delete(Integer id) {
		Knowledge find = em.find(Knowledge.class, id);
		if (find != null) {
			em.remove(find);
		}
	}

	@Override
	public Knowledge find(Integer id) {
		return em.find(Knowledge.class, id);
	}

	@Override
	public void updateStatus(Integer id, Integer status) {
		String sql = "update Knowledge set status = :status where id = :id";
		Query query = em.createQuery(sql);
		query.setParameter("status", status);
		query.setParameter("id", id);
		query.executeUpdate();
	}

}
