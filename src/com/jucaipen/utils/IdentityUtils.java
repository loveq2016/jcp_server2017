package com.jucaipen.utils;
 
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
/**  
 *  新身份证 -- 六位数字地址码，八位数字出生日期码，三位数字顺序码和一位数字校验码
 *  
 *  
 *  1. 地址码（ABCDEF）：表示编码对象常住户口所在县(市、旗、区)的行政区划代码，按GB/T2260的规定执行。
 *   2. 出生日期码（YYYYMMDD）：表示编码对象出生的年、月、日，按GB/T7408的规定执行，年、月、日分别用4位、2位（不足两位加0）、2位（不足两位加0）数字表示，之间不用分隔符。
 *  3. 顺序码（xxx）：表示在同一地址码所标识的区域范围内，对同年、同月、同日出生的人编定的顺序号，顺序码的奇数分配给男性，偶数分配给女性。
 *   4．校验码（R），一位数字，通过前17位数字根据一定计算得出，检验码分别是“0、1、2、……10”共11个数字，当检验码为“10”时，为了保证公民身份证号码18位，所以用“X”表示。
 *
 */
public class IdentityUtils {
//    位权值数组
    private static byte[] Wi=new byte[17];
//    身份证前部分字符数
    private static final byte fPart = 6;
//    身份证算法求模关键值
    private static final byte fMod = 11;
//    旧身份证长度
    private static final byte oldIDLen = 15;
//    新身份证长度
    private static final byte newIDLen = 18;
//    新身份证年份标志
    private static final String yearFlag = "19";
//    校验码串 
    private static final String CheckCode="10X98765432"; 
//    最小的行政区划码
    private static final int minCode = 150000;
//    最大的行政区划码
    private static final int maxCode = 700000;
//    旧身份证号码
//    private String oldIDCard="";
//    新身份证号码
//    private String newIDCard="";
//    地区及编码
    
    
    //private String Area[][2] = 
    private static void setWiBuffer(){
        for(int i=0;i<Wi.length;i++){    
            int k = (int) Math.pow(2, (Wi.length-i));
            Wi[i] = (byte)(k % fMod);
        }
    }
    
    //获取新身份证的最后一位:检验位
    private static String getCheckFlag(String idCard){
        int sum = 0;
        //进行加权求和 
        for(int i=0; i<17; i++){        
            sum += Integer.parseInt(idCard.substring(i,i+1)) * Wi[i];
        }
        //取模运算，得到模值
        byte iCode = (byte) (sum % fMod); 
        return CheckCode.substring(iCode,iCode+1);
    }
    
    //判断串长度的合法性
    private static boolean checkLength(final String idCard,boolean newIDFlag){
        boolean right = (idCard.length() == oldIDLen) || (idCard.length() == newIDLen);
        newIDFlag = false;
        if(right){
            newIDFlag = (idCard.length() == newIDLen);
        }
        return right;
    }
    
    //获取时间串
    private static String getIDDate(final String idCard,boolean newIDFlag){
        String dateStr = "";
        if(newIDFlag)
            dateStr = idCard.substring(fPart,fPart+8);
        else
            dateStr = yearFlag + idCard.substring(fPart,fPart+6);
        return dateStr;
    }
    
    //判断时间合法性
    private static boolean checkDate(final String dateSource){
        String dateStr = dateSource.substring(0,4)+"-"+dateSource.substring(4,6)+"-"+dateSource.substring(6,8);
        System.out.println(dateStr);
        DateFormat df = DateFormat.getDateInstance();
        df.setLenient(false);
        try {
            Date date= df.parse(dateStr);
            return (date!=null);
        } catch (ParseException e) {
        	System.out.println("err");
            return false;
        }
    }
    
    //旧身份证转换成新身份证号码
    public static String getNewIDCard(final String oldIDCard){
        //初始化方法
    	IdentityUtils.setWiBuffer();
        if(!checkIDCard(oldIDCard)){
            return oldIDCard;
        }
        String newIDCard = oldIDCard.substring(0, fPart);
        newIDCard += yearFlag;
        newIDCard += oldIDCard.substring(fPart, oldIDCard.length());
        String ch = getCheckFlag(newIDCard);
        newIDCard += ch;
        return newIDCard;
    }
    
    //新身份证转换成旧身份证号码
    public static String getOldIDCard(final String newIDCard){
        //初始化方法
    	IdentityUtils.setWiBuffer();
        if(!checkIDCard(newIDCard)){
            return newIDCard;
        }
        String oldIDCard = newIDCard.substring(0,fPart)+
                    newIDCard.substring(fPart+yearFlag.length(),newIDCard.length()-1);
        return oldIDCard;
    }
    
