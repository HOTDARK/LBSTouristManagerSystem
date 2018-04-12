package com.hd.tsa.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hd.tsa.entity.UserHarvestAddrList;
import com.hd.tsa.entity.UserInfo;
import com.hd.sfw.core.utils.HttpUtils;
import com.hd.sfw.core.utils.PropertiesUtils;
import com.hd.sfw.core.utils.json.JsonUtils;
import com.hd.sys.core.consts.FunLogConst;
import com.hd.sys.core.log.LogOpt;
import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.WxUserList.WxUser;
import com.soecode.wxtools.bean.WxUserList.WxUser.WxUserGet;

import net.sf.json.JSONObject;

/**
 * 用户中心控制类
 * @author LG
 *
 */
@Controller
@RequestMapping("/userCenter")
public class UserCenterAction extends UserBaseAction {

	// 实例化 统一业务API入口
		private IService iService = new WxService();
		
		@LogOpt(level = FunLogConst.LEVEL_3, desc = "跳转用户中心", parentDesc = "微信管理")
		@RequestMapping("/userCenterHome")
		public ModelAndView userCenterHome(HttpServletRequest request, HttpServletResponse response,
				HttpSession httpSession, String openid, String storeStatus) {
			ModelAndView view = new ModelAndView("userCenter.jsp");
			view.addObject("openid", openid);
			try {
				UserInfo userInfo=null;
				WxUser wxUser = iService.getUserInfoByOpenId(new WxUserGet(openid, WxConsts.LANG_CHINA));
				Map<String, Object> map = getUserInfo(request, response, httpSession, openid);
				if ((boolean) map.get(SESSION_MAP_USER_STATE)) {
					userInfo = JsonUtils.json2Object(((JSONObject) map.get(SESSION_MAP_USER)).toString(),
							UserInfo.class, "");
					view.addObject("wxUser", wxUser);
					view.addObject("userInfo", userInfo);
					if (storeStatus!=null && storeStatus!="") {
						view.addObject("storeStatus", storeStatus);
					}
				} else {
					view.setViewName("userBind.jsp");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return view;
		}
		
		
		@LogOpt(level = FunLogConst.LEVEL_3, desc = "跳转个人详情", parentDesc = "微信管理")
		@RequestMapping("/userCenterInfo")
		public ModelAndView userCenterInfo(HttpServletRequest request, HttpServletResponse response,
				HttpSession httpSession, String openid) {
			ModelAndView view = new ModelAndView("userCenterInfo.jsp");
			view.addObject("openid", openid);
			try {
				UserInfo userInfo=null;
				WxUser wxUser = iService.getUserInfoByOpenId(new WxUserGet(openid, WxConsts.LANG_CHINA));
				Map<String, Object> map = getUserInfo(request, response, httpSession, openid);
				if ((boolean) map.get(SESSION_MAP_USER_STATE)) {
					userInfo = JsonUtils.json2Object(((JSONObject) map.get(SESSION_MAP_USER)).toString(),
							UserInfo.class, "");
				} else {
					throw new Exception("获取用户信息异常");
				}
				view.addObject("wxUser", wxUser);
				view.addObject("userInfo", userInfo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return view;
		}
		
		@LogOpt(level = FunLogConst.LEVEL_3, desc = "跳转解绑", parentDesc = "微信管理")
		@RequestMapping("/unBind")
		public ModelAndView unBind(HttpServletRequest request, HttpServletResponse response,
				HttpSession httpSession, String openid) {
			ModelAndView view = new ModelAndView("unBind.jsp");
			view.addObject("openid", openid);
			try {
				UserInfo userInfo=null;
//				WxUser wxUser = iService.getUserInfoByOpenId(new WxUserGet(openid, WxConsts.LANG_CHINA));
				Map<String, Object> map = getUserInfo(request, response, httpSession, openid);
				if ((boolean) map.get(SESSION_MAP_USER_STATE)) {
					userInfo = JsonUtils.json2Object(((JSONObject) map.get(SESSION_MAP_USER)).toString(),
							UserInfo.class, "");
				} else {
					throw new Exception("获取用户信息异常");
				}
				view.addObject("userInfo", userInfo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return view;
		}
		
		
		@LogOpt(level = FunLogConst.LEVEL_3, desc = "跳转收货地址列表", parentDesc = "微信管理")
		@RequestMapping("/goAddressList")
		public ModelAndView goAddressList(String openid) {
			ModelAndView view = new ModelAndView("addressList.jsp");
			view.addObject("openid", openid);
			try {
				UserInfo userInfo=null;
				Map<String, Object> map = getUserInfo(null, null, null, openid);
				if ((boolean) map.get(SESSION_MAP_USER_STATE)) {
					userInfo = JsonUtils.json2Object(((JSONObject) map.get(SESSION_MAP_USER)).toString(),
							UserInfo.class, "");
					String getData = PropertiesUtils.getProperty(DietAction.class, "propurl", "user").concat("harvestAddr/obtainAddrs.action");
					String resultStr = HttpUtils.sendPost(getData, "xgh="+userInfo.getXgh(), 0);
					UserHarvestAddrList list=JsonUtils.json2Object(resultStr, UserHarvestAddrList.class, null);
					view.addObject("addList", list.getList());
				} else {
					view.setViewName("userBind.jsp");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return view;
		}
		
		
}
