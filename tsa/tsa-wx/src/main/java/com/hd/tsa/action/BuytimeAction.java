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

import com.hd.tsa.entity.BuytimeRecord;
import com.hd.tsa.entity.UserInfo;
import com.hd.sfw.core.utils.HttpUtils;
import com.hd.sfw.core.utils.PropertiesUtils;
import com.hd.sfw.core.utils.json.JsonUtils;
import com.hd.sys.action.base.ActionResult;
import com.hd.sys.core.consts.FunLogConst;
import com.hd.sys.core.log.LogOpt;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/buytime")
public class BuytimeAction extends UserBaseAction {
	private static Logger logger = Logger.getLogger(BuytimeAction.class);
	
	@SuppressWarnings("unchecked")
	@LogOpt(level = FunLogConst.LEVEL_3, desc = "微信端获取学时基础数据", parentDesc = "微信管理")
	@RequestMapping("/getBaseData")
	@ResponseBody
	public Map<String,Object> getBaseData(HttpServletRequest request, HttpServletResponse response,
			HttpSession httpSession, String openid) {
		Map<String,Object> map=new HashMap<>();
		try {
			Map<String,Object> userMap=getUserInfo(request, response,httpSession,openid);
			if ((boolean) userMap.get(SESSION_MAP_USER_STATE)) {
				UserInfo userInfo=JsonUtils.json2Object(userMap.get(SESSION_MAP_USER).toString(), UserInfo.class, null);
				String getDataUrl = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("wxLearnDriving/getStudentInfo.action");
				String result = HttpUtils.sendPost(getDataUrl, "enrollUser="+userInfo.getXgh(), 0);
				Map<String,Object> stuInfo=JsonUtils.json2Object(result, Map.class, null);
				if(stuInfo.get("studentInfo")!=null){
					Map<String, String> backMap=isBindBackground(request, response, httpSession, openid, "vehicle");
					if("".equals(backMap.get("backOrgCode"))){
						map.put("suc", false);
						map.put("msg", "您已经是学员，不需要购买学时");
						return map;
					}
				}
				String getData = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("wxBuytime/getBaseData.action");
				String resultStr = HttpUtils.sendPost(getData, "user="+userInfo.getXgh(), 0);
				map=JsonUtils.json2Object(resultStr, Map.class, null);
				map.put("userInfo", userInfo);
				map.put("suc", true);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return map;
	}
	
	@SuppressWarnings("unchecked")
	@LogOpt(level = FunLogConst.LEVEL_3, desc = "微信端获取学时基础数据", parentDesc = "微信管理")
	@RequestMapping("/goBuyTime")
	@ResponseBody
	public Map<String,Object> goBuyTime(HttpServletRequest request, HttpServletResponse response,
			HttpSession httpSession, String openid) {
		Map<String,Object> map=new HashMap<>();
		try {
			Map<String,Object> userMap=getUserInfo(request, response,httpSession,openid);
			if ((boolean) userMap.get(SESSION_MAP_USER_STATE)) {
				UserInfo userInfo=JsonUtils.json2Object(userMap.get(SESSION_MAP_USER).toString(), UserInfo.class, null);
				String getDataUrl = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("wxLearnDriving/getStudentInfo.action");
				String result = HttpUtils.sendPost(getDataUrl, "enrollUser="+userInfo.getXgh(), 0);
				Map<String,Object> stuInfo=JsonUtils.json2Object(result, Map.class, null);
				if(stuInfo.get("studentInfo")!=null){
					Map<String, String> backMap=isBindBackground(request, response, httpSession, openid, "vehicle");
					if("".equals(backMap.get("backOrgCode"))){
						map.put("suc", false);
						map.put("msg", "您已经是学员，不需要购买学时");
						return map;
					}
				}
				map.put("suc", true);
			} else {
				map.put("suc", false);
				map.put("msg", "您还没有绑定手机号");
				map.put("flag", 3);
				return map;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return map;
	}
	
	@LogOpt(level = FunLogConst.LEVEL_3, desc = "微信端购买学时", parentDesc = "微信管理")
	@RequestMapping("/buyTime")
	@ResponseBody
	public ActionResult buyTime(BuytimeRecord buytimeRecord) {
		ActionResult actionResult=new ActionResult();
		try {
			String getData = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("wxBuytime/wxBuytime.action");
			Object[] obj={buytimeRecord};
			String param=HttpUtils.packParam(obj);
			String resultStr = HttpUtils.sendPost(getData, param, 0);
			actionResult=JsonUtils.json2Object(resultStr, ActionResult.class, null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return actionResult;
	}
	
	
	@SuppressWarnings("unchecked")
	@LogOpt(level = FunLogConst.LEVEL_3, desc = "微信端获取我的购买列表", parentDesc = "微信管理")
	@RequestMapping("/getMyList")
	@ResponseBody
	public Map<String,Object> getMyList(String openid,int pageNum) {
		Map<String,Object> map=new HashMap<>();
		try {
			Map<String,Object> userMap = getUserInfo(null, null,null,openid);
			if ((boolean) userMap.get(SESSION_MAP_USER_STATE)) {
				UserInfo userInfo = JsonUtils.json2Object(((JSONObject) userMap.get(SESSION_MAP_USER)).toString(),
						UserInfo.class, "");
				String getData = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("wxBuytime/getMyList.action");
				String resultStr = HttpUtils.sendPost(getData, "user="+userInfo.getXgh()+"&pageNum="+pageNum+"&displayLength=10", 0);
				map=JsonUtils.json2Object(resultStr, Map.class, null);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return map;
	}
	
	
	
}
