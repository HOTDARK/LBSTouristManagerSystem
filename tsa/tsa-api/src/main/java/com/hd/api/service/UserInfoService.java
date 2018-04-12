package com.hd.api.service;

import com.hd.api.entity.UserInfo;
import com.hd.api.entity.vo.UserInfoList;
import com.hd.api.server.service.base.BaseWebService;


/**
 * 用户信息处理业务
 * @author somnuscy
 *
 */
public interface UserInfoService extends BaseWebService<UserInfo,UserInfoList,String> {
	
	/**
	 * 根据条件查询用户信息
	 * @param openId
	 * @param phone
	 * @return
	 */
	public UserInfo findUserInfoByItems(String openId, String phone);
	/**
	 * 根据手机号码修改用户绑定信息
	 * @param phone
	 * @param openId
	 */
	public void updateOpenIdByPhone(String phone, String openId) throws Exception;
	
	/**
	 * 根据登录名或手机号查询用户信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public UserInfo findByIdOrPhone(String id) throws Exception ;
}
