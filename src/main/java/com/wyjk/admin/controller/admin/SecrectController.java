package com.wyjk.admin.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wyjk.admin.common.JsonResult;
import com.wyjk.admin.common.pagination.PageResult;
import com.wyjk.admin.controller.BaseController;
import com.wyjk.admin.service.ISecretService;
import com.wyjk.admin.vo.SecretVO;

@Controller
@RequestMapping("/admin/secrect")
public class SecrectController extends BaseController {
	
	@Value("${app.upload.fileDomain}")
	private String fileDomain;
	@Autowired
	private ISecretService service;
	
	@RequestMapping(value = { "/", "/index" })
	public String index() {
		return "admin/content/secrect/index";
	}
	
	@RequestMapping(value = { "/list" })
	@ResponseBody
	public PageResult<SecretVO> list(
			Integer pageNumber,
            Integer pageSize,
            String searchText) {
		PageResult<SecretVO> page = service.findAll(pageNumber, pageSize, searchText);
		return page;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap map) {
		return "admin/content/secrect/form";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Integer id, ModelMap map) {
		SecretVO entity = service.find(id);
		map.put("entity", entity);
		map.put("fileDomain", fileDomain);
		return "admin/content/secrect/form";
	}
	
	@RequestMapping(value= {"/save"} ,method = RequestMethod.POST)
	@ResponseBody
	public JsonResult save(SecretVO entity, ModelMap map){
		try {
			service.saveOrUpdate(entity);
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
	
	@RequestMapping(value = "/updateOrder", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult updateOrder(
			@RequestParam(name="id") Integer id,
			@RequestParam(name="upOrDown") String upOrDown 
		) throws Exception {
		service.updateOrder(id, upOrDown);
		return JsonResult.success();
	}
	
}
