package com.jucaipen.main.payinfo;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import org.apache.log4j.Logger;
import sun.misc.BASE64Decoder;
/**
 * RSA签名公共类
 * @author shmily
 */
public class TraderRSAUtil{
    private static Logger log= Logger.getLogger(TraderRSAUtil.class);
  //  private static String  RSA_PRA= Property.getProperty("TUOJI_RSA_PRA");
    /* private static String        RSA_PUC       = Property.getProperty("TUOJI_RSA_PUC");*/
    /**
     * 加密算法RSA
     */
    public static final String   KEY_ALGORITHM = "RSA";

  /*  private TraderRSAUtil()
    {

    }

    public static TraderRSAUtil getInstance()
    {
        if (null == instance)
            return new TraderRSAUtil();
        return instance;
    }*/

    /**
     * 
     * 公钥、私钥文件生成
     * @param keyPath：保存文件的路径
     * @param keyFlag：文件名前缀
     */
   /* private void generateKeyPair(String key_path, String name_prefix)
    {
        java.security.KeyPairGenerator keygen = null;
        try
        {
            keygen = java.security.KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e1)
        {
            log.error(e1.getMessage());
        }
        SecureRandom secrand = new SecureRandom();
        secrand.setSeed("3500".getBytes()); // 初始化随机产生器
        keygen.initialize(1024, secrand);
        KeyPair keys = keygen.genKeyPair();
        PublicKey pubkey = keys.getPublic();
        PrivateKey prikey = keys.getPrivate();

        String pubKeyStr = new String(org.apache.commons.codec.binary.Base64
                .encodeBase64(pubkey.getEncoded()));
        String priKeyStr = new String(org.apache.commons.codec.binary.Base64
                .encodeBase64(org.apache.commons.codec.binary.Base64
                        .encodeBase64(prikey.getEncoded())));
        File file = new File(key_path);
        if (!file.exists())
        {
            file.mkdirs();
        }
        try
        {
            // 保存私钥
            FileOutputStream fos = new FileOutputStream(new File(key_path
                    + name_prefix + "_RSAKey_private.txt"));
            fos.write(priKeyStr.getBytes());
            fos.close();
            // 保存公钥
            fos = new FileOutputStream(new File(key_path + name_prefix
                    + "_RSAKey_public.txt"));
            fos.write(pubKeyStr.getBytes());
            fos.close();
        } catch (IOException e)
        {
            log.error(e.getMessage());
        }
    }*/

