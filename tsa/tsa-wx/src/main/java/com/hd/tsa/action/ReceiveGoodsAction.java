package com.hd.tsa.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hd.api.server.variable.MessageEnum;
import com.hd.tsa.entity.UserHarvestAddr;
import com.hd.tsa.entity.UserHarvestAddrList;
import com.hd.tsa.entity.UserInfo;
import com.hd.sfw.core.model.BaseModel;
import com.hd.sfw.core.utils.CollectionUtils;
import com.hd.sfw.core.utils.HttpUtils;
import com.hd.sfw.core.utils.PropertiesUtils;
import com.hd.sfw.core.utils.json.JsonUtils;
import com.hd.sys.action.base.ActionResult;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/receiveGoods")
public class ReceiveGoodsAction extends UserBaseAction{

	/**
	 * 获取收货地址列表
	 * @param request
	 * @param response
	 * @param httpSession
	 * @param sessionId
	 * @param pageNum
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findReceiveGoodsList")
	public ActionResult findReceiveGoodsList(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession, String openid){
		ActionResult actionResult = new ActionResult();
		Map<String, Object> map = getUserInfo(request, response, httpSession, openid);
		try {
			if(CollectionUtils.isNotEmpty(map) && (boolean) map.get(SESSION_MAP_USER_STATE)){
				UserInfo user = JsonUtils.json2Object(((JSONObject) map.get(SESSION_MAP_USER)).toString(), UserInfo.class, "");
				String findOrderUrl = PropertiesUtils.getProperty(ReceiveGoodsAction.class, "propurl", "user").concat("harvestAddr/obtainAddrs.action");
				String result = HttpUtils.sendPost(findOrderUrl,"xgh=" + user.getXgh(), 0);
				UserHarvestAddrList addrResult = JsonUtils.json2Object(result, UserHarvestAddrList.class, null);
				if(addrResult != null && addrResult.getErrCode().equals(MessageEnum.SUC.toString())){
					actionResult.setSuccess(true);
					actionResult.put("loginFlag", true);
					actionResult.put("callPort", true);
					actionResult.put("list", addrResult.getList());
				}else{
					actionResult.setSuccess(true);
					actionResult.put("loginFlag", true);
					actionResult.put("callPort", false);
				}
			}else{
				actionResult.setSuccess(true);
				actionResult.put("loginFlag", false);
				actionResult.setMessage("wx/jumpPage.action?viewName=userBind.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return actionResult;
	}
	
	/**
	 * 跳转编辑收货地址页面
	 * @param request
	 * @param response
	 * @param httpSession
	 * @param sessionId
	 * @param id
	 * @return
	 */
	@RequestMapping("/forwardEditAddr")
	public ModelAndView forwardEditAddr(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession, String openid, Long id){
		ModelAndView model = new ModelAndView();
		model.addObject("openid", openid);
		try {
			Map<String, Object> map = getUserInfo(request, response, httpSession, openid);
			if(map != null && (boolean)map.get(SESSION_MAP_USER_STATE)){
				UserInfo user = JsonUtils.json2Object(((JSONObject) map.get(SESSION_MAP_USER)).toString(), UserInfo.class, "");
				model.addObject("user", user);
			}
			if(id != null){
				String findOrderUrl = PropertiesUtils.getProperty(ReceiveGoodsAction.class, "propurl", "user").concat("harvestAddr/obtainAddrById.action");
				String result = HttpUtils.sendPost(findOrderUrl,"id=" + id, 0);
				UserHarvestAddr entity = JsonUtils.json2Object(result, UserHarvestAddr.class, null);
				model.addObject("entity", entity);
				model.addObject("type", 2);
			} else {
				model.addObject("type", 1);
			}
			model.setViewName("addressInfo.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	/**
	 * 根据id获取收货地址
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getUserAddr")
	public UserHarvestAddr getUserHarvestAddr(Long id){
		UserHarvestAddr entity = new UserHarvestAddr();
		try {
			String findOrderUrl = PropertiesUtils.getProperty(ReceiveGoodsAction.class, "propurl", "user").concat("harvestAddr/obtainAddrById.action");
			String result = HttpUtils.sendPost(findOrderUrl,"id=" + id, 0);
			entity = JsonUtils.json2Object(result, UserHarvestAddr.class, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}
	
	/**
	 * 保存收货地址
	 * @param request
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveReceiveGoods")
	public boolean saveReceiveGoods(HttpServletRequest request, UserHarvestAddr entity){
		try {
			String findOrderUrl = PropertiesUtils.getProperty(ReceiveGoodsAction.class, "propurl", "user").concat("harvestAddr/addAddr.action");
			Object[] objs = {entity};
			String paramStr = HttpUtils.packParam(objs);
			String result = HttpUtils.sendPost(findOrderUrl, paramStr, 0);
			BaseModel model = JsonUtils.json2Object(result, UserHarvestAddr.class, null);
			if(model != null && model.getErrCode().equals(MessageEnum.SUC.toString())){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 修改收货地址
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateReceiveGoods")
	public boolean updateReceiveGoods(UserHarvestAddr entity){
		try {
			String findOrderUrl = PropertiesUtils.getProperty(ReceiveGoodsAction.class, "propurl", "user").concat("harvestAddr/modifyAddr.action");
			Object[] objs = {entity};
			String paramStr = HttpUtils.packParam(objs);
			String result = HttpUtils.sendPost(findOrderUrl, paramStr, 0);
			BaseModel model = JsonUtils.json2Object(result, UserHarvestAddr.class, null);
			if(model != null && model.getErrCode().equals(MessageEnum.SUC.toString())){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 设置默认地址
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/setDefault")
	public boolean setDefault(Long id){
		String findOrderUrl = PropertiesUtils.getProperty(ReceiveGoodsAction.class, "propurl", "user").concat("harvestAddr/obtainAddrById.action");
		String result = HttpUtils.sendPost(findOrderUrl,"id=" + id, 0);
		UserHarvestAddr entity = JsonUtils.json2Object(result, UserHarvestAddr.class, null);
		entity.setAddrDefault(1);
		findOrderUrl = PropertiesUtils.getProperty(ReceiveGoodsAction.class, "propurl", "user").concat("harvestAddr/modifyAddr.action");
		Object[] objs = {entity};
		String paramStr = HttpUtils.packParam(objs);
		result = HttpUtils.sendPost(findOrderUrl, paramStr, 0);
		BaseModel model = JsonUtils.json2Object(result, UserHarvestAddr.class, null);
		if(model != null && model.getErrCode().equals(MessageEnum.SUC.toString())){
			return true;
		}
		return false;
	}
	
	/**
	 * 删除收货地址
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteReceiveGoods")
	public boolean deleteReceiveGoods(Long id){
		try {
			String findOrderUrl = PropertiesUtils.getProperty(ReceiveGoodsAction.class, "propurl", "user").concat("harvestAddr/deleteAddr.action");
			String result = HttpUtils.sendPost(findOrderUrl, "id=" + id, 0);
			BaseModel model = JsonUtils.json2Object(result, UserHarvestAddr.class, null);
			if(model != null && model.getErrCode().equals(MessageEnum.SUC.toString())){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
