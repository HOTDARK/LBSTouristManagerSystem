package com.hd.api.service;

import com.hd.api.entity.UserHarvestAddr;
import com.hd.api.entity.vo.UserHarvestAddrList;
import com.hd.api.server.service.base.BaseWebService;
import com.hd.sfw.core.model.BaseModel;

/**
 * 用户收货地址管理
 * @author somnuscy
 *
 */
public interface UserHarvestAddrService extends BaseWebService<UserHarvestAddr, UserHarvestAddrList, Long> {
	
	/**
	 * 按学工号修改收货地址
	 * @param xgh 学工号
	 * @throws Exception 修改收货地址可能发生的异常
	 */
	public BaseModel updateByXgh(String xgh) throws Exception;

}