    /**
     * 读取密钥文件内容
     * @param key_file:文件路径
     * @return
     */
    /*private static String getKeyContent(String key_file)
    {
        File file = new File(key_file);
        BufferedReader br = null;
        InputStream ins = null;
        StringBuffer sReturnBuf = new StringBuffer();
        try
        {
            ins = new FileInputStream(file);
            br = new BufferedReader(new InputStreamReader(ins, "UTF-8"));
            String readStr = null;
            readStr = br.readLine();
            while (readStr != null)
            {
                sReturnBuf.append(readStr);
                readStr = br.readLine();
            }
        } catch (IOException e)
        {
            return null;
        } finally
        {
            if (br != null)
            {
                try
                {
                    br.close();
                    br = null;
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            if (ins != null)
            {
                try
                {
                    ins.close();
                    ins = null;
                } catch (IOException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }
        return sReturnBuf.toString();
    }
*/
    /**
     * 签名处理
     * @param prikeyvalue：私钥文件
     * @param sign_str：签名源内容
     * @return
     */
    public static String sign(String prikeyvalue, String sign_str)
    {
       try
        {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64
                    .getBytesBASE64(prikeyvalue));
            KeyFactory keyf = KeyFactory.getInstance("RSA");
            PrivateKey myprikey = keyf.generatePrivate(priPKCS8);
            // 用私钥对信息生成数字签名
            java.security.Signature signet = java.security.Signature
                    .getInstance("MD5withRSA");
            signet.initSign(myprikey);
            signet.update(sign_str.getBytes("UTF-8"));
            byte[] signed = signet.sign(); // 对信息的数字签名
            // return Base64.getBASE64(signed);
            return new String(org.apache.commons.codec.binary.Base64
                    .encodeBase64(signed));
        } catch (java.lang.Exception e)
        {
            log.error("签名失败," + e.getMessage());
        }
        return null;
    }

    /**
     * 签名验证
     * @param pubkeyvalue：公钥
     * @param oid_str：源串
     * @param signed_str：签名结果串
     * @return
     */
   /* public static boolean checksign(String pubkeyvalue, String oid_str,
            String signed_str)
    {
        try
        {
            X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(Base64
                    .getBytesBASE64(pubkeyvalue));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey pubKey = keyFactory.generatePublic(bobPubKeySpec);
            byte[] signed = Base64.getBytesBASE64(signed_str);// 这是SignatureData输出的数字签名
            java.security.Signature signetcheck = java.security.Signature
                    .getInstance("MD5withRSA");
            signetcheck.initVerify(pubKey);
            signetcheck.update(oid_str.getBytes("UTF-8"));
            return signetcheck.verify(signed);
        } catch (java.lang.Exception e)
        {
            log.error("签名验证异常," + e.getMessage());
        }
        return false;
    }
*/
    /** 
     * 得到公钥 
     * @param key 密钥字符串（经过base64编码） 
     * @throws Exception 
     */
    public static PublicKey getPublicKey(String key) throws Exception
    {
        byte[] keyBytes;
        keyBytes = (new BASE64Decoder()).decodeBuffer(key);

        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    /** 
     * 得到私钥 
     * @param key 密钥字符串（经过base64编码） 
     * @throws Exception 
     */
    public static PrivateKey getPrivateKey(String key) throws Exception
    {
        byte[] keyBytes;
        keyBytes = (new BASE64Decoder()).decodeBuffer(key);

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

  /*  public static String decodeRSAPrivate(String plainText)
    {
        try
        {

            PrivateKey privateKey = getPrivateKey(RSA_PRA);
            Cipher cipher = Cipher.getInstance("RSA");
            // 解密
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            BASE64Decoder dec = new BASE64Decoder();
            byte[] miwen = dec.decodeBuffer(plainText);
            byte[] deBytes = cipher.doFinal(miwen);
            String s = new String(deBytes);
            return s;
        } catch (Exception e)
        {
            System.out.print(e);
            return "error";
        }
    }*/

    /*public static void main(String[] args)
    {
        String text = "app_id=2015092300315923&charset=UTF-8";
//        String pri = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAOilN4tR7HpNYvSBra/DzebemoAiGtGeaxa+qebx/O2YAdUFPI+xTKTX2ETyqSzGfbxXpmSax7tXOdoa3uyaFnhKRGRvLdq1kTSTu7q5s6gTryxVH2m62Py8Pw0sKcuuV0CxtxkrxUzGQN+QSxf+TyNAv5rYi/ayvsDgWdB3cRqbAgMBAAECgYEAj02d/jqTcO6UQspSY484GLsL7luTq4Vqr5L4cyKiSvQ0RLQ6DsUG0g+Gz0muPb9ymf5fp17UIyjioN+ma5WquncHGm6ElIuRv2jYbGOnl9q2cMyNsAZCiSWfR++op+6UZbzpoNDiYzeKbNUz6L1fJjzCt52w/RbkDncJd2mVDRkCQQD/Uz3QnrWfCeWmBbsAZVoM57n01k7hyLWmDMYoKh8vnzKjrWScDkaQ6qGTbPVL3x0EBoxgb/smnT6/A5XyB9bvAkEA6UKhP1KLi/ImaLFUgLvEvmbUrpzY2I1+jgdsoj9Bm4a8K+KROsnNAIvRsKNgJPWd64uuQntUFPKkcyfBV1MXFQJBAJGs3Mf6xYVIEE75VgiTyx0x2VdoLvmDmqBzCVxBLCnvmuToOU8QlhJ4zFdhA1OWqOdzFQSw34rYjMRPN24wKuECQEqpYhVzpWkA9BxUjli6QUo0feT6HUqLV7O8WqBAIQ7X/IkLdzLa/vwqxM6GLLMHzylixz9OXGZsGAkn83GxDdUCQA9+pQOitY0WranUHeZFKWAHZszSjtbe6wDAdiKdXCfig0/rOdxAODCbQrQs7PYy1ed8DuVQlHPwRGtokVGHATU=";
//        String pub = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC+fQi1mZFzfWQ9ZiauedXF8ximqsdeqnBIdPXr+LbYlAN2n47HGSPiJmV3qFOoOl0UfQLjt9AebonyzV3eXcdTO9HYhuNGTTeVqYBI/ZAI6ZkbqV5m8WmJqOhpvXlq7tDRnvWCpr+7+XXH+2Z8NY9QIdv8YX//Hz+RbZqG/hBprQIDAQAB";
        String pub =   "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC+fQi1mZFzfWQ9ZiauedXF8ximqsdeqnBIdPXr+LbYlAN2n47HGSPiJmV3qFOoOl0UfQLjt9AebonyzV3eXcdTO9HYhuNGTTeVqYBI/ZAI6ZkbqV5m8WmJqOhpvXlq7tDRnvWCpr+7+XXH+2Z8NY9QIdv8YX//Hz+RbZqG/hBprQIDAQAB";
      String pri = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAL59CLWZkXN9ZD1mJq551cXzGKaqx16qcEh09ev4ttiUA3afjscZI+ImZXeoU6g6XRR9AuO30B5uifLNXd5dx1M70diG40ZNN5WpgEj9kAjpmRupXmbxaYmo6Gm9eWru0NGe9YKmv7v5dcf7Znw1j1Ah2/xhf/8fP5Ftmob+EGmtAgMBAAECgYAbijIvJyOrQc6zPciL4xtwAgRnXDt0yUlwgFBprEyrJUMW7FyVFmJkuM5krVZi9QUuCCDZ0WJqc+rHyr8uffpqIxr6kLaHGW9EIdotQQOUGDjxuIM0Nth1JR7L2M3Ize/pENlBHD4VRGJXlIe1Sck4lDcmroGfAYJraXVMAbBLlQJBAPEpuVAfYmb5oc87S27YkMb4w95smIvOmgUSurWH1czE5rPZTjw84XEZ9DKQ0sHykf3Wd0gkr88eF6V+grHgyl8CQQDKNTG62yGVZJLaZW/QPbGwomXbZp1NkVFZpMEQKtF5BtaNkEb23etMak9lwAZop/IO9FbFiYZKxQVIBdJy7B9zAkEA5irxT/Q0QZ3dfmDUNN4DB7QjlOTGjyDHRTJD+r/DIWWH3nz3O2zLqduggqZQUIVIKyGGxNcm6KGK5guhh03JNwJAYLcErCz3aS8Llm4CBwYbITLrqP7xqhIhaNEGjqcMPejpYhhp+NW4H3qRs0cfSZtaiZCQmDbpBoYpuoMeX11UxQJBAJ8hVrgQ2ZoKOPTZR+je2bq21dlj36NcCkk7fg7voUy+kLS4mUDmhlQoFIAlK0mQ4n1k130y4b5QnCCpjs5xiFw=";
        String sign = sign(pri, text);
        System.out.println("sign:" + sign);
        boolean istrue = checksign(pub, text, sign);
        System.out.println("result:" + istrue);
    }*/
}
