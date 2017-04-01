package com.jucaipen.utils;

/**
 * @author Administrator
 *
 */
public class SqlUtil {
	/**
	 * 股权条目表
	 */
	public static final String EQITEM_TABLE = "JCPGuquanItem";
	/**
	 * 新闻表
	 */
	public static final String NEWS_TABLE = "JCPNews";
	/**
	 * 用户表
	 */
	public static final String USER_TABLE = "JCPUser";
	/**
	 * 预约股权表
	 */
	public static final String ORDER_TABLE = "JCPGuquanItem_yuyue";
	/**
	 * 新闻标题
	 */
	public static final String NEWS_TITLE = "Title";
	/**
	 * 新闻描述
	 */
	public static final String NEWS_DES = "ZhaiYao";
	/**
	 * 新闻id
	 */
	public static final String NEWS_ID = "Id";
	/**
	 * 新闻关键字
	 */
	public static final String NEWS_KEYWORD = "KeyWord";
	/**
	 * 新闻发布者
	 */
	public static final String NEWS_REPOTER = "FaBuRen";
	/**
	 * 新闻主体
	 */
	public static final String NEWS_BODYS = "Bodys";
	/**
	 * 新闻图片URL
	 */
	public static final String NEW_IMAGE = "ImageUrl";
	/**
	 * 新闻图片缩略图
	 */
	public static final String NEWS_IMAGETHUMB = "ImagesThumb";
	/**
	 * 新闻插入时间
	 */
	public static final String NEWS_INSERT = "InsertDate";
	/**
	 *  新闻评论数
	 */
	public static final String NEWS_COMMS = "CommonCount";

	/**
	 * 新闻详细网址
	 */
	public static final String NEWS_HTMLPATH="PageUrl";
	/**
	 *  新闻来源
	 */
	public static final String NEWS_COMEFROM="FK_FromId";
	/**
	 * 股权id
	 */
	public static final String EQUITY_ID = "Id";
	/**
	 * 股份名称
	 */
	public static final String EQUITY_NAME="gufenmingcheng";
	/**
	 * 股份简称
	 */
	public static final String EQUITY_SIMPLENAME = "gufenjiancheng";
	/**
	 * 股权推荐机构
	 */
	public static final String EQUITY_RECOMM = "tuijianjigou";
	/**
	 * 股权-净利润
	 */
	public static final String EQUITY_NETPROFIT = "jinglirun";
	/**
	 * 股份代码
	 */
	public static final String EQUITY_CODE = "gufendaima";
	/**
	 * 股权-每股收益
	 */
	public static final String EQUITY_BVPS = "meigujinzichan";
	/**
	 * 股权-投资门槛
	 */
	public static final String EQUITY_IT = "touzimenkan";
	/**
	 * 股权-利润增长率
	 */
	public static final String EQUITY_RAT = "lirunzengzhanglv";
	/**
	 * 转让股数
	 */
	public static final String EQUITY_TRANSNUM = "zhuanranggushu";
	/**
	 *   拟转让价格
	 */
	public static final String EQUITY_INTENDSTRANSFER="nizhuanrangjiage";
	/**
	 * 股权图片
	 */
	public static final String EQUITY_IMAGE = "guquanimageurl";
	/**
	 * 用户登录密码
	 */
	public static final String USER_PASSWORD = "PassWord";
	/**
	 * 用户出生日期
	 */
	public static final String USER_BIRTH = "Birthday";
	/**
	 * 用户性别
	 */
	public static final String USER_SEX = "Sex";
	/**
	 * 用户真实姓名
	 */
	public static final String USER_TRUENAME = "TrueName";
	/**
	 * 用户昵称
	 */
	public static final String USER_NICKNAME = "NickName";
	/**
	 * 用户手机号
	 */
	public static final String USER_MOBILE = "MobileNum";
	/**
	 * 用户头像
	 */
	public static final String USRE_FACEIMAGE = "UserFace";
	/**
	 * 用户Email
	 */
	public static final String USER_EMAIL = "Email";
	/**
	 * 用户简介
	 */
	public static final String USER_BODYS = "UserInformation";
	/**
	 * 用户所在省份
	 */
	public static final String USER_LOCALPROVINCE = "ProvinceID";
	/**
	 * 用户所在城市
	 */
	public static final String USER_LOCALCITY = "CiytID";
	/**
	 * 用户所在区
	 */
	public static final String USER_LOCALAREA = "AreaID";
	/**
	 * 用户注册ip
	 */
	public static  final String USER_REGINIP="RegIp";
	/**
	 * 注册时间
	 */
	public static final String USER_REGINDATE="RegisterDate";
	/**
	 * 注册信息来源
	 */
	public static final String USER_REGFROM="RegFrom";
	/**
	 *   微信id
	 */
	public static final String USER_WEIXINID="WeiXinId";
	/**
	 *   新浪微博id
	 */
	public static final String USER_SINAID="WeiboId";
	/**
	 *   QQ id
	 */
	public static final String USER_QQID="QQOpenId";
	/**
	 *  注册途径（5 APP）
	 */
	public static final String USER_REGINFROM="RegFrom";

