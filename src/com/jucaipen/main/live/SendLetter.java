package com.jucaipen.main.live;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jucaipen.model.TxtLiveMsg;
import com.jucaipen.utils.TimeUtils;
/**
 * @author —Ó¿ ∑…
 *    ∑¢ÀÕÀΩ–≈
 */
public class SendLetter extends HttpServlet {
	private static final long serialVersionUID = 550629360048240065L;
	private String ip;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		ip=request.getRemoteAddr();
		
		createPrivateLetter("");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	private void createPrivateLetter(String messBody) {
		TxtLiveMsg msg=new TxtLiveMsg();
		msg.setInsertDate(TimeUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		msg.setIp(ip);
		msg.setMessBody(messBody);
		msg.setMsgType(0);
		
		
	}

}
