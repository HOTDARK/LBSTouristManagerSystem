package com.hd.tsa.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hd.tsa.entity.UserInfo;
import com.hd.tsa.entity.VehicleBack;
import com.hd.tsa.entity.VehicleOrder;
import com.hd.sfw.core.model.BaseModel;
import com.hd.sfw.core.utils.HttpUtils;
import com.hd.sfw.core.utils.PropertiesUtils;
import com.hd.sfw.core.utils.json.JsonUtils;
import com.hd.sys.core.consts.FunLogConst;
import com.hd.sys.core.log.LogOpt;

@Controller
@RequestMapping("/vehicleBack")
public class VehicleBackAction extends UserBaseAction {

	private static Logger logger = Logger.getLogger(VehicleBackAction.class);
	
	@SuppressWarnings("unchecked")
	@LogOpt(level = FunLogConst.LEVEL_3, desc = "获取后台操作列表", parentDesc = "微信管理")
	@RequestMapping("/getVehicleBackList")
	@ResponseBody
	public Map<String,Object> getVehicleBackList(String permitCode , String backAccount,String openid,int pageNum,int displayLength) {
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			permitCode=permitCode.replace("|", "'");
			String queryReparInfoByUser = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("vehicleBack/getBackList.action");
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
	public ModelAndView getDetail(String orderNum,String state,String backAccount,String orgCode,String openid){
		ModelAndView view=new ModelAndView("useVehicleBack.jsp");
		view.addObject("openid", openid);
		try {
			Map<String,Object> userMap=getUserInfo(null, null, null, openid);
			if ((boolean) userMap.get(SESSION_MAP_USER_STATE)) {
				UserInfo userInfo=JsonUtils.json2Object(userMap.get(SESSION_MAP_USER).toString(), UserInfo.class, null);
				view.addObject("userInfo", userInfo);
				String getData = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("wxUseVehicle/getDetail.action");
				String resultStr = HttpUtils.sendPost(getData, "orderNum="+orderNum, 0);
				Map<String,Object> map=JsonUtils.json2Object(resultStr, Map.class, null);
				view.addObject("order", map.get("data"));
				view.addObject("backAccount", backAccount);
				view.addObject("openid", openid);
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
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/findDriverAndCar")
	public Map<String, Object> findDriverAndCar(){
		Map<String, Object> map = new HashMap<>();
		try {
			String getData = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("vehicleBack/findDriverAndCar.action");
			String resultStr = HttpUtils.sendPost(getData, "", 0);
			map=JsonUtils.json2Object(resultStr, Map.class, null);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		return map;
	}

	@ResponseBody
	@RequestMapping("/auditOrder")
	public BaseModel auditOrder(VehicleOrder vehicleOrder,String userAccount,String sendMessage){
		BaseModel result = new BaseModel();
		try {
			String getData = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("vehicleBack/saveAuditing.action");
			Object[] obj={vehicleOrder};
			String param=HttpUtils.packParam(obj).concat("&userAccount="+userAccount+"&sendMessage="+sendMessage);
			
			String resultStr = HttpUtils.sendPost(getData, param, 0);
			result=JsonUtils.json2Object(resultStr, BaseModel.class, null);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/rejectOrder")
	public BaseModel rejectOrder(VehicleOrder vehicleOrder,String userAccount){
		BaseModel result = new BaseModel();
		try {
			String getData = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("vehicleBack/closeOrder.action");
			Object[] obj={vehicleOrder};
			String param=HttpUtils.packParam(obj).concat("&userAccount="+userAccount);
			String resultStr = HttpUtils.sendPost(getData, param, 0);
			result=JsonUtils.json2Object(resultStr, BaseModel.class, null);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 出车
	 * @param id
	 * @param userAccount
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/depart")
	public BaseModel depart(Long id,String userAccount){
		BaseModel result = new BaseModel();
		try {
			String getData = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("vehicleBack/sureVehicleOrder.action");
			String resultStr = HttpUtils.sendPost(getData, "id="+id+"&userAccount="+userAccount, 0);
			result=JsonUtils.json2Object(resultStr, BaseModel.class, null);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 回场
	 * @param id
	 * @param orderNum
	 * @param userAccount
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getBack")
	public BaseModel getBack(VehicleBack entity,String userAccount){
		BaseModel result = new BaseModel();
		try {
			Object[] objs={entity};
			String param=HttpUtils.packParam(objs).concat("&userAccount="+userAccount);
			String getData = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("vehicleBack/closeVehicleBack.action");
			String resultStr = HttpUtils.sendPost(getData, param, 0);
			result=JsonUtils.json2Object(resultStr, BaseModel.class, null);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/getBackInfo")
	public Map<String,Object> getBackInfo(String orderNum){
		Map<String,Object> map=new HashMap<>();
		try {
			String getData = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("vehicleBack/backOrder.action");
			String resultStr = HttpUtils.sendPost(getData, "orderNum="+orderNum, 0);
			map=JsonUtils.json2Object(resultStr, Map.class, null);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/saveBackInfo")
	public BaseModel saveBackInfo(VehicleBack back,String userAccount,String sDate,String eDate){
		BaseModel baseModel=new BaseModel();
		try {
			String getData = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("vehicleBack/saveVehicleBack.action");
			Object[] obj={back};
			String param=HttpUtils.packParam(obj).concat("&userAccount="+userAccount+"&startDate="+sDate+"&endDate="+eDate);
			String resultStr = HttpUtils.sendPost(getData, param, 0);
			baseModel=JsonUtils.json2Object(resultStr, VehicleBack.class, null);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		return baseModel;
	}
}