	/**
	 * 预约表id
	 */
	public static final String ORDER_ID = "Id";
	/**
	 * 预约股权用户id
	 */
	public static final String ORDER_UID = "UserId";
	/**
	 * 预约用户真实名称
	 */
	public static final String ORDER_UNAME = "TrueName";
	/**
	 * 预约人手机号码
	 */
	public static final String ORDER_MOBILE = "MobileNum";
	/**
	 * 预约股权评价
	 */
	public static final String ORDER_REMARK = "Remark";
	/**
	 * 预约股权时间
	 */
	public static final String ORDER_DATE = "InsertDate";
	/**
	 * 股权投资金额
	 */
	public static final String ORDER_TOUZI = "TouZiMoney";
	/**
	 * 预约股权id
	 */
	public static final String ORDER_EID = "ItemId";
	/**
	 * 预约用户登录设备ip
	 */
	public static final String ORDER_UIP = "Ip";
	/**
	 * 新闻收藏表
	 */
	public static final String NEWSFAVORITES_TABLE = "JCPNews_Favorites";
	/**
	 * 新闻评论表
	 */
	public static final String NEWSCOMM_TABLE = "JCPNewsCommen";
	/**
	 * 新闻回应表
	 */
	public static final String NEWSCOMMRES_TABLE = "JCPNewsCommenRes";
	/**
	 * 股权收藏表
	 */
	public static final String EQUITYFAVORITES_TABLE = "JCPGuquanItem_Favorites";
	/**
	 * 股权收藏Id
	 */
	public static final String EQUITYFAVORITES_ID = "Id";
	/**
	 * 股权收藏用户id
	 */
	public static final String EQUITYFAVORITES_UID = "UserId";
	/**
	 * 收藏股权的id
	 */
	public static final String EQUITYFAVORITES_EID = "ProductId";
	/**
	 * 收藏股权时间
	 */
	public static final String EQUITYFAVORITES_DATE = "InsertDate";
	/**
	 * 预约类型   1  投资项目预约      2  私募项目预约
	 */
	public static final String EQUITYORDER_ORDERTYPE="yuyuetype";
	/**
	 * 收藏知识/视频id
	 */
	public static final String FAVORITES_NID = "FK_Id";
	/**
	 * 添加评论的用户
	 */
	public static final String NEWSCOMM_UID="FK_UserId";
	/**
	 * 分类id
	 */
	public static final String NEWSCOMM_PARENTID="ParentId";
	/**
	 * 新闻评价内容
	 */
	public static final String NEWSCOMM_BODYS = "Bodys";
	/**
	 * 新闻评价点赞数量
	 */
	public static final String NEWSCOMM_GOODNUM = "Good";
	/**
	 * 新闻评论是否显示
	 */
	public static final String NEWSCOMM_ISSHOW = "IsShow";
	/**
	 * 新闻评论回复次数
	 */
	public static final String NEWSCOMM_REPNUM = "RepCount";
	/**
	 * 新闻回复--回复评论的id
	 */
	public static final String NEWSCOMMRES_COMMID = "CommId";
	/**
	 *   新闻评论分类
	 */
	public static final String NEWSCOMM_COMMTYPE="CommType";
	/**
	 * 省份表
	 */
	public static final String PROVINCE_TABLE = "JCP_Province";
	/**
	 * 省份id
	 */
	public static final String PROVINCE_ID = "ProvinceId";
	
	/**
	 *  区id
	 */
	public static final String EARE_ID = "EareId";

	
	
