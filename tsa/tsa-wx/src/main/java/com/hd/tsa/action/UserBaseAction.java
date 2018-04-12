package com.hd.tsa.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hd.api.server.variable.MessageEnum;
import com.hd.tsa.entity.UserBackRelation;
import com.hd.tsa.entity.UserInfo;
import com.hd.sfw.core.model.BaseModel;
import com.hd.sfw.core.utils.HttpUtils;
import com.hd.sfw.core.utils.PropertiesUtils;
import com.hd.sfw.core.utils.json.JsonUtils;
import com.hd.sys.action.base.ActionResult;
import com.hd.sys.action.base.BaseAction;
import com.hd.sys.core.consts.FunLogConst;
import com.hd.sys.core.log.LogOpt;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/wxUserBase")
public class UserBaseAction extends BaseAction{
	
	private static Logger logger = Logger.getLogger(UserBaseAction.class);

	/**
	 * 登录sessionMap
	 */
	public final static String SESSION_MAP = "sessionMap";
	/**
	 * 登录sessionMap 用户信息
	 */
	public final static String SESSION_MAP_USER = "userInfo";
	/**
	 * 登录sessionMap 用户登录状态
	 */
	public final static String SESSION_MAP_USER_STATE = "userState";
	
	/**
	 * 用户登录验证
	 * @param request
	 * @param response
	 * @param httpSession
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@LogOpt(level = FunLogConst.LEVEL_3, desc = "用户登录验证", parentDesc = FunLogConst.DESC_SYSTEM_USER)
	@RequestMapping("/getUserInfo")
	protected Map<String, Object> getUserInfo(HttpServletRequest request, HttpServletResponse response,
			HttpSession httpSession,String openid) {
		
		Map<String, Object> map = null;
		try {
			String getUserInfoUrl = PropertiesUtils.getProperty(UserBaseAction.class, "propurl", "user").concat("wxUserMgr/obtainOpenState.action");
			String result = HttpUtils.sendPost(getUserInfoUrl, "openId=" + openid, 0);
			JSONObject jsonObject = JSONObject.fromObject(result);
			map = (Map<String, Object>) jsonObject;
		} catch (Exception e) {
			logger.error("用户登录验证失败：" + e.getMessage(), e);
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 获取手机验证码
	 * @param request
	 * @param response
	 * @param httpSession
	 * @param phone
	 * @return
	 */
	@LogOpt(level = FunLogConst.LEVEL_3, desc = "获取手机验证码", parentDesc = "微信管理")
	@RequestMapping("/getValiCode")
	@ResponseBody
	public ActionResult getValiCode(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession,
			String phone) {
		ActionResult ar = new ActionResult();
		String msgStr = "";
		
		try {
			String code = PropertiesUtils.getProperty(UserBaseAction.class, "propurl", "user").concat("valiCode/getValiCode.action");
			String result = HttpUtils.sendPost(code, "phone=" + phone, 0);
			JSONObject jsonObject = JSONObject.fromObject(result);
			String errCode = jsonObject.get("errCode").toString();
			if (MessageEnum.SUC.toString().equals(errCode)) {
				msgStr = "验证码已发送";
				ar.setSuccess(true);
			} else if (MessageEnum.ERR.toString().equals(errCode)
					|| MessageEnum.ERR_PARAMETER.toString().equals(errCode)) {
				msgStr = jsonObject.get("errMsg").toString();
				ar.setSuccess(false);
			}
		} catch (Exception e) {
			logger.error("获取手机验证码失败：" + e.getMessage(), e);
			msgStr = "手机验证码发送失败，请重试";
			ar.setSuccess(false);
		}
		ar.setMessage(msgStr);
		return ar;
	}
	
