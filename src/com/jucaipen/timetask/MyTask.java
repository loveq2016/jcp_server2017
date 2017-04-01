package com.jucaipen.timetask;

import java.util.TimerTask;

import com.jucaipen.utils.LoginUtil;

public class MyTask extends TimerTask{
	private String url="http://user.jucaipen.com/wx_activity/admin_updatecount.ashx";

	@Override
	public void run() {
		LoginUtil.sendHttpGet(url, null);
	}

}
