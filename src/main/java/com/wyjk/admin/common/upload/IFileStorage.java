package com.wyjk.admin.common.upload;

import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IFileStorage {

    void init();

    /**
     * @param file
     * @return 完整的访问url
     */
    String store(MultipartFile file);
    
    String store(MultipartFile file, String... paths);
    
    /**
     * 上传到指定域名文件夹<br>
     * <code>e.g. upload-dir\www.12123.com\aaa.jpg</code>
     * @param file
     * @param path 存储子目录
     * @return url
     */
    String store(MultipartFile file, String path);
    
    /**
     * 上传到指定域名文件夹<br>
     * <code>e.g. upload-dir\www.12123.com\headimg\aaa.jpg</code>
     * @param file
     * @param path 存储子目录
     * @param subPathName 子目录
     * @return url
     */
    String store(MultipartFile file, String path, String subPathName);

    Path load(String filename);

    Resource loadAsResource(String filename);

    /**
     * 删除文件
     * @param relativePath
     * @return 
     */
    boolean deleteFileByRelativePath(String relativePath);
    
    void deleteAll();

}
