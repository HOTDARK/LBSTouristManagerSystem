package com.hd.api.service;

import com.hd.api.entity.ValiCode;
import com.hd.sfw.core.model.BaseModel;

/**
 * 信息验证码接口
 * @author somnuscy
 *
 */
public interface ValiCodeService {
	
	/**
	 * 发送短信接口
	 * @param phone 接收手机号码，最多500个；多个手机号码，中间间隔用英文逗号。
	 * @param content 短信内容
	 * @return
	 */
	public BaseModel sendSms(String phone, String content);
	
	/**
	 * 发送验证码
	 * @param phone
	 * @return
	 */
	public BaseModel sendValiCode(String phone);
	
	/**
	 * 判断验证码是否匹配
	 * @param phone 手机号
	 * @param valiCode 验证码
	 * @return
	 */
	public boolean compareValiCode(String phone, String valiCode);
	
	/**
	 * 获取验证码
	 * @param phone 手机号
	 * @return
	 */
	public ValiCode obtainValiCode(String phone);
	
}
