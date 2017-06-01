package com.wyjk.admin.service;

import com.wyjk.admin.common.pagination.PageResult;
import com.wyjk.admin.vo.SecretVO;

public interface ISecretService {

	PageResult<SecretVO> findAll(Integer pageNumber, Integer pageSize, String searchText);

	SecretVO find(Integer id);

	void saveOrUpdate(SecretVO entity);

	void delete(Integer id);

	void updateOrder(Integer id, String upOrDown);

}
