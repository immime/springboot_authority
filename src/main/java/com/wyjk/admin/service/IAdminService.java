package com.wyjk.admin.service;

import com.wyjk.admin.entity.Admin;
import com.wyjk.admin.service.support.IBaseService;

/**
 * <p>
 * 用户服务类
 * </p>
 *
 * @author SPPan
 * @since 2016-12-28
 */
public interface IAdminService extends IBaseService<Admin, Integer> {

	/**
	 * 根据用户名查找用户
	 * @param username
	 * @return
	 */
	Admin findByUserName(String username);

	/**
	 * 增加或者修改用户
	 * @param user
	 */
	void saveOrUpdate(Admin user);

	/**
	 * 给用户分配角色
	 * @param id 用户ID
	 * @param roleIds 角色Ids
	 */
	void grant(Integer id, String[] roleIds);

}
