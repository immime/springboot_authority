package com.wyjk.admin.service.support.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wyjk.admin.common.AppProperties;
import com.wyjk.admin.common.upload.FileUUIDNameStorage;
import com.wyjk.admin.common.upload.IFileStorage;
import com.wyjk.admin.service.support.IFileService;

@Service
public class FileServiceImpl implements IFileService {
	
	private IFileStorage fileStorage;
	private AppProperties appProperties;

	@Autowired
	public FileServiceImpl(AppProperties appProperties) {
		super();
		this.fileStorage = new FileUUIDNameStorage(appProperties.UPLOAD_DIR);
		this.appProperties = appProperties;
	}

	@Override
	public String saveEditorImg(MultipartFile file) {
		return fileStorage.store(file, appProperties.UPLOAD_MAINPATH, appProperties.UPLOAD_EDITOR_FILES_PATH);
	}


	@Override
	public void deleteByUrl(String url) {
		fileStorage.deleteFileByRelativePath(url);
	}

	@Override
	public String saveImg(MultipartFile file, String subPath) {
		return fileStorage.store(file, appProperties.UPLOAD_MAINPATH, subPath);
	}

}
