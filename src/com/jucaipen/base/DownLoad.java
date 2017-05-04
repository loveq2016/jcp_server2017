package com.jucaipen.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.jucaipen.model.ApkInfo;
import com.jucaipen.service.ApkInfoServer;

public class DownLoad extends HttpServlet {
	private String rootPath;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		rootPath = "D:/apkInfo/apk/";
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setHeader("content-disposition", "attachment;filename=wt.apk");
		ApkInfo info = ApkInfoServer.findLastApkInfo(1);
		String fileName = info.getApkPath();
		String mimeType = getServletContext().getMimeType("wt.apk");
		response.setContentType(mimeType);
		File f = new File(rootPath + fileName);
		if (f.exists()) {
			IOUtils.copy(new FileInputStream(f), response.getOutputStream());
		} else {
			response.getWriter().write("文件不存在");
		}

	}

}
