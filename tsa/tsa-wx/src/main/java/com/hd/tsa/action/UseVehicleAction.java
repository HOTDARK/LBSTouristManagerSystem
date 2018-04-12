package com.hd.tsa.action;

import java.text.SimpleDateFormat;
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

import com.hd.tsa.entity.UserInfo;
import com.hd.tsa.entity.VehicleBack;
import com.hd.tsa.entity.VehicleOrder;
import com.hd.sfw.core.utils.HttpUtils;
import com.hd.sfw.core.utils.PropertiesUtils;
import com.hd.sfw.core.utils.json.JsonUtils;
import com.hd.sys.action.base.ActionResult;
import com.hd.sys.core.consts.FunLogConst;
import com.hd.sys.core.log.LogOpt;

import net.sf.json.JSONObject;

/**
 * 用车控制类
 * @author LG
 *
 */
@Controller
@RequestMapping("/wxUseVehicle")
public class UseVehicleAction extends UserBaseAction {
	
	private static Logger logger = Logger.getLogger(UseVehicleAction.class);
	
	@SuppressWarnings("unchecked")
	@LogOpt(level = FunLogConst.LEVEL_3, desc = "微信端获取我的用车列表", parentDesc = "微信管理")
	@RequestMapping("/getMyUseVehicleList")
	@ResponseBody
	public Map<String,Object> getMyUseVehicleList(HttpServletRequest request, HttpServletResponse response,
			HttpSession httpSession, String openid,int pageNum) {
		Map<String,Object> map=new HashMap<>();
		try {
			Map<String,Object> userMap = getUserInfo(request, response,httpSession,openid);
			if ((boolean) userMap.get(SESSION_MAP_USER_STATE)) {
				UserInfo userInfo = JsonUtils.json2Object(((JSONObject) userMap.get(SESSION_MAP_USER)).toString(),
						UserInfo.class, "");
				String getMyList = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("wxUseVehicle/getMyVehicleList.action");
				String params="xgh="+userInfo.getXgh()+"&sjhm="+userInfo.getSjhm()+"&pageNum="+pageNum+"&displayLength=10";
				String serviceResult = HttpUtils.sendPost(getMyList, params, 0);
				map=JsonUtils.json2Object(serviceResult, Map.class, "");
				map.put("userInfo", userInfo);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return map;
	}
	
	@SuppressWarnings("unchecked")
	@LogOpt(level = FunLogConst.LEVEL_3, desc = "微信端获取用车基础数据", parentDesc = "微信管理")
	@RequestMapping("/useVehicleBase")
	@ResponseBody
	public Map<String,Object> useVehicleBase(HttpServletRequest request, HttpServletResponse response,
			HttpSession httpSession, String openid) {
		Map<String,Object> map=new HashMap<>();
		try {
			Map<String,Object> userMap=getUserInfo(request, response,httpSession,openid);
			if ((boolean) userMap.get(SESSION_MAP_USER_STATE)) {
				UserInfo userInfo=JsonUtils.json2Object(userMap.get(SESSION_MAP_USER).toString(), UserInfo.class, null);
				
				String getData = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("wxUseVehicle/getUseVehicleBaseData.action");
				String resultStr = HttpUtils.sendPost(getData, "", 0);
				map=JsonUtils.json2Object(resultStr, Map.class, null);
				map.put("xm", userInfo.getXm());
				map.put("sjhm",userInfo.getSjhm());
				map.put("xgh",userInfo.getXgh());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return map;
	}
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/getCompanysByParentCode")
	public Map<String,Object> getCompanysByParentCode(String parentCode){
		Map<String,Object> companys=null;
		try {
			String getData = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("wxUseVehicle/getCompanysByParentCode.action");
			String resultStr = HttpUtils.sendPost(getData, "parentCode="+parentCode, 0);
			companys=JsonUtils.json2Object(resultStr, Map.class, null);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		return companys;
	}
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/useVehicle")
	public Map<String,Object> useVehicle(VehicleOrder order){
		Map<String,Object> map=null;
		try {
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String stTime=sf.format(order.getStartOrderDate());
			String emTime=sf.format(order.getEstimateDate());
			String getData = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("wxUseVehicle/useVehicle.action");
			Object[] obj={order};
			String paramStr = HttpUtils.packParam(obj);
			paramStr+="&stTime="+stTime+"&emTime="+emTime;
			String resultStr = HttpUtils.sendPost(getData, paramStr, 0);
			map=JsonUtils.json2Object(resultStr, Map.class, null);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		return map;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/getDetail")
	public ModelAndView getDetail(String orderNum,String openid){
		ModelAndView view=new ModelAndView("useVehicleDetail.jsp");
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
				JSONObject jsonObject=JSONObject.fromObject(map.get("data"));
				VehicleOrder order=JsonUtils.json2Object(jsonObject.toString(),VehicleOrder.class,null);
				if(order!=null && "106".equals(order.getState())){
					getData = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("vehicleBack/backOrder.action");
					resultStr = HttpUtils.sendPost(getData, "orderNum="+orderNum, 0);
					Map<String,Object> backMap=JsonUtils.json2Object(resultStr,Map.class,null);
					JSONObject backJson=JSONObject.fromObject(backMap.get("back"));
					VehicleBack backEntity = JsonUtils.json2Object(backJson.toString(),VehicleBack.class,null);
					int totalPay=Integer.parseInt(backEntity.getRoadToll()) + Integer.parseInt(backEntity.getParkingRate()) + Integer.parseInt(backEntity.getMileage())*Integer.parseInt(backEntity.getPrice());
					view.addObject("vehicleBack", backEntity);
					view.addObject("totalPay", totalPay);
				}
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
	@RequestMapping("/closeVehicleOrder")
	public ActionResult closeVehicleOrder(String id,String openid){
		ActionResult actionResult=new ActionResult();
		try {
			Map<String,Object> userMap=getUserInfo(null, null, null, openid);
			if ((boolean) userMap.get(SESSION_MAP_USER_STATE)) {
				UserInfo userInfo=JsonUtils.json2Object(userMap.get(SESSION_MAP_USER).toString(), UserInfo.class, null);
				String getData = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("wxUseVehicle/closeVehicleOrder.action");
				String resultStr = HttpUtils.sendPost(getData, "id="+id+"&xgh="+userInfo.getXgh()+"&xm="+userInfo.getXm(), 0);
				actionResult=JsonUtils.json2Object(resultStr, ActionResult.class, null);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		return actionResult;
	}
	
	//evaluateOrder
	@ResponseBody
	@RequestMapping("/evaluateOrder")
	public ActionResult evaluateOrder(Long id,int orderStar,String evaluateInfo,
			int personStar,int vehicleStar,String openid){
		//String createUser,String createUserName,String telephone,String personSex,
		ActionResult actionResult=new ActionResult();
		try {
			Map<String,Object> userMap=getUserInfo(null, null, null, openid);
			if ((boolean) userMap.get(SESSION_MAP_USER_STATE)) {
				UserInfo userInfo=JsonUtils.json2Object(userMap.get(SESSION_MAP_USER).toString(), UserInfo.class, null);
				String getData = PropertiesUtils.getProperty(RepairAction.class, "propurl", "vehicle").concat("wxUseVehicle/evaluateOrder.action");
				String param="id="+id+"&createUser="+userInfo.getXgh()+"&createUserName="+userInfo.getXm()+
						"&evaluateInfo="+evaluateInfo+"&telephone="+userInfo.getSjhm()+"&personSex="+userInfo.getXbm()+"&orderStar="+orderStar+
						"&personStar="+personStar+"&vehicleStar="+vehicleStar;
				String resultStr = HttpUtils.sendPost(getData, param, 0);
				actionResult=JsonUtils.json2Object(resultStr, ActionResult.class, null);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
		return actionResult;
	}
}
