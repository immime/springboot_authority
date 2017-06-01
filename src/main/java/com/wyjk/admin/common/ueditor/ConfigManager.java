package com.wyjk.admin.common.ueditor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import com.wyjk.admin.common.ueditor.define.ActionMap;

/**
 * 配置管理器
 * 
 * @author hancong03@baidu.com
 *
 */
public final class ConfigManager {
  private final MultipartFile uploadFile;
  private final String rootPath;
  private final String originalPath;
  private final String contextPath;
  private static final String configFileName = "config.json";
  private String parentPath = null;
  private JSONObject jsonConfig = null;
  // 涂鸦上传filename定义
  private final static String SCRAWL_FILE_NAME = "scrawl";
  // 远程图片抓取filename定义
  private final static String REMOTE_FILE_NAME = "remote";

  /*
   * 通过一个给定的路径构建一个配置管理器， 该管理器要求地址路径所在目录下必须存在config.properties文件
   */
  private ConfigManager(String rootPath, String contextPath, String uri)
      throws FileNotFoundException, IOException {

    rootPath = rootPath.replace("\\", "/");
    this.uploadFile = null;
    this.rootPath = rootPath;
    this.contextPath = contextPath;

    if (contextPath.length() > 0) {
      this.originalPath = this.rootPath + uri.substring(contextPath.length());
    } else {
      this.originalPath = this.rootPath + uri;
    }

    this.initEnv();

  }

  public ConfigManager(String rootPath, File configFile) {
    this.uploadFile = null;
    this.rootPath = rootPath;
    this.contextPath = "";
    this.originalPath = "";
    this.initEnv(configFile);
  }

  public ConfigManager(MultipartFile uploadFile, File configFile) {
    this.uploadFile = uploadFile;
    this.rootPath = "";
    this.contextPath = "";
    this.originalPath = "";
    this.initEnv(uploadFile, configFile);
  }

  private void initEnv(MultipartFile uploadFile, File configFile) {
    if (!configFile.isAbsolute()) {
      configFile = new File(configFile.getAbsolutePath());
    }

    this.parentPath = configFile.getParent();

    String configContent = jsonRead(configFile);

    try {
      JSONObject jsonConfig = new JSONObject(configContent);
      this.jsonConfig = jsonConfig;
    } catch (Exception e) {
      this.jsonConfig = null;
    }
  }

  private void initEnv(File configFile) {

    if (!configFile.isAbsolute()) {
      configFile = new File(configFile.getAbsolutePath());
    }

    this.parentPath = configFile.getParent();

    String configContent = jsonRead(configFile);

    try {
      JSONObject jsonConfig = new JSONObject(configContent);
      this.jsonConfig = jsonConfig;
    } catch (Exception e) {
      this.jsonConfig = null;
    }
  }
  
  /**
   * 读取文件类容为字符串
   * 
   * @param file
   * @return
   */
  private String jsonRead(File file) {
    Scanner scanner = null;
    StringBuilder buffer = new StringBuilder();
    try {
      scanner = new Scanner(file, "utf-8");
      while (scanner.hasNextLine()) {
        buffer.append(scanner.nextLine());
      }
    } catch (Exception e) {

    } finally {
      if (scanner != null) {
        scanner.close();
      }
    }
    return buffer.toString();
  }

  /**
   * 配置管理器构造工厂
   * 
   * @param rootPath 服务器根路径
   * @param contextPath 服务器所在项目路径
   * @param uri 当前访问的uri
   * @return 配置管理器实例或者null
   */
  public static ConfigManager getInstance(String rootPath, String contextPath, String uri) {

    try {
      return new ConfigManager(rootPath, contextPath, uri);
    } catch (Exception e) {
      return null;
    }

  }

  /**
   * 配置管理器构造工厂
   * 
   * @param rootPath 服务器根路径
   * @param configFile 配置文件
   * @return 配置管理器实例或者null
   */
  public static ConfigManager getInstance(String rootPath, File configFile) {

    try {
      return new ConfigManager(rootPath, configFile);
    } catch (Exception e) {
      return null;
    }

  }
  
  public static ConfigManager getInstance(MultipartFile uploadFile, File configFile) {
    try {
      return new ConfigManager(uploadFile, configFile);
    } catch (Exception e) {
      return null;
    }
  }

  // 验证配置文件加载是否正确
  public boolean valid() {
    return this.jsonConfig != null;
  }

  public JSONObject getAllConfig() {

    return this.jsonConfig;

  }

