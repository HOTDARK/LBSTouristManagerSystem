package com.hd.api.action;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hd.api.entity.UserInfo;
import com.hd.api.entity.UserLoginHis;
import com.hd.api.server.variable.MessageEnum;
import com.hd.api.service.UserInfoService;
import com.hd.api.service.UserLoginHisService;
import com.hd.api.service.ValiCodeService;
import com.hd.api.validate.ValidateObject;
import com.hd.api.validate.ValidateParam;
import com.hd.sfw.core.model.BaseModel;

/**
 * 微信用户登录功能控制类 
 * @author somnuscy
 *
 */
@Controller
@RequestMapping("/wxUserMgr")
public class WxUserMgrAction {

	/**
	 * 登录sessionMap 用户信息
	 */
	public final static String SESSION_MAP_USER = "userInfo";
	/**
	 * 登录sessionMap 错误信息
	 */
	public final static String SESSION_MAP_ERRMSG = "errMsg";
	/**
	 * 登录sessionMap 用户登录状态
	 */
	public final static String SESSION_MAP_USER_STATE = "userState";
	
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private ValiCodeService valiCodeService;
	@Autowired
	private UserLoginHisService userLoginHisService;
	
	/**
	 * 绑定OpenId
	 * @param request
	 * @param openId
	 * @param phone
	 * @param valiCode
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/bindOpenId" ,method={RequestMethod.POST})
	public BaseModel bindOpenId(HttpServletRequest request, String openId, String phone, String valiCode){
		// -->验证入参开始
		List<ValidateObject> params = new LinkedList<ValidateObject>();
		params.add(new ValidateObject(openId, "[openId]为空"));
		params.add(new ValidateObject(phone, "[手机号码]为空"));
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
				UserInfo puser = userInfoService.findUserInfoByItems(null, phone);
				if (puser == null) {
					msg.setErrCode(MessageEnum.ERR.toString());
					msg.setErrMsg("找不到该手机号的用户信息，请核对!");
					return msg;
				}
				UserInfo info = userInfoService.findUserInfoByItems(openId, null);
				if (info == null) {
					// 执行绑定
					userInfoService.updateOpenIdByPhone(phone, openId);
					msg.setErrCode(MessageEnum.SUC.toString());
					msg.setErrMsg("绑定成功!");
				} else {
					msg.setErrCode(MessageEnum.ERR.toString());
					msg.setErrMsg("微信终端已绑定，无法重复绑定!");
				}
			} catch (Exception e) {
				msg.setErrCode(MessageEnum.ERR_PARAMETER.toString());
				msg.setErrMsg(e.getMessage());
				e.printStackTrace();
			}
		} else {
			msg.setErrCode(MessageEnum.ERR.toString());
			msg.setErrMsg("手机验证码校验失败!");
		}
		return msg;
	}
	
	/**
	 * 解绑OpenId
	 * @param request
	 * @param openId
	 * @param phone
	 * @param valiCode
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/unbundOpenId" ,method={RequestMethod.POST})
	public BaseModel unbundOpenId(HttpServletRequest request, String openId, String phone, String valiCode){
		// -->验证入参开始
		List<ValidateObject> params = new LinkedList<ValidateObject>();
		params.add(new ValidateObject(openId, "[openId]为空"));
		params.add(new ValidateObject(phone, "[手机号码]为空"));
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
				UserInfo info = userInfoService.findUserInfoByItems(openId, phone);
				if (info == null) {
					msg.setErrCode(MessageEnum.ERR_PARAMETER.toString());
					msg.setErrMsg("微信终端与手机号无绑定关系，无法解绑!");
				} else {
					// 执行解绑
					userInfoService.updateOpenIdByPhone(phone, "");
					msg.setErrCode(MessageEnum.SUC.toString());
					msg.setErrMsg("解绑成功!");
				}
			} catch (Exception e) {
				msg.setErrCode(MessageEnum.ERR_PARAMETER.toString());
				msg.setErrMsg(e.getMessage());
				e.printStackTrace();
			}
		} else {
			msg.setErrCode(MessageEnum.ERR.toString());
			msg.setErrMsg("手机验证码校验失败!");
		}
		return msg;
	}
	
	/**
	 * 根据openId获取用户信息
	 * @param request
	 * @param openId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/obtainOpenState" ,method={RequestMethod.POST})
	public Map<String, Object> obtainOpenState(HttpServletRequest request, String openId){
		Map<String, Object> map = new HashMap<String, Object>();
		// -->验证入参开始
		List<ValidateObject> params = new LinkedList<ValidateObject>();
		params.add(new ValidateObject(openId, "[openId]为空"));
		BaseModel msg = ValidateParam.validateParams(params);
		if (!MessageEnum.SUC.toString().equals(msg.getErrCode())) {
			map.put(SESSION_MAP_USER_STATE, false);
			map.put(SESSION_MAP_ERRMSG, msg.getErrMsg());
			return map;
		}
		// -->验证入参结束
		UserInfo userInfo = new UserInfo();
		userInfo.setOpenId(openId);
		try {
			UserInfo info = userInfoService.findUserInfoByItems(openId, null);
			if (info != null) {
				Date now = new Date();
				map.put(SESSION_MAP_USER_STATE, true);
				map.put(SESSION_MAP_USER, info);
				// 登录成功，修改登录信息
				UserInfo sinfo = new UserInfo();
				sinfo.setXgh(info.getXgh());
				sinfo.setLastDlip(request.getRemoteAddr());
				sinfo.setLastDlsj(now);
				try {
					userInfoService.updateById(sinfo);
				} catch (Exception e) {
					e.printStackTrace();
				}
				// 获取成功，记录用户登录历史信息
				UserLoginHis his = new UserLoginHis();
				his.setXgh(info.getXgh());
				his.setDlfs(3);
				his.setDlip(request.getRemoteAddr());
				his.setDlsj(now);
				try {
					userLoginHisService.save(his);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				map.put(SESSION_MAP_USER_STATE, false);
				map.put(SESSION_MAP_ERRMSG, "未获取到openId绑定信息!");
			}
		} catch (Exception e) {
			map.put(SESSION_MAP_USER_STATE, false);
			map.put(SESSION_MAP_ERRMSG, e.getMessage());
			e.printStackTrace();
		}
		return map;
	}
	
}
