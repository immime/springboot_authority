package com.wyjk.admin.controller.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wyjk.admin.controller.BaseController;
import com.wyjk.admin.entity.User;
import com.wyjk.admin.service.IUserService;

@Controller
public class IndexController extends BaseController{
	
	@Autowired
	private IUserService userService;
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping(value={"/","/index"})
	public String index(){
		List<User> users = userService.findAll();
		logger.debug(users.toString());
		return "index";
	}
	
	@RequestMapping(value={"/previlige/no"})
	public String previligeNo() {
		logger.error("没有权限");
		return "index";
	}
	
}