	/**
	 * 将openid和手机号绑定
	 * @param request
	 * @param response
	 * @param httpSession
	 * @param phone
	 * @return
	 */
	@LogOpt(level = FunLogConst.LEVEL_3, desc = "微信端绑定手机号", parentDesc = "微信管理")
	@RequestMapping("/bindPhone")
	@ResponseBody
	public ActionResult bindPhone(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession,
			String openId, String phone, String valiCode) {
		ActionResult ar = new ActionResult();
		try {
			String bindPhone = PropertiesUtils.getProperty(UserBaseAction.class, "propurl", "user").concat("wxUserMgr/bindOpenId.action");
			String result = HttpUtils.sendPost(bindPhone, "phone=" + phone+"&openId="+openId+"&valiCode="+valiCode, 0);
			BaseModel bm = JsonUtils.json2Object(result, BaseModel.class, "");
			if (MessageEnum.SUC.toString().equals(bm.getErrCode())) {
				ar.setSuccess(true);
			} else if (MessageEnum.ERR.toString().equals(bm.getErrCode())
					|| MessageEnum.ERR_PARAMETER.toString().equals(bm.getErrCode())) {
				ar.setMessage(bm.getErrMsg());
				ar.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
			ar.setMessage("绑定出错，请重试");
			ar.setSuccess(false);
		}
		return ar;
	}
	
	/**
	 * 手机解绑
	 * @param request
	 * @param response
	 * @param httpSession
	 * @param openId
	 * @param phone
	 * @param valiCode
	 * @return
	 */
	@LogOpt(level = FunLogConst.LEVEL_3, desc = "微信端解除绑定", parentDesc = "微信管理")
	@RequestMapping("/unBindPhone")
	@ResponseBody
	public ActionResult unBindPhone(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession,
			String openId, String phone, String valiCode) {
		ActionResult ar = new ActionResult();
		try {
			String unBindPhone = PropertiesUtils.getProperty(UserBaseAction.class, "propurl", "user").concat("wxUserMgr/unbundOpenId.action");
			String result = HttpUtils.sendPost(unBindPhone, "phone=" + phone+"&openId="+openId+"&valiCode="+valiCode, 0);
			BaseModel bm = JsonUtils.json2Object(result, BaseModel.class, "");
			if (MessageEnum.SUC.toString().equals(bm.getErrCode())) {
				ar.setSuccess(true);
			} else if (MessageEnum.ERR.toString().equals(bm.getErrCode())
					|| MessageEnum.ERR_PARAMETER.toString().equals(bm.getErrCode())) {
				ar.setMessage(bm.getErrMsg());
				ar.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
			ar.setMessage("解绑出错，请重试");
			ar.setSuccess(false);
		}
		return ar;
	}
	
	@LogOpt(level = FunLogConst.LEVEL_3, desc = "微信端注册", parentDesc = "微信管理")
	@RequestMapping("/userRegister")
	@ResponseBody
	public ActionResult userRegister(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession,
			String openId, UserInfo userInfo, String valiCode) {
		ActionResult ar = new ActionResult();
		try {
			
			String userRegister = PropertiesUtils.getProperty(UserBaseAction.class, "propurl", "user").concat("userMgr/userReg.action");
			String param="sjhm=" + userInfo.getSjhm()+"&openId="+openId+"&valiCode="+valiCode+"&dlmm="+userInfo.getDlmm()
				+"&xm="+userInfo.getXm()+"&xbm="+userInfo.getXbm()+"&zcfs=3";
			String result = HttpUtils.sendPost(userRegister, param, 0);
			BaseModel bm = JsonUtils.json2Object(result, BaseModel.class, "");
			if (MessageEnum.SUC.toString().equals(bm.getErrCode())) {
				ar.setSuccess(true);
			} else if (MessageEnum.ERR.toString().equals(bm.getErrCode())
					|| MessageEnum.ERR_PARAMETER.toString().equals(bm.getErrCode())) {
				ar.setMessage(bm.getErrMsg());
				ar.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
			ar.setMessage("注册出错，请重试");
			ar.setSuccess(false);
		}
		return ar;
	}
	
	@LogOpt(level = FunLogConst.LEVEL_3, desc = "微信端绑定后台", parentDesc = "微信管理")
	@RequestMapping("/bindRepairBackground")
	@ResponseBody
	public BaseModel bindRepairBackground(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession,
			String openId,String backAccount,String backType, String phone, String valiCode) {
		BaseModel baseModel=null;
		try {
			String bindBackPermit = PropertiesUtils.getProperty(RepairAction.class, "propurl", "user").concat("userPermit/bindBackPermit.action");
			String result = HttpUtils.sendPost(bindBackPermit, "openId="+openId+"&backAccount="+backAccount+"&backType="+backType+"&phone="+phone+"&valiCode="+valiCode, 0);
			baseModel = JsonUtils.json2Object(result,
					BaseModel.class, "");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return baseModel;
	}
	
	@LogOpt(level = FunLogConst.LEVEL_3, desc = "是否绑定后台用户", parentDesc = "微信管理")
	@RequestMapping("/isBindBackground")
	@ResponseBody
	public Map<String,String> isBindBackground(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession,
			String openid,String backType) {
		Map<String,String> map=null;
		UserBackRelation userBackRelation=null;
		try {
			String obtainBackPermit = PropertiesUtils.getProperty(RepairAction.class, "propurl", "user").concat("userPermit/obtainBackPermit.action");
			String permitResult = HttpUtils.sendPost(obtainBackPermit,
					"openId="+openid+"&backType="+backType, 0);
			userBackRelation=JsonUtils.json2Object(permitResult,
					UserBackRelation.class, "");
			map=new HashMap<>();
			Map<String, Object> userMap = getUserInfo(request, response, httpSession, openid);
			if ((boolean) userMap.get(SESSION_MAP_USER_STATE)) {
				UserInfo userInfo = JsonUtils.json2Object(((JSONObject) userMap.get(SESSION_MAP_USER)).toString(),
						UserInfo.class, "");
				map.put("uphone", userInfo.getSjhm());
			}
			
			if(userBackRelation==null || StringUtils.isBlank(userBackRelation.getBackAccount())){
				map.put("backOrgCode", "");
			} else {
				map.put("backOrgCode", userBackRelation.getBackOrgCode());
				String permitString="";
				for(int i=0;i<userBackRelation.getPermits().size();i++){
					permitString+="|"+userBackRelation.getPermits().get(i).getBackFuncCode()+"|,";
				}
				if(!"".equals(permitString)){
					permitString=permitString.substring(0, permitString.length()-1);
				}
				map.put("permitString", permitString);
				map.put("backAccount", userBackRelation.getBackAccount());
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@SuppressWarnings("unchecked")
	@LogOpt(level = FunLogConst.LEVEL_3, desc = "获取报修后台待处理列表", parentDesc = "微信管理")
	@RequestMapping("/getBackgroundList")
	@ResponseBody
	public Map<String,Object> getRepairBackgroundList(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession,
			String permitStr,String orgCode,int currentPage,int iDisplayLength,String backAccount) {
		Map<String,Object> map=null;
		try {
			permitStr=permitStr.replace("|", "'");
			String queryReparInfoByUser = PropertiesUtils.getProperty(RepairAction.class, "propurl", "repair").concat("repairInfo/queryReparInfoByUser.action");
			String permitResult = HttpUtils.sendPost(queryReparInfoByUser,
					"permitStr="+permitStr+"&orgCode="+orgCode+"&currentPage="+currentPage+"&iDisplayLength="+iDisplayLength+"&userAccount="+backAccount, 0);
			map=JsonUtils.json2Object(permitResult, Map.class, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
