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
import com.wyjk.admin.service.INoticeService;
import com.wyjk.admin.vo.NoticeVO;

@Controller
@RequestMapping("/admin/notice")
public class NoticeController extends BaseController {
	
	@Value("${app.upload.fileDomain}")
	private String fileDomain;
	@Autowired
	private INoticeService service;
	
	@RequestMapping(value = { "/", "/index" })
	public String index() {
		return "admin/content/notice/index";
	}
	
	@RequestMapping(value = { "/list" })
	@ResponseBody
	public PageResult<NoticeVO> list(
			Integer pageNumber,
            Integer pageSize,
            String searchText) {
		PageResult<NoticeVO> page = service.findAll(pageNumber, pageSize, searchText);
		return page;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap map) {
		return "admin/content/notice/form";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Integer id, ModelMap map) {
		NoticeVO entity = service.find(id);
		map.put("entity", entity);
		map.put("fileDomain", fileDomain);
		return "admin/content/notice/form";
	}
	
	@RequestMapping(value= {"/save"} ,method = RequestMethod.POST)
	@ResponseBody
	public JsonResult save(NoticeVO entity, ModelMap map){
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
