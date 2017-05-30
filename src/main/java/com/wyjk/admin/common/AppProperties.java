package com.wyjk.admin.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppProperties {
    @Value("${app.upload.fileDomain}")
	public String UPLOAD_FILE_DOMAIN;

	@Value("${app.upload.dir}")
	public String UPLOAD_DIR;
	
	@Value("${app.upload.mainPath}")
	public String UPLOAD_MAINPATH;
	
	@Value("${app.upload.editorFilesPath}")
	public String UPLOAD_EDITOR_FILES_PATH;

}
