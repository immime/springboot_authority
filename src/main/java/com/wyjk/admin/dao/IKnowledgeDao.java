package com.wyjk.admin.dao;

import com.wyjk.admin.entity.Knowledge;

public interface IKnowledgeDao {
	
	void add(Knowledge knowledge);

	void update(Knowledge knowledge);

	void delete(Integer id);

	Knowledge find(Integer id);
	
}
