package com.wyjk.admin.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.wyjk.admin.common.pagination.PageResult;
import com.wyjk.admin.dao.ISecretDao;
import com.wyjk.admin.vo.SecretVO;

@Repository
public class SecretDaoImpl implements ISecretDao {
	
	@PersistenceContext
	private EntityManager em;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public PageResult<SecretVO> findAll(Integer pageNumber, Integer pageSize, String searchText) {
		// 默认值
		pageNumber = pageNumber == null ? 1 : pageNumber;
		pageSize = pageSize == null ? 10 : pageSize;
    	
    	StringBuffer querySql = new StringBuffer("SELECT t.id, t.title, t.content FROM knowledge t left join knowledge_category c ON c.id = t.category_id");
    	StringBuffer countSql = new StringBuffer("SELECT count(t.id) from knowledge t left join knowledge_category c ON c.id = t.category_id");
    	StringBuffer where = new StringBuffer( " where c.id=2"); // 秘籍category_id=2
    	StringBuffer order = new StringBuffer(" order by t.gmt_create DESC");
    	Map<String,Object> queryParams = new HashMap<>();
    	Map<String,Object> countParams = new HashMap<>();
    	//设置where查询条件 可选,注意前面空格
    	if(StringUtils.isNotEmpty(searchText)){
    	    where.append(" and t.title like :searchText");
    	    queryParams.put("searchText", "%"+searchText+"%");
    	    countParams.put("searchText", "%"+searchText+"%");
    	}
    	//组装查询语句
    	querySql.append(where).append(order).append(" limit :start, :offset");
    	
    	//组装总数统计sql
    	countSql.append(where);
    	
		queryParams.put("start", (pageNumber - 1) * pageSize);
		queryParams.put("offset", pageSize);
		
		NamedParameterJdbcTemplate jdbc = new NamedParameterJdbcTemplate(jdbcTemplate);
    	
    	List<SecretVO> list = jdbc.query(querySql.toString(), queryParams, new RowMapper<SecretVO>() {

			@Override
			public SecretVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				SecretVO vo = new SecretVO();
				vo.setId(rs.getInt("id"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				return vo;
			}
		});
    	int totalCount = jdbc.queryForObject(countSql.toString(), countParams, Integer.class);
		
    	// 分页封装
		PageResult<SecretVO> page = new PageResult<>();
		page.pageNumber = pageNumber;
		page.pageSize = pageSize;
		page.total = totalCount;
		page.list = list;
		Map<String, Object> conditions  = new HashMap<String, Object>();
		page.conditions = conditions;
    	
		return page;
	}
	
	@Override
	public SecretVO find(Integer id) {
		String sql = "SELECT t.id, t.title, t.content FROM knowledge t where t.id = ?";
		return jdbcTemplate.query(sql, new ResultSetExtractor<SecretVO>() {

			@Override
			public SecretVO extractData(ResultSet rs) throws SQLException, DataAccessException {
				while(rs.next()) {
					SecretVO vo = new SecretVO();
					vo.setId(rs.getInt("id"));
					vo.setTitle(rs.getString("title"));
					vo.setContent(rs.getString("content"));
					return vo;
				}
				return null;
			}
		}, new Object[] { id });
	}
	
	
}
