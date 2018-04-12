package com.hd.tsa.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hd.tsa.entity.CuisineInfo;
import com.hd.sfw.core.utils.HttpUtils;
import com.hd.sfw.core.utils.PropertiesUtils;
import com.hd.sfw.core.utils.json.JsonUtils;

@Controller
@RequestMapping("/diet")
public class DietAction extends UserBaseAction {
	
	private static Logger logger = Logger.getLogger(DietAction.class);
	@SuppressWarnings("unchecked")
	@RequestMapping("/goDiet")
	public ModelAndView goDiet(String openid) {
		ModelAndView view=new ModelAndView("dietIndex.jsp");
		try {
			if(openid!=null && !"".equals(openid)){
				view.addObject("openid", openid);
			}
			Map<String,Object> userMap=getUserInfo(null, null,null,openid);
			if ((boolean) userMap.get(SESSION_MAP_USER_STATE)) {
				String getData = PropertiesUtils.getProperty(DietAction.class, "propurl", "diet").concat("wxCuisineInfo/findByCondition.action");
				String resultStr = HttpUtils.sendPost(getData, "state=1&deleted=0&sortColumns=seqNum desc", 0);
				List<CuisineInfo> list=JsonUtils.json2Object(resultStr, List.class, null);
				view.addObject("cuisineInfo", list);
				view.addObject("clength", list.size());
			} else {
				view.setViewName("userBind.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
		return view;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/getStore")
	@ResponseBody
	public Map<String,Object> getStore(Long id,int iDisplayLength,int pageNum) {
		Map<String,Object> map=new HashMap<>();
		try {
			String getData = PropertiesUtils.getProperty(DietAction.class, "propurl", "diet").concat("wxStoreInfo/findByPage.action");
			String param="deleteFlag=0&pageNum="+pageNum+"&iDisplayLength="+iDisplayLength+"&storeStatus=1&status=1";
			if(id!=null){
				param+="&cuisineId="+id;
			}
			String resultStr = HttpUtils.sendPost(getData, param, 0);
			
			map=JsonUtils.json2Object(resultStr, Map.class, null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
		return map;
	}
	
	@RequestMapping("/goTrolley")
	public ModelAndView goTrolley(String openid) {
		ModelAndView view=new ModelAndView("localTrolley.jsp");
		try {
			if(openid!=null && !"".equals(openid)){
				view.addObject("openid", openid);
			}
			Map<String,Object> userMap=getUserInfo(null, null,null,openid);
			if (!(boolean) userMap.get(SESSION_MAP_USER_STATE)) {
				view.setViewName("userBind.jsp");
			} 
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
		return view;
	}
}
