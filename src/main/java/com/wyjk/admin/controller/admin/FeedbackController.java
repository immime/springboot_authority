package com.wyjk.admin.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wyjk.admin.common.pagination.PageResult;
import com.wyjk.admin.controller.BaseController;
import com.wyjk.admin.service.IFeedbackService;
import com.wyjk.admin.vo.FeedbackVO;

@Controller
@RequestMapping("/admin/feedback")
public class FeedbackController extends BaseController {
	
	@Value("${app.upload.fileDomain}")
	private String fileDomain;
	@Autowired
	private IFeedbackService service;
	
	@RequestMapping(value = { "/", "/index" })
	public String index() {
		return "admin/feedback/index";
	}
	
	@RequestMapping(value = { "/list" })
	@ResponseBody
	public PageResult<FeedbackVO> list(
			Integer pageNumber,
            Integer pageSize,
            String searchText) {
		PageResult<FeedbackVO> page = service.findAll(pageNumber, pageSize, searchText);
		return page;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap map) {
		return "admin/feedback/form";
	}

	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable Integer id, ModelMap map) {
		FeedbackVO entity = service.find(id);
		service.updateStatus(id, 0);
		map.put("entity", entity);
		map.put("fileDomain", fileDomain);
		return "admin/feedback/form";
	}
	
	
}
