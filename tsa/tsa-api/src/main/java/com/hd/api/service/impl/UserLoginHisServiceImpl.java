package com.hd.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hd.api.dao.UserLoginHisMapper;
import com.hd.api.entity.UserLoginHis;
import com.hd.api.server.log.BSASOAPInfo;
import com.hd.api.server.variable.MessageEnum;
import com.hd.api.service.UserLoginHisService;
import com.hd.sfw.core.model.BaseModel;

@Service
public class UserLoginHisServiceImpl implements UserLoginHisService {
	
	@Autowired
	private UserLoginHisMapper userLoginHisMapper;

	@Override
	@BSASOAPInfo("登录历史-保存日志")
	public BaseModel save(UserLoginHis entity) throws Exception {
		BaseModel msg = new BaseModel();
		try {
			userLoginHisMapper.insert(entity);
			msg.setErrCode(MessageEnum.SUC.toString());
		} catch (Exception e) {
			msg.setErrCode(MessageEnum.ERR.toString());
			msg.setErrCode(e.getMessage());
		}
		return msg;
	}


}
