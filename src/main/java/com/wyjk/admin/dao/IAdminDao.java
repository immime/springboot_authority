package com.wyjk.admin.dao;

import org.springframework.stereotype.Repository;

import com.wyjk.admin.dao.support.IBaseDao;
import com.wyjk.admin.entity.Admin;

@Repository
public interface IAdminDao extends IBaseDao<Admin, Integer> {

	Admin findByUserName(String username);

}
