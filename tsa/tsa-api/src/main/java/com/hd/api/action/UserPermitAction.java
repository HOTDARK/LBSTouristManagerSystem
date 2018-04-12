package com.hd.api.action;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hd.api.entity.UserBackRelation;
import com.hd.api.server.variable.MessageEnum;
import com.hd.api.service.UserPermitService;
import com.hd.api.service.ValiCodeService;
import com.hd.api.validate.ValidateObject;
import com.hd.api.validate.ValidateParam;
import com.hd.sfw.core.model.BaseModel;
import com.hd.sfw.core.utils.json.JsonUtils;

/**
 * 用户权限控制类
 * @author somnuscy
 *
 */
@Controller
@RequestMapping("/userPermit")
public class UserPermitAction {

	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private ValiCodeService valiCodeService;
	@Autowired
	private UserPermitService userBackRelationService;
	
	/**
	 * 根据前端用户、后台用户绑定后台用户权限
	 * @param request
	 * @param response
	 * @param openId 前端微信openId
	 * @param backAccount 后台用户
	 * @param backType  后台类型（repair：报修；vehicle：车辆）
	 * @param phone 手机号
	 * @param valiCode 验证码
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/bindBackPermit" ,method={RequestMethod.POST})
	public BaseModel bindBackPermit(HttpServletRequest request, HttpServletResponse response, String openId, String backAccount, String backType, String phone, String valiCode){
		logger.info("进入根据前端用户、后台用户绑定后台用户权限");
		// -->验证入参开始
		List<ValidateObject> params = new LinkedList<ValidateObject>();
		params.add(new ValidateObject(openId, "[openId]为空"));
		params.add(new ValidateObject(backAccount, "[后台用户]为空"));
		params.add(new ValidateObject(backType, "[后台类型]为空"));
		params.add(new ValidateObject(phone, "[手机号]为空"));
		params.add(new ValidateObject(valiCode, "[手机验证码]为空"));
		BaseModel msg = ValidateParam.validateParams(params);
		if (!MessageEnum.SUC.toString().equals(msg.getErrCode())) {
			return msg;
		}
		if (!ValidateParam.isMobileNo(phone)) {
			msg = new BaseModel();
			msg.setErrCode(MessageEnum.ERR_PARAMETER.toString());
			msg.setErrMsg("请输入正确的手机号");
			return msg;
		}
		// -->验证入参结束
		boolean b = valiCodeService.compareValiCode(phone, valiCode);
		if (b) {
			try {
				msg = userBackRelationService.optionBackPermit(openId, backAccount, backType);
				if (msg == null) {
					msg = new BaseModel();
					msg.setErrCode(MessageEnum.ERR.toString());
					msg.setErrMsg("绑定后台用户权限失败");
				}
			} catch (Exception e) {
				msg.setErrCode(MessageEnum.ERR.toString());
				msg.setErrMsg("绑定后台用户权限失败："+e.getMessage());
				e.printStackTrace();
			}
		} else {
			msg.setErrCode(MessageEnum.ERR.toString());
			msg.setErrMsg("手机验证码校验失败!");
		}
		return msg;
	}
	
	/**
	 * 根据openId获取用户后台绑定权限
	 * @param request
	 * @param response
	 * @param openId 前端微信openId
	 * @param backType 后台类型（repair：报修；vehicle：车辆）
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/obtainBackPermit" ,method={RequestMethod.POST})
	public UserBackRelation obtainBackPermit(HttpServletRequest request, HttpServletResponse response, String openId, String backType){
		UserBackRelation relation = new UserBackRelation();
		// -->验证入参开始
		List<ValidateObject> params = new LinkedList<ValidateObject>();
		params.add(new ValidateObject(openId, "[openId]为空"));
		params.add(new ValidateObject(backType, "[后台类型]为空"));
		BaseModel msg = ValidateParam.validateParams(params);
		if (!MessageEnum.SUC.toString().equals(msg.getErrCode())) {
			relation.setErrCode(msg.getErrCode());
			relation.setErrMsg(msg.getErrMsg());
			return relation;
		}
		// -->验证入参结束
		try {
			relation = userBackRelationService.obtainBackPermit(openId, backType);
		} catch (Exception e) {
			relation.setErrCode(MessageEnum.ERR.toString());
			relation.setErrMsg("获取用户后台绑定权限失败："+e.getMessage());
			e.printStackTrace();
		}
		return relation;
	}
	
	/**
	 * 根据后台账号修改前端用户绑定后台机构(当后台修改管理用户机构时调用)
	 * @param request
	 * @param response
	 * @param backAccount 后台账号
	 * @param backOrgCode 后台机构编码
	 * @param backType 后台类型（repair：报修；vehicle：车辆）
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/modifyOrgCode" ,method={RequestMethod.POST})
	public BaseModel modifyOrgCode(HttpServletRequest request, HttpServletResponse response, String backAccount, String backOrgCode, String backType){
		// -->验证入参开始
		List<ValidateObject> params = new LinkedList<ValidateObject>();
		params.add(new ValidateObject(backAccount, "[后台账号]为空"));
		params.add(new ValidateObject(backOrgCode, "[后台机构编码]为空"));
		params.add(new ValidateObject(backType, "[后台类型]为空"));
		BaseModel msg = ValidateParam.validateParams(params);
		if (!MessageEnum.SUC.toString().equals(msg.getErrCode())) {
			return msg;
		}
		// -->验证入参结束
		UserBackRelation relation = userBackRelationService.findByBackAccount(backAccount, backType);
		try {
			if (relation == null) {
				msg.setErrCode(MessageEnum.SUC.toString());
				msg.setErrMsg("");
			} else {
				relation.setBackOrgCode(backOrgCode);
				relation.setBindDate(new Date());
				return userBackRelationService.updateById(relation);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	/**
	 * 根据后台用户列表、后台权限列表修改前端用户绑定后台权限(当后台修改用户角色或往角色下添加人员时调用)
	 * @param request
	 * @param response
	 * @param relStrs 用户绑定权限关系json串
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/modifyPermit" ,method={RequestMethod.POST})
	public BaseModel modifyPermit(HttpServletRequest request, HttpServletResponse response, String relStrs){
		// -->验证入参开始
		List<ValidateObject> params = new LinkedList<ValidateObject>();
		params.add(new ValidateObject(relStrs, "[用户绑定权限关系]为空"));
		BaseModel msg = ValidateParam.validateParams(params);
		if (!MessageEnum.SUC.toString().equals(msg.getErrCode())) {
			return msg;
		}
		// -->验证入参结束
		// 转换json字符串到列表对象
		List<UserBackRelation> relations = JsonUtils.json2GenericObject(relStrs, new TypeReference<List<UserBackRelation>>(){}, null);
		try {
			msg = userBackRelationService.updatePermit(relations);
		} catch (Exception e) {
			msg.setErrCode(MessageEnum.ERR.toString());
			msg.setErrMsg("用户绑定权限关系列表出错："+e.getMessage());
			e.printStackTrace();
		}
		return msg;
	}
	
	
	
}
