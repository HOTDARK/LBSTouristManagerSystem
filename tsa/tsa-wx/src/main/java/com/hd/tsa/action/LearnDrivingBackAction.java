package com.hd.tsa.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hd.tsa.entity.LearnOrderApply;
import com.hd.tsa.entity.UserInfo;
import com.hd.tsa.entity.VehicleInfo;
import com.hd.sfw.core.model.BaseModel;
import com.hd.sfw.core.utils.HttpUtils;
import com.hd.sfw.core.utils.PropertiesUtils;
import com.hd.sfw.core.utils.json.JsonUtils;
import com.hd.sys.core.consts.FunLogConst;
import com.hd.sys.core.log.LogOpt;
import com.hd.sys.entity.SysUser;

@Controller
@RequestMapping("/learnDrivingBack")
public class LearnDrivingBackAction extends UserBaseAction {

	private static Logger logger = Logger.getLogger(LearnDrivingBackAction.class);
	
	@SuppressWarnings("unchecked")
	@LogOpt(level = FunLogConst.LEVEL_3, desc = "获取报名后台操作列表", parentDesc = "微信管理")
	@RequestMapping("/getBackList")
	@ResponseBody
	public Map<String,Object> getBackList(String permitCode , String backAccount,String openid,int pageNum,int displayLength) {
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			permitCode=permitCode.replace("|", "'");
			String queryReparInfoByUser = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("wxLearnDriving/getBackList.action");
			String permitResult = HttpUtils.sendPost(queryReparInfoByUser,
					"permitCode="+permitCode+"&pageNum="+pageNum+"&displayLength="+displayLength+"&userAccount="+backAccount, 0);
			map=JsonUtils.json2Object(permitResult, Map.class, null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询审核用车预约单列表：" + e.getMessage(), e);
		}
		return map;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/getDetail")
	public ModelAndView getDetail(Long id,String openid,String backAccount,String orgCode,int ftable){
		ModelAndView view=new ModelAndView("learnDrivingBack.jsp");
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
				view.addObject("backAccount", backAccount);
				view.addObject("orgCode", orgCode);
			} else {
				view.setViewName("userBind.jsp");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		return view;
	}
	
	@ResponseBody
	@RequestMapping("/audit")
	public BaseModel audit(LearnOrderApply entity,String userAccount){
		BaseModel result = new BaseModel();
		try {
			String getData = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("wxLearnDriving/adoptLearnApply.action");
			Object[] obj={entity};
			String param=HttpUtils.packParam(obj).concat("&userAccount="+userAccount);
			String resultStr = HttpUtils.sendPost(getData, param, 0);
			result=JsonUtils.json2Object(resultStr, BaseModel.class, null);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/reject")
	public BaseModel reject(LearnOrderApply entity,String userAccount){
		BaseModel result = new BaseModel();
		try {
			String getData = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("wxLearnDriving/saveRejectApply.action");
			Object[] obj={entity};
			String param=HttpUtils.packParam(obj).concat("&userAccount="+userAccount);
			String resultStr = HttpUtils.sendPost(getData, param, 0);
			result=JsonUtils.json2Object(resultStr, BaseModel.class, null);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/cancel")
	public BaseModel cancel(Long id,String userAccount,String reason){
		BaseModel result = new BaseModel();
		try {
			String getData = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("wxLearnDriving/cancelLearnOrder.action");
			String resultStr = HttpUtils.sendPost(getData, "id="+id+"&userAccount="+userAccount+"&cancelReason="+reason, 0);
			result=JsonUtils.json2Object(resultStr, BaseModel.class, null);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		return result;
	}
	
	
	@ResponseBody
	@RequestMapping("/findVehicleAndCoach")
	public Map<String, Object> findVehicleAndCoach(int flag){
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			String getData = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("wxLearnDriving/queryVehicleInfo.action");
			String resultStr = HttpUtils.sendPost(getData, "", 0);
			List<VehicleInfo> vs=JsonUtils.json2GenericObject(resultStr, new TypeReference<List<VehicleInfo>>() {
			}, null);
			map.put("vs", vs);
			if(flag==1){
				String getData1 = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("wxLearnDriving/queryCoach.action");
				String resultStr1 = HttpUtils.sendPost(getData1, "", 0);
				List<SysUser> coaches=JsonUtils.json2GenericObject(resultStr1, new TypeReference<List<SysUser>>() {
				}, null);
				map.put("coaches", coaches);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		return map;
	}
}
