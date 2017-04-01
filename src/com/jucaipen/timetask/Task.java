package com.jucaipen.timetask;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;

import com.jucaipen.utils.FileUtils;
import com.jucaipen.utils.LoginUtil;
import com.jucaipen.utils.TimeUtils;
/**
 * Ö´ÐÐÄÚÈÝ
 * @author admin_Hzw
 *
 */
public class Task extends TimerTask {
	String path="D:\\Test\\my.txt";
	private Map<String, String> param=new HashMap<String, String>();
	private static final String url="http://user.jucaipen.com/activity/activitydata.ashx?action=updatecount";
	private static final String key="jcp168!@#";
	public void run() {
		param.clear();
		param.put("key", key);
		String result = LoginUtil.sendHttpPost(url, param);
		opFile("time:"+TimeUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss")+"      "+result);
	}
	
	public void opFile(String content){
		FileUtils.createFile(path);
		FileUtils.contentToTxt(path, "\r\n"+content);
	}
	
	
}