package com.hd.api.action;

import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hd.api.entity.UserInfo;
import com.hd.api.entity.UserLoginHis;
import com.hd.api.entity.vo.UserInfoList;
import com.hd.api.server.variable.MessageEnum;
import com.hd.api.service.UserInfoService;
import com.hd.api.service.UserLoginHisService;
import com.hd.api.service.ValiCodeService;
import com.hd.api.validate.ValidateObject;
import com.hd.api.validate.ValidateParam;
import com.hd.sfw.core.model.BaseModel;
import com.hd.sfw.core.utils.CollectionUtils;
import com.hd.sfw.core.utils.DateUtils;
import com.hd.sfw.core.utils.PropertiesUtils;

/**
 * 用户登录功能控制类
 * @author somnuscy
 *
 */
@Controller
@RequestMapping("/userMgr")
public class UserMgrAction {

	/**
	 * 日志记录
	 */
	private Logger logger = Logger.getLogger(this.getClass());
	/**
	 * 登录session
	 */
	public final static Map<String, Map<String, Object>> SESSION = new LinkedHashMap<String, Map<String, Object>>();
	/**
	 * 登录session 用户信息
	 */
	public final static String SESSION_USER = "userInfo";
	/**
	 * 登录session 记录时间
	 */
	public final static String SESSION_TIME = "sessionTime";
	/**
	 * 登录session 用户登录状态（true：已登录，false：未登录）
	 */
	public final static String SESSION_USER_STATE = "userState";
	/**
	 * 登录session 用户信息完善状态（true：已完善，false：未完善）,当为false时，SESSION_USER_MSG必有值
	 */
	public final static String SESSION_USER_PERFECT_STATE = "perfectState";
	/**
	 * 登录session 用户信息描述信息
	 */
	public final static String SESSION_USER_MSG = "userMsg";
	
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private ValiCodeService valiCodeService;
	@Autowired
	private UserLoginHisService userLoginHisService;
	
	/**
	 * 获取用户管理中心登录标识(分发sessionId)
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/obtainSessionId" ,method={RequestMethod.GET})
	public Map<String, String> obtainSessionId(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("textml;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Methods", "GET");
		response.setHeader("Access-Control-Max-Age", "0");
		response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("XDomainRequestAllowed", "1");
		Map<String, String> map = new HashMap<String, String>();
		map.put("sessionId", request.getSession().getId());
		return map;
	}
	
	/**
	 * 用户注册
	 * @param request
	 * @param response
	 * @param userInfo
	 * @param valiCode
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/userReg" ,method={RequestMethod.POST})
	public BaseModel userReg(HttpServletRequest request, HttpServletResponse response, UserInfo userInfo, String valiCode){
		// -->验证入参开始
		List<ValidateObject> params = new LinkedList<ValidateObject>();
		params.add(new ValidateObject(valiCode, "[手机验证码]为空"));
		params.add(new ValidateObject(userInfo, "[用户注册信息]为空"));
		BaseModel msg = ValidateParam.validateParams(params);
		if (!MessageEnum.SUC.toString().equals(msg.getErrCode())) {
			return msg;
		}
		params = new LinkedList<ValidateObject>();
		params.add(new ValidateObject(userInfo.getSjhm(), "用户注册信息[手机号码]为空"));
		params.add(new ValidateObject(userInfo.getDlmm(), "用户注册信息[登录密码]为空"));
		params.add(new ValidateObject(userInfo.getZcfs(), "用户注册信息[注册方式]为空"));
		msg = ValidateParam.validateParams(params);
		if (!MessageEnum.SUC.toString().equals(msg.getErrCode())) {
			return msg;
		}
		if (!ValidateParam.isMobileNo(userInfo.getSjhm())) {
			msg = new BaseModel();
			msg.setErrCode(MessageEnum.ERR_PARAMETER.toString());
			msg.setErrMsg("请输入正确的手机号");
			return msg;
		}
		// -->验证入参结束
		boolean b = valiCodeService.compareValiCode(userInfo.getSjhm(), valiCode);
		if (b) {
			UserInfo user = new UserInfo();
			user.setSjhm(userInfo.getSjhm());
			try {
				UserInfoList list = userInfoService.findByCondition(user);
				if (MessageEnum.SUC.toString().equals(list.getErrCode()) && CollectionUtils.isNotEmpty(list.getList())) {
					msg.setErrCode(MessageEnum.ERR.toString());
					msg.setErrMsg("手机号已绑定[学工号]，不能重复注册");
					return msg;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 如果手机号，没有绑定过学工号，学工号字段直接存入用户手机号
			userInfo.setXgh(userInfo.getSjhm());
			// 默认信息
			userInfo.setYhts(PropertiesUtils.getProperty(this.getClass(), "propftp", "default_user_photo"));
			userInfo.setYhzt(1);
			userInfo.setMmzt(0);
			userInfo.setZcsj(new Date());
			try {
				msg = userInfoService.save(userInfo);
				// 如果是微信端注册的，自动执行绑定
				if (StringUtils.isNotBlank(userInfo.getOpenId())) {
					// 执行绑定
					userInfoService.updateOpenIdByPhone(userInfo.getSjhm(), userInfo.getOpenId());
				}
			} catch (Exception e) {
				msg.setErrCode(MessageEnum.ERR.toString());
				msg.setErrMsg("用户注册失败："+e.getMessage());
				e.printStackTrace();
			}
		} else {
			msg.setErrCode(MessageEnum.ERR.toString());
			msg.setErrMsg("手机验证码校验失败!");
		}
		return msg;
	}
	
	/**
	 * 用户登录
	 * @param request
	 * @param response
	 * @param userAccount
	 * @param userPwd
	 * @param dlfs
	 * @param sessionId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/userLogin" ,method={RequestMethod.POST})
	public BaseModel userLogin(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="userAccount",defaultValue="",required=false)String userAccount,
			@RequestParam(value="userPwd",defaultValue="",required=false)String userPwd,
			@RequestParam(value="dlfs",required=false)Integer dlfs, String sessionId) {
		// -->验证入参开始
		List<ValidateObject> params = new LinkedList<ValidateObject>();
		params.add(new ValidateObject(sessionId, "[会话ID]为空"));
		params.add(new ValidateObject(userAccount, "[用户账号]为空"));
		params.add(new ValidateObject(dlfs, "[登录方式]为空"));
		BaseModel msg = ValidateParam.validateParams(params);
		if (!MessageEnum.SUC.toString().equals(msg.getErrCode())) {
			return msg;
		}
		// -->验证入参结束
		if (StringUtils.isBlank(userAccount)) {
			msg.setErrCode(MessageEnum.ERR_PARAMETER.toString());
			msg.setErrMsg("帐号不能为空!");
			return msg;
		}
		UserInfo user = null;
		// 通过统一认证系统登录
		if (dlfs == 1) {
			try {
				user = userInfoService.findByIdOrPhone(userAccount);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (user == null || user.getXgh() == null) {
				msg.setErrCode(MessageEnum.ERR.toString());
				msg.setErrMsg("用户帐号不存在!");
				return msg;
			}
		} else {
			// 通过本系统登录
			if (StringUtils.isBlank(userPwd)) {
				msg.setErrCode(MessageEnum.ERR_PARAMETER.toString());
				msg.setErrMsg("密码不能为空!");
				return msg;
			}
			try {
				user = userInfoService.findByIdOrPhone(userAccount);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(user != null && userPwd.equals(user.getDlmm())){
				if(user.getYhzt() != 1) {
					msg.setErrCode(MessageEnum.ERR.toString());
					msg.setErrMsg("用户已被冻结!");
					return msg;
				}
			} else {
				msg.setErrCode(MessageEnum.ERR.toString());
				msg.setErrMsg("帐号密码错误!");
				return msg;
			}
		}
		Map<String, Object> param = new HashMap<String, Object>();
		user.setDlmm(null);
		param.put(SESSION_USER, user);
		param.put(SESSION_TIME, new Date());
		SESSION.put(sessionId, param);
		logger.info(MessageFormat.format("用户【{0}】登录成功，sessionId：{1}", user.getXgh(), sessionId));
		msg.setErrCode(MessageEnum.SUC.toString());
		msg.setErrMsg("");
		Date now = new Date(); 
		// 登录成功，修改登录信息
		UserInfo info = new UserInfo();
		info.setXgh(userAccount);
		info.setLastDlip(request.getRemoteAddr());
		info.setLastDlsj(now);
		try {
			userInfoService.updateById(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 登录成功，记录用户登录历史信息
		UserLoginHis his = new UserLoginHis();
		his.setXgh(userAccount);
		his.setDlfs(dlfs);
		his.setDlip(request.getRemoteAddr());
		his.setDlsj(now);
		try {
			userLoginHisService.save(his);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	/**
	 * 用户登出
	 * @param request
	 * @param sessionId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/userLogOut" ,method={RequestMethod.POST})
	public BaseModel userLogOut(HttpServletRequest request, String sessionId) {
		// -->验证入参开始
		List<ValidateObject> params = new LinkedList<ValidateObject>();
		params.add(new ValidateObject(sessionId, "[会话ID]为空"));
		BaseModel msg = ValidateParam.validateParams(params);
		if (!MessageEnum.SUC.toString().equals(msg.getErrCode())) {
			return msg;
		}
		// -->验证入参结束
		try{
			Map<String, Object> session = SESSION.get(sessionId);
			if (CollectionUtils.isNotEmpty(session)) {
				SESSION.remove(sessionId);
				logger.info("用户登出成功，sessionId："+sessionId);
			}
			msg.setErrCode(MessageEnum.SUC.toString());
		}catch(Exception e){
			msg.setErrCode(MessageEnum.ERR.toString());
			msg.setErrMsg("用户登出失败："+e.getMessage());
		}
		return msg;
	}
	
	/**
	 * 用户修改密码
	 * @param request
	 * @param userAccount
	 * @param userPwdOld
	 * @param userPwdNew
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/modifyPwd" ,method={RequestMethod.POST})
	public BaseModel modifyPwd(HttpServletRequest request, String userAccount, String userPwdOld, String userPwdNew){
		// -->验证入参开始
		List<ValidateObject> params = new LinkedList<ValidateObject>();
		params.add(new ValidateObject(userAccount, "[用户账号]为空"));
		params.add(new ValidateObject(userPwdOld, "[原密码]为空"));
		params.add(new ValidateObject(userPwdNew, "[新密码]为空"));
		BaseModel msg = ValidateParam.validateParams(params);
		if (!MessageEnum.SUC.toString().equals(msg.getErrCode())) {
			return msg;
		}
		// -->验证入参结束
		if (userPwdOld.equals(userPwdNew)) {
			msg.setErrCode(MessageEnum.ERR.toString());
			msg.setErrMsg("新密码不能与原密码相同!");
			return msg;
		}
		try {
			UserInfo info = userInfoService.findById(userAccount);
			if (info == null || info.getXgh() == null) {
				msg.setErrCode(MessageEnum.ERR.toString());
				msg.setErrMsg("用户帐号不存在!");
				return msg;
			} else {
				if (!userPwdOld.equals(info.getDlmm())) {
					msg.setErrCode(MessageEnum.ERR.toString());
					msg.setErrMsg("用户密码不匹配!");
					return msg;
				}
			}
			UserInfo userInfo = new UserInfo();
			userInfo.setXgh(userAccount);
			userInfo.setMmzt(1);
			userInfo.setDlmm(userPwdNew);
			msg = userInfoService.updateById(userInfo);
		} catch (Exception e) {
			msg.setErrCode(MessageEnum.ERR.toString());
			msg.setErrMsg("修改密码失败："+e.getMessage());
			e.printStackTrace();
		}
		return msg;
	}
	
	/**
	 * 用户找回密码
	 * @param request
	 * @param userPwdNew
	 * @param phone
	 * @param valiCode
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/retrievePwd" ,method={RequestMethod.POST})
	public BaseModel retrievePwd(HttpServletRequest request, String userPwdNew, String phone, String valiCode){
		// -->验证入参开始
		List<ValidateObject> params = new LinkedList<ValidateObject>();
		params.add(new ValidateObject(phone, "[手机号]为空"));
		params.add(new ValidateObject(userPwdNew, "[新密码]为空"));
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
				UserInfo info = userInfoService.findByIdOrPhone(phone);
				if (info == null || info.getXgh() == null) {
					msg.setErrCode(MessageEnum.ERR.toString());
					msg.setErrMsg("用户帐号不存在!");
					return msg;
				}
				UserInfo userInfo = new UserInfo();
				userInfo.setXgh(info.getXgh());
				userInfo.setMmzt(1);
				userInfo.setDlmm(userPwdNew);
				msg = userInfoService.updateById(userInfo);
			} catch (Exception e) {
				msg.setErrCode(MessageEnum.SUC.toString());
				msg.setErrMsg("修改密码失败："+e.getMessage());
				e.printStackTrace();
			}
		} else {
			msg.setErrCode(MessageEnum.ERR.toString());
			msg.setErrMsg("手机验证码校验失败!");
		}
		return msg;
	}
	
	/**
	 * 获取用户登录状态
	 * @param request
	 * @param sessionId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/obtainUserState" ,method={RequestMethod.POST})
	public Map<String, Object> obtainUserState(HttpServletRequest request, String sessionId){
		// 获取状态时，清除已过期的session
		clearExpSession();
		sessionId = (StringUtils.isBlank(sessionId)?"":sessionId);
		logger.info(MessageFormat.format("获取登录sessionId：{0}", sessionId));
		Map<String, Object> param = SESSION.get(sessionId);
		if (CollectionUtils.isNotEmpty(param)) {
			// 如果未过期用户，刷新SESSION_MAP里，该用户SESSION_TIME
			param.put(SESSION_TIME, new Date());
			SESSION.put(sessionId, param);
			param.put(SESSION_USER_STATE, true);
			UserInfo info = (UserInfo)param.get(SESSION_USER);
			if (StringUtils.isNotBlank(info.getSjhm())) {
				param.put(SESSION_USER_PERFECT_STATE, true);
			} else {
				param.put(SESSION_USER_PERFECT_STATE, false);
				param.put(SESSION_USER_MSG, "用户信息未完善，请前往用户中心修改");
			}
		} else {
			param = new HashMap<String, Object>();
			param.put(SESSION_USER_STATE, false);
		}
		return param;
	}
	
	/**
	 * 清除已过期的session
	 */
	private void clearExpSession(){
		Date ndate = new Date();
		Iterator<Entry<String, Map<String, Object>>> itr = SESSION.entrySet().iterator();
		while (itr.hasNext()){
			Entry<String, Map<String, Object>> item = itr.next();
			Date sdate = (Date)item.getValue().get(SESSION_TIME);
			int minute = DateUtils.dateDiff('m', ndate, sdate);
			Integer sminute = Integer.parseInt(PropertiesUtils.getProperty(this.getClass(), "common", "expiration_minute"));
			if (minute > sminute) {
				itr.remove();
			}
		}
	}
	
	/**
	 * 修改用户信息
	 * @param request
	 * @param response
	 * @param userInfo
	 * @param sessionId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/userModify" ,method={RequestMethod.POST})
	public BaseModel userModify(HttpServletRequest request, HttpServletResponse response, UserInfo userInfo, String sessionId){
		// -->验证入参开始
		List<ValidateObject> params = new LinkedList<ValidateObject>();
		params.add(new ValidateObject(sessionId, "[会话ID]为空"));
		params.add(new ValidateObject(userInfo, "[用户信息]为空"));
		BaseModel msg = ValidateParam.validateParams(params);
		if (!MessageEnum.SUC.toString().equals(msg.getErrCode())) {
			return msg;
		}
		params = new LinkedList<ValidateObject>();
		params.add(new ValidateObject(userInfo.getXgh(), "用户信息[学号/工号/手机号]为空"));
		msg = ValidateParam.validateParams(params);
		if (!MessageEnum.SUC.toString().equals(msg.getErrCode())) {
			return msg;
		}
		// -->验证入参结束
		try {
			msg = userInfoService.updateById(userInfo);
			// 刷新缓存数据
			UserInfo user = userInfoService.findByIdOrPhone(userInfo.getXgh());
			Map<String, Object> param = new HashMap<String, Object>();
			user.setDlmm(null);
			param.put(SESSION_USER, user);
			param.put(SESSION_TIME, new Date());
			SESSION.put(sessionId, param);
			logger.info(MessageFormat.format("用户【{0}】信息已刷新，sessionId：{1}", user.getXgh(), sessionId));
		} catch (Exception e) {
			msg.setErrCode(MessageEnum.ERR.toString());
			msg.setErrMsg(e.getMessage());
			e.printStackTrace();
		}
		return msg;
	}
	
	/**
	 * 根据(学号/工号/手机号)，获取用户信息
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/obtainUserById" ,method={RequestMethod.POST})
	public UserInfo obtainUserById(HttpServletRequest request, HttpServletResponse response, String id){
		UserInfo user = new UserInfo();
		// -->验证入参开始
		List<ValidateObject> params = new LinkedList<ValidateObject>();
		params.add(new ValidateObject(id, "[学号/工号/手机号]为空"));
		BaseModel msg = ValidateParam.validateParams(params);
		if (!MessageEnum.SUC.toString().equals(msg.getErrCode())) {
			user.setErrCode(MessageEnum.ERR_PARAMETER.toString());
			user.setErrMsg(msg.getErrMsg());
			return user;
		}
		// -->验证入参结束
		try {
			user = userInfoService.findByIdOrPhone(id);
			if (user == null || StringUtils.isBlank(user.getXgh())) {
				user = new UserInfo();
				user.setErrCode(MessageEnum.ERR.toString());
				user.setErrMsg("用户帐号不存在!");
				return user;
			}
			user.setDlmm(null);
			user.setErrCode(MessageEnum.SUC.toString());
			user.setErrMsg("");
			logger.info(MessageFormat.format("返回用户信息：{0}", user.toString()));
		} catch (Exception e) {
			user.setErrCode(MessageEnum.ERR.toString());
			user.setErrMsg(e.getMessage());
			e.printStackTrace();
		}
		return user;
	}
	
}
