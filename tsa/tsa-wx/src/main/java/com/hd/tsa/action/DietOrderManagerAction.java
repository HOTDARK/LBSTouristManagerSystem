package com.hd.tsa.action;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hd.sfw.core.utils.HttpUtils;
import com.hd.sfw.core.utils.PropertiesUtils;
import com.hd.sfw.core.utils.json.JsonUtils;

@Controller
@RequestMapping("/dietOrderManager")
public class DietOrderManagerAction extends UserBaseAction {

	private static Logger logger = Logger.getLogger(DietOrderManagerAction.class);
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/getOrderList")
	@ResponseBody
	public Map<String,Object> getOrderList(String backAccount,int pageNum,int displayLength,int receiveFlag) {
		Map<String,Object> map=null;
		try {
			String queryReparInfoByUser = PropertiesUtils.getProperty(RepairAction.class, "propurl", "diet").concat("manageDietOrder/findPageByUserAccount.action");
			String params="pageNum="+pageNum+"&iDisplayLength="+displayLength+"&userAccount="+backAccount+"&receiveFlag="+receiveFlag;
			String permitResult = HttpUtils.sendPost(queryReparInfoByUser,
					params, 0);
			map=JsonUtils.json2Object(permitResult, Map.class, null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return map;
	}
	
	@RequestMapping("/acceptOrder")
	@ResponseBody
	public boolean acceptOrder(String backAccount,String orderNum) {
		boolean isSuccess=false;
		try {
			String queryReparInfoByUser = PropertiesUtils.getProperty(RepairAction.class, "propurl", "diet").concat("manageDietOrder/storeOrders.action");
			String params="userAccount="+backAccount+"&orderNum="+orderNum;
			String permitResult = HttpUtils.sendPost(queryReparInfoByUser,
					params, 0);
			isSuccess=JsonUtils.json2Object(permitResult, Boolean.class, null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return isSuccess;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/goOrderDetail")
	public ModelAndView goOrderDetail(String backAccount,String orderNum,String openid) {
		ModelAndView view=new ModelAndView("orderDetailManager.jsp");
		try {
			String queryReparInfoByUser = PropertiesUtils.getProperty(RepairAction.class, "propurl", "diet").concat("manageDietOrder/findById.action");
			String params="id="+orderNum;
			String permitResult = HttpUtils.sendPost(queryReparInfoByUser,
					params, 0);
			Map<String, Object> map = JsonUtils.json2Object(permitResult, Map.class, null);
			view.addObject("entity",map.get("entity"));
			view.addObject("list",map.get("list"));
			view.addObject("store",map.get("store"));
			view.addObject("backAccount",backAccount);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return view;
	}
}
