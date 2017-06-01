package com.wyjk.admin.common.ueditor.upload;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MultipartFile;

import com.wyjk.admin.common.ueditor.PathFormat;
import com.wyjk.admin.common.ueditor.define.AppInfo;
import com.wyjk.admin.common.ueditor.define.BaseState;
import com.wyjk.admin.common.ueditor.define.FileType;
import com.wyjk.admin.common.ueditor.define.State;

public class BinaryUploader {

	public static final State save(HttpServletRequest request,
			Map<String, Object> conf, MultipartFile uploadFile) {
		boolean isAjaxUpload = request.getHeader( "X_Requested_With" ) != null;

		if (!ServletFileUpload.isMultipartContent(request)) {
			return new BaseState(false, AppInfo.NOT_MULTIPART_CONTENT);
		}

		ServletFileUpload upload = new ServletFileUpload(
				new DiskFileItemFactory());

        if ( isAjaxUpload ) {
            upload.setHeaderEncoding( "UTF-8" );
        }

		try {
			if (uploadFile == null) {
				return new BaseState(false, AppInfo.NOTFOUND_UPLOAD_DATA);
			}
			
			// 文件上传路径
		    String fileUploadRootDir = (String) conf.get("fileUploadRootDir");
		    String fileUploadSubDir = (String) conf.get("fileUploadSubDir");

			String savePath = (String) conf.get("savePath");
			
			String originFileName = uploadFile.getOriginalFilename();
			String suffix = FileType.getSuffixByFilename(originFileName);

			originFileName = originFileName.substring(0,
					originFileName.length() - suffix.length());
			savePath = savePath + suffix;

			long maxSize = ((Long) conf.get("maxSize")).longValue();

			if (!validType(suffix, (String[]) conf.get("allowFiles"))) {
				return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
			}

			savePath = PathFormat.parse(savePath, originFileName);

			String physicalPath = fileUploadRootDir + fileUploadSubDir + savePath;

			InputStream is = uploadFile.getInputStream();
			State storageState = StorageManager.saveFileByInputStream(is,
					physicalPath, maxSize);
			is.close();

			if (storageState.isSuccess()) {
				// 文件上传路径
			    String fileDomain = (String) conf.get("fileDomain");
			    String url = fileDomain + fileUploadSubDir +PathFormat.format(savePath);
				storageState.putInfo("url", url);
				storageState.putInfo("type", suffix);
				storageState.putInfo("original", originFileName + suffix);
			}

			return storageState;
		} catch (IOException e) {
		}
		return new BaseState(false, AppInfo.IO_ERROR);
	}

	private static boolean validType(String type, String[] allowTypes) {
		List<String> list = Arrays.asList(allowTypes);

		return list.contains(type);
	}
	
}
