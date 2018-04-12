/*
 * Copyright © 2017, hd (ChongQing) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.api.dao;
import com.hd.sfw.dao.mybatis.BaseMapper;

import java.util.List;

import com.hd.api.entity.UserBackPermit;

/**
 * TODO 类描述
 * @version	0.0.1
 * @author	generator
 * @date	2017-10-25
 */
public interface UserBackPermitMapper extends BaseMapper<UserBackPermit,java.lang.Long>{
	
	/**
	 * 批量插入权限数据
	 * @param permits
	 */
	public void insertBatch(List<UserBackPermit> permits);
	
	/**
	 * 根据后台绑定ID删除权限列表
	 * @param backId
	 */
	public void deleteByBackId(Long backId);

}
