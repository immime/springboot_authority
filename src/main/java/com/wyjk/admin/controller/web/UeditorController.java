package com.wyjk.admin.controller.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.wyjk.admin.common.ueditor.ActionEnter;

@Controller
@RequestMapping("/ueditor")
public class UeditorController {

	@Value(value = "classpath:config.json")
	private Resource config;
	
	@RequestMapping("/execute")
	public void excute(@RequestParam(name = "file", required = false) MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) {

		String action = (String) request.getParameter("action");

		File configFile = null;
		try {
			configFile = config.getFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		if ("config".equals(action)) {
			try {
				String jsonData = this.jsonRead(configFile);
				PrintWriter writer = response.getWriter();
				writer.write(jsonData);
				writer.flush();
				writer.close();
				return;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// response.setContentType("application/json");
//		String rootPath = request.getSession().getServletContext().getRealPath("/");
		response.setHeader("Content-Type", "text/html");

		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			String exec;
			try {
				exec = new ActionEnter(request, file, configFile).exec();
				writer.write(exec);
				writer.flush();
				writer.close();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			writer.close();
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

}
