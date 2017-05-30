package com.wyjk.admin.service.support;

import org.springframework.web.multipart.MultipartFile;

public interface IFileService {
	
	String saveEditorImg(MultipartFile file);
	String saveImg(MultipartFile file, String subPath);
	void deleteByUrl(String url);
}
