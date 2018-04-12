package com.hd.api.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.hd.api.entity.SmsRes;
import com.hd.api.entity.ValiCode;
import com.hd.api.server.variable.MessageEnum;
import com.hd.api.service.ValiCodeService;
import com.hd.sfw.core.model.BaseModel;
import com.hd.sfw.core.utils.HttpUtils;
import com.hd.sfw.core.utils.Md5Utils;
import com.hd.sfw.core.utils.PropertiesUtils;
import com.hd.sfw.core.utils.json.JsonUtils;

@Service
public class ValiCodeServiceImpl implements ValiCodeService {
	
	private Logger logger = Logger.getLogger(this.getClass());
	private static int CLEARINTERVAL = -5;
	private static int SIZETHRESHHOLD = 500;
	private Date lastClearTime = new Date();
	private static Map<String,ValiCode>  map = new HashMap<String,ValiCode>();
	
	@Override
	public BaseModel sendSms(String phone, String content) {
		// 调用发送短信接口
		String account = PropertiesUtils.getProperty(this.getClass(), "common", "sms_account");
		String token = Md5Utils.md5Upper(PropertiesUtils.getProperty(this.getClass(), "common", "sms_token"));
		String extcode = PropertiesUtils.getProperty(this.getClass(), "common", "sms_extcode");
		String param = String.format("account=%s&access_token=%s&receiver=%s&smscontent=%s&extcode=%s", account, token, phone, content, extcode);
		String result = HttpUtils.sendPost(PropertiesUtils.getProperty(this.getClass(), "common", "sms_url"), param, 0);
		logger.info("发送短信接口返回：" + result);
		SmsRes res = JsonUtils.json2Object(result, SmsRes.class, null);
		BaseModel msg = new BaseModel();
		if (res != null) {
			if ("0".equals(res.getRes_code())) {
				msg.setErrCode(MessageEnum.SUC.toString());
			} else {
				msg.setErrCode(MessageEnum.ERR.toString());
			}
			msg.setErrMsg(res.getRes_message());
		} else {
			msg.setErrCode(MessageEnum.ERR.toString());
			msg.setErrMsg("调用短信接口失败");
		}
		return msg;
	}
	
	@Override
	public BaseModel sendValiCode(String phone) {
		ValiCode valCode = obtainValiCode(phone);
		// 调用短信接口，发送到指定手机。
		return sendSms(phone, String.format(PropertiesUtils.getProperty(this.getClass(), "common", "sms_format"), valCode.getValiCode()));
	}

	@Override
	public boolean compareValiCode(String phone, String valiCode) {
		logger.info("---------(二)用户输入随机验证码:手机号码："+phone+"-----------验证码："+valiCode);
		ValiCode vc = getValCode(phone);
		if(vc!=null&&vc.getValiCode() != null){
			logger.info("---------(三)从内存中获取系统产生随机验证码:手机号码："+vc.getMobile()+"-----------验证码："+vc.getValiCode());
			boolean ret = vc.getValiCode().equals(valiCode);
			//if validated ok, remove it;
			if (ret) removeCode(phone);
			return ret;
		}
		return false;
	}
	
	@Override
	public ValiCode obtainValiCode(String phone) {
		//clear the expired valcodes
		triggerClear();
		int code = (int)((Math.random()*9+1)*100000);
		ValiCode vc = new ValiCode(phone, String.valueOf(code));
		//add the new ValCode to pool
		addValCode(vc);
		System.out.println("---------手机号码："+phone+"-----------验证码："+code);
		logger.info("---------(一)系统产生随机验证码:手机号码："+phone+"-----------验证码："+code);
		return vc;
	}

	/**
	 * trigger the clear operation under 2 conditions:
	 * 1. beyond CLEARINTERVAL
	 * 2. beyond SIZETHRESHOLD	
	 */
	private void triggerClear(){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, CLEARINTERVAL);//分钟设为0
		Date date = calendar.getTime();
		if (date.after(lastClearTime)){
			clearExpCodes();
			lastClearTime = new Date();
		} else{
			if (getSize() > SIZETHRESHHOLD)
				clearExpCodes();
		}

	}
	
	private void clearExpCodes(){
		Iterator<Entry<String,ValiCode>> itr = map.entrySet().iterator();
		while (itr.hasNext()){
			Entry<String,ValiCode> item = itr.next();
			if (item.getValue().isExpired()) 
				removeCode(item.getKey());
		}
	}
	
	private ValiCode getValCode(String mobile){
		return map.get(mobile);
	}
	
	private void removeCode(String mobile){
		logger.info("---------(四)从内存中清除系统产生随机验证码:手机号码："+mobile);
		map.remove(mobile);
	}
	
	private void addValCode(ValiCode vc){
	    map.put(vc.getMobile(), vc);
	}
	
	private int getSize(){
	    return map.size();
	}

}
