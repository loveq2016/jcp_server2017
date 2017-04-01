package com.jucaipen.model;

/**
 * @author Administrator
 *
 *  聊天消息对象    {fromId,toId,{"hello",2,5,8,"i love you"}}
 */
public class MessageObject {
	
	public static void main(String[] args) {
		String msg="{1,10,{'hello',1,8,10,'i'}}";
		String m=msg.replace("{","").replace("}","");
		String[] g=m.split(",");
		for(int i=0;i<g.length;i++){
			System.out.println("p:  "+g[i]+" \n");
			
		}
		
	}
	

}
