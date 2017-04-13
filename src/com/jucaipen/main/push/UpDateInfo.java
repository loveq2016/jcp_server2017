package com.jucaipen.main.push;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.device.AliasDeviceListResult;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;
import com.jucaipen.model.TacticsDetails;
import com.jucaipen.model.TacticsSale;
import com.jucaipen.service.TacticsDetailSer;
import com.jucaipen.service.TacticsSaleSer;
import com.jucaipen.utils.JPushUtils;
import com.jucaipen.utils.StringUtil;
import com.jucaipen.utils.TimeUtils;
/**
 * @author Administrator
 *
 *    战法更新推送
 */
public class UpDateInfo extends HttpServlet {
	private static final long serialVersionUID = 2383867895971970945L;
	private Collection<String> aliases = new ArrayList<String>();
	private Collection<String>  removeData=new ArrayList<String>();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String tacticsId = request.getParameter("tacticsId");
		initAlias(tacticsId);
		out.flush();
		out.close();
	}
	
	

	/**
	 * @param tacticsId  获取购买战法的用户别名
	 */
	private void initAlias(String tacticsId) {
		aliases.clear();
		int id = Integer.parseInt(tacticsId);
		TacticsDetails details = TacticsDetailSer.findDetailsById(id);
		int fkId = details.getFkId();
		List<TacticsSale> sales = TacticsSaleSer.findTacticsIsSale(fkId);
		if (sales != null) {
			for (TacticsSale sale : sales) {
				if (TimeUtils.isLive(sale.getStartDate(), sale.getEndDate())) {
					int uId = sale.getUserId();
					if(uId>0){
						aliases.add(uId + "");
					}
				}
			}
			if (!aliases.isEmpty()) {
				pushInfo(StringUtil.spliteStr(details.getBody(), 50), id,
						aliases);
			} else {
				System.out.println("no user");
			}
		}
	}

	/**
	 * @param title
	 * @param id
	 * @param aliases2  向注册推送  并购买战法的用户推送战法信息
	 */
	private void pushInfo(String title, int id, Collection<String> aliases2) {
		removeData.clear();
		JPushClient client = JPushUtils.getJPush();
		Iterator<String> aliase = aliases2.iterator();
		while (aliase.hasNext()) {
			String element = aliase.next();
			if(!hasRegin(element,client)){
				removeData.add(element);
			}
		} 
		
		Iterator<String> remove = removeData.iterator();
		
		while(remove.hasNext()){
			String element = remove.next();
			aliases2.remove(element);
		}
		if(aliases2.size()>0){
			PushPayload payLoad = JPushUtils.createNptifyForAliase(title, "type", 1, "id",
					id,null,null,null,0,aliases2);
			PushResult result = JPushUtils.pushMsg(client, payLoad);
			System.out.println(result.toString());
		}
		
	}

	/**
	 * @param element
	 * @param client
	 * @return   判断当前用户是否注册推送
	 */
	private boolean hasRegin(String element, JPushClient client) {
		try {
			AliasDeviceListResult isAndroidExit = client.getAliasDeviceList(element, "android");
			AliasDeviceListResult isIosExit = client.getAliasDeviceList(element, "ios");
			return isAndroidExit.registration_ids.toString().length()>3||isIosExit.registration_ids.toString().length()>3;
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIRequestException e) {
			e.printStackTrace();
		}
		return false;
	}

}
