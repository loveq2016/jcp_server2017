package com.jucaipen.base;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
/**
 * @author 杨朗飞  
 * 
 * 下载 apk文件    ----测试
 */
public class DownLoad extends HttpServlet {
	private String rootPath;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		rootPath = "D:/apkInfo/";
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setHeader("content-disposition", "attachment;filename=wt.apk");
		/*ApkInfo info = ApkInfoServer.findLastApkInfo(1);
		String fileName = info.getApkPath();*/
		String mimeType = getServletContext().getMimeType("wt.apk");
		response.setContentType(mimeType);
		File f = new File(rootPath + "wt.apk");
		FileInputStream fis=new FileInputStream(f);
		ServletOutputStream outputStream = response.getOutputStream();
		if (f.exists()) {
			IOUtils.copy(fis, outputStream);
			IOUtils.closeQuietly(outputStream);
			IOUtils.closeQuietly(fis);
		} else {
			response.getWriter().write("文件不存在");
		}
	}

}
