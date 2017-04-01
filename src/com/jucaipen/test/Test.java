package com.jucaipen.test;

import java.util.Date;
import java.util.List;

import com.jucaipen.model.TextLive;
import com.jucaipen.service.TxtLiveSer;
import com.jucaipen.utils.TimeUtils;

public class Test {
	
	public static void main(String[] args) {
		String time=TimeUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
		System.out.println(time);
	}
	
}
