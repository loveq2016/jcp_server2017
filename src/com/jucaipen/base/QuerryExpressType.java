package com.jucaipen.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.model.ClientOsInfo;
import com.jucaipen.model.ExpressionType;
import com.jucaipen.service.FaceTypeServer;
import com.jucaipen.utils.HeaderUtil;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;

/**
 * @author YLF
 * 
 *   获取所有表情包信息
 *
 */
@SuppressWarnings("serial")
public class QuerryExpressType extends HttpServlet {
	private List<ExpressionType> types;
	private String result;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userAgent=request.getParameter("User-Agent");
		ClientOsInfo os=HeaderUtil.getMobilOS(userAgent);
		int isDevice=HeaderUtil.isVaildDevice(os, userAgent);
		if(isDevice==HeaderUtil.PHONE_APP){
			initFaceType();
			result=JsonUtil.getExpressionType(types);
		}else{
			result=StringUtil.isVaild;
		}
		out.print(result);
		out.flush();
		out.close();
	}
	private void initFaceType() {
		//初始化表情包信息
		types=FaceTypeServer.findAllFace();
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
            doGet(request, response);
	}

}
