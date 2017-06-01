package com.wyjk.admin.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.wyjk.admin.common.pagination.PageResult;
import com.wyjk.admin.dao.IBannerDao;
import com.wyjk.admin.entity.Banner;

@Repository
public class BannerDaoImpl implements IBannerDao {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Banner findNextByOrder(Integer sortOrder) {
		String querySql = "from Banner where sortOrder > ?1 order by sortOrder asc";
		TypedQuery<Banner> query = em.createQuery(querySql, Banner.class);
		query.setParameter(1, sortOrder);
		query.setMaxResults(1);
		List<Banner> list = query.getResultList();
		return (list != null && list.size() > 0) ? list.get(0) : null;
	}

	@Override
	public Banner findLastByOrder(Integer sortOrder) {
		String querySql = "from Banner where sortOrder < ?1 order by sortOrder desc";
		TypedQuery<Banner> query = em.createQuery(querySql, Banner.class);
		query.setParameter(1, sortOrder);
		query.setMaxResults(1);
		List<Banner> list = query.getResultList();
		return (list != null && list.size() > 0) ? list.get(0) : null;
	}

	@Override
	public Banner find(Integer id) {
		return em.find(Banner.class, id);
	}

	@Override
	public void update(Banner banner) {
		em.merge(banner);
	}

	@Override
	public void save(Banner banner) {
		em.persist(banner);
	}

	@Override
	public void delete(Integer id) {
		Banner entity = find(id);
		if (entity != null) {
			em.remove(entity );
		}
	}

	@Override
	public PageResult<Banner> findPage(Integer pageNumber, Integer pageSize, String searchText) {
		PageResult<Banner> page = new PageResult<Banner>();
        page.pageNumber = pageNumber == null ? 1 : pageNumber;
        page.pageSize = pageSize == null ? 10 : pageSize;
        
        List<Object> params = new ArrayList<Object>();
        
        StringBuffer sql = new StringBuffer("select t.id, t.icon_url as iconUrl, target_url as targetUrl, t.sort_order as sortOrder from banner t");
        StringBuffer searchSql = new StringBuffer(" where 1=1");
        StringBuffer countSql = new StringBuffer("select count(1) from banner t");
        if (null != searchText) {
            searchSql.append(" AND t.target_url like ?");
            params.add("%" + searchText + "%");
        }
        
        Query query = em.createNativeQuery(sql.append(searchSql).append(" ORDER BY t.sort_order ASC").toString());
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        for (int i = 1; i <= params.size(); i++) {
            query.setParameter(i, params.get(i - 1));
        }
        query.setFirstResult(page.getFirstResult());
        query.setMaxResults(page.pageSize);
        page.list = query.getResultList();
        
        Query queryCount = em.createNativeQuery(countSql.append(searchSql).toString());
        for (int i = 1; i <= params.size(); i++) {
        	queryCount.setParameter(i, params.get(i - 1));
        }
        Object obj = queryCount.getSingleResult();
        if (obj != null) {
            page.total = Integer.parseInt(obj.toString());
        }
        
        return page;
	}

}
