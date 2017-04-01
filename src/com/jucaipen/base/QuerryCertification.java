package com.jucaipen.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.model.Certification;
import com.jucaipen.service.CertificationSer;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.MsgCode;

/**
 * @author Administrator
 *
 *  获取资格证书列表
 */
@SuppressWarnings("serial")
public class QuerryCertification extends HttpServlet {
	private String result;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		result=initCertificationList();
		out.println(result);
		out.flush();
		out.close();
	}

	
	/**
	 * @return  初始化资格证书列表
	 */
	private String initCertificationList() {
		List<Certification> certs = CertificationSer.findAllCertification();
		return MsgCode.CURRENT_VERSION==MsgCode.HISTORY_VISION_1 ? JsonUtil.getCertificationList(certs) : JsonUtil.getCertificationListV2(certs, MsgCode.RET_SUCCESS_CODE, MsgCode.RET_SUCCESS);
	}

}
