package com.jucaipen.base;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jucaipen.model.ApkInfo;
import com.jucaipen.service.ApkInfoServer;
import com.jucaipen.utils.CacheUtils;
import com.jucaipen.utils.Constant;
import com.jucaipen.utils.JsonUtil;
import com.jucaipen.utils.StringUtil;
import com.jucaipen.utils.TimeUtils;
public class UpdateApkInfo extends HttpServlet {
	private static final long serialVersionUID = -7105622510661532582L;
	private String result;
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String fromType=request.getParameter("fromType");
		String versionName=request.getParameter("versionName");
		String versionCode=request.getParameter("versionCode");
		String filePath = request.getParameter("file");
		String length=request.getParameter("length");
        if(StringUtil.isNotNull(fromType)){
        	if(StringUtil.isInteger(fromType)){
        		int type=Integer.parseInt(fromType);
            	if(type==0){
            		uploadInfo(filePath,versionCode,versionName,length);
            	}else{
            		ApkInfo info = ApkInfoServer.findLastApkInfo(1);
            		result=info.getApkPath();
            	}
        	}else{
        		 result=JsonUtil.getRetMsg(4,"fromType 参数异常");
        	}
        }else{
        	uploadInfo(filePath,versionCode,versionName,length);
        }
		out.print(result);
		out.flush();
		out.close();
	}
	
	private void uploadInfo(String filePath,String versionCode,String versionName,String length) {
		if(StringUtil.isNotNull(filePath)&&filePath.startsWith("http")){
			 if(StringUtil.isNotNull(versionCode)&&StringUtil.isInteger(versionCode)){
				 int maxId=querryMaxId();
					if (maxId > 0) {
						// 更新apk数据库数据
						ApkInfo info = createApkDate(versionCode,versionName, filePath,maxId,length);
						if (info != null) {
							int updateApkInfo = updateApkInfo(info);
							result=updateApkInfo>0 ? JsonUtil.getRetMsg(0, "文件上传处理成功") : JsonUtil.getRetMsg(1, "文件上传处理失败");
						} else {
							result=JsonUtil.getRetMsg(1, "文件上传处理失败");
						}
					} else {
						result=JsonUtil.getRetMsg(1, "文件上传处理失败");
					}
			 }else{
				 result=JsonUtil.getRetMsg(3,"versionCode 参数异常");
			 }
		}else{
			result=JsonUtil.getRetMsg(2, "file 不能为空");
		}
		
	}

	private ApkInfo createApkDate(String versionCode, String versionName,
			String filePath, int maxId, String length) {
		ApkInfo info = new ApkInfo();
		info.setId(++maxId);
		info.setVsionName(versionName);
		info.setApkPath(filePath);
		info.setPkgName("com.example.androidnetwork");
		info.setUpdateDate(TimeUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		info.setIsForce(0);
		info.setLength(length);
		if (StringUtil.isInteger(versionCode)) {
			int vCode = Integer.parseInt(versionCode);
			info.setVersionCode(vCode);
		}
		new CacheUtils(Constant.FILE_CACHE).addToCache("apkInfo"+versionCode, info);;
		return info;
	}

	/**
	 * @param info
	 * 
	 *            上传APK文件
	 * @param string 
	 */
	private int updateApkInfo(ApkInfo info) {
		 return ApkInfoServer.insertApkInfo(info);
	}
	
	/**
	 * 获取APK信息最大的id
	 */
	private int querryMaxId() {
		return ApkInfoServer.querryMaxId();

	}
	

}
