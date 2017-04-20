package com.jucaipen.main.payinfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.alibaba.fastjson.JSONObject;
import com.jucaipen.utils.StringUtil;
public class TestUtil{
    /**
     * ǩ����֤
     * 
     * @param reqObj
     * @return
     */
 /*   public static  boolean checkSign(JSONObject reqObj, String rsa_public,
            String md5_key)
    {
        if (reqObj == null)
        {
            return false;
        }
        String sign_type = reqObj.getString("sign_type");
        if (SignTypeEnum.MD5.getCode().equals(sign_type))
        {
            return checkSignMD5(reqObj, md5_key);
        } else
        {
            return checkSignRSA(reqObj, rsa_public);
        }
    }*/

    /**
     * MD5ǩ����֤
     * 
     * @param signSrc
     * @param sign
     * @return
     */
   /* private static boolean checkSignMD5(JSONObject reqObj, String md5_key)
    {
        System.out.println("����MD5ǩ����֤");
        if (reqObj == null)
        {
            return false;
        }
        String sign = reqObj.getString("sign");
        // ���ɴ�ǩ����
        String sign_src = genSignData(reqObj);
        sign_src += "&key=" + md5_key;
        System.out.println("���ر��Ĵ�ǩ��ԭ��" + sign_src);
        System.out.println("���ر���ǩ����" + sign);
        try
        {
            if (sign.equalsIgnoreCase(Md5Algorithm.getInstance().md5Digest(
                    sign_src.getBytes("utf-8"))))
            {
                System.out.println("MD5ǩ����֤ͨ��");
                return true;
            } else
            {
                System.out.println("MD5ǩ����֤δͨ��");
                return false;
            }
        } catch (UnsupportedEncodingException e)
        {
            System.out.println("MD5ǩ����֤�쳣" + e.getMessage());
            return false;
        }
    }*/

    /**
     * RSAǩ����֤
     * 
     * @param reqObj
     * @return
     */
   /* private static boolean checkSignRSA(JSONObject reqObj, String rsa_public)
    {

        System.out.println("����RSAǩ����֤");
        if (reqObj == null)
        {
            return false;
        }
        String sign = reqObj.getString("sign");
        // ���ɴ�ǩ����
        String sign_src = genSignData(reqObj);
        System.out.println("���ر��Ĵ�ǩ��ԭ��" + sign_src);
        System.out.println("���ر���ǩ����" + sign);
        try
        {
            if (TraderRSAUtil.checksign(rsa_public, sign_src, sign))
            {
                System.out.println("RSAǩ����֤ͨ��");
                return true;
            } else
            {
                System.out.println("RSAǩ����֤δͨ��");
                return false;
            }
        } catch (Exception e)
        {
            System.out.println("RSAǩ����֤�쳣" + e.getMessage());
            return false;
        }
    }*/

    /**
     * ��ǩ
     * @param reqObj
     * @param rsa_private
     * @param md5_key
     * @return
     */
    public String addSign(JSONObject reqObj, String rsa_private, String md5_key)
    {
        if (reqObj == null)
        {
            return "";
        }
        String sign_type = reqObj.getString("sign_type");
        if (SignTypeEnum.MD5.getCode().equals(sign_type))
        {
            return addSignMD5(reqObj, md5_key);
        } else
        {
            return addSignRSA(reqObj, rsa_private);
        }
    }

    /**
     * RSA��ǩ��
     * 
     * @param reqObj
     * @param rsa_private
     * @return
     */
    private String addSignRSA(JSONObject reqObj, String rsa_private)
    {
        System.out.println("����RSA��ǩ��");
        if (reqObj == null)
        {
            return "";
        }
        // ���ɴ�ǩ����
        String sign_src = genSignData(reqObj);
        System.out.println("�̻�[" + reqObj.getString("oid_trader") + "]��ǩ��ԭ��:"
                + sign_src);
        try
        {
            return TraderRSAUtil.sign(rsa_private, sign_src);
        } catch (Exception e)
        {
            System.out.println("RSA��ǩ���쳣" + e.getMessage());
            return "";
        }
    }

    /**
     * MD5��ǩ��
     * 
     * @param reqObj
     * @param md5_key
     * @return
     */
    private String addSignMD5(JSONObject reqObj, String md5_key)
    {

        if (reqObj == null)
        {
            return "";
        }
        // ���ɴ�ǩ����
        String sign_src = genSignData(reqObj);
        sign_src += "&key=" + md5_key;
        System.out.println("�̻�[" + reqObj.getString("oid_trader") + "]��ǩ��ԭ��:"
                + sign_src);
        try
        {
            return Md5Algorithm.getInstance().md5Digest(
                    sign_src.getBytes("utf-8"));
        } catch (Exception e)
        {
            System.out.println("MD5��ǩ���쳣" + e.getMessage());
            return "";
        }
    }

    /**
     * 
     * 
     * @param paramMap
     * @return
     */
    public static String genSignData(JSONObject jsonObject)
    {
        StringBuffer content = new StringBuffer();

        List<String> keys = new ArrayList<String>(jsonObject.keySet());
        Collections.sort(keys, String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < keys.size(); i++)
        {
            String key = (String) keys.get(i);

            if ("sign".equals(key))
            {
                continue;
            }
            String value = (String) jsonObject.getString(key);

            if (!StringUtil.isNotNull(value))
            {
                continue;
            }
            content.append((i == 0 ? "" : "&") + key + "=" + value);

        }
        String signSrc = content.toString();
        if (signSrc.startsWith("&"))
        {
            signSrc = signSrc.replaceFirst("&", "");
        }
        return signSrc;
    }

}
