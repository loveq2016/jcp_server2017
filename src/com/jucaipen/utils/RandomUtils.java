package com.jucaipen.utils;

import java.util.Random;

/**
 * @author Administrator
 *
 *
 *  ���������
 */
public class RandomUtils {
	private static Random random;
	
	
	public static int getRandomData(int max,int min){
		if(random==null){
			random=new Random();
		}
		return  random.nextInt(max)+1;
	}
	
	
	public static String getRandomData(int count){
		StringBuffer buffer=new StringBuffer();
		if(random==null){
			random=new Random();
		}
		for(int i=0;i<count;i++){
			int r=random.nextInt(10);
			buffer.append(r);
		}
		return buffer.toString();
	}
	
	
	    public static String getCharAndNumr(int length) {
	        String val = "";
	        Random random = new Random();
	        for (int i = 0; i < length; i++) {
	            // �����ĸ��������
	            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; 
	            // �ַ���
	            if ("char".equalsIgnoreCase(charOrNum)) {
	                // ȡ�ô�д��ĸ����Сд��ĸ
	                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; 
	                val += (char) (choice + random.nextInt(26));
	            } else if ("num".equalsIgnoreCase(charOrNum)) { // ����
	                val += String.valueOf(random.nextInt(10));
	            }
	        }
	        return val;
	    }
	
}
