package com.hd.tsa.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hd.tsa.entity.ProductsInfo;
import com.hd.tsa.entity.StoreInfo;
import com.hd.sfw.core.utils.HttpUtils;
import com.hd.sfw.core.utils.PropertiesUtils;
import com.hd.sfw.core.utils.json.JsonUtils;

@Controller
@RequestMapping("/restaurant")
public class RestaurantAction extends UserBaseAction  {

	private static Logger logger = Logger.getLogger(RestaurantAction.class);
	@SuppressWarnings("unchecked")
	@RequestMapping("/goRestaurant")
	public ModelAndView goRestaurant(String openid,Long id) {
		ModelAndView view=new ModelAndView("restaurant.jsp");
		try {
			view.addObject("openid", openid);
			String getData = PropertiesUtils.getProperty(DietAction.class, "propurl", "diet").concat("wxStoreInfo/findById.action");
			String resultStr = HttpUtils.sendPost(getData, "deleteFlag=0&id="+id, 0);
			StoreInfo storeInfo=JsonUtils.json2Object(resultStr, StoreInfo.class, null);
			
			String getData1 = PropertiesUtils.getProperty(DietAction.class, "propurl", "diet").concat("wxProductsInfo/findByCondition.action");
			String resultStr1 = HttpUtils.sendPost(getData1, "deleted=0&storeId="+id+"&sortColumns=seqNum desc&state=1", 0);
			List<ProductsInfo> productsInfos=JsonUtils.json2Object(resultStr1, List.class, null);
			
			
			view.addObject("productsInfos", productsInfos);
			view.addObject("storeInfo", storeInfo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
		return view;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/getFoods")
	@ResponseBody
	public Map<String, Object> getFoods(Long productsId,int iDisplayLength,int pageNum) {
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			String getData2 = PropertiesUtils.getProperty(DietAction.class, "propurl", "diet").concat("wxFoodVariety/findPageByProductsId.action");
			String resultStr2 = HttpUtils.sendPost(getData2, "deleted=0&state=1&productsId="+productsId+"&iDisplayLength="+iDisplayLength+"&pageNum="+pageNum, 0);
			map=JsonUtils.json2Object(resultStr2, Map.class, null);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
		return map;
	}
	
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/getStores")
	public List<StoreInfo> getStores(String ids){
		List<StoreInfo> list = new ArrayList<>();
		try {
			String getData = PropertiesUtils.getProperty(DietAction.class, "propurl", "diet").concat("wxStoreInfo/findByIds.action");
			String resultStr = HttpUtils.sendPost(getData, "deleteFlag=0&ids="+ids, 0);
			list=JsonUtils.json2Object(resultStr, List.class, null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/getCommets")
	@ResponseBody
	public Map<String, Object> getCommets(Long storeId,int iDisplayLength,int pageNum,int storeScore) {
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			String getData2 = PropertiesUtils.getProperty(DietAction.class, "propurl", "diet").concat("wxCommentInfo/findByPage.action");
			String resultStr2 = HttpUtils.sendPost(getData2, "deleteFlag=0&storeId="+storeId+"&iDisplayLength="+iDisplayLength+"&pageNum="+pageNum+"&storeScore="+storeScore+"&sortColumns=id desc", 0);
			map=JsonUtils.json2Object(resultStr2, Map.class, null);
			
			String getData = PropertiesUtils.getProperty(DietAction.class, "propurl", "diet").concat("wxCommentInfo/findCommentNum.action");
			String resultStr = HttpUtils.sendPost(getData, "deleteFlag=0&storeId="+storeId, 0);
			Map<String, Object> map2=JsonUtils.json2Object(resultStr, Map.class, null);
			
			map.put("all", map2.get("all"));
			map.put("manyi", map2.get("manyi"));
			map.put("bumanyi", map2.get("bumanyi"));
			map.put("pic", map2.get("pic"));
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
		return map;
	}
}
