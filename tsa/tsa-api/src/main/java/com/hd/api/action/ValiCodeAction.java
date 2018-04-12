package com.hd.api.action;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hd.api.server.variable.MessageEnum;
import com.hd.api.service.ValiCodeService;
import com.hd.api.validate.ValidateObject;
import com.hd.api.validate.ValidateParam;
import com.hd.sfw.core.model.BaseModel;

/**
 * 用户登录功能控制类 
 * @author somnuscy
 *
 */
@Controller
@RequestMapping("/valiCode")
public class ValiCodeAction {
	
	@Autowired
	private ValiCodeService valiCodeService;

	/**
	 * 获取手机验证码
	 * @param request
	 * @param response
	 * @param phone
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getValiCode",method={RequestMethod.POST})
	public BaseModel getValiCode(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="phone",defaultValue="",required=false) String phone) {
		// -->验证入参开始
		List<ValidateObject> params = new LinkedList<ValidateObject>();
		params.add(new ValidateObject(phone, "[手机号码]为空"));
		BaseModel msg = ValidateParam.validateParams(params);
		if (!MessageEnum.SUC.toString().equals(msg.getErrCode())) {
			return msg;
		}
		if (!ValidateParam.isMobileNo(phone)) {
			msg = new BaseModel();
			msg.setErrCode(MessageEnum.ERR_PARAMETER.toString());
			msg.setErrMsg("请输入正确的手机号");
			return msg;
		}
		// -->验证入参结束
		return valiCodeService.sendValiCode(phone);
	}
	
	/**
	 * 发送短信
	 * @param request
	 * @param response
	 * @param phone 接收手机号码，最多500个；多个手机号码，中间间隔用英文逗号。
	 * @param content 短信内容
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/sendSms",method={RequestMethod.POST})
	public BaseModel sendSms(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="phone",defaultValue="",required=false) String phone, @RequestParam(value="content",defaultValue="",required=false) String content) {
		// -->验证入参开始
		List<ValidateObject> params = new LinkedList<ValidateObject>();
		params.add(new ValidateObject(phone, "[接收手机号码]为空"));
		params.add(new ValidateObject(phone, "[短信内容]为空"));
		BaseModel msg = ValidateParam.validateParams(params);
		if (!MessageEnum.SUC.toString().equals(msg.getErrCode())) {
			return msg;
		}
		// -->验证入参结束
		msg = valiCodeService.sendSms(phone, content);
		return msg;
	}
	
}
