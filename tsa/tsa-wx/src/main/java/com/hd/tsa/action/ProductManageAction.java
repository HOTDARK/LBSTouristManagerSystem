package com.hd.tsa.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hd.tsa.entity.ProductsInfo;
import com.hd.sfw.core.utils.CollectionUtils;
import com.hd.sfw.core.utils.HttpUtils;
import com.hd.sfw.core.utils.PropertiesUtils;
import com.hd.sfw.core.utils.json.JsonUtils;
import com.hd.sys.action.base.ActionResult;

/**
 * 餐品管理控制层
 * @author 寂寞先生
 * 2018年1月8日
 */
@Controller
@RequestMapping("/productsManage")
public class ProductManageAction extends UserBaseAction {
	
	private static Logger logger = Logger.getLogger(ProductManageAction.class);

	/**
	 * 根据用户account获取餐厅餐品
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getProductList")
	@SuppressWarnings("unchecked")
	public ActionResult getProductList(Long storeId, Integer pageNum){
		ActionResult result = new ActionResult();
		try {
			String getData = PropertiesUtils.getProperty(StoreManageAction.class, "propurl", "diet").concat("manageProductsInfo/findPage.action");
			String resultStr = HttpUtils.sendPost(getData, "storeId="+storeId+"&pageNum="+pageNum, 0);
			Map<String, Object> map =JsonUtils.json2Object(resultStr, HashMap.class, null);
			if (CollectionUtils.isNotEmpty(map)) {
				result.put("proList", map.get("list"));
				result.put("totalPages", map.get("pageNums"));
			}else{
				result.put("totalPages", 0);
				result.put("proList", null);
			}
			result.setSuccess(true);
		} catch (Exception e) {
			result.setMessage("获取餐品数据失败");
			logger.error("获取餐品数据失败："+e.getMessage(), e);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据id获取餐品信息
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getProduct")
	public ActionResult getProduct(Long id){
		ActionResult result = new ActionResult();
		try { 
			String getData = PropertiesUtils.getProperty(StoreManageAction.class, "propurl", "diet").concat("manageProductsInfo/findById.action");
			String resultStr = HttpUtils.sendPost(getData, "id="+id, 0);
			ProductsInfo entity =JsonUtils.json2Object(resultStr, ProductsInfo.class, null);
			if (entity!=null && entity.getId()!=null) {
				result.setSuccess(true);
				result.put("pro", entity);
			}else{
				result.setMessage("获取餐品失败");
			}
		} catch (Exception e) {
			result.setMessage("获取餐品失败");
			logger.error("根据id获取餐品信息失败："+e.getMessage(), e);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 修改餐品信息
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/editProduct")
	public ActionResult editProduct(ProductsInfo entity){
		ActionResult result = new ActionResult();
		try {
			Object[] objs = {entity};
			String paramStr = HttpUtils.packParam(objs);
			String getData = PropertiesUtils.getProperty(StoreManageAction.class, "propurl", "diet").concat("manageProductsInfo/updateProducts.action");
			String resultStr = HttpUtils.sendPost(getData, paramStr, 0);
			boolean rs =JsonUtils.json2Object(resultStr, boolean.class, null);
			if (rs) {
				result.setSuccess(true);
			}
		} catch (Exception e) {
			logger.error("微信修改餐品信息失败："+e.getMessage(), e);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 新增餐品
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/insertProduct")
	public ActionResult insertProduct(ProductsInfo entity){
		ActionResult result = new ActionResult();
		try {
			entity.setState(1);
			entity.setDeleted(0);
			Object[] objs = {entity};
			String paramStr = HttpUtils.packParam(objs);
			String getData = PropertiesUtils.getProperty(StoreManageAction.class, "propurl", "diet").concat("manageProductsInfo/saveProducts.action");
			String resultStr = HttpUtils.sendPost(getData, paramStr, 0);
			boolean rs =JsonUtils.json2Object(resultStr, boolean.class, null);
			if (rs) {
				result.setSuccess(true);
			}
		} catch (Exception e) {
			logger.error("微信新增餐品信息失败："+e.getMessage(), e);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 激活餐品
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/activePro")
	public ActionResult activePro(Long id){
		ActionResult result = new ActionResult();
		try {
			ProductsInfo entity = new ProductsInfo();
			entity.setId(id);
			entity.setState(1);
			Object[] objs = {entity};
			String paramStr = HttpUtils.packParam(objs);
			String getData = PropertiesUtils.getProperty(StoreManageAction.class, "propurl", "diet").concat("manageProductsInfo/updateProductsState.action");
			String resultStr = HttpUtils.sendPost(getData, paramStr, 0);
			boolean rs =JsonUtils.json2Object(resultStr, boolean.class, null);
			if (rs) {
				result.setSuccess(true);
			}
		} catch (Exception e) {
			logger.error("微信端激活餐品失败："+e.getMessage(), e);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 冻结餐品
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/frozenPro")
	public ActionResult frozenPro(Long id){
		ActionResult result = new ActionResult();
		try {
			ProductsInfo entity = new ProductsInfo();
			entity.setId(id);
			entity.setState(0);
			Object[] objs = {entity};
			String paramStr = HttpUtils.packParam(objs);
			String getData = PropertiesUtils.getProperty(StoreManageAction.class, "propurl", "diet").concat("manageProductsInfo/updateProductsState.action");
			String resultStr = HttpUtils.sendPost(getData, paramStr, 0);
			boolean rs =JsonUtils.json2Object(resultStr, boolean.class, null);
			if (rs) {
				result.setSuccess(true);
			}
		} catch (Exception e) {
			logger.error("微信端冻结餐品失败："+e.getMessage(), e);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据id删除餐品信息
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deletePro")
	public ActionResult deletePro(Long id){
		ActionResult result = new ActionResult();
		try {
			String getData = PropertiesUtils.getProperty(StoreManageAction.class, "propurl", "diet").concat("manageProductsInfo/deleteById.action");
			String resultStr = HttpUtils.sendPost(getData, "id="+id, 0);
			boolean rs =JsonUtils.json2Object(resultStr, boolean.class, null);
			if (rs) {
				result.setSuccess(true);
			}
		} catch (Exception e) {
			logger.error("删除餐品失败："+e.getMessage(), e);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据用户account获取餐厅有效餐品
	 * @param account
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getAllPro")
	@SuppressWarnings("unchecked")
	public ActionResult getAllPro(Long storeId){
		ActionResult result = new ActionResult();
		try {
			String getData = PropertiesUtils.getProperty(StoreManageAction.class, "propurl", "diet").concat("manageProductsInfo/findByStoreId.action");
			String resultStr = HttpUtils.sendPost(getData, "storeId="+storeId, 0);
			Map<String, Object> map =JsonUtils.json2Object(resultStr, HashMap.class, null);
			if (CollectionUtils.isNotEmpty(map)) {
				result.put("proList", map.get("list"));
				result.setSuccess(true);
			}else{
				result.setMessage("获取餐品数据失败");
			}
		} catch (Exception e) {
			logger.error("获取餐厅所有餐品失败："+e.getMessage(), e);
			e.printStackTrace();
		}
		return result;
	}
}
