package com.wyjk.admin.common.ueditor.upload;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.wyjk.admin.common.ueditor.define.State;

public class Uploader {
    private MultipartFile uploadFile = null;
	private HttpServletRequest request = null;
	private Map<String, Object> conf = null;

	public Uploader(MultipartFile uploadFile, HttpServletRequest request, Map<String, Object> conf) {
	    this.uploadFile = uploadFile;
		this.request = request;
		this.conf = conf;
	}

	public final State doExec() {
		String filedName = (String) this.conf.get("fieldName");
		State state = null;

		if ("true".equals(this.conf.get("isBase64"))) {
			state = Base64Uploader.save(this.request.getParameter(filedName),
					this.conf);
		} else {
			state = BinaryUploader.save(this.request, this.conf, uploadFile);
		}

		return state;
	}
}
