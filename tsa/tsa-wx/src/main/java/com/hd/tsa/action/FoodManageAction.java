package com.hd.tsa.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hd.tsa.entity.FoodVariety;
import com.hd.sfw.core.utils.CollectionUtils;
import com.hd.sfw.core.utils.HttpUtils;
import com.hd.sfw.core.utils.PropertiesUtils;
import com.hd.sfw.core.utils.json.JsonUtils;
import com.hd.sys.action.base.ActionResult;

/**
 * 菜品管理控制层
 * @author 寂寞先生
 * 2018年1月9日
 */
@Controller
@RequestMapping("/foodManage")
public class FoodManageAction {
	
	private static Logger logger = Logger.getLogger(FoodManageAction.class);
	
	/**
	 * 根据用户account获取餐厅菜品
	 * @param account
	 * @param pageNum
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getFoodList")
	@SuppressWarnings("unchecked")
	public ActionResult getFoodList(Long storeId, Integer pageNum){
		ActionResult result = new ActionResult();
		try {
			String getData = PropertiesUtils.getProperty(StoreManageAction.class, "propurl", "diet").concat("manageFoodVariety/findPage.action");
			String resultStr = HttpUtils.sendPost(getData, "storeId="+storeId+"&pageNum="+pageNum, 0);
			Map<String, Object> map =JsonUtils.json2Object(resultStr, HashMap.class, null);
			if (CollectionUtils.isNotEmpty(map)) {
				result.put("foodList", map.get("list"));
				result.put("totalPages", map.get("pageNums"));
			}else{
				result.put("totalPages", 0);
				result.put("foodList", null);
			}
			result.setSuccess(true);
		} catch (Exception e) {
			result.setMessage("获取菜品数据失败");
			logger.error("获取菜品数据失败："+e.getMessage(), e);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据id获取菜品及菜品所属关系
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getFood")
	@SuppressWarnings("unchecked")
	public ActionResult getFood(Long id){
		ActionResult result = new ActionResult();
		try {
			String getData = PropertiesUtils.getProperty(StoreManageAction.class, "propurl", "diet").concat("manageFoodVariety/findById.action");
			String resultStr = HttpUtils.sendPost(getData, "id="+id, 0);
			Map<String, Object> map =JsonUtils.json2Object(resultStr, HashMap.class, null);
			if (CollectionUtils.isNotEmpty(map)) {
				result.put("entity", map.get("entity"));
				result.put("relList", map.get("list"));
				result.setSuccess(true);
			}else{
				result.setMessage("获取菜品信息失败");
			}
		} catch (Exception e) {
			result.setMessage("获取菜品信息失败");
			logger.error("根据id获取菜品信息失败："+e.getMessage(), e);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 新增菜品
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/insertFood")
	public ActionResult insertFood(FoodVariety entity){
		ActionResult result = new ActionResult();
		try {
			String productsIds = entity.getProductsIds();
			entity.setDeleted(0);
			entity.setState(1);
			entity.setCreateDate(new Date());
			Object[] objs = {entity,productsIds};
			String paramStr = HttpUtils.packParam(objs);
			String getData = PropertiesUtils.getProperty(StoreManageAction.class, "propurl", "diet").concat("manageFoodVariety/saveFood.action");
			String resultStr = HttpUtils.sendPost(getData, paramStr, 0);
			boolean rs =JsonUtils.json2Object(resultStr, boolean.class, null);
			if (rs) {
				result.setSuccess(true);
			}
		} catch (Exception e) {
			logger.error("新增菜品失败："+e.getMessage(), e);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 修改菜品及相关信息
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/editFood")
	public ActionResult editFood(FoodVariety entity){
		ActionResult result = new ActionResult();
		try {
			String productsIds = entity.getProductsIds();
			entity.setUpdateDate(new Date());
			Object[] objs = {entity,productsIds};
			String paramStr = HttpUtils.packParam(objs);
			String getData = PropertiesUtils.getProperty(StoreManageAction.class, "propurl", "diet").concat("manageFoodVariety/updateFood.action");
			String resultStr = HttpUtils.sendPost(getData, paramStr, 0);
			boolean rs =JsonUtils.json2Object(resultStr, boolean.class, null);
			if (rs) {
				result.setSuccess(true);
			}
		} catch (Exception e) {
			logger.error("修改菜品失败："+e.getMessage(), e);
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 菜品上架/下架
	 * @param id
	 * @param state
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/editFoodState")
	public ActionResult editFoodState(Long id, Integer state){
		ActionResult result = new ActionResult();
		try {
			FoodVariety entity = new FoodVariety();
			entity.setId(id);
			entity.setState(state);
			Object[] objs = {entity};
			String paramStr = HttpUtils.packParam(objs);
			String getData = PropertiesUtils.getProperty(StoreManageAction.class, "propurl", "diet").concat("manageFoodVariety/updateFoodState.action");
			String resultStr = HttpUtils.sendPost(getData, paramStr, 0);
			boolean rs =JsonUtils.json2Object(resultStr, boolean.class, null);
			if (rs) {
				result.setSuccess(true);
			}
		} catch (Exception e) {
			if (state==0) {
				logger.error("菜品下架失败："+e.getMessage(), e);
				result.setMessage("菜品下架失败");
			}else{
				logger.error("菜品上架失败："+e.getMessage(), e);
				result.setMessage("菜品上架失败");
			}
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据id删除菜品及其所属关系
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteFood")
	public ActionResult deleteFood(Long id){
		ActionResult result = new ActionResult();
		try {
			String getData = PropertiesUtils.getProperty(StoreManageAction.class, "propurl", "diet").concat("manageFoodVariety/deleteFoodById.action");
			String resultStr = HttpUtils.sendPost(getData, "foodId="+id, 0);
			boolean rs =JsonUtils.json2Object(resultStr, boolean.class, null);
			if (rs) {
				result.setSuccess(true);
			}
		} catch (Exception e) {
			result.setMessage("删除菜品失败");
			logger.error("删除菜品及其关系失败："+e.getMessage(), e);
			e.printStackTrace();
		}
		return result;
	}
}
