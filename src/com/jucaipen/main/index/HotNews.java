package com.jucaipen.main.index;
import javax.servlet.http.HttpServlet;
/**
 * @author Administrator
 *         获取今日热点 isIndex 
 *         0 首页   首页   精选
 *         1 全部数据
 *      
 */
@SuppressWarnings("serial")
public class HotNews extends HttpServlet {/*
	private String result;
	private List<JcpNews> news;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");  
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userAgent = request.getParameter("User-Agent");
		ClientOsInfo os = HeaderUtil.getMobilOS(userAgent);
		int isDevice = HeaderUtil.isVaildDevice(os, userAgent);
		if (isDevice == HeaderUtil.PHONE_APP) {
			String isIndex = request.getParameter("isIndex");
			if (StringUtil.isNotNull(isIndex)) {
				if (StringUtil.isInteger(isIndex)) {
					int index = Integer.parseInt(isIndex);
					if (index == 0) {
						// 首页今日热点
						result=initIndexData();
					} else {
						// 获取所有今日热点
						String page = request.getParameter("page");
						if (StringUtil.isNotNull(page)
								&& StringUtil.isInteger(page)) {
							int p = Integer.parseInt(page);
							result=initAllData(p);
						} else {
							result = JsonUtil.getRetMsg(3, "page参数有误");
						}
					}
				} else {
					result = JsonUtil.getRetMsg(2, "isIndex参数数字格式化异常");
				}
			} else {
				result = JsonUtil.getRetMsg(1, "isIndex参数不能为空");
			}
		} else {
			result = StringUtil.isVaild;
		}
		out.println(result);
		out.flush();
		out.close();
	}

	private String initAllData(int page) {
		// 获取全部今日热点信息
		news = JcpNewsSer.findAll(page);
		for (JcpNews n : news) {
			int fromId = n.getComeFrom();
			String from = ResourceFromServer.getRSources(fromId);
			n.setFrom(from);
		}
		return JsonUtil.getNewsList(news);
	}

	private String initIndexData() {
		// 获取首页今日热点信息
		news = JcpNewsSer.findLastNewsByNewsNum(3);
		for (JcpNews n : news) {
			int fromId = n.getComeFrom();
			String from = ResourceFromServer.getRSources(fromId);
			n.setFrom(from);
		}
		return JsonUtil.getNewsList(news);
	}

*/}
