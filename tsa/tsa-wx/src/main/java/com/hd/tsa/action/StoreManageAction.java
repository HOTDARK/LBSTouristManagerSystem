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

import com.hd.tsa.entity.CuisineInfo;
import com.hd.tsa.entity.StoreInfo;
import com.hd.sfw.core.utils.CollectionUtils;
import com.hd.sfw.core.utils.HttpUtils;
import com.hd.sfw.core.utils.PropertiesUtils;
import com.hd.sfw.core.utils.json.JsonUtils;
import com.hd.sys.action.base.ActionResult;
import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.WxJsapiConfig;

/**
 * 餐厅管理控制层
 * @author 寂寞先生
 * 2018年1月5日
 */
@Controller
@RequestMapping("/storeManage")
public class StoreManageAction extends UserBaseAction  {

	private static Logger logger = Logger.getLogger(StoreManageAction.class);
	
	// 实例化 统一业务API入口
	private IService iService = new WxService();
	
	/**
	 * 跳转餐厅信息管理页面
	 * @param id
	 * @param openid
	 * @return
	 */
	@RequestMapping("/goStoreInfo")
	public ModelAndView goStoreInfo(String account, String openid){
		ModelAndView modelAndView = new ModelAndView("storeInfo.jsp");
		try {
			List<String> jsApiList = new ArrayList<>();
			// 需要用到哪些JS SDK API 就设置哪些
			jsApiList.add("chooseImage");// 拍照或从手机相册中选图接口
			jsApiList.add("uploadImage");// 上传图片接口
			String domain = PropertiesUtils.getProperty(StoreManageAction.class, "wx", "wx.domain");
			WxJsapiConfig config = iService.createJsapiConfig(
					domain+"/drsp-wx/storeManage/goStoreInfo.action?account="+account+"&openid=" + openid,
					jsApiList);
			config.setAppid(PropertiesUtils.getProperty(StoreManageAction.class, "wx", "wx.appId"));
			modelAndView.addObject("config", config);
			modelAndView.addObject("account", account);
			modelAndView.addObject("openid", openid);
		} catch (Exception e) {
			logger.error("跳转餐厅信息页面失败"+e.getMessage(), e);
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	/**
	 * 根据用户account获取餐厅信息
	 * @param account
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/getStoreInfo")
	public ActionResult getStoreInfo(String account){
		ActionResult result = new ActionResult();
		try {
			String getData = PropertiesUtils.getProperty(StoreManageAction.class, "propurl", "diet").concat("manageStoreInfo/findByUserAccount.action");
			String resultStr = HttpUtils.sendPost(getData, "userAccount="+account, 0);
			Map<String, Object> map =JsonUtils.json2Object(resultStr, HashMap.class, null);
			Map<String, Object> smap = (Map<String, Object>)map.get("store");
			if (smap.get("errCode")!=null && smap.get("errCode").equals("ERR")){
				result.put("errMsg", smap.get("errMsg"));
			}else{
				result.setSuccess(true);
				result.put("store", map.get("store"));
				result.put("relList", map.get("relList"));
				result.put("sacList", map.get("sacList"));
			}
		} catch (Exception e) {
			logger.error("微信商家管理端获取餐厅信息失败："+e.getMessage(), e);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 保存餐厅信息
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveStore")
	public ActionResult saveStore(StoreInfo entity){
		ActionResult result = new ActionResult();
		try {
			if (entity.getImgIds()!=null && entity.getImgIds()!="") {
				entity.setImgIds(entity.getImgIds().substring(0, entity.getImgIds().length()-1));
			}
			if (entity.getPaths()!=null && entity.getPaths()!="") {
				entity.setPaths(entity.getPaths().substring(0, entity.getPaths().length()-1)); 
			}
			Object[] objs = {entity};
			String paramStr = HttpUtils.packParam(objs);
			String getData = PropertiesUtils.getProperty(StoreManageAction.class, "propurl", "diet").concat("manageStoreInfo/updateStore.action");
			String resultStr = HttpUtils.sendPost(getData, paramStr, 0);
			boolean reStr =JsonUtils.json2Object(resultStr, boolean.class, null);
			result.setSuccess(reStr);
		} catch (Exception e) {
			logger.error("修改餐厅信息失败："+e.getMessage(), e);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 跳转餐厅餐品管理页面
	 * @param id
	 * @param openid
	 * @return
	 */
	@RequestMapping("/goProductMange")
	public ModelAndView goProductMange(Long storeId, String openid){
		ModelAndView modelAndView = new ModelAndView("productsManage.jsp");
		modelAndView.addObject("storeId", storeId);
		modelAndView.addObject("openid", openid);
		return modelAndView;
	}
	
	/**
	 * 跳转菜品管理页面
	 * @param account
	 * @param openid
	 * @return
	 */
	@RequestMapping("/goFoodManage")
	public ModelAndView goFoodManage(Long storeId, String account, String openid){
		ModelAndView modelAndView = new ModelAndView("foodManage.jsp");
		try {
			List<String> jsApiList = new ArrayList<>();
			// 需要用到哪些JS SDK API 就设置哪些
			jsApiList.add("chooseImage");// 拍照或从手机相册中选图接口
			jsApiList.add("uploadImage");// 上传图片接口
			String domain = PropertiesUtils.getProperty(StoreManageAction.class, "wx", "wx.domain");
			WxJsapiConfig config = iService.createJsapiConfig(
					domain+"/drsp-wx/storeManage/goFoodManage.action?storeId="+storeId+"&account="+account+"&openid=" + openid,
					jsApiList);
			config.setAppid(PropertiesUtils.getProperty(StoreManageAction.class, "wx", "wx.appId"));
			modelAndView.addObject("config", config);
			modelAndView.addObject("storeId", storeId);
			modelAndView.addObject("openid", openid);
			modelAndView.addObject("account", account);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	/**
	 * 跳转订单管理页面
	 * @param account
	 * @param openid
	 * @return
	 */
	@RequestMapping("/goCommentManage")
	public ModelAndView goCommentManage(Long storeId, String openid){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("commentManage.jsp");
		modelAndView.addObject("storeId", storeId);
		modelAndView.addObject("openid", openid);
		return modelAndView;
	}
	
	/**
	 * 获取所有菜系
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/getCuisineList")
	public ActionResult getCuisineList(){
		ActionResult result = new ActionResult();
		try {
			CuisineInfo entity = new CuisineInfo();
			entity.setState(1);
			entity.setDeleted(0);
			Object[] objs = {entity};
			String paramStr = HttpUtils.packParam(objs);
			String getData = PropertiesUtils.getProperty(StoreManageAction.class, "propurl", "diet").concat("manageCuisineInfo/findByCondition.action");
			String resultStr = HttpUtils.sendPost(getData, paramStr, 0);
			Map<String, Object> map =JsonUtils.json2Object(resultStr, HashMap.class, null);
			if (CollectionUtils.isNotEmpty(map)) {
				result.put("cuiList", map.get("list"));
			}else{
				result.put("cuiList", null);
			}
			result.setSuccess(true);
		} catch (Exception e) {
			logger.error("微信餐厅管理获取菜系失败："+e.getMessage(), e);
			e.printStackTrace();
		}
		return result;
	}
	
}