    //判断身份证号码的合法性
    public static boolean checkIDCard(final String idCard){
        //初始化方法
    	IdentityUtils.setWiBuffer();
        boolean isNew = true;
        //String message = "";
        if (!checkLength(idCard,isNew)){
            //message = "ID长度异常";
        	System.out.println("ID长度异常");
            return false;
        }
        String idDate = getIDDate(idCard, isNew);
        if(!checkDate(idDate)){
            //message = "ID时间异常";
        	System.out.println("ID时间异常");
            return false;
        }
        if(isNew){
            String checkFlag = getCheckFlag(idCard);
            String theFlag = idCard.substring(idCard.length()-1,idCard.length());
            if(!checkFlag.equals(theFlag)){
                //message = "新身份证校验位异常";
            	System.out.println("新身份证校验位异常");
                return false;
            }
        }
        return true;
    }
    
    //获取一个随机的"伪"身份证号码
    public static String getRandomIDCard(final boolean idNewID){
        //初始化方法
    	IdentityUtils.setWiBuffer();
        Random ran = new Random();
        String idCard = getAddressCode(ran)+getRandomDate(ran,idNewID)+getIDOrder(ran);
        if(idNewID){
            String ch = getCheckFlag(idCard);
            idCard += ch;
        }
        return idCard;
    }
    
    //产生随机的地区编码
    private static String getAddressCode(Random ran) {
        if(ran==null){
            return "";
        }else{
            int addrCode = minCode + ran.nextInt(maxCode-minCode);
            return Integer.toString(addrCode);
        }
    }
    
    //产生随机的出生日期
    private static String getRandomDate(Random ran, boolean idNewID) {
        if(ran==null){
            return "";
        }
        int year = 0;
        if(idNewID){
            year = 1900 + ran.nextInt(2007-1900);
        }else{
            year = 1 + ran.nextInt(99);
        }
        int month = 1+ran.nextInt(12);
        int day = 0;
        if(month==2){
            day= 1+ran.nextInt(28);
        }else if(month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12){
            day= 1+ran.nextInt(31);
        }else{
            day= 1+ran.nextInt(30);
        }
        NumberFormat nf = NumberFormat.getIntegerInstance();
        nf.setMaximumIntegerDigits(2);
        nf.setMinimumIntegerDigits(2);
        String dateStr = Integer.toString(year)+nf.format(month)+nf.format(day);
        return dateStr;
    }
 
    //产生随机的序列号
    private static String getIDOrder(Random ran) {
        NumberFormat nf = NumberFormat.getIntegerInstance();
        nf.setMaximumIntegerDigits(3);
        nf.setMinimumIntegerDigits(3);
        if(ran==null){
            return "";
        }else{
            int order = 1+ran.nextInt(999);
            return nf.format(order);
        }
    }
 
    public IdentityUtils(){
        setWiBuffer();
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        
        String randomID=IdentityUtils.getRandomIDCard(true);
        System.out.println("随机身份证："+randomID);
        /*
        String oldID="";
        String newID=Identity.getNewIDCard(oldID);
        System.out.println("旧身份证："+oldID);
        System.out.println("新身份证："+newID);
        String oldCard = Identity.getOldIDCard(newID);
        System.out.println("旧身份证："+oldCard);
        /*
        String dateSource="2000-9-30";
        if(id.checkDate(dateSource))
            System.out.println("正确时间串："+dateSource);
        else
            System.out.println("错误时间串："+dateSource);
         * 
         * 
         */
    }
    
    
    /**
     * @param idNum
     * @return  判断身份证号是否合法
     */
    public static boolean isIdCard(String idNum){
    	   //定义判别用户身份证号的正则表达式（要么是15位，要么是18位，最后一位可以为字母）  
        Pattern idNumPattern = Pattern.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");  
        //通过Pattern获得Matcher  
        Matcher idNumMatcher = idNumPattern.matcher(idNum);  
        //判断用户输入是否为身份证号  
        if(idNumMatcher.matches()){  
            //如果是，定义正则表达式提取出身份证中的出生日期  
            Pattern birthDatePattern= Pattern.compile("\\d{6}(\\d{4})(\\d{2})(\\d{2}).*");//身份证上的前6位以及出生年月日  
            //通过Pattern获得Matcher  
            Matcher birthDateMather= birthDatePattern.matcher(idNum);  
            //通过Matcher获得用户的出生年月日  
            if(birthDateMather.find()){  
                String year = birthDateMather.group(1);  
                String month = birthDateMather.group(2);  
                String date = birthDateMather.group(3);  
                //输出用户的出生年月日  
                System.out.println("出生日期："+year+"年"+month+"月"+date+"日");                  
            }   
            return true;
        }else{  
            //如果不是，输出信息提示用户  
            System.out.println("您输入的并不是身份证号");  
            return false;  
        }
    }
    
    
    
}