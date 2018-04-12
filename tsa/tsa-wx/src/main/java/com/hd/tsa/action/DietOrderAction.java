package com.hd.tsa.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hd.tsa.entity.CommentAttachment;
import com.hd.tsa.entity.CommentInfo;
import com.hd.tsa.entity.DietOrder;
import com.hd.tsa.entity.StoreInfo;
import com.hd.tsa.entity.UserHarvestAddr;
import com.hd.tsa.entity.UserInfo;
import com.hd.sfw.core.utils.HttpUtils;
import com.hd.sfw.core.utils.PropertiesUtils;
import com.hd.sfw.core.utils.json.JsonUtils;
import com.hd.sys.action.base.ActionResult;
import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.WxJsapiConfig;
import com.soecode.wxtools.exception.WxErrorException;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/dietOrder")
public class DietOrderAction extends UserBaseAction{
	
	private static Logger logger = Logger.getLogger(DietOrderAction.class);
	// 实例化 统一业务API入口
	private IService iService = new WxService();
	@ResponseBody
	@RequestMapping("/insertOrder")
	public ActionResult insertOrder(HttpServletRequest request, HttpServletResponse response,
			HttpSession httpSession, @RequestBody DietOrder entity){
		ActionResult result = new ActionResult();
		try {
			Map<String,Object> userMap=getUserInfo(request, response,httpSession,entity.getOpenid());
			if ((boolean) userMap.get(SESSION_MAP_USER_STATE)) {
				UserInfo userInfo=JsonUtils.json2Object(userMap.get(SESSION_MAP_USER).toString(), UserInfo.class, null);
				entity.setOrderUser(userInfo.getXgh());
				entity.setOrderPhone(userInfo.getSjhm());
				entity.setOrderDate(new Date());
				entity.setEvaluate(0);
				entity.setState(0);
				String listStr = JsonUtils.toJson(entity.getFoodList(), null, null, null);
				entity.setFoodList(null);
				Map<String, Object> umap = new HashMap<String, Object>();
				umap.put("listStr", listStr);
				Object[] objs = {entity,umap};
				String paramStr = HttpUtils.packParam(objs);
				String getData = PropertiesUtils.getProperty(DietOrderAction.class, "propurl", "diet").concat("wxDietOrder/createOrder.action");
				String resultStr = HttpUtils.sendPost(getData, paramStr, 0);
				String orderNum=JsonUtils.json2Object(resultStr, String.class, null);
				if (!StringUtils.isEmpty(orderNum)) {
					result.setSuccess(true);
					result.put("orderNum", orderNum);
				}else{
					result.setMessage("生成订单失败");
				}
			}else{
				result.setMessage("生成订单失败");
			}
		} catch (Exception e) {
			result.setMessage("生成订单失败");
			logger.error("生成订单失败："+e.getMessage(), e);
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 跳转支付页面
	 * @param openid
	 * @param storeId
	 * @param orderNum
	 * @return
	 */
	@RequestMapping("/goPayment")
	public ModelAndView forwardPayment(HttpServletRequest request, HttpServletResponse response,HttpSession httpSession,
			String openid, Long storeId, String orderNum, Long addressId){
		ModelAndView mav = new  ModelAndView("payment.jsp");
		try {
			Map<String,Object> userMap=getUserInfo(request, response,httpSession,openid);
			if ((boolean) userMap.get(SESSION_MAP_USER_STATE)) {
				UserInfo userInfo=JsonUtils.json2Object(userMap.get(SESSION_MAP_USER).toString(), UserInfo.class, null);
				mav.addObject("xgh", userInfo.getXgh());
				mav.addObject("openid", openid);
				mav.addObject("storeId", storeId);
				mav.addObject("addressId", addressId);
				mav.addObject("orderNum", orderNum);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	/**
	 * 跳转地址管理页面
	 * @param openid
	 * @param storeId
	 * @param orderNum
	 * @return
	 */
	@RequestMapping("/goAddList")
	public ModelAndView forwardAddList(HttpServletRequest request, HttpServletResponse response,HttpSession httpSession,String openid, Long storeId, String orderNum){
		ModelAndView mav = new  ModelAndView("address_list.jsp");
		Map<String,Object> userMap=getUserInfo(request, response,httpSession,openid);
		if ((boolean) userMap.get(SESSION_MAP_USER_STATE)) {
			UserInfo userInfo=JsonUtils.json2Object(userMap.get(SESSION_MAP_USER).toString(), UserInfo.class, null);
			mav.addObject("xgh", userInfo.getXgh());
			mav.addObject("openid", openid);
			mav.addObject("storeId", storeId);
			mav.addObject("orderNum", orderNum);
		}
		return mav;
	}
	
	/**
	 * 获取订单相关信息
	 * @param orderNum
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping("/getOrderInfo")
	public ActionResult getOrderInfo(String orderNum){
		ActionResult result = new ActionResult();
		try {
			String getData = PropertiesUtils.getProperty(DietOrderAction.class, "propurl", "diet").concat("wxDietOrder/findById.action");
			String resultStr = HttpUtils.sendPost(getData, "id="+orderNum, 0);
			Map<String, Object> map=JsonUtils.json2Object(resultStr, Map.class, null);
			if (!map.isEmpty()) {
				result.setSuccess(true);
				result.put("order", map.get("entity"));
				result.put("foodList", map.get("list"));
			}else{
				result.setMessage("获取订单详情失败");
			}
		} catch (Exception e) {
			result.setMessage("获取订单详情失败");
			e.printStackTrace();
			logger.error("获取订单详情失败："+e.getMessage(), e);
		}
		return result;
	}
	
	/**
	 * 获取店铺信息
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getStoreInfo")
	public ActionResult getStoreInfo(Long id){
		ActionResult result = new ActionResult();
		try {
			String getData = PropertiesUtils.getProperty(DietOrderAction.class, "propurl", "diet").concat("wxStoreInfo/findById.action");
			String resultStr = HttpUtils.sendPost(getData, "id="+id, 0);
			StoreInfo entity =JsonUtils.json2Object(resultStr, StoreInfo.class, null);
			if (entity!=null && entity.getId()!=null) {
				result.setSuccess(true);
				result.put("store", entity);
			}else{
				result.setMessage("获取订单店铺信息失败");
			}
		} catch (Exception e) {
			result.setMessage("获取订单店铺信息失败");
			e.printStackTrace();
			logger.error("获取订单店铺信息失败："+e.getMessage(), e);
		}
		return result;
	}
	
	/**
	 * 确认支付
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/confirmPayment")
	public ActionResult confirmPayment(DietOrder entity){
		ActionResult result = new ActionResult();
		try {
			String findOrderUrl = PropertiesUtils.getProperty(ReceiveGoodsAction.class, "propurl", "user").concat("harvestAddr/obtainAddrById.action");
			String address = HttpUtils.sendPost(findOrderUrl,"id=" + entity.getAddress(), 0);
			UserHarvestAddr userHarvestAddr = JsonUtils.json2Object(address, UserHarvestAddr.class, null);
			if (userHarvestAddr!=null) {
				entity.setAddress(userHarvestAddr.getAddrArea()+userHarvestAddr.getAddrDetail());
				entity.setReceiveName(userHarvestAddr.getAddrConsignee());
				entity.setOrderPhone(userHarvestAddr.getAddrContact());
				entity.setPayType(2);
				entity.setState(1);
				Object[] objs = {entity};
				String paramStr = HttpUtils.packParam(objs);
				String getData = PropertiesUtils.getProperty(DietOrderAction.class, "propurl", "diet").concat("wxDietOrder/updateOrder.action");
				String resultStr = HttpUtils.sendPost(getData, paramStr, 0);
				boolean rest = JsonUtils.json2Object(resultStr, boolean.class, null);
				result.setSuccess(rest);
			}
		} catch (Exception e) {
			logger.error("支付失败："+e.getMessage(), e);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 跳转支付成功页面
	 * @param orderNum
	 * @param openid
	 * @return
	 */
	@RequestMapping("/goSuccess")
	public ModelAndView goSuccess(String orderNum, String openid){
		ModelAndView modelAndView = new ModelAndView("payment_success.jsp");
		modelAndView.addObject("orderNum", orderNum);
		modelAndView.addObject("openid", openid);
		return modelAndView;
	}
	
	/**
	 * 跳转支付失败页面
	 * @param orderNum
	 * @param openid
	 * @param addressId
	 * @param storeId
	 * @return
	 */
	@RequestMapping("/goFailure")
	public ModelAndView goFailure(String orderNum, String openid, Long addressId, Long storeId){
		ModelAndView modelAndView = new ModelAndView("payment_failure.jsp");
		modelAndView.addObject("orderNum", orderNum);
		modelAndView.addObject("openid", openid);
		modelAndView.addObject("addressId", addressId);
		modelAndView.addObject("storeId", storeId);
		return modelAndView;
	}
	
	/**
	 * 跳转订单列表页面
	 * @param openid
	 * @return
	 */
	@RequestMapping("/goOrderList")
	public ModelAndView goOrderList(String openid, String flag){
		ModelAndView modelAndView = new ModelAndView("myorder.jsp");
		modelAndView.addObject("openid", openid);
		modelAndView.addObject("flag", flag);
		return modelAndView;
	}
	
	/**
	 * 获取我的订单列表
	 * @param request
	 * @param response
	 * @param httpSession
	 * @param openid
	 * @param pageNum
	 * @param timeType
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getMyOrder")
	@SuppressWarnings("unchecked")
	public ActionResult getMyOrder(HttpServletRequest request, HttpServletResponse response,HttpSession httpSession,
			String openid,Integer pageNum, Integer timeType){
		ActionResult result = new ActionResult();
		try {
			Map<String,Object> userMap=getUserInfo(request, response,httpSession,openid);
			if ((boolean) userMap.get(SESSION_MAP_USER_STATE)) {
				UserInfo userInfo=JsonUtils.json2Object(userMap.get(SESSION_MAP_USER).toString(), UserInfo.class, null);
				Map<String, Object> umap = new HashMap<String, Object>();
				umap.put("timeType", timeType);
				umap.put("pageNum", pageNum);
				DietOrder entity = new DietOrder();
				entity.setOrderUser(userInfo.getXgh());
				Object[] objs = {entity,umap};
				String paramStr = HttpUtils.packParam(objs);
				String getData = PropertiesUtils.getProperty(DietOrderAction.class, "propurl", "diet").concat("wxDietOrder/findByPage.action");
				String resultStr = HttpUtils.sendPost(getData, paramStr, 0);
				Map<String, Object> map = JsonUtils.json2Object(resultStr, HashMap.class, null);
				result.put("pageNums", map.get("pageNums"));
				result.put("list", map.get("list"));
				result.setSuccess(true);
			}else{
				result.setMessage("验证用户失败");
			}
		} catch (Exception e) {
			result.setMessage("获取我的订单失败");
			logger.error("获取我的订单失败："+e.getMessage(), e);
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 跳转订单详情页面
	 * @param openid
	 * @param id
	 * @return
	 */
	@RequestMapping("/goOrderdetails")
	public ModelAndView goOrderdetails(String openid, Long orderNum, Long storeId){
		ModelAndView modelAndView = new ModelAndView("order_details.jsp");
		modelAndView.addObject("openid", openid);
		modelAndView.addObject("orderNum", orderNum);
		modelAndView.addObject("storeId", storeId);
		return modelAndView;
	}
	
	/**
	 * 跳转订单评价页面
	 * @param openid
	 * @param id
	 * @return
	 */
	@RequestMapping("/goOrderEvaluate")
	public ModelAndView goOrderEvaluate(String openid, String id,Long storeId){
		ModelAndView modelAndView = new ModelAndView("order_evaluate.jsp");
		modelAndView.addObject("openid", openid);
		List<String> jsApiList = new ArrayList<>();
		// 需要用到哪些JS SDK API 就设置哪些
		jsApiList.add("chooseImage");// 拍照或从手机相册中选图接口
		jsApiList.add("uploadImage");// 上传图片接口
		String domain = PropertiesUtils.getProperty(RepairAction.class, "wx", "wx.domain");
		WxJsapiConfig config=null;
		try {
			config = iService.createJsapiConfig(
					domain+"/drsp-wx/dietOrder/goOrderEvaluate.action?id="+id+"&storeId="+storeId+"&openid=" + openid,
					jsApiList);
		} catch (WxErrorException e) {
			e.printStackTrace();
		}
		config.setAppid(PropertiesUtils.getProperty(RepairAction.class, "wx", "wx.appId"));
		
		modelAndView.addObject("config", config);
		modelAndView.addObject("id", id);
		modelAndView.addObject("storeId", storeId);
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping("/insertComment")
	public ActionResult insertComment(HttpServletRequest request, HttpServletResponse response,HttpSession httpSession,
			String openid,CommentInfo commentInfo,String filePaths,String oldFileNames,String fileNames,
			String fileSizes,String fileExtensions){
		ActionResult result = new ActionResult();
		try {
			Map<String,Object> userMap = getUserInfo(request, response, httpSession, openid);
			if ((boolean) userMap.get(SESSION_MAP_USER_STATE)) {
				UserInfo userInfo=JsonUtils.json2Object(userMap.get(SESSION_MAP_USER).toString(), UserInfo.class, null);
				commentInfo.setUser(userInfo.getXgh());
				commentInfo.setUserName(userInfo.getXm());
				commentInfo.setUserPhoto(userInfo.getYhts());
				commentInfo.setDate(new Date());
				commentInfo.setPictureFlag(filePaths==""?0:1);
				commentInfo.setDeleteFlag(0);
				commentInfo.setReplayFlag(0);
				List<CommentAttachment> list=new ArrayList<>();
				JSONArray jsonObject=null;
				CommentAttachment commentAttachment=null;
				if(!"".equals(filePaths)){
					String[] filePathsArr=filePaths.split(",");
					String[] oldFileNamesArr=oldFileNames.split(",");
					String[] fileNamesArr=fileNames.split(",");
					String[] fileSizesArr=fileSizes.split(",");
					String[] fileExtensionsArr=fileExtensions.split(",");
					//Date date=new Date();
					for(int i=0;i<filePathsArr.length;i++){
						commentAttachment=new CommentAttachment();
						commentAttachment.setFilePath(filePathsArr[i]);
						commentAttachment.setOldFileName(oldFileNamesArr[i]);
						commentAttachment.setFileName(fileNamesArr[i]);
						commentAttachment.setSize(Long.parseLong(fileSizesArr[i]));
						commentAttachment.setFileSuffix(fileExtensionsArr[i]);
						//commentAttachment.setCreateDate(date);
						list.add(commentAttachment);
					}
					jsonObject=JSONArray.fromObject(list);
					
				}
				String listStr="";
				if(jsonObject!=null){
					listStr=jsonObject.toString();
				}
				Object[] objs={commentInfo};
				String params=HttpUtils.packParam(objs);
				params+="&listStr="+listStr;
				String insertCommentUrl = PropertiesUtils.getProperty(ReceiveGoodsAction.class, "propurl", "diet").concat("wxCommentInfo/saveEvaluateOrder.action");
				String address = HttpUtils.sendPost(insertCommentUrl,params, 0);
				result.setSuccess(Boolean.parseBoolean(address));
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("新增评价失败："+e.getMessage(), e);
		}
		return result;
	}

	/**
	 * 再来一份
	 * @param orderNum
	 * @param storeId
	 * @param openid
	 * @return
	 */
	@RequestMapping("/onceAgain")
	public ModelAndView onceAgain(HttpServletResponse response,String orderNum, String storeId, String openid){
		ModelAndView mav = new  ModelAndView("payment.jsp");
		try {
			String getData = PropertiesUtils.getProperty(DietOrderAction.class, "propurl", "diet").concat("wxDietOrder/createOnce.action");
			String resultStr = HttpUtils.sendPost(getData, "orderNum="+orderNum, 0);
			String newOrderNum = JsonUtils.json2Object(resultStr, String.class, null);
			if (newOrderNum!=null) {
				mav.addObject("openid", openid);
				mav.addObject("storeId", storeId);
				mav.addObject("orderNum", newOrderNum);
			}else{
				response.sendRedirect("goOrderList.action?flag=failure&openid="+openid);
			}
		} catch (Exception e) {
			logger.error("再来一份失败："+e.getMessage(), e);
			e.printStackTrace();
		}
		return mav;
	}
}
