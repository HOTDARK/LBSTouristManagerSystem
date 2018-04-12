package com.hd.api.validate;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.hd.api.server.variable.MessageEnum;
import com.hd.sfw.core.model.BaseModel;
import com.hd.sfw.core.utils.CollectionUtils;

/**
 * 字段验证公用类
 * @author somnuscy
 *
 */
public class ValidateParam {
	
	/**
	 * 验证字段是否为空(多个)
	 * 
	 * @param params
	 * @return
	 */
	public static BaseModel validateParams(List<ValidateObject> params) {
		BaseModel returnMsg = new BaseModel();
		returnMsg.setErrCode(MessageEnum.SUC.toString());
		returnMsg.setErrMsg("");
		if (CollectionUtils.isNotEmpty(params)) {
			for (Iterator<ValidateObject> iterator = params.iterator(); iterator.hasNext();) {
				ValidateObject validateObject = iterator.next();
				returnMsg = validateParam(validateObject.getParam(), validateObject.getMsg());
				if (!MessageEnum.SUC.toString().equals(returnMsg.getErrCode())) {
					return returnMsg;
				}
			}
		}
		return returnMsg;
	}
	
	/**
	 * 验证字段是否为空(单个)
	 * @param param：需要验证的字段。
	 * 目前支持：String,int,Integer,long,Long,ArrayList
	 * @param msg: 错误信息
	 * @return
	 */
	public static BaseModel validateParam(Object param, String msg) {
		BaseModel returnMsg = new BaseModel();
		returnMsg.setErrCode(MessageEnum.ERR_PARAMETER.toString());
		returnMsg.setErrMsg(msg);
		if (param != null) {
			if ("java.lang.Integer".equals(param.getClass().getName())) {
				if ((Integer) param != 0) {
					returnMsg.setErrCode(MessageEnum.SUC.toString());
					returnMsg.setErrMsg("");
				}
			} else if ("java.lang.String".equals(param.getClass().getName())) {
				if (StringUtils.isNotBlank(String.valueOf(param))) {
					returnMsg.setErrCode(MessageEnum.SUC.toString());
					returnMsg.setErrMsg("");
				}
			} else if ("java.lang.Long".equals(param.getClass().getName())) {
				if ((Long) param != 0) {
					returnMsg.setErrCode(MessageEnum.SUC.toString());
					returnMsg.setErrMsg("");
				}
			} else if ("java.util.ArrayList".equals(param.getClass().getName())) {
				if (CollectionUtils.isNotEmpty((List<?>) param)) {
					returnMsg.setErrCode(MessageEnum.SUC.toString());
					returnMsg.setErrMsg("");
				}
				// 入参对象为空判断：只要是input包下的对象，只要不满足前面判断、不为空。即通过验证
			} else if ("com.hd.api.entity".equals(param.getClass().getPackage().getName())) {
				returnMsg.setErrCode(MessageEnum.SUC.toString());
				returnMsg.setErrMsg("");
			}
		}
		return returnMsg;
	}
	
	/**
	 * 手机号验证
	 * 号段规则:http://www.jihaoba.com/tools/haoduan/
	 * 电信号段:133 153 177 173 180 181 189
	 * 联通号段:130 131 132 155 156 185 186 145 176
     * 移动号段:139 138 137 136 135 134 147 150 151 152 157 158 159 178 182 183 184 187 188
     * 虚拟运营商号段:170 171
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobileNo(String mobiles){
		Pattern p = Pattern.compile("^((13[0-9])|(14[5,7])|(15[^4,\\D])|(17[0-1,3,6-8])|(18[0-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}
	
}
