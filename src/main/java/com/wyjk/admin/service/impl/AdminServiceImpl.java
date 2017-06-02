package com.wyjk.admin.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.wyjk.admin.common.utils.MD5Utils;
import com.wyjk.admin.dao.IAdminDao;
import com.wyjk.admin.dao.support.IBaseDao;
import com.wyjk.admin.entity.Role;
import com.wyjk.admin.entity.Admin;
import com.wyjk.admin.service.IRoleService;
import com.wyjk.admin.service.IAdminService;
import com.wyjk.admin.service.support.impl.BaseServiceImpl;

/**
 * <p>
 * 用户账户表  服务实现类
 * </p>
 *
 * @author SPPan
 * @since 2016-12-28
 */
@Service
public class AdminServiceImpl extends BaseServiceImpl<Admin, Integer> implements IAdminService {

	@Autowired
	private IAdminDao userDao;
	
	@Autowired
	private IRoleService roleService;
	
	@Override
	public IBaseDao<Admin, Integer> getBaseDao() {
		return this.userDao;
	}

	@Override
	public Admin findByUserName(String username) {
		return userDao.findByUserName(username);
	}

	@Override
	public void saveOrUpdate(Admin user) {
		if(user.getId() != null){
			Admin dbUser = find(user.getId());
			dbUser.setNickName(user.getNickName());
			dbUser.setSex(user.getSex());
			dbUser.setBirthday(user.getBirthday());
			dbUser.setTelephone(user.getTelephone());
			dbUser.setEmail(user.getEmail());
			dbUser.setAddress(user.getAddress());
			dbUser.setLocked(user.getLocked());
			dbUser.setDescription(user.getDescription());
			dbUser.setUpdateTime(new Date());
			update(dbUser);
		}else{
			user.setCreateTime(new Date());
			user.setUpdateTime(new Date());
			user.setDeleteStatus(0);
			user.setPassword(MD5Utils.md5("111111"));
			save(user);
		}
	}
	
	

	@Override
	public void delete(Integer id) {
		Admin user = find(id);
		Assert.state(!"admin".equals(user.getUserName()),"超级管理员用户不能删除");
		super.delete(id);
	}

	@Override
	public void grant(Integer id, String[] roleIds) {
		Admin user = find(id);
		Assert.notNull(user, "用户不存在");
		Assert.state(!"admin".equals(user.getUserName()),"超级管理员用户不能修改管理角色");
		Role role;
		Set<Role> roles = new HashSet<Role>();
		if(roleIds != null){
			for (int i = 0; i < roleIds.length; i++) {
				Integer rid = Integer.parseInt(roleIds[i]);
				role = roleService.find(rid);
				roles.add(role);
			}
		}
		user.setRoles(roles);
		update(user);
	}
	
}
