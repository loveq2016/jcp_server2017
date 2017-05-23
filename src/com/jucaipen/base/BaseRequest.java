package com.jucaipen.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @author ���ʷ�
 * 
 *   �������дʣ��滻�������
 */
public class BaseRequest extends HttpServletRequestWrapper{

	public BaseRequest(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getParameter(String name) {
		if(name==null){
			return null;
		}
		String param = super.getParameter(name);
		return param;
	}
	
	

}
