package com.hd.api.service;

import com.hd.api.entity.UserLoginHis;
import com.hd.sfw.core.model.BaseModel;


/**
 * 用户登录历史信息
 * @author somnuscy
 *
 */
public interface UserLoginHisService {
	
	/**
	 * 用户登录历史信息-添加
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public BaseModel save(UserLoginHis entity) throws Exception;
}
