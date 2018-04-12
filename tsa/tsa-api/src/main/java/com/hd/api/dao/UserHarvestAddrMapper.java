/*
 * Copyright © 2017, hd (ChongQing) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.api.dao;
import com.hd.sfw.dao.mybatis.BaseMapper;
import com.hd.api.entity.UserHarvestAddr;

/**
 * TODO 类描述
 * @version	0.0.1
 * @author	generator
 * @date	2017-11-30
 */
public interface UserHarvestAddrMapper extends BaseMapper<UserHarvestAddr,java.lang.Long>{
	
	/**
	 * 按学工号修改收货地址
	 * @param xgh
	 */
	public void updateByXgh(String xgh);
}