  public Map<String, Object> getConfig(int type) throws JSONException {

    Map<String, Object> conf = new HashMap<String, Object>();
    String savePath = null;

    switch (type) {

      case ActionMap.UPLOAD_FILE:
        conf.put("isBase64", "false");
        conf.put("maxSize", this.jsonConfig.getLong("fileMaxSize"));
        conf.put("allowFiles", this.getArray("fileAllowFiles"));
        conf.put("fieldName", this.jsonConfig.getString("fileFieldName"));
        conf.put("fileDomain", this.jsonConfig.getString("fileDomain"));
        conf.put("fileUploadDir", this.jsonConfig.getString("fileUploadDir"));
        savePath = this.jsonConfig.getString("filePathFormat");
        break;

      case ActionMap.UPLOAD_IMAGE:
        conf.put("isBase64", "false");
        conf.put("maxSize", this.jsonConfig.getLong("imageMaxSize"));
        conf.put("allowFiles", this.getArray("imageAllowFiles"));
        conf.put("fieldName", this.jsonConfig.getString("imageFieldName"));
        conf.put("fileDomain", this.jsonConfig.getString("fileDomain"));
        conf.put("fileUploadRootDir", this.jsonConfig.getString("fileUploadRootDir"));
        conf.put("fileUploadSubDir", this.jsonConfig.getString("fileUploadSubDir"));
        savePath = this.jsonConfig.getString("imagePathFormat");
        break;

      case ActionMap.UPLOAD_VIDEO:
        conf.put("maxSize", this.jsonConfig.getLong("videoMaxSize"));
        conf.put("allowFiles", this.getArray("videoAllowFiles"));
        conf.put("fieldName", this.jsonConfig.getString("videoFieldName"));
        conf.put("fileDomain", this.jsonConfig.getString("fileDomain"));
        conf.put("fileUploadRootDir", this.jsonConfig.getString("fileUploadRootDir"));
        conf.put("fileUploadSubDir", this.jsonConfig.getString("fileUploadSubDir"));
        savePath = this.jsonConfig.getString("videoPathFormat");
        break;

      case ActionMap.UPLOAD_SCRAWL:
        conf.put("filename", ConfigManager.SCRAWL_FILE_NAME);
        conf.put("maxSize", this.jsonConfig.getLong("scrawlMaxSize"));
        conf.put("fieldName", this.jsonConfig.getString("scrawlFieldName"));
        conf.put("isBase64", "true");
        conf.put("fileDomain", this.jsonConfig.getString("fileDomain"));
        conf.put("fileUploadRootDir", this.jsonConfig.getString("fileUploadRootDir"));
        conf.put("fileUploadSubDir", this.jsonConfig.getString("fileUploadSubDir"));
        savePath = this.jsonConfig.getString("scrawlPathFormat");
        break;

      case ActionMap.CATCH_IMAGE:
        conf.put("filename", ConfigManager.REMOTE_FILE_NAME);
        conf.put("filter", this.getArray("catcherLocalDomain"));
        conf.put("maxSize", this.jsonConfig.getLong("catcherMaxSize"));
        conf.put("allowFiles", this.getArray("catcherAllowFiles"));
        conf.put("fieldName", this.jsonConfig.getString("catcherFieldName") + "[]");
        conf.put("fileDomain", this.jsonConfig.getString("fileDomain"));
        conf.put("fileUploadRootDir", this.jsonConfig.getString("fileUploadRootDir"));
        conf.put("fileUploadSubDir", this.jsonConfig.getString("fileUploadSubDir"));
        savePath = this.jsonConfig.getString("catcherPathFormat");
        break;

      case ActionMap.LIST_IMAGE:
        conf.put("allowFiles", this.getArray("imageManagerAllowFiles"));
        conf.put("dir", this.jsonConfig.getString("imageManagerListPath"));
        conf.put("count", this.jsonConfig.getInt("imageManagerListSize"));
        break;

      case ActionMap.LIST_FILE:
        conf.put("allowFiles", this.getArray("fileManagerAllowFiles"));
        conf.put("dir", this.jsonConfig.getString("fileManagerListPath"));
        conf.put("count", this.jsonConfig.getInt("fileManagerListSize"));
        break;

    }

    conf.put("savePath", savePath);
    conf.put("rootPath", this.rootPath);

    return conf;

  }

  private void initEnv() throws FileNotFoundException, IOException {

    File file = new File(this.originalPath);

    if (!file.isAbsolute()) {
      file = new File(file.getAbsolutePath());
    }

    this.parentPath = file.getParent();

    String configContent = this.readFile(this.getConfigPath());

    try {
      JSONObject jsonConfig = new JSONObject(configContent);
      this.jsonConfig = jsonConfig;
    } catch (Exception e) {
      this.jsonConfig = null;
    }

  }

  private String getConfigPath() {
    return this.parentPath + File.separator + ConfigManager.configFileName;
  }

  private String[] getArray(String key) throws JSONException {

    JSONArray jsonArray = this.jsonConfig.getJSONArray(key);
    String[] result = new String[jsonArray.length()];

    for (int i = 0, len = jsonArray.length(); i < len; i++) {
      result[i] = jsonArray.getString(i);
    }

    return result;

  }

  private String readFile(String path) throws IOException {

    StringBuilder builder = new StringBuilder();

    try {

      InputStreamReader reader = new InputStreamReader(new FileInputStream(path), "UTF-8");
      BufferedReader bfReader = new BufferedReader(reader);

      String tmpContent = null;

      while ((tmpContent = bfReader.readLine()) != null) {
        builder.append(tmpContent);
      }

      bfReader.close();

    } catch (UnsupportedEncodingException e) {
      // 忽略
    }

    return this.filter(builder.toString());

  }

  // 过滤输入字符串, 剔除多行注释以及替换掉反斜杠
  private String filter(String input) {

    return input.replaceAll("/\\*[\\s\\S]*?\\*/", "");

  }

}
