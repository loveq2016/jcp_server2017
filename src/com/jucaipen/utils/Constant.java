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
	 *  推荐讲师
	 */
	public static final String RECODER_TEACHER="cache_recoder_teacher";
	
	
	/**
	 *  3s缓存
	 */
	public static final String FAST_CANCHE="cache_fast";
	
	
	/**
	 *   1H 
	 */
	public static final String FILE_CACHE="cache_file";
	
	
	/**
	 *   存储  sign
	 */
	public static final String CACHE_SIGN="cache_sign";
	
	public static final  String urls[]={"http://recordcdn.quklive.com/broadcast/activity/1469002576632934/20160817130010-20160817140207.m3u8",
			"http://recordcdn.quklive.com/broadcast/activity/1469002576632934/20160818130010-20160818140219.m3u8",
			"http://recordcdn.quklive.com/broadcast/activity/1469002576632934/20160819150500-20160819154838.m3u8",
			"http://recordcdn.quklive.com/broadcast/activity/1469002576632934/20160822150934-20160822160025.m3u8",
			"http://recordcdn.quklive.com/broadcast/activity/1469002576632934/20160823085947-20160823091548.m3u8",
			"http://recordcdn.quklive.com/broadcast/activity/1469002576632934/20160825102745-20160825113430.m3u8",
			"http://recordcdn.quklive.com/broadcast/activity/1469002576632934/20160830102530-20160830113240.m3u8",
			"http://recordcdn.quklive.com/broadcast/activity/1469002576632934/20160901092130-20160901102101.m3u8",
			"http://recordcdn.quklive.com/broadcast/activity/1469002576632934/20160905135930-20160905150400.m3u8",
			"http://recordcdn.quklive.com/broadcast/activity/1469002576632934/20160906191940-20160906202001.m3u8"
			};
	
	
	public static final String urls2[]={"http://recordcdn.quklive.com/broadcast/activity/9458019977964845/20160719192025-20160719202126.m3u8",
			"http://recordcdn.quklive.com/broadcast/activity/1469002576632934/20160721130012-20160721140053.m3u8",
			"http://recordcdn.quklive.com/broadcast/activity/1469002576632934/20160725102510-20160725113411.m3u8",
			"http://recordcdn.quklive.com/broadcast/activity/1469002576632934/20160727192010-20160727202111.m3u8",
			"http://recordcdn.quklive.com/broadcast/activity/1469002576632934/20160729130000-20160729140032.m3u8",
			"http://recordcdn.quklive.com/broadcast/activity/1469002576632934/20160801102551-20160801113353.m3u8",
			"http://recordcdn.quklive.com/broadcast/activity/1469002576632934/20160802090747-20160802092208.m3u8",
			"http://recordcdn.quklive.com/broadcast/activity/1469002576632934/20160809092055-20160809102256.m3u8",
			"http://recordcdn.quklive.com/broadcast/activity/1469002576632934/20160815085910-20160815091500.m3u8",
			"http://recordcdn.quklive.com/broadcast/activity/1469002576632934/20160816092110-20160816102025.m3u8"
			};
	
	

}
