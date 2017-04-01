package com.jucaipen.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;
/**
 * @author Administrator
 *      下载APK文件
 */
@SuppressWarnings("serial")
public class DownFile extends HttpServlet {
	private String rootPath;
	private String loadPath;
	private String fm;
	private String result;
	//http://img.jucaipen.com/jucaipenUpload/2015/7/3/20157317140_300_200.jpg

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
			String fileName = request.getParameter("fileName");
			if (StringUtil.isNotNull(fileName)) {
				String adr=request.getLocalAddr();
				if(adr.equals("121.40.227.121")){
					fm = fileName;
				}else{
					fm = new String(fileName.getBytes("ISO-8859-1"),"utf-8");
				}
				loadPath = rootPath + fm;
				File apkFile = new File(loadPath);
				if (apkFile.exists()) {
					downLoadApk(response);
				} else {
					result=JsonUtil.getRetMsg(3, "文件不存在");
				}
			} else {
				PrintWriter out = response.getWriter();
				result = JsonUtil.getRetMsg(1, "下载文件名不能为空");
				out.write(result);
				out.flush();
				out.close();
			}
	}

	private void downLoadApk(HttpServletResponse response) {
		try {
			// 设置响应头，控制浏览器下载该文件
			response.setHeader("content-disposition", "attachment;filename="
					+ URLEncoder.encode(fm, "UTF-8"));
			// 读取要下载的文件，保存到文件输入流
			FileInputStream in = new FileInputStream(loadPath);
			// 创建输出流
			OutputStream out = response.getOutputStream();
			// 创建缓冲区
			byte buffer[] = new byte[1024];
			int len = 0;
			// 循环将输入流中的内容读取到缓冲区当中
			while ((len = in.read(buffer)) > 0) {
				// 输出缓冲区的内容到浏览器，实现文件下载
				out.write(buffer, 0, len);
			}
			// 关闭文件输入流
			in.close();
			// 关闭输出流
			out.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			System.out.println("err1");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("err2");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("err3");
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
