package com.hd.tsa.action;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hd.tsa.entity.Comment;
import com.hd.tsa.entity.DriveModelSub;
import com.hd.tsa.entity.LearnOrderApply;
import com.hd.tsa.entity.UserInfo;
import com.hd.sfw.core.model.BaseModel;
import com.hd.sfw.core.utils.HttpUtils;
import com.hd.sfw.core.utils.PropertiesUtils;
import com.hd.sfw.core.utils.json.JsonUtils;
import com.hd.sys.action.base.ActionResult;
import com.hd.sys.core.consts.FunLogConst;
import com.hd.sys.core.log.LogOpt;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/wxLearnDriving")
public class LearnDrivingAction extends UserBaseAction {
	private static Logger logger = Logger.getLogger(LearnDrivingAction.class);
	
	@SuppressWarnings("unchecked")
	@LogOpt(level = FunLogConst.LEVEL_3, desc = "微信端获取学员信息", parentDesc = "微信管理")
	@RequestMapping("/goLearnDriving")
	@ResponseBody
	public Map<String,Object> goLearnDriving(String openid) {
		Map<String,Object> map=new HashMap<>();
		try {
			Map<String,Object> userMap = getUserInfo(null, null,null,openid);
			if ((boolean) userMap.get(SESSION_MAP_USER_STATE)) {
				UserInfo userInfo = JsonUtils.json2Object(((JSONObject) userMap.get(SESSION_MAP_USER)).toString(),
						UserInfo.class, "");
				String getData = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("wxLearnDriving/getStudentInfo.action");
				String resultStr = HttpUtils.sendPost(getData, "enrollUser="+userInfo.getXgh(), 0);
				map=JsonUtils.json2Object(resultStr, Map.class, null);
				map.put("userInfo", userInfo);
				
				if(map.get("studentInfo")==null){
					map.put("suc", false);
					map.put("msg", "您还不是学员，请先报名");
					map.put("flag", 2);
				} else {
					JSONObject info=JSONObject.fromObject(map.get("studentInfo"));
					if("001004".equals(info.get("stuStatus")) || "001005".equals(info.get("stuStatus"))){
						map.put("suc", false);
						map.put("msg", "您已经"+info.get("stuStatusStr")+"，无法预约学车");
						map.put("flag", 2);
					} else if ("001003".equals(info.get("stuStatus"))){
						if("001001".equals(info.get("currentSub"))){
							map.put("suc", false);
							map.put("msg", "您暂未通过科目一的学习");
							map.put("flag", 4);
						} else if ("001004".equals(info.get("currentSub"))){
							map.put("suc", false);
							map.put("msg", "您已通过科目三的学习");
							map.put("flag", 4);
						} else {
							
							map.put("suc", true);
							map.put("flag", 1);
						}
					}
				}
			} else {
				map.put("suc", false);
				map.put("msg", "您还没有绑定用户");
				map.put("flag", 3);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@LogOpt(level = FunLogConst.LEVEL_3, desc = "微信端获取dms", parentDesc = "微信管理")
	@RequestMapping("/getDms")
	@ResponseBody
	public Map<String,Object> getDms(DriveModelSub entity) {
		Map<String,Object> map=new HashMap<>();
		try {
				String getData = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("wxLearnDriving/getDms.action");
				Object[] obj={entity};
				String param=HttpUtils.packParam(obj);
				String resultStr = HttpUtils.sendPost(getData, param, 0);
				map=JsonUtils.json2Object(resultStr, Map.class, null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return map;
	}
	
	@LogOpt(level = FunLogConst.LEVEL_3, desc = "微信端预约学车", parentDesc = "微信管理")
	@RequestMapping("/learnOrderApply")
	@ResponseBody
	public ActionResult learnOrderApply(LearnOrderApply entity) {
		ActionResult actionResult=new ActionResult();
		try {
			String getData = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("wxLearnDriving/learnOrderApply.action");
			Object[] obj={entity};
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String param=HttpUtils.packParam(obj);
			param+="&sTime="+sf.format(entity.getStartTime());
			String resultStr = HttpUtils.sendPost(getData, param, 0);
			actionResult=JsonUtils.json2Object(resultStr, ActionResult.class, null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return actionResult;
	}
	
	@SuppressWarnings("unchecked")
	@LogOpt(level = FunLogConst.LEVEL_3, desc = "微信端获取我的学车列表", parentDesc = "微信管理")
	@RequestMapping("/getMyList")
	@ResponseBody
	public Map<String,Object> getMyList(String openid,int pageNum) {
		Map<String,Object> map=new HashMap<>();
		try {
			Map<String,Object> userMap = getUserInfo(null, null,null,openid);
			if ((boolean) userMap.get(SESSION_MAP_USER_STATE)) {
				UserInfo userInfo = JsonUtils.json2Object(((JSONObject) userMap.get(SESSION_MAP_USER)).toString(),
						UserInfo.class, "");
				String getData = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("wxLearnDriving/getMyList.action");
				String resultStr = HttpUtils.sendPost(getData, "stuUser="+userInfo.getXgh()+"&pageNum="+pageNum+"&displayLength=10", 0);
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
	public ModelAndView getDetail(Long id,String openid,int ftable){
		ModelAndView view=new ModelAndView("learnDrivingDetail.jsp");
		view.addObject("openid", openid);
		try {
			Map<String,Object> userMap=getUserInfo(null, null, null, openid);
			if ((boolean) userMap.get(SESSION_MAP_USER_STATE)) {
				UserInfo userInfo=JsonUtils.json2Object(userMap.get(SESSION_MAP_USER).toString(), UserInfo.class, null);
				view.addObject("userInfo", userInfo);
				String getData = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("wxLearnDriving/getDetail.action");
				String resultStr = HttpUtils.sendPost(getData, "id="+id+"&ftable="+ftable, 0);
				Map<String,Object> map=JsonUtils.json2Object(resultStr, Map.class, null);
				view.addObject("learnOrder", map.get("learnOrder"));
			} else {
				view.setViewName("userBind.jsp");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		return view;
	}
	
	
	@LogOpt(level = FunLogConst.LEVEL_3, desc = "微信端确认上车", parentDesc = "微信管理")
	@RequestMapping("/getOn")
	@ResponseBody
	public boolean getOn(String openid,Long id) {
		boolean r=false;
		try {
			String getData = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("learnapply/confirmStart.action");
			String resultStr = HttpUtils.sendPost(getData, "id="+id, 0);
			r=Boolean.parseBoolean(resultStr);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return r;
	}
	
	@LogOpt(level = FunLogConst.LEVEL_3, desc = "微信端确认下车", parentDesc = "微信管理")
	@RequestMapping("/getOff")
	@ResponseBody
	public boolean getOff(String openid,Long id,int realStuTime) {
		boolean r=false;
		try {
			String getData = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("learnapply/confirmOver.action");
			String resultStr = HttpUtils.sendPost(getData, "id="+id+"&realStuTime="+realStuTime, 0);
			r=Boolean.parseBoolean(resultStr);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * 学车评价
	 * @param entity
	 * @param openid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/evaluateLearn")
	public ActionResult evaluateLearn(Comment entity, String openid){
		ActionResult result = new ActionResult();
		try {
			Map<String,Object> userMap=getUserInfo(null, null, null, openid);
			if ((boolean) userMap.get(SESSION_MAP_USER_STATE)) {
				UserInfo userInfo=JsonUtils.json2Object(userMap.get(SESSION_MAP_USER).toString(), UserInfo.class, null);
				entity.setCreateUser(userInfo.getXgh());
				entity.setCreateUserName(userInfo.getXm());
				entity.setTelephone(userInfo.getSjhm());
				entity.setPersonSex(userInfo.getXbm());
				Object[] objs = {entity};
				String paramStr = HttpUtils.packParam(objs);
				String getData = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("wxLearnDriving/comment.action");
				String resultStr = HttpUtils.sendPost(getData, paramStr, 0);
				BaseModel model=JsonUtils.json2Object(resultStr, BaseModel.class, null);
				if (model.getErrCode().equals("SUC")) {
					result.setSuccess(true);
				}
			}
		} catch (Exception e) {
			logger.error("微信端学车评价失败："+e.getMessage(), e);
			e.printStackTrace();
		}
		return result;
	}
}
