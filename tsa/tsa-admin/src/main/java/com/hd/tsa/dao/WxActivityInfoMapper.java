/*
 * Copyright © 2017, hd (ChongQing) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.tsa.dao;
import com.hd.sfw.dao.mybatis.BaseMapper;
import com.hd.tsa.entity.WxActivityInfo;

/**
 * TODO 类描述
 * @version	0.0.1
 * @author	generator
 * @date	2017-11-13
 */
public interface WxActivityInfoMapper extends BaseMapper<WxActivityInfo,java.lang.Long>{

	/**
	 * 查询活动信息及素材不为空
	 * @param id
	 * @throws Exception
	 */
	public int queryInfoAndLayoutAndRel(Long id)throws Exception;
}
