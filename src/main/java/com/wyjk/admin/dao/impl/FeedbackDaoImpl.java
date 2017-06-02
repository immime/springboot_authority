package com.wyjk.admin.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.wyjk.admin.dao.IFeedbackDao;
import com.wyjk.admin.vo.FeedbackVO;

@Repository
public class FeedbackDaoImpl implements IFeedbackDao {
	
	@PersistenceContext
	private EntityManager em;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public PageResult<FeedbackVO> findAll(Integer pageNumber, Integer pageSize, String searchText) {
		// 默认值
		pageNumber = pageNumber == null ? 1 : pageNumber;
		pageSize = pageSize == null ? 10 : pageSize;
    	
    	StringBuffer querySql = new StringBuffer("SELECT t.id, t.content, t.gmt_create AS gmtCreate, t.gmt_modified AS gmtModified, t.image_urls AS imageUrls, t.ip, t.nickname, t.phone, t.platform, t.status FROM feedback t");
    	StringBuffer countSql = new StringBuffer("SELECT count(t.id) FROM feedback t");
    	StringBuffer where = new StringBuffer( " WHERE t.status=1");
    	StringBuffer order = new StringBuffer(" ORDER BY t.gmt_create DESC");
    	Map<String,Object> queryParams = new HashMap<>();
    	Map<String,Object> countParams = new HashMap<>();
    	//设置where查询条件 可选,注意前面空格
    	if(StringUtils.isNotEmpty(searchText)){
    	    where.append(" and t.phone like :searchText");
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
    	
    	List<FeedbackVO> list = jdbc.query(querySql.toString(), queryParams, new RowMapper<FeedbackVO>() {

			@Override
			public FeedbackVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				return packResultSetData(rs);
			}
		});
    	int totalCount = jdbc.queryForObject(countSql.toString(), countParams, Integer.class);
		
    	// 分页封装
		PageResult<FeedbackVO> page = new PageResult<>();
		page.pageNumber = pageNumber;
		page.pageSize = pageSize;
		page.total = totalCount;
		page.list = list;
		Map<String, Object> conditions  = new HashMap<String, Object>();
		page.conditions = conditions;
    	
		return page;
	}

	@Override
	public FeedbackVO find(Integer id) {
		String sql = "SELECT t.id, t.content, t.gmt_create AS gmtCreate, t.gmt_modified AS gmtModified, t.image_urls AS imageUrls, t.ip, t.nickname, t.phone, t.platform, t.status FROM feedback t where t.id = ?";
		
		FeedbackVO vo = jdbcTemplate.query(sql, new ResultSetExtractor<FeedbackVO>() {

			@Override
			public FeedbackVO extractData(ResultSet rs) throws SQLException, DataAccessException {
				while(rs.next()) {
					return packResultSetData(rs);
				}
				return null;
			}
		}, new Object[] { id });
		
		return vo;
	}

	@Override
	public void updateStatus(Integer id, Integer targetStatus) {
		jdbcTemplate.update("update feedback set status = ? where id = ?", new Object[]{ targetStatus, id });
	}
	
	/**
	 * ResultSet封装为VO
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private FeedbackVO packResultSetData(ResultSet rs) throws SQLException {
		FeedbackVO vo = new FeedbackVO();
		vo.setId(rs.getInt("id"));
		vo.setContent(rs.getString("content"));
		vo.setGmtCreate(rs.getDate("gmtCreate"));
		vo.setGmtModified(rs.getDate("gmtModified"));
		vo.setImageUrls(rs.getString("imageUrls"));
		vo.setIp(rs.getString("ip"));
		vo.setNickname(rs.getString("nickname"));
		vo.setPhone(rs.getString("phone"));
		vo.setPlatform(rs.getString("platform"));
		vo.setStatus(rs.getInt("status"));
		
		// imageUrls转为list
		if (vo != null && StringUtils.isNotBlank(vo.getImageUrls())) {
			List<String> urls = new ArrayList<>();
			String imageUrls = vo.getImageUrls().trim();
			if (imageUrls.contains(",")) {
				urls = Arrays.asList(imageUrls.split(","));
			} else {
				urls.add(imageUrls);
			}
			vo.setImgs(urls);
		}
		
		return vo;
	}

}
