package com.jucaipen.utils;
public class Constant {
	
	public static final String CONNECT_DRIVER="";
	
	public static final String CONNECT_NAME="";
	
	public static final String CONNECT_PWD="";
	
	
	/*
	 * SqlServer 测试数据库 new 192.168.1.127 old 192.168.1.233 JcpStudyPlatformData
	 * 198 128 JCPData
	 */
	public static final String SQLSERVER_DRIVER_TEST = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public static final String SQLSERVER_URL_TEST = "jdbc:sqlserver://192.168.1.198; DatabaseName=JcpStudyPlatformData";
	public static final String SQLSERVER_UNAME_TEST = "sa";
	public static final String SQLSERVER_UPWD_TEST = "111111";

	/**
	 * 新数据库 正式
	 */
	public static final String SQLSERVER_DRIVER_N = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public static final String SQLSERVER_URL_N = "jdbc:sqlserver://121.41.46.228; DatabaseName=JcpStudyPlatformData";
	public static final String SQLSERVER_UNAME_N = "jcpstudy";
	public static final String SQLSERVER_UPWD_N = "jcp@)!^168";

	/**
	 * SqlServer 视频数据库
	 */
	public static final String SQLSERVER_DRIVER_VIDEO = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public static final String SQLSERVER_URL_VIDEO = "jdbc:sqlserver://121.41.46.228; DatabaseName=ChatRoom";
	public static final String SQLSERVER_UNAME_VIDEO = "chat";
	public static final String SQLSERVER_UPWD_VIDEO = "cHat2013";

	/*
	 * SqlServer 正式数据库
	 */
	public static final String SQLSERVER_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public static final String SQLSERVER_URL = "jdbc:sqlserver://121.41.46.228; DatabaseName=JCPData";
	public static final String SQLSERVER_UNAME = "jcp";
	public static final String SQLSERVER_UPWD = "jCp#)2016";

	/*
	 * 本地MySql 数据库
	 */
	public static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	public static final String MYSQL_URL = "jdbc:mysql://121.40.227.121:3306/jucaipen";
	public static final String MYSQL_UNAME = "jucaipen";
	public static final String MYSQL_UPWD = "jucaipen168";
	public static final String MYSQL_ENCODING = "useUnicode=true&characterEncoding=UTF8";

	/*
	 * 本地MySql 数据库 test
	 */
	public static final String MYSQL_DRIVER_TEST = "com.mysql.jdbc.Driver";
	public static final String MYSQL_URL_TEST = "jdbc:mysql://localhost:3306/test_main";
	public static final String MYSQL_UNAME_TEST = "root";
	public static final String MYSQL_UPWD_TEST = "111111";
	@SuppressWarnings("unused")
	public static final String MYSQL_ENCODING_TEST = "useUnicode=true&characterEncoding=UTF8";

	/**
	 * Derby 数据库配置信息
	 */
	public static final String DERBY_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	public static final String DERBY_URL = "jdbc:derby://121.40.227.121:1521/APP;create=true";
	public static final String DERBY_UNAME = "jucaipen168";
	public static final String DERBY_PWD = "jucaipen168";
	
	
	/**
	 *   默认缓存对象
	 */
	public static final String DEFAULT_CACHE="cache_default";
	
	/**
	 *   视频缓存
	 */
	public static final String VIDEO_CACHE="cache_video";
	
	
	/**
	 *   缓存讲师排名、用户信息
	 */
	public static final String TEACHER_CACHE="cache_teacher";
	
	
	/**
	 *  3s缓存
	 */
	public static final String FAST_CANCHE="cache_fast";
	
	
	

}
