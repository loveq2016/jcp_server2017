package com.jucaipen.main.userid;

public class KeyStoreUtils {

	 /** 
     * 生成密钥 
     */  
    public static void genkey() {  
        String[] arstringCommand = new String[] {  
  
        "cmd ", "/k",  
                "start", // cmd Shell命令  
  
                "keytool",  
                "-genkey", // -genkey表示生成密钥
                "-keyalg", // -keyalg 指定密钥的算�?(�?RSA DSA（如果不指定默认采用DSA�?  
                "RSA", 
                "-validity", // -validity指定证书有效�?单位：天)，这里是36000�? 
                "36500",  
                "-keysize",//     指定密钥长度  
                "1024",
                "-sigalg",
                "SHA1withRSA",
                
                "-alias", // -alias指定别名，这里是ss  
                "ss",  
                "-keystore", // -keystore指定存储位置，这里是d:/demo.keystore  
                "d:/demo.keystore",  
                "-dname",// CN=(名字与姓�?, OU=(组织单位名称), O=(组织名称), L=(城市或区域名�?,  
                            // ST=(州或省份名称), C=(单位的两字母国家代码)"     CN=(SS), OU=(SS), O=(SS), L=(BJ), ST=(BJ), C=(CN)
                "CN=111, OU=222, O=3333, L=SH, ST=SH, C=CN",  
                "-storepass", // 指定密钥库的密码(获取keystore信息�?��的密�?  
                "123456",   
                "-keypass",// 指定别名条目的密�?私钥的密�?  
                "123456",   
                "-v"// -v 显示密钥库中的证书详细信�? 
        };  
        execCommand(arstringCommand);  
    }  
    
    /** 
     * 导出证书文件 
     */  
    public static void export() {  
  
        String[] arstringCommand = new String[] {  
  
        "cmd ", "/k",  
                "start", // cmd Shell命令  
  
                "keytool",  
                "-export", // - export指定为导出操�?  
                "-keystore", // -keystore指定keystore文件，这里是d:/demo.keystore  
                "d:/demo.keystore",  
                "-alias", // -alias指定别名，这里是ss  
                "ss",  
                "-file",//-file指向导出路径  
                "d:/demo.cer",  
                "-storepass",// 指定密钥库的密码  
                "123456",  
                "-rfc" 
        };  
        execCommand(arstringCommand);  
    }  
    public static  void execCommand(String[] arstringCommand) {  
        for (int i = 0; i < arstringCommand.length; i++) {  
            System.out.print(arstringCommand[i] + " ");  
        }  
        try {  
            Runtime.getRuntime().exec(arstringCommand);  
  
        } catch (Exception e) {  
            System.out.println(e.getMessage());  
        }  
    }  
    public void execCommand(String arstringCommand) {  
        try {  
            Runtime.getRuntime().exec(arstringCommand);  
              
        } catch (Exception e) {  
            System.out.println(e.getMessage());  
        }  
    }  
}
