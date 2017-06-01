package com.wyjk.admin.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wyjk.admin.common.JsonResult;
import com.wyjk.admin.service.support.IFileService;

@RestController
@RequestMapping("/admin/file")
public class FileManageController {
	
	@Autowired
	private IFileService fileService;
	
	@RequestMapping("/upload")
    public JsonResult upload(@RequestParam("file") MultipartFile file) {
		String url = fileService.saveEditorImg(file);
        return JsonResult.success("上传成功", url);
    }
	
	@RequestMapping("/uploadBanner")
    public JsonResult banner(@RequestParam("file") MultipartFile file) {
		String url = fileService.saveImg(file, "banner");
		return JsonResult.success("上传成功", url);
    }
	
	@RequestMapping("/uploadNoticeIcon")
    public JsonResult notice(@RequestParam("file") MultipartFile file) {
		String url = fileService.saveImg(file, "noticeIcon");
		return JsonResult.success("上传成功", url);
    }
	
	@RequestMapping("/uploadPostImg")
    public JsonResult post(@RequestParam("file") MultipartFile file) {
		String url = fileService.saveImg(file, "post");
		return JsonResult.success("上传成功", url);
    }
	
	@RequestMapping("/remove")
    public JsonResult remove(@RequestParam("url") String url) {
		fileService.deleteByUrl(url);
		return JsonResult.success("上传成功", url);
    }
}
