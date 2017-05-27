package com.wyjk.admin.dao;

import org.springframework.stereotype.Repository;

import com.wyjk.admin.dao.support.IBaseDao;
import com.wyjk.admin.entity.User;

@Repository
public interface IUserDao extends IBaseDao<User, Integer> {

	User findByUserName(String username);

}
