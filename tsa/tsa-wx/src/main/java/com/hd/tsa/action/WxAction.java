package com.hd.tsa.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hd.tsa.util.MessageHandler;
import com.hd.sfw.core.utils.PropertiesUtils;
import com.hd.sys.core.consts.FunLogConst;
import com.hd.sys.core.log.LogOpt;
import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.api.WxMessageRouter;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.WxMenu;
import com.soecode.wxtools.bean.WxMenu.WxMenuButton;
import com.soecode.wxtools.bean.WxUserList.WxUser;
import com.soecode.wxtools.bean.WxUserList.WxUser.WxUserGet;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutMessage;
import com.soecode.wxtools.bean.result.WxMenuResult;
import com.soecode.wxtools.bean.result.WxOAuth2AccessTokenResult;
import com.soecode.wxtools.exception.WxErrorException;
import com.soecode.wxtools.util.xml.XStreamTransformer;

/**
 * 微信控制类
 * 
 * @author LG
 *
 */
@Controller
@RequestMapping("/wx")
public class WxAction extends UserBaseAction{

	private static Logger logger = Logger.getLogger(WxAction.class);
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	// 实例化 统一业务API入口
	private IService iService = new WxService();

	/**
	 * 验证服务器有效性并处理微信服务器消息
	 * 
	 * @param request
	 * @param response
	 * @param httpSession
	 * @param id
	 * @return 
	 * @return
	 */
	@LogOpt(level = FunLogConst.LEVEL_3, desc = "处理微信服务器消息", parentDesc = "微信管理")
	@RequestMapping("/getMessage")
	@ResponseBody
	public void getMessage(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession,
			String signature, String timestamp, String nonce, String echostr) {
		PrintWriter out = null;
		response.setContentType("text/html; charset=UTF-8");
//		logger.info("\n消息接收器echostr:"+echostr+"\n");
		 if (echostr!=null && !"".equals(echostr)) {
			try {
				out = response.getWriter();
				request.setCharacterEncoding("UTF-8");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
//			if (iService.checkSignature(signature, timestamp, nonce, echostr)) {
//				System.out.println("echostr:"+echostr);
//				out.print(echostr);
//			}
			out.print(echostr);
		} else {
			WxMessageRouter router = new WxMessageRouter(iService);
			try {
				request.setCharacterEncoding("UTF-8");
				response.setCharacterEncoding("UTF-8");
				out = response.getWriter();
				// 微信服务器推送过来的是XML格式。
				WxXmlMessage wx = XStreamTransformer.fromXml(WxXmlMessage.class, request.getInputStream());
				// 添加规则；这里的规则是指所有消息都交给DemoHandler处理
				// 注意！！每一个规则，必须由end()或者next()结束。不然不会生效。
				// end()是指消息进入该规则后不再进入其他规则。 而next()是指消息进入了一个规则后，如果满足其他规则也能进入，处理。
				router.rule().handler(new MessageHandler()).end();
				// 把消息传递给路由器进行处理
				WxXmlOutMessage xmlOutMsg = router.route(wx);
				logger.info("返回消息："+xmlOutMsg.toXml()+"\n");
				if (xmlOutMsg != null) {
					out.print(xmlOutMsg.toXml());// 因为是明文，所以不用加密，直接返回给用户。
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				out.close();
			}
		}
		
		
	}

	
	/**
	 * 用户auth2.0认证
	 * @param request
	 * @param response
	 * @param httpSession
	 * @param code
	 * @return
	 */
	@LogOpt(level = FunLogConst.LEVEL_3, desc = "用户auth2.0认证", parentDesc = "微信管理")
	@RequestMapping("/auth")
	public ModelAndView auth(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession,
			String code,String state) {
		ModelAndView view = new ModelAndView("index.jsp");
		WxOAuth2AccessTokenResult result=null;
		try {
			result = iService.oauth2ToGetAccessToken(code);
			WxUser user = iService.oauth2ToGetUserInfo(result.getAccess_token(),
					new WxUserGet(result.getOpenid(), WxConsts.LANG_CHINA));
			view.addObject("openid", user.getOpenid());
			view.addObject("userInfo", user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}

	/**
	 * 创建菜单
	 * 
	 * @return
	 */
	@LogOpt(level = FunLogConst.LEVEL_3, desc = "创建菜单", parentDesc = "微信管理")
	@RequestMapping("/createMenu")
	public void createMenu(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) {
		WxMenu menu = new WxMenu();
		List<WxMenuButton> btnList = new ArrayList<>();
		// 设置CLICK类型的按钮1
		List<WxMenuButton> subList1 = new ArrayList<>();
		WxMenuButton btn1_1 = new WxMenuButton();
		btn1_1.setType(WxConsts.BUTTON_VIEW);
		btn1_1.setUrl("http://v.buy360.vip/m.php");
		btn1_1.setName("微闻西大");
//		WxMenuButton btn1_2 = new WxMenuButton();
//		btn1_2.setType(WxConsts.BUTTON_VIEW);
//		btn1_2.setUrl("http://www.baidu.com");
//		btn1_2.setName("子按钮3_2");
		subList1.add(btn1_1);
//		subList1.add(btn1_2);
		// 把子按钮列表设置进按钮3
		WxMenuButton btn1 = new WxMenuButton();
		btn1.setName("新闻政策");
		btn1.setSub_button(subList1);
		// 设置VIEW类型的按钮2
		WxMenuButton btn2 = new WxMenuButton();
		btn2.setType(WxConsts.BUTTON_VIEW);
		String oauthUrl = "";
		try {
			String domain = PropertiesUtils.getProperty(RepairAction.class, "wx", "wx.domain");
			oauthUrl = iService.oauth2buildAuthorizationUrl(domain+"/drsp-wx/wx/auth.action",
					WxConsts.OAUTH2_SCOPE_USER_INFO, "hdtech");

		} catch (WxErrorException e1) {
			e1.printStackTrace();
		}
		btn2.setUrl(oauthUrl);
		btn2.setName("西大后勤");
		// 设置含有子按钮的按钮3
		List<WxMenuButton> subList = new ArrayList<>();
		// 子按钮
		WxMenuButton btn3_1 = new WxMenuButton();
		btn3_1.setType(WxConsts.BUTTON_VIEW);
		btn3_1.setUrl("https://mp.weixin.qq.com/mp/homepage?__biz=MzIzMTAxODU5OQ==&hid=8&sn=e48a4bd012ab5f32573de9aae931ac82&scene=18#wechat_redirect");
		btn3_1.setName("西大心理");
		WxMenuButton btn3_2 = new WxMenuButton();
		btn3_2.setType(WxConsts.BUTTON_VIEW);
		btn3_2.setUrl("https://mp.weixin.qq.com/mp/homepage?__biz=MzA4MDE2NTczMg==&hid=7&sn=c68c882e13ab6ebc4c50c8b682ce588d&scene=18#wechat_redirect");
		btn3_2.setName("西大青创");
		subList.add(btn3_1);
		subList.add(btn3_2);
		// 把子按钮列表设置进按钮3
		WxMenuButton btn3 = new WxMenuButton();
		btn3.setName("友情链接");
		btn3.setSub_button(subList);
		// 将三个按钮设置进btnList
		btnList.add(btn1);
		btnList.add(btn2);
		btnList.add(btn3);
		// 设置进菜单类
		menu.setButton(btnList);
		// 调用API即可
		try {
			// 参数1--menu ，参数2--是否是个性化定制。如果是个性化菜单栏，需要设置MenuRule
			iService.createMenu(menu, false);
		} catch (WxErrorException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除菜单
	 * 
	 * @return
	 */
	@LogOpt(level = FunLogConst.LEVEL_3, desc = "删除菜单", parentDesc = "微信管理")
	@RequestMapping("/deleteMenu")
	public void deleteMenu(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) {
		try {
			String result = iService.deleteMenu();
			System.out.println(result);
		} catch (WxErrorException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取菜单
	 * 
	 * @return
	 */
	@LogOpt(level = FunLogConst.LEVEL_3, desc = "获取菜单", parentDesc = "微信管理")
	@RequestMapping("/getMenu")
	public void getMenu(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) {
		try {
			WxMenuResult result = iService.getMenu();
			System.out.println(result.toString());
		} catch (WxErrorException e) {
			e.printStackTrace();
		}
	}

	@LogOpt(level = FunLogConst.LEVEL_3, desc = "跳转页面", parentDesc = "微信管理")
	@RequestMapping("/jumpPage")
	public ModelAndView jumpPage(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession,
			String viewName,String openid,String idNum,Integer type) {
		if(viewName==null || "".equals(viewName)){
			viewName="index.jsp";
		}
		if(idNum==null || "".equals(idNum)){
			idNum="1";
		}
		ModelAndView view = new ModelAndView(viewName);
		String pageStr = PropertiesUtils.getProperty(this.getClass(), "common", "filterPage");
		String [] pages = pageStr.split(",");
		boolean b = true;
		for (int i = 0; i < pages.length; i++) {
			if (viewName.contains(pages[i])) {
				b = false;
				break;
			}
		}
		if (b) {
			Map<String,Object> userMap=getUserInfo(request, response,httpSession,openid);
			if (!(boolean) userMap.get(SESSION_MAP_USER_STATE)) {
				view.setViewName("userBind.jsp");
			}
		}
		view.addObject("idNum", idNum);
		view.addObject("openid", openid);
		view.addObject("type", type);
		return view;
	}
	/**
	 * 跳转首页
	 * @param request
	 * @param response
	 * @param httpSession
	 * @param openid
	 * @return
	 */
	@LogOpt(level = FunLogConst.LEVEL_3, desc = "跳转首页", parentDesc = "微信管理")
	@RequestMapping("/jumpIndex")
	public ModelAndView jumpIndex(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession,String openid) {
		ModelAndView view = new ModelAndView("index.jsp");
		view.addObject("openid", openid);
		return view;
	}
}
