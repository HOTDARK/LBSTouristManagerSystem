package com.hd.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hd.api.dao.UserInfoMapper;
import com.hd.api.entity.UserInfo;
import com.hd.api.entity.vo.UserInfoList;
import com.hd.api.server.log.BSASOAPInfo;
import com.hd.api.server.variable.MessageEnum;
import com.hd.api.service.UserInfoService;
import com.hd.sfw.core.model.BaseModel;

@Component
@WebService(targetNamespace="http://bean.user.webservice.tsa.hd.com")
public class UserInfoServiceImpl implements UserInfoService {
	
    @Autowired
	private UserInfoMapper userInfoMapper; 
    
	@Override
	@BSASOAPInfo("用户管理-用户注册")
	public BaseModel save(UserInfo entity) throws Exception {
		BaseModel result = new BaseModel();
		try{
			userInfoMapper.insert(entity);
			result.setErrCode(MessageEnum.SUC.toString());
		}catch(Exception e){
			result.setErrCode(MessageEnum.ERR.toString());
			result.setErrMsg(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	@Override
	@BSASOAPInfo("用户管理-删除用户")
	public BaseModel deleteById(String id) throws Exception {
		BaseModel result = new BaseModel();
		try{
			userInfoMapper.deleteByPK(id);
			result.setErrCode(MessageEnum.SUC.toString());
		}catch(Exception e){
			result.setErrCode(MessageEnum.ERR.toString());
			result.setErrMsg(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	@Override
	@BSASOAPInfo("用户管理-修改用户")
	public BaseModel updateById(UserInfo entity) throws Exception {
		BaseModel result = new BaseModel();
		try{
			userInfoMapper.updateByPK(entity);
			result.setErrCode(MessageEnum.SUC.toString());
		}catch(Exception e){
			result.setErrCode(MessageEnum.ERR.toString());
			result.setErrMsg(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public UserInfo findById(String id) throws Exception {
		UserInfo result = new UserInfo();
		try{
			result =  userInfoMapper.findByPK(id);
			if(result == null){
			   result = new UserInfo();
			}
			result.setErrCode(MessageEnum.SUC.toString());
		} catch(Exception e){
			result.setErrCode(MessageEnum.ERR.toString());
			result.setErrMsg(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public UserInfoList findByCondition(UserInfo entity) throws Exception {
		UserInfoList result = new UserInfoList();
		try{
			List<UserInfo> list = userInfoMapper.findByCondition(entity);
			result.setList(list);
			result.setErrCode(MessageEnum.SUC.toString());
		} catch (Exception e){
			result.setErrCode(MessageEnum.ERR.toString());
			result.setErrMsg(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public UserInfoList findPageByCondition(UserInfo entity) throws Exception {
		UserInfoList result = new UserInfoList();
		try{
			Integer rowcount = userInfoMapper.findNumByCondition(entity);
			List<UserInfo> list = new ArrayList<>();
			if(rowcount>0){
				list = userInfoMapper.findByPage(entity, entity.getiDisplayStart(), entity.getiDisplayLength());
			}
			result.setList(list);
			result.setIsQueryCount(true);
			result.setiTotalRecords(rowcount);
			result.setErrCode(MessageEnum.SUC.toString());
		}catch(Exception e){
		    result.setErrCode(MessageEnum.ERR.toString());
			result.setErrMsg(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public UserInfo findUserInfoByItems(String openId, String phone) {
		return userInfoMapper.findUserInfoByItems(openId, phone);
	}

	@Override
	@BSASOAPInfo("用户管理-根据手机号码修改用户绑定信息")
	public void updateOpenIdByPhone(String phone, String openId) throws Exception {
		userInfoMapper.updateOpenIdByPhone(phone, openId);
	}

	@Override
	public UserInfo findByIdOrPhone(String id) throws Exception {
		return userInfoMapper.findByIdOrPhone(id);
	}

}
