/*
 * Copyright © 2017, hd (ChongQing) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.api.dao;

import org.apache.ibatis.annotations.Param;

import com.hd.api.entity.UserBackRelation;
import com.hd.sfw.dao.mybatis.BaseMapper;

/**
 * TODO 类描述
 * @version	0.0.1
 * @author	generator
 * @date	2017-10-25
 */
public interface UserBackRelationMapper extends BaseMapper<UserBackRelation,java.lang.Long>{
	
	/**
	 * 按学工号、后台类型查询绑定用户关系
	 * @param xgh
	 * @param backType
	 * @return
	 */
	public UserBackRelation findByXgh(@Param("xgh")String xgh, @Param("backType")String backType);
	
	/**
	 * 按后台工号、后台类型查询绑定用户关系
	 * @param backAccount
	 * @param backType
	 * @return
	 */
	public UserBackRelation findByBackAccount(@Param("backAccount")String backAccount, @Param("backType")String backType);

}
