package com.hd.tsa.util;

import com.soecode.wxtools.api.WxMessageMatcher;
import com.soecode.wxtools.bean.WxXmlMessage;

public class MessageMatcher implements WxMessageMatcher {

	@Override
	public boolean match(WxXmlMessage message) {
		if (message.getContent().equals("Matcher")) {
			
			return true;
		}
		return false;
	}

}
