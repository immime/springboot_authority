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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.wyjk.admin.common.pagination.PageResult;
import com.wyjk.admin.dao.INoticeDao;
import com.wyjk.admin.vo.NoticeVO;

@Repository
public class NoticeDaoImpl implements INoticeDao {
	
	@PersistenceContext
	private EntityManager em;
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Override
	public PageResult<NoticeVO> findAll(Integer pageNumber, Integer pageSize, String searchText) {
		// 默认值
		pageNumber = pageNumber == null ? 1 : pageNumber;
		pageSize = pageSize == null ? 10 : pageSize;
    	
    	StringBuffer querySql = new StringBuffer("SELECT t.id, t.title, t.icon_url, t.content FROM knowledge t left join knowledge_category c ON c.id = t.category_id");
    	StringBuffer countSql = new StringBuffer("SELECT count(t.id) from knowledge t left join knowledge_category c ON c.id = t.category_id");
    	StringBuffer where = new StringBuffer( " where c.id=1");
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
    	
    	List<NoticeVO> list = jdbc.query(querySql.toString(), queryParams, new RowMapper<NoticeVO>() {

			@Override
			public NoticeVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				NoticeVO vo = new NoticeVO();
				vo.setId(rs.getInt("id"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setIconUrl(rs.getString("icon_url"));
				return vo;
			}
		});
    	int totalCount = jdbc.queryForObject(countSql.toString(), countParams, Integer.class);
		
    	// 分页封装
		PageResult<NoticeVO> page = new PageResult<NoticeVO>();
		page.pageNumber = pageNumber;
		page.pageSize = pageSize;
		page.total = totalCount;
		page.list = list;
		Map<String, Object> conditions  = new HashMap<String, Object>();
		page.conditions = conditions;
    	
		return page;
	}

	@Override
	public NoticeVO find(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}