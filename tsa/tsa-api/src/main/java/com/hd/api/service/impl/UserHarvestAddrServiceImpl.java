package com.hd.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hd.api.dao.UserHarvestAddrMapper;
import com.hd.api.entity.UserHarvestAddr;
import com.hd.api.entity.vo.UserHarvestAddrList;
import com.hd.api.server.variable.MessageEnum;
import com.hd.api.service.UserHarvestAddrService;
import com.hd.sfw.core.model.BaseModel;

@Service
public class UserHarvestAddrServiceImpl implements UserHarvestAddrService {

	@Autowired
	private UserHarvestAddrMapper userHarvestAddrMapper; 
	
	@Override
	public BaseModel save(UserHarvestAddr entity) throws Exception {
		BaseModel result = new BaseModel();
		try {
			userHarvestAddrMapper.insert(entity);
			result.setErrCode(MessageEnum.SUC.toString());
		} catch (Exception e) {
			result.setErrCode(MessageEnum.ERR.toString());
			result.setErrMsg(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public BaseModel deleteById(Long id) throws Exception {
		BaseModel result = new BaseModel();
		try {
			userHarvestAddrMapper.deleteByPK(id);
			result.setErrCode(MessageEnum.SUC.toString());
		} catch (Exception e) {
			result.setErrCode(MessageEnum.ERR.toString());
			result.setErrMsg(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public BaseModel updateById(UserHarvestAddr entity) throws Exception {
		BaseModel result = new BaseModel();
		try {
			userHarvestAddrMapper.updateByPK(entity);
			result.setErrCode(MessageEnum.SUC.toString());
		} catch (Exception e) {
			result.setErrCode(MessageEnum.ERR.toString());
			result.setErrMsg(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public UserHarvestAddr findById(Long id) throws Exception {
		return userHarvestAddrMapper.findByPK(id);
	}

	@Override
	public UserHarvestAddrList findByCondition(UserHarvestAddr entity) throws Exception {
		UserHarvestAddrList lists = new UserHarvestAddrList();
		try {
			List<UserHarvestAddr> userHarvestAddrs = userHarvestAddrMapper.findByCondition(entity);
			lists.setList(userHarvestAddrs);
			lists.setErrCode(MessageEnum.SUC.toString());
		} catch (Exception e) {
			lists.setErrCode(MessageEnum.ERR.toString());
			lists.setErrMsg(e.getMessage());
			e.printStackTrace();
		}
		return lists;
	}

	@Override
	public UserHarvestAddrList findPageByCondition(UserHarvestAddr entity) throws Exception {
		UserHarvestAddrList lists = new UserHarvestAddrList();
		try{
			Integer rowcount = userHarvestAddrMapper.findNumByCondition(entity);
			List<UserHarvestAddr> list = new ArrayList<UserHarvestAddr>();
			if(rowcount > 0){
				list = userHarvestAddrMapper.findByPage(entity, entity.getiDisplayStart(), entity.getiDisplayLength());
			}
			lists.setList(list);
			lists.setIsQueryCount(true);
			lists.setiTotalRecords(rowcount);
			lists.setErrCode(MessageEnum.SUC.toString());
		}catch(Exception e){
			lists.setErrCode(MessageEnum.ERR.toString());
			lists.setErrMsg(e.getMessage());
			e.printStackTrace();
		}
		return lists;
	}

	@Override
	public BaseModel updateByXgh(String xgh) throws Exception {
		BaseModel result = new BaseModel();
		try {
			userHarvestAddrMapper.updateByXgh(xgh);
			result.setErrCode(MessageEnum.SUC.toString());
		} catch (Exception e) {
			result.setErrCode(MessageEnum.ERR.toString());
			result.setErrMsg(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

}
