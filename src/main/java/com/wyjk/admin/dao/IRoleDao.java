package com.wyjk.admin.dao;

import org.springframework.stereotype.Repository;

import com.wyjk.admin.dao.support.IBaseDao;
import com.wyjk.admin.entity.Role;

@Repository
public interface IRoleDao extends IBaseDao<Role, Integer> {

}
