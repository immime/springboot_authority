package com.wyjk.admin.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.wyjk.admin.common.pagination.PageResult;
import com.wyjk.admin.dao.IAccountDao;
import com.wyjk.admin.vo.AccountVO;

@Repository
public class AccountDaoImpl implements IAccountDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public PageResult<AccountVO> findAll(Integer pageNumber, Integer pageSize, String searchText) {
		// 默认值
		pageNumber = pageNumber == null ? 1 : pageNumber;
		pageSize = pageSize == null ? 10 : pageSize;

		StringBuffer querySql = new StringBuffer(
				"SELECT t.id, t.nickname, t.mobile, t.gender, t.gmt_create AS gmtCreate, t.status FROM user t");
		StringBuffer countSql = new StringBuffer("SELECT count(t.id) FROM user t");
		StringBuffer where = new StringBuffer(" WHERE t.status=1");
		StringBuffer order = new StringBuffer(" ORDER BY t.gmt_create DESC");
		Map<String, Object> queryParams = new HashMap<>();
		Map<String, Object> countParams = new HashMap<>();
		// 设置where查询条件 可选,注意前面空格
		if (StringUtils.isNotEmpty(searchText)) {
			where.append(" AND (t.mobile LIKE :searchText OR t.nickname LIKE :searchText)");
			queryParams.put("searchText", "%" + searchText + "%");
			countParams.put("searchText", "%" + searchText + "%");
		}
		// 组装查询语句
		querySql.append(where).append(order).append(" LIMIT :start, :offset");

		// 组装总数统计sql
		countSql.append(where);

		queryParams.put("start", (pageNumber - 1) * pageSize);
		queryParams.put("offset", pageSize);

		NamedParameterJdbcTemplate jdbc = new NamedParameterJdbcTemplate(jdbcTemplate);

		List<AccountVO> list = jdbc.query(querySql.toString(), queryParams, new RowMapper<AccountVO>() {

			@Override
			public AccountVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				return packResultSetData(rs);
			}
		});
		int totalCount = jdbc.queryForObject(countSql.toString(), countParams, Integer.class);

		// 分页封装
		PageResult<AccountVO> page = new PageResult<>();
		page.pageNumber = pageNumber;
		page.pageSize = pageSize;
		page.total = totalCount;
		page.list = list;
		Map<String, Object> conditions = new HashMap<String, Object>();
		page.conditions = conditions;

		return page;
	}

	@Override
	public AccountVO find(Integer id) {
		String sql = "SELECT t.id, t.nickname, t.mobile, t.gender, t.gmt_create AS gmtCreate, t.status FROM user t where t.id = ?";

		AccountVO vo = jdbcTemplate.query(sql, new ResultSetExtractor<AccountVO>() {

			@Override
			public AccountVO extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {
					return packResultSetData(rs);
				}
				return null;
			}
		}, new Object[] { id });

		return vo;
	}

	@Override
	public void updateStatus(Integer id, int targetStatus) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * ResultSet封装为VO
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private AccountVO packResultSetData(ResultSet rs) throws SQLException {
		AccountVO vo = new AccountVO();
		vo.setId(rs.getInt("id"));
		vo.setNickname(rs.getString("nickname"));
		vo.setPhone(rs.getString("mobile"));
		vo.setRegisterTime(rs.getDate("gmtCreate"));
		vo.setStatus(rs.getInt("status"));
		return vo;
	}

}
