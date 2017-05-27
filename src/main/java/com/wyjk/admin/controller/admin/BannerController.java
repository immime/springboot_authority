package com.wyjk.admin.controller.admin;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wyjk.admin.common.JsonResult;
import com.wyjk.admin.controller.BaseController;
import com.wyjk.admin.entity.Banner;
import com.wyjk.admin.entity.User;
import com.wyjk.admin.service.IBannerService;
import com.wyjk.admin.service.specification.SimpleSpecificationBuilder;
import com.wyjk.admin.service.specification.SpecificationOperator.Operator;

@Controller
@RequestMapping("/admin/banner")
public class BannerController extends BaseController {
	@Autowired
	private IBannerService service;
	
	@RequestMapping(value = { "/", "/index" })
	public String index() {
		return "admin/banner/index";
	}
	
	@RequestMapping(value = { "/list" })
	@ResponseBody
	public Page<Banner> list() {
		SimpleSpecificationBuilder<Banner> builder = new SimpleSpecificationBuilder<Banner>();
		String searchText = request.getParameter("searchText");
		if(StringUtils.isNotBlank(searchText)){
			builder.add("nickName", Operator.likeAll.name(), searchText);
		}
		Page<Banner> page = service.findAll(builder.generateSpecification(), getPageRequest());
		return page;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap map) {
		return "admin/banner/form";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Integer id, ModelMap map) {
		Banner banner = service.find(id);
		map.put("banner", banner);
		return "admin/banner/form";
	}
	
	@RequestMapping(value= {"/save"} ,method = RequestMethod.POST)
	@ResponseBody
	public JsonResult save(Banner banner, ModelMap map){
		try {
			service.saveOrUpdate(banner);
		} catch (Exception e) {
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success();
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult delete(@PathVariable Integer id, ModelMap map) {
		try {
			service.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success();
	}
	
}
