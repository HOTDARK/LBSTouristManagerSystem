package com.hd.tsa.util;

import java.util.Map;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.bean.WxUserList.WxUser;
import com.soecode.wxtools.bean.WxUserList.WxUser.WxUserGet;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutMessage;
import com.soecode.wxtools.exception.WxErrorException;

public class MessageHandler implements com.soecode.wxtools.api.WxMessageHandler {

	@Override
	public WxXmlOutMessage handle(WxXmlMessage wxMessage, Map<String, Object> context, IService iService)
			throws WxErrorException {
		WxXmlOutMessage xmlOutMsg=null;
		try {
			 WxUser user = iService.getUserInfoByOpenId(new
			 WxUserGet(wxMessage.getFromUserName(), WxConsts.LANG_CHINA));
			// System.out.println("用户信息："+user.toString());
//			if ("btn1_key".equals(wxMessage.getEventKey())) {
//				System.out.println("按了下按钮");
//				String oauthUrl = iService.oauth2buildAuthorizationUrl("http://lg.ngrok.veelove.cn/drsp-wx/wx/auth.action",WxConsts.OAUTH2_SCOPE_USER_INFO, "hdtech");
//				xmlOutMsg = WxXmlOutMessage.TEXT().content("<a href='"+oauthUrl+"'>auth2.0</a>").toUser(wxMessage.getFromUserName())
//						.fromUser(wxMessage.getToUserName()).build();
//			}
//			if(wxMessage.getEvent()==WxConsts.EVT_SUBSCRIBE){
//				xmlOutMsg = WxXmlOutMessage.TEXT().content("欢迎关注	").toUser(wxMessage.getFromUserName())
//						.fromUser(wxMessage.getToUserName()).build();
//			}
				xmlOutMsg = WxXmlOutMessage.TEXT().content("欢迎您，"+user.getNickname()).toUser(wxMessage.getFromUserName())
						.fromUser(wxMessage.getToUserName()).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xmlOutMsg;
	}

}
