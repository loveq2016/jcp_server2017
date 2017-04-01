package com.jucaipen.test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class JsonTest {
	
	public static void main(String[] args) {
		JsonObject object=new JsonObject();
		object.addProperty("ret_code", 0);
		object.addProperty("ret_msg", "·µ»Ø³É¹¦");
		JsonArray array=new JsonArray();
		
		for (int i = 0; i < 2; i++) {
			JsonObject object2=new JsonObject();
			object2.addProperty("id", 1);
			object2.addProperty("msg", "hh");
			array.add(object2);
		}
		
		object.add("data", array);
		System.out.println("res:"+object.toString());
	}

}
