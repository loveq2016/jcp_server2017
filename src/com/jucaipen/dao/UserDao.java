package com.jucaipen.dao;

import java.util.List;

import com.jucaipen.model.User;
/**
 * @author ylf
 * 
 *         用户操作类
 */
public interface UserDao {
	
	/**
	 * @param id
	 * @return  通过id修改手机号和真实姓名
	 */
	public int updateUserTrueNameAndTelById(String telPhone,User u);
	
	public int updateLoginNum(int num,int uId,String ip);
	
	public User findUserByUserNameOrMobile(String uName);
	
	
	/**
	 * @param userId
	 * @return   获取用户手机  邮箱验证状态
	 */
	public User findUserIsCheck(int userId);
	/**
	 * @param id
	 * @param tel
	 * @return   通过ID修改用户手机号
	 */
	public int updatePhoneById(int id,String tel);
	/**
	 * @return 用户注册
	 */
	public int reginUser(User user);
	
	public User findUserChatInfo(int uId);
	
	/**
	 * @return 查找所有用户
	 */
	public List<User> findUser(int page);
	
	/**
	 * @param investCode
	 * @return 根据邀请码获取用户信息
	 */
	public User findUserByInvestCode(String investCode);
	
	/**
	 * @param id
	 * @return 通过id查询用户信息
	 */
	public User findUserById(int id);
	
	
	public User findBaseInfoById(int id);
	/**
	 * @param id
	 * @return  获取登录成功后的信息
	 */
	public User findLoginInfoById(int id);

	/**
	 * @param id
	 * @return 删除用户信息
	 */
	public int deleteUserById(int id);
	/**
	 * @param id
	 * @return 修改用户信息
	 */
	public int updataUserById(int id, User user);
	/**
	 * @param id
	 * @param logoPath
	 * @return  修改用户头像URL
	 */
	public int updateUserLogoById(int id ,String logoPath);
	/**
	 * @param email
	 * @return 查询该账号用户信息
	 */
	public User findUserByAccount(String email);
	/**
	 * @param telPhone
	 * @return 通过手机号查询用户信息
	 */
	public User findUserByTelPhone(String telPhone);
	/**
	 * @param qqId
	 * @return  通过qqId 查询用户信息
	 */
	public User findUserByQqopenId(String qqId);
	/**
	 * @param wenxinId
	 * @return  通过微信id 查询用户信息
	 */
	public User findUserByWeixinId(String wenxinId);
	/**
	 * @param sinaId
	 * @return  通过新浪id 查询用户信息
	 */
	public User findUserBySinaId(String sinaId);
	/**
	 * account
	 * @return 根据手机号或Email查询用户
	 */
	public User findUserByOther(String account);
	/**
	 * @param id
	 * @return 通过id查询用户密码
	 */
	public User findPasswordById(int id);
	/**
	 * @param id
	 * @param pwd
	 * @return 修改密码
	 */
	public int updataPasswordById(int id, String pwd);
	/**
	 * @param id
	 * @return根据id查询用户昵称 --用于聊天
	 */
	public User findUserInfoById(int id);
	/**
	 * @param accountId
	 * @return 修改第三方账号id
	 */
	public int upDataAccountId(int id,int accountType,String accountId);
	
	/**
	 * @param id
	 * @return   解除第三方账号
	 */
	public int delAccountId(int id,int accountType);
	/**
	 * @param accountId
	 * @return 通过账号id 登录 程序
	 */
	public User otherAccountLogin(int accountType,String accountId);
	/**
	 * @param id
	 * @return   根据用户id 查询第三方账号
	 */
	public User querryOtherAccount(int id);
	/**
	 * @param id
	 * @return  获取用户头像信息
	 */
	public String querryFaceImage(int id);
	/**
	 * @param integeral
	 * @return  修改用户积分信息
	 */
	public int updateIntegeral(int integeral,int uId);
	
	/**
	 * @param uId
	 * @param leavel
	 * @return  修改用户等级信息
	 */
	public int updateUserLeavel(int uId,int leavel);
	/**
	 * @param uId
	 * @return  获取用户积分等级信息
	 */
	public User querryIntegeralByUid(int uId);

}