	/**
	 *    城市id
	 */
	public static final String CITY_ID="CityId";
	/**
	 * 省份名称
	 */
	public static final String PROVINCE_Name = "ProvinceName";
	/**
	 * 排序
	 */
	public static final String PROVINCE_SORT = "SortId";
	/**
	 * 省份备注
	 */
	public static final String PROVINCE_REMARK="PRemark";
	/**
	 * 城市表
	 */
	public static final String CITY_TABLE = "JCPCity";
	/**
	 * 城市所属省份id
	 */
	public static final String CITY_PID = "FK_ProId";
	/**
	 * 城市名称
	 */
	public static final String CITY_NAME = "CityName";
	/**
	 * 城市排序
	 */
	public static final String CITY_SORT="SortId";
	/**
	 * 区表
	 */
	public static final String AREA_TABLE = "JCPEare";
	/**
	 * 区所属城市id
	 */
	public static final String AREA_CID = "FK_CityId";
	/**
	 *   区域所属省id
	 */
	public static final String AREA_PID="ProcinceID";
	/**
	 * 区名称
	 */
	public static final String AREA_NAME = "EareName";
	/**
	 * 区域排序
	 */
	public static final String AREA_SORT="SortId";
	/**
	 * 机构表
	 */
	public static final String ORAG_TABLE = "JCPJigou";
	/**
	 * 机构名称
	 */
	public static final String ORAG_NAME = "JgName";
	/**
	 * 机构英文名称
	 */
	public static final String ORAG_NAMEEN = "JgNameEn";
	/**
	 * 机构简称
	 */
	public static final String ORAG_SIMPLENAME = "JianCheng";
	/**
	 * 机构创建时间
	 */
	public static final String ORAG_CREATEDATE = "CreateDate";
	/**
	 * 机构性质
	 */
	public static final String ORAG_TYPE = "Xingzhi";
	/**
	 * 机构投资领域
	 */
	public static final String ORAG_AREA = "Touzilinyu";
	/**
	 * 机构LOGO
	 */
	public static final String ORAG_LOGO = "JgLogo";
	/**
	 * 机构简介
	 */
	public static final String ORAG_BODYS = "Bodys";
	/**
	 * 机构数据插入时间
	 */
	public static final String ORAG_INSERTDATE = "InsertDate";
	/**
	 * 教师表
	 */
	public static final String TEACHER_TABLE = "JCPTeacher";
	/**
	 * 教师姓名
	 */
	public static final String TEACHER_NAME = "TearcherName";
	/**
	 * 教师头像
	 */
	public static final String TEACHER_PHOTO = "PhotoUrl";
	/**
	 * 教师概要
	 */
	public static final String TEACHER_SUMM = "Gaiyao";
	/**
	 * 教师简介
	 */
	public static final String TEACHER_BODYS = "Bodys";
	/**
	 * 教师排序
	 */
	public static final String TEACHER_SORT = "Px";
	/**
	 * 消息表
	 */
	public static final String SYSINFO_TABLE = "JCPUser_Message";
	/**
	 * 发送消息id
	 */
	public static final String SYSINFO_SENDID = "SendID";
	/**
	 * 接受消息id
	 */
	public static final String SYSINFO_RECEIVEID = "ReceiveID";
	/**
	 * 消息标题
	 */
	public static final String SYSINFO_TITLE = "Title";
	/**
	 * 消息内容
	 */
	public static final String SYSINFO_BODYS = "Bodys";
	/**
	 * 发送消息时间
	 */
	public static final String SYSINFO_SENDDATE = "SendDate";
	/**
	 * 读取消息时间
	 */
	public static final String SYSINFO_READDATE = "ReadDate";
	/**
	 * 消息类型
	 */
	public static final String SYSINFO_TYPE = "Types";
	/**
	 * 消息读取记录表
	 */
	public static final String READINFO_TABLE = "JCPUser_Message_Read";
	/**
	 * 消息读取id
	 */
	public static final String READINFO_ID = "Id";
	/**
	 * 消息读取用户id
	 */
	public static final String READINFO_USERID = "UserId";
	/**
	 * 消息id
	 */
	public static final String READINFO_MESSID = "MessId";
	/**
	 * 消息读取时间
	 */
	public static final String READINFO_READDATE = "ReadDate";
	/**
	 * 信息来源表
	 */
	public static final String RESOURCEFROM_TABLE = "JCPComeFrom";
	/**
	 * 信息来源名称
	 */
	public static final String RESOURCEFOM_NAME = "FromName";
	/**
	 * 信息来源网页
	 */
	public static final String RESOURCEFROM_HTML = "FromUrl";
	/**
	 * 信息来源排序
	 */
	public static final String RESOURCEFROM_SORTID = "SortId";
	/**
	 * 视频表
	 */
	public static final String VIDEO_TABLE = "JCPVideo";
	/**
	 * 视频标题
	 */
	public static final String VIDEO_TITLE = "Title";
	/**
	 * 视频关键字
	 */
	public static final String VIDEO_KEYWORD = "KeyWords";
	/**
	 * 视频描述
	 */
	public static final String VIDEO_DESC = "Description";
	/**
	 * 视频分类id
	 */
	public static final String VIDEO_CLASSID = "ClassId";
	/**
	 * 视频图片
	 */
	public static final String VIDEO_IMAGES = "ImagesUrl";
	/**
	 * 视频资源路径
	 */
	public static final String VIDEO_URL = "VideoUrl";
	/**
	 * 视频日期
	 */
	public static final String VIDEO_DATE = "VideoDate";
	/**
	 * 是否是本站视频（0---不是  1---是）
	 */
	public static final String VIDEO_ISMYVIDEO="IsMySiteVideo";
	/**
	 * 视频点击量
	 */
	public static final String VIDEO_HITCOUNT = "PlayCount";
	/**
	 * 视频插入时间
	 */
	public static final String VIDEO_INSERTDATE = "InsertDate";
	/**
	 * 视频描述内容
	 */
	public static final String VIDEO_BODYS = "Bodys";
	/**
	 * 授课讲师id
	 */
	public static final String VIDEO_TEACHERID = "TearcherId";
	/**
	 * 评论数量
	 */
	public static final String VIDEO_COMMCOUNT = "CommCount";
	/**
	 * 是否首页显示 （ 1首页显示 0 非首页显示）
	 */
	public static final String VIDEO_ISINDEX = "IsIndex";
	/**
	 * 是否推荐 （1 推荐 0 不推荐）
	 */
	public static final String VIDEO_INRECOMM = "IsTuijian";
	/**
	 * 是否置顶 （ 1 置顶 0 不置顶）
	 */
	public static final String VIDEO_ISTOP = "IsTop";
	/**
	 * 是否公开课（ 1 公开课 0 非公开课）
	 */
	public static final String VIDEO_ISPUBLIC = "IsPublic";
	/**
	 * 是否本站视频 （ 1 本站视频 0 非本站视频）
	 */
	public static final String VIDEO_ISMYSIT = "IsMySiteVideo";
	/**
	 * 视频分类表
	 */
	public static final String VIDEOCLASS_TABLE = "JCPVideoClass";
	/**
	 * 视频分类id
	 */
	public static final String VIDEOCLASS_ID = "ID";
	/**
	 * 上级分类id
	 */
	public static final String VIDEOCLASS_PARENTID = "ParentId";
	/**
	 * 关键字
	 */
	public static final String VIDEOCLASS_KEYWORD = "KeyWord";
	/**
	 * 描述
	 */
	public static final String VIDEOCLASS_DESC = "Description";
	/**
	 * 分类名称
	 */
	public static final String VIDEOCLASS_CLASSNAME = "Title";
	/**
	 * 排序
	 */
	public static final String VIDEOCLASS_SORTID = "SortId";
	/**
	 * 是否删除 （ 1 删除 0 正常）
	 */
	public static final String VIDEOCLASS_ISDELETE = "IsDelete";
	/*
	 * 新闻二级分类表
	 */
	public static final String NEWTWOCLASS_TABLE = "JCPNewsSmallClass";
	/**
	 * 所属一级分类
	 */
	public static final String NEWTWOCLASS_BIGID = "BigId";
	/**
	 * 二级分类名称
	 */
	public static final String NEWTWOCLASS_SMALLNAME = "SmallName";
	/**
	 * 关键字
	 */
	public static final String NEWTWOCLASS_KEYWORD = "KeyWord";
	/**
	 * 描述
	 */
	public static final String NEWTWOCLASS_DESC = "Description";
	/**
	 * 模版名称
	 */
	public static final String NEWTWOCLASS_MODELPATH = "TempleteName";
	/**
	 * 文件路径
	 */
	public static final String NEWTWOCLASS_FILEPATH = "FilePath";
	/**
	 * 连接地址
	 */
	public static final String NEWTWOCLASS_LINKURL = "LinkUrl";
	/**
	 * 排序
	 */
	public static final String NEWTWOCLASS_SORTID = "PxId";
	/**
	 * 新闻一级分类
	 */
	public static final String NEWONECLASS_TABLE = "JCPNewsBigClass";
	/**
	 * 一级分类名称
	 */
	public static final String NEWONECLASS_BIGNAME = "ClassName";
	/**
	 * 关键字
	 */
	public static final String NEWONECLASS_KEYWORD = "KeyWord";
	/**
	 * 描述
	 */
	public static final String NEWONECLASS_DESC = "Description";
	/**
	 * 连接地址
	 */
	public static final String NEWONECLASS_LINKURL = "LinkUrl";
	/**
	 * 排序id
	 */
	public static final String NEWONECLASS_SORTID = "SortId";
	/**
	 *   视频评论表
	 */
	public static final String VIDEOCOMM_TABLE = "VideoCommen";
	/**
	 *   视频评论id
	 */
	public static final String VIDEOCOMM_ID = "Id";
	/**
	 *   评论的视频id
	 */
	public static final String VIDEOCOMM_VIDEOID = "VideoId";
	/**
	 *   评论视频的用户id
	 */
	public static final String VIDEOCOMM_USERID = "UserId";
	/**
	 *  视频评论内容
	 */
	public static final String VIDEOCOMM_BODYS = "Bodys";
	/**
	 *  视频评论点赞数
	 */
	public static final String VIDEOCOMM_GOODCOUNT = "GoodCount";
	/**
	 *  视频评论回复数
	 */
	public static final String VIDEOCOMM_REPLYCOUNT = "ReplyCount";
	/**
	 *  视频评论时间
	 */
	public static final String VIDEOCOMM_INSERTDATE = "InsertDate";
	/**
	 *   评论视频用户id
	 */
	public static final String VIDEOCOMM_IP = "IP";
	/**
	 *  视频是否显示
	 */
	public static final String VIDEOCOMM_ISSHOW = "IsShow";
	/**
	 *   私募表
	 */
	public static final String PRIVATEPLACE_TABLE="JCPSiMu";
	/**
	 *   私募id
	 */
	public static final String PRIVATEPLACE_ID="Id";
	/**
	 *  私募标题
	 */
	public static final String PRIVATEPLACE_TITLE="Title"
;	/**
	 *   私募经理名称
	 */
	public static final String PRIVATEPLACE_MANAGERNAME="ManagerName";
	/**
	 *  连接专题地址
	 */
	public static final String PRIVATEPLACE_LINKURL="LinkPageUrl";
	/**
	 *   私募经理图片
	 */
	public static final String PRIVATEPLACE_MANAGERIMAGE="ManagerImage";
	/**
	 *  私募-投资门槛
	 */
	public static final String PRIVATEPLACE_MENKAN="MenKan";
	/**
	 * 总户评级
	 */
	public static final String PRIVATEPLACE_GRADE="Pingji";
	/**
	 * 今年收益
	 */
	public static final String PRIVATEPLACE_THISPRFIT="ThisYearShouyi";
	/**
	 * 去年收益
	 */
	public static final String PRIVATEPLACE_LASTPRIFIT="LastYearShouyi";
	/**
	 * 推荐理由
	 */
	public static final String PRIVATEPLACE_MARKREASON="TuijianInfo";
	/**
	 * 是否首页
	 */
	public static final String PRIVATEPLACE_ISINDEX="IsIndex";
	/**
	 * 是否推荐
	 */
	public static final String PRIVATEPLACE_ISTUIJIAN="IsTuijian";
	/**
	 * 是否置顶
	 */
	public static final String PRIVATEPLACE_ISTOP="IsTop";	
	/**
	 *  登录日志表名
	 */
	public static final String LOGINLOG_TABLE="JCPUserLoginLog";
	/**
	 *  登录日志，用户id
	 */
	public static final String LOGINLOG_UID="UserId";
	/**
	 *  登录日志，用户名
	 */
	public static final String LOGINLOG_UNAME="LoginAccount";
	/**
	 *  登录日志，用户密码
	 */
	public static final String LOGINLOG_PWD="LoginPassWord";
	/**
	 *  登录日志--登录时间
	 */
	public static final String LOGINLOG_LOGINDATE="LoginDate";
	/**
	 * 登录日志 --登录结果
	 */
	public static final String LOGINLOG_LOGINRES="LoginResult";
	/**
	 *  登录用户ip
	 */
	public static final String LOGINLOG_IP="LoginIP";
	/**
	 * 登录日志--登录信息备注
	 */
	public static final String LOGINLOG_REMARK="Remark";

}
