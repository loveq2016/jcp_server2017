package com.jucaipen.main.my;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jucaipen.model.Account;
import com.jucaipen.service.AccountSer;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;
/**
 * @author 杨朗飞
 *    获取聚财币数量
 */
public class GetSurplus extends HttpServlet {
	private String result;
	private static final long serialVersionUID = -1912264632434270947L;
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
        String userId=request.getParameter("userId");
        if(StringUtil.isNotNull(userId)&&StringUtil.isInteger(userId)){
        	int uId=Integer.parseInt(userId);
            result=getSurplus(uId);        	
        }else{
        	result=JsonUtil.getRetMsg(1, "userId 参数异常");
        }
		out.println(result);
		out.flush();
		out.close();
	}
	private String getSurplus(int uId) {
	    Account account = AccountSer.findAccountByUserId(uId);
		return JsonUtil.getRetMsgJu(0, "获取成功", account.getJucaiBills());
	}

}
