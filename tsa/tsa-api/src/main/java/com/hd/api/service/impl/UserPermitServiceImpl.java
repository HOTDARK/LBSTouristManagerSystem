package com.hd.api.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hd.api.dao.UserBackPermitMapper;
import com.hd.api.dao.UserBackRelationMapper;
import com.hd.api.entity.UserBackPermit;
import com.hd.api.entity.UserBackRelation;
import com.hd.api.entity.UserInfo;
import com.hd.api.entity.vo.UserBackRelationList;
import com.hd.api.server.variable.MessageEnum;
import com.hd.api.service.UserInfoService;
import com.hd.api.service.UserPermitService;
import com.hd.sfw.core.model.BaseModel;
import com.hd.sfw.core.utils.CollectionUtils;
import com.hd.sfw.core.utils.HttpUtils;
import com.hd.sfw.core.utils.PropertiesUtils;
import com.hd.sfw.core.utils.json.JsonUtils;

@Service
public class UserPermitServiceImpl implements UserPermitService {

	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private UserBackRelationMapper userBackRelationMapper;
	@Autowired
	private UserBackPermitMapper userBackPermitMapper;
	
	@Override
	public BaseModel optionBackPermit(String openId, String backAccount, String backType) throws RuntimeException {
		BaseModel msg = new BaseModel();
		msg.setErrCode(MessageEnum.SUC.toString());
		msg.setErrMsg("");
		UserInfo info = userInfoService.findUserInfoByItems(openId, null);
		if (info == null) {
			msg.setErrCode(MessageEnum.ERR.toString());
			msg.setErrMsg("用户信息不存在");
			return msg;
		}
		UserBackRelation existRelation = findByBackAccount(backAccount, backType);
		if (existRelation != null) {
			msg.setErrCode(MessageEnum.ERR.toString());
			msg.setErrMsg("管理后台工号已绑定，不能重复绑定");
			return msg;
		}
		String url = PropertiesUtils.getProperty(this.getClass(), "propurl", backType);
		if (StringUtils.isBlank(url)) {
			msg.setErrCode(MessageEnum.ERR.toString());
			msg.setErrMsg("管理后台地址不存在");
			return msg;
		}
		url = url.concat("userPermit/getFuncPerByAccount.action");
		// 调用后台用户接口
		String result = HttpUtils.sendPost(url, "backAccount=" + backAccount, 0);
		logger.info("调用后台用户接口返回：" + result);
		UserBackRelation relation = JsonUtils.json2Object(result, UserBackRelation.class, null);
		if (relation != null && MessageEnum.SUC.toString().equals(relation.getErrCode())) {
			relation.setXgh(info.getXgh());
			relation.setBackType(backType);
			relation.setBindDate(new Date());
			userBackRelationMapper.insert(relation);
			List<UserBackPermit> permits = relation.getPermits();
			if (CollectionUtils.isNotEmpty(permits)) {
				permits.forEach((o)->{
					o.setBackId(relation.getId());
					o.setBindDate(new Date());
				});
				userBackPermitMapper.insertBatch(permits);
			}
		} else {
			msg.setErrCode(MessageEnum.ERR.toString());
			msg.setErrMsg("管理后台工号不存在");
		}
		return msg;
	}

	@Override
	public UserBackRelation obtainBackPermit(String openId, String backType) throws Exception {
		UserBackRelation msg = new UserBackRelation();
		UserInfo info = userInfoService.findUserInfoByItems(openId, null);
		if (info == null) {
			msg.setErrCode(MessageEnum.ERR.toString());
			msg.setErrMsg("用户信息不存在");
			return msg;
		}
		UserBackRelation relation = userBackRelationMapper.findByXgh(info.getXgh(), backType);
		if (relation == null) {
			relation = new UserBackRelation();
			relation.setErrCode(MessageEnum.SUC.toString());
			return relation;
		}
		UserBackPermit permit = new UserBackPermit();
		permit.setBackId(relation.getId());
		List<UserBackPermit> permits = userBackPermitMapper.findByCondition(permit);
		relation.setPermits(permits);
		return relation;
	}

	@Override
	public UserBackRelation findByBackAccount(String backAccount, String backType) {
		return userBackRelationMapper.findByBackAccount(backAccount, backType);
	}

	@Override
	public BaseModel save(UserBackRelation entity) throws Exception {
		return null;
	}

	@Override
	public BaseModel deleteById(Long id) throws Exception {
		return null;
	}

	@Override
	public BaseModel updateById(UserBackRelation entity) throws Exception {
		BaseModel result = new BaseModel();
		try{
			userBackRelationMapper.updateByPK(entity);
			result.setErrCode(MessageEnum.SUC.toString());
		}catch(Exception e){
			result.setErrCode(MessageEnum.ERR.toString());
			result.setErrMsg(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public UserBackRelation findById(Long id) throws Exception {
		return null;
	}

	@Override
	public UserBackRelationList findByCondition(UserBackRelation entity) throws Exception {
		return null;
	}

	@Override
	public UserBackRelationList findPageByCondition(UserBackRelation entity) throws Exception {
		return null;
	}

	@Override
	public BaseModel updatePermit(List<UserBackRelation> relations) throws RuntimeException {
		BaseModel msg = new BaseModel();
		if (CollectionUtils.isNotEmpty(relations)) {
			for (UserBackRelation relation : relations) {
				UserBackRelation existRelation = findByBackAccount(relation.getBackAccount(), relation.getBackType());
				if (existRelation == null) {
					continue;
				}
				// 执行修改。先删除，再新增
				userBackPermitMapper.deleteByBackId(existRelation.getId());
				userBackRelationMapper.deleteByPK(existRelation.getId());
				existRelation.setBindDate(new Date());
				userBackRelationMapper.insert(existRelation);
				List<UserBackPermit> permits = relation.getPermits();
				if (CollectionUtils.isNotEmpty(permits)) {
					permits.forEach((o)->{
						o.setBackId(existRelation.getId());
						o.setBindDate(new Date());
					});
					userBackPermitMapper.insertBatch(permits);
				}
			}
		} else {
			msg.setErrCode(MessageEnum.SUC.toString());
			msg.setErrMsg("");
		}
		return null;
	}

}
