package com.wyjk.admin.dao;

import com.wyjk.admin.common.pagination.PageResult;
import com.wyjk.admin.vo.SecretVO;

public interface ISecretDao {

	PageResult<SecretVO> findAll(Integer pageNumber, Integer pageSize, String searchText);

	SecretVO find(Integer id);

}
