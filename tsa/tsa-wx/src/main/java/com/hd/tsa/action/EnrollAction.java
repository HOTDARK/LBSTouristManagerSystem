package com.hd.tsa.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hd.tsa.entity.StudentAudit;
import com.hd.tsa.entity.UserInfo;
import com.hd.sfw.core.utils.HttpUtils;
import com.hd.sfw.core.utils.PropertiesUtils;
import com.hd.sfw.core.utils.json.JsonUtils;
import com.hd.sys.action.base.ActionResult;
import com.hd.sys.core.consts.FunLogConst;
import com.hd.sys.core.log.LogOpt;

@Controller
@RequestMapping("/enroll")
public class EnrollAction extends UserBaseAction  {
	private static Logger logger = Logger.getLogger(EnrollAction.class);

	@SuppressWarnings("unchecked")
	@LogOpt(level = FunLogConst.LEVEL_3, desc = "微信端获取报名基础数据", parentDesc = "微信管理")
	@RequestMapping("/enrollBase")
	@ResponseBody
	public Map<String,Object> enrollBase(HttpServletRequest request, HttpServletResponse response,
			HttpSession httpSession, String openid) {
		Map<String,Object> map=new HashMap<>();
		try {
			Map<String,Object> userMap=getUserInfo(request, response,httpSession,openid);
			if ((boolean) userMap.get(SESSION_MAP_USER_STATE)) {
				UserInfo userInfo=JsonUtils.json2Object(userMap.get(SESSION_MAP_USER).toString(), UserInfo.class, null);
				String getData = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("wxEnroll/enrollBase.action");
				String resultStr = HttpUtils.sendPost(getData, "", 0);
				map=JsonUtils.json2Object(resultStr, Map.class, null);
				map.put("userInfo", userInfo);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return map;
	}
	

	
	@LogOpt(level = FunLogConst.LEVEL_3, desc = "微信端报名", parentDesc = "微信管理")
	@RequestMapping("/wxEnroll")
	@ResponseBody
	public ActionResult wxEnroll(HttpServletRequest request, HttpServletResponse response,
			HttpSession httpSession, String openid,StudentAudit studentAutit) {
		ActionResult actionResult=new ActionResult();
		try {
			String getData = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("wxEnroll/wxEnroll.action");
			Object[] obj={studentAutit};
			String param=HttpUtils.packParam(obj);
			String resultStr = HttpUtils.sendPost(getData, param , 0);
			actionResult=JsonUtils.json2Object(resultStr, ActionResult.class, null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return actionResult;
	}
	
	@SuppressWarnings("unchecked")
	@LogOpt(level = FunLogConst.LEVEL_3, desc = "微信端获取报名基础数据", parentDesc = "微信管理")
	@RequestMapping("/getMyEnroll")
	@ResponseBody
	public Map<String,Object> getMyEnroll(HttpServletRequest request, HttpServletResponse response,
			HttpSession httpSession, String openid) {
		Map<String,Object> map=new HashMap<>();
		try {
			Map<String,Object> userMap=getUserInfo(request, response,httpSession,openid);
			if ((boolean) userMap.get(SESSION_MAP_USER_STATE)) {
				UserInfo userInfo=JsonUtils.json2Object(userMap.get(SESSION_MAP_USER).toString(), UserInfo.class, null);
				String getData = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("wxEnroll/wxGetMyEnroll.action");
				String resultStr = HttpUtils.sendPost(getData, "enrollUser="+userInfo.getXgh(), 0);
				map=JsonUtils.json2Object(resultStr, Map.class, null);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return map;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/getDetail")
	public ModelAndView getDetail(Long id,String openid){
		ModelAndView view=new ModelAndView("enrollDetail.jsp");
		view.addObject("openid", openid);
		try {
			Map<String,Object> userMap=getUserInfo(null, null, null, openid);
			if ((boolean) userMap.get(SESSION_MAP_USER_STATE)) {
				UserInfo userInfo=JsonUtils.json2Object(userMap.get(SESSION_MAP_USER).toString(), UserInfo.class, null);
				view.addObject("userInfo", userInfo);
				String getData = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("wxEnroll/getDetail.action");
				String resultStr = HttpUtils.sendPost(getData, "id="+id, 0);
				Map<String,Object> map=JsonUtils.json2Object(resultStr, Map.class, null);
				view.addObject("sa", map.get("sa"));
			} else {
				view.setViewName("userBind.jsp");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		return view;
	}
}
