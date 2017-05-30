package com.wyjk.admin.common.upload;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUUIDNameStorage implements IFileStorage {
	private final Path rootLocation;
	private final String uploadDir;
	
	public FileUUIDNameStorage(String uploadDir) {
		this.uploadDir = uploadDir;
		this.rootLocation = Paths.get(uploadDir);
	}

	@Override
	public void init() {
		try {
			Files.createDirectory(rootLocation);
		} catch (IOException e) {
			throw new StorageException("Could not initialize storage", e);
		}
	}

	@Override
	public String store(MultipartFile file) {
		if (file.isEmpty()) {
			throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
		}
		String originalFilename = file.getOriginalFilename();
		// 文件后缀
		String fileExt = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
		StringBuilder savePath = new StringBuilder();
		String uuid = UUID.randomUUID().toString();
		String randomFilename = savePath.append(uuid).append(".").append(fileExt).toString();
		
		Path fullPath = this.rootLocation.resolve(randomFilename);
		try {
			Files.copy(file.getInputStream(), fullPath);
		} catch (IOException e) {
			throw new StorageException("Failed to store file " + randomFilename, e);
		}

		String url = "/" + randomFilename;
		return url;
	}

	@Override
	public String store(MultipartFile file, String... paths) {
		String url = "/";
		String originalFilename = file.getOriginalFilename();
		// 文件后缀
		String fileExt = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
		StringBuilder savePath = new StringBuilder();
		String uuid = UUID.randomUUID().toString();
		String randomFilename = savePath.append(uuid).append(".").append(fileExt).toString();
		
		if (paths == null || paths.length == 0) {
			store(file);
		}
		
		Path path = rootLocation;
		for (int i = 0; i < paths.length; i++) {
			path = path.resolve(paths[i]);
			if (!Files.exists(path)) {
				try {
					Files.createDirectories(path);
				} catch (IOException e) {
					throw new StorageException("Failed to store file " + randomFilename, e);					
				}
			}
			if (i == 0) {
				url = url + paths[i];
			} else {
				url = url + "/" + paths[i];
			}			
		}
		
		path = path.resolve(randomFilename);
		try {
			Files.copy(file.getInputStream(), path);
			url = url + "/" + randomFilename;
		} catch (IOException e) {
			throw new StorageException("Failed to store file " + randomFilename, e);
		}
		
		return url;
	}

	@Override
	public String store(MultipartFile file, String path) {
		if (file.isEmpty()) {
			throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
		}
		String originalFilename = file.getOriginalFilename();
		// 文件后缀
		String fileExt = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
		StringBuilder savePath = new StringBuilder();
		String uuid = UUID.randomUUID().toString();
		String randomFilename = savePath.append(uuid).append(".").append(fileExt).toString();
		
		Path rootPath = this.rootLocation.resolve(path);
		Path fullPath = rootPath.resolve(randomFilename);
		try {
			if (!Files.exists(rootPath)) {
				Files.createDirectories(rootPath);
			}
			Files.copy(file.getInputStream(), fullPath);
		} catch (IOException e) {
			throw new StorageException("Failed to store file " + randomFilename, e);
		}
		String url = "/" + path + "/" + randomFilename;
		return url;
	}

	@Override
	public String store(MultipartFile file, String path, String subPathName) {
		if (file.isEmpty()) {
			throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
		}
		String originalFilename = file.getOriginalFilename();
		// 文件后缀
		String fileExt = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
		StringBuilder savePath = new StringBuilder();
		String uuid = UUID.randomUUID().toString();
		String randomFilename = savePath.append(uuid).append(".").append(fileExt).toString();
				
		Path rootPath = this.rootLocation.resolve(path);
		Path subPath = rootPath.resolve(subPathName);
		Path fullPath = subPath.resolve(randomFilename);		
		try {			
			if (!Files.exists(rootPath)) {
				Files.createDirectories(rootPath);
			}
			if (!Files.exists(subPath)) {
				Files.createDirectories(subPath);
			}
			Files.copy(file.getInputStream(), fullPath);
		} catch (IOException e) {
			throw new StorageException("Failed to store file " + randomFilename, e);
		}
		String url = "/" + path + "/" + subPathName + "/" + randomFilename;
		return url;
	}

	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	@Override
	public Resource loadAsResource(String filename) {
		try {
			Path file = load(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new StorageFileNotFoundException("Could not read file: " + filename);

			}
		} catch (MalformedURLException e) {
			throw new StorageFileNotFoundException("Could not read file: " + filename, e);
		}
	}

	@Override
	public boolean deleteFileByRelativePath(String relativePath) {
		boolean success = false;
		boolean isRegularFile = false;
		if (StringUtils.isBlank(relativePath)) {
			return false;
		}
		
		Path path = Paths.get(uploadDir + relativePath);
		BasicFileAttributeView bav = Files.getFileAttributeView(path, BasicFileAttributeView.class);
		BasicFileAttributes ba;
		try {
			ba = bav.readAttributes();
			isRegularFile = ba.isRegularFile();
		} catch (IOException e1) {	
			return false;
		}
		
		if (!isRegularFile) {
			return false;
		}
		
		try {
			success = Files.deleteIfExists(path);
		} catch (IOException e) {
			throw new StorageException("Failed to delete file " + path, e);
		}
		
		return success;
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

}
