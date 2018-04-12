/*
 * Copyright © 2017, hd (ChongQing) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.api.dao;
import com.hd.sfw.dao.mybatis.BaseMapper;

import org.apache.ibatis.annotations.Param;

import com.hd.api.entity.UserInfo;

/**
 * TODO 类描述
 * @version	0.0.1
 * @author	generator
 * @date	2017-08-22
 */
public interface UserInfoMapper extends BaseMapper<UserInfo,java.lang.String>{
	
	/**
	 * 根据条件查询用户信息
	 * @param openId
	 * @param phone
	 * @return
	 */
	public UserInfo findUserInfoByItems(@Param("openId")String openId, @Param("phone")String phone);
	/**
	 * 根据手机号码修改用户绑定信息
	 * @param phone
	 * @param openId
	 */
	public void updateOpenIdByPhone(@Param("phone")String phone, @Param("openId")String openId);
	
	/**
	 * 根据登录名或手机号查询用户信息
	 * @param id
	 * @return
	 */
	public UserInfo findByIdOrPhone(String id);
	
}
