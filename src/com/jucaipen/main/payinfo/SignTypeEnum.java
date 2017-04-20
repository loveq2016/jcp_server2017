package com.jucaipen.main.payinfo;

import com.jucaipen.utils.StringUtil;

/**
* ǩ����ʽö��
* @author guoyx
* @date:May 13, 2013 8:22:15 PM
* @version :1.0
*
*/
public enum SignTypeEnum
{

    RSA("RSA", "RSAǩ��"),
    MD5("MD5", "MD5ǩ��");

    private final String code;
    private final String msg;

    SignTypeEnum(String code, String msg)
    {
        this.code = code;
        this.msg = msg;
    }

    public String getCode()
    {
        return code;
    }

    public String getMsg()
    {
        return msg;
    }
    public static boolean isSignType(String code){
        if(!StringUtil.isNotNull(code)){
            return false;
        }
        for(SignTypeEnum s:SignTypeEnum.values()){
            if(s.code.equals(code)){
                return true;
            }
        }
        return false;
    }
}
