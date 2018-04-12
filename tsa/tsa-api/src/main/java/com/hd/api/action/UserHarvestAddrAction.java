package com.hd.api.action;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hd.api.entity.UserHarvestAddr;
import com.hd.api.entity.UserInfo;
import com.hd.api.entity.vo.UserHarvestAddrList;
import com.hd.api.server.variable.MessageEnum;
import com.hd.api.service.UserHarvestAddrService;
import com.hd.api.service.UserInfoService;
import com.hd.api.validate.ValidateObject;
import com.hd.api.validate.ValidateParam;
import com.hd.sfw.core.model.BaseModel;

/**
 * 用户收货地址功能控制类 
 * @author somnuscy
 *
 */
@Controller
@RequestMapping("/harvestAddr")
public class UserHarvestAddrAction {
	
	@Autowired
	private UserHarvestAddrService userHarvestAddrService;
	@Autowired
	private UserInfoService userInfoService;

	/**
	 * 根据学工号查询所有的收货地址
	 * @param request
	 * @param response
	 * @param xgh
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/obtainAddrs",method={RequestMethod.POST})
	public UserHarvestAddrList obtainAddrs(HttpServletRequest request, HttpServletResponse response, String xgh) {
		UserHarvestAddrList list = new UserHarvestAddrList();
		// -->验证入参开始
		List<ValidateObject> params = new LinkedList<ValidateObject>();
		params.add(new ValidateObject(xgh, "[学工号]为空"));
		BaseModel msg = ValidateParam.validateParams(params);
		if (!MessageEnum.SUC.toString().equals(msg.getErrCode())) {
			list.setErrCode(msg.getErrCode());
			list.setErrMsg(msg.getErrMsg());
			return list;
		}
		// -->验证入参结束
		UserHarvestAddr entity = new UserHarvestAddr();
		entity.setXgh(xgh);
		try {
			list = userHarvestAddrService.findByCondition(entity);
		} catch (Exception e) {
			list.setErrCode(MessageEnum.ERR.toString());
			list.setErrMsg("获取收货地址出错："+e.getMessage());
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 添加收货地址
	 * @param request
	 * @param response
	 * @param harvestAddr
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addAddr",method={RequestMethod.POST})
	public BaseModel addAddr(HttpServletRequest request, HttpServletResponse response, UserHarvestAddr harvestAddr) {
		// -->验证入参开始
		List<ValidateObject> params = new LinkedList<ValidateObject>();
		params.add(new ValidateObject(harvestAddr, "[收货地址信息]为空"));
		BaseModel msg = ValidateParam.validateParams(params);
		if (!MessageEnum.SUC.toString().equals(msg.getErrCode())) {
			return msg;
		}
		params = new LinkedList<ValidateObject>();
		params.add(new ValidateObject(harvestAddr.getXgh(), "收货地址信息[学工号]为空"));
		params.add(new ValidateObject(harvestAddr.getAddrConsignee(), "收货地址信息[收货人]为空"));
		params.add(new ValidateObject(harvestAddr.getAddrContact(), "收货地址信息[联系方式]为空"));
		params.add(new ValidateObject(harvestAddr.getAddrArea(), "收货地址信息[所在地区]为空"));
		params.add(new ValidateObject(harvestAddr.getAddrDetail(), "收货地址信息[详细地址]为空"));
		msg = ValidateParam.validateParams(params);
		if (!MessageEnum.SUC.toString().equals(msg.getErrCode())) {
			return msg;
		}
		// -->验证入参结束
		try {
			UserInfo userInfo = userInfoService.findById(harvestAddr.getXgh());
			if (userInfo == null || userInfo.getXgh() == null) {
				msg.setErrCode(MessageEnum.ERR.toString());
				msg.setErrMsg("用户[学工号]不存在!");
				return msg;
			}
			if (harvestAddr.getAddrDefault()==null) {
				harvestAddr.setAddrDefault(0);
			} else if (harvestAddr.getAddrDefault() == 1) {
				userHarvestAddrService.updateByXgh(harvestAddr.getXgh());
			}
			return userHarvestAddrService.save(harvestAddr);
		} catch (Exception e) {
			msg.setErrCode(MessageEnum.ERR.toString());
			msg.setErrMsg("添加收货地址出错："+e.getMessage());
			e.printStackTrace();
		}
		return msg;
	}
	
	/**
	 * 修改收货地址
	 * @param request
	 * @param response
	 * @param harvestAddr
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/modifyAddr",method={RequestMethod.POST})
	public BaseModel modifyAddr(HttpServletRequest request, HttpServletResponse response, UserHarvestAddr harvestAddr) {
		// -->验证入参开始
		List<ValidateObject> params = new LinkedList<ValidateObject>();
		params.add(new ValidateObject(harvestAddr, "[收货地址信息]为空"));
		BaseModel msg = ValidateParam.validateParams(params);
		if (!MessageEnum.SUC.toString().equals(msg.getErrCode())) {
			return msg;
		}
		params = new LinkedList<ValidateObject>();
		params.add(new ValidateObject(harvestAddr.getId(), "收货地址信息[主键ID]为空"));
		params.add(new ValidateObject(harvestAddr.getXgh(), "收货地址信息[学工号]为空"));
		params.add(new ValidateObject(harvestAddr.getAddrConsignee(), "收货地址信息[收货人]为空"));
		params.add(new ValidateObject(harvestAddr.getAddrContact(), "收货地址信息[联系方式]为空"));
		params.add(new ValidateObject(harvestAddr.getAddrArea(), "收货地址信息[所在地区]为空"));
		params.add(new ValidateObject(harvestAddr.getAddrDetail(), "收货地址信息[详细地址]为空"));
		msg = ValidateParam.validateParams(params);
		if (!MessageEnum.SUC.toString().equals(msg.getErrCode())) {
			return msg;
		}
		// -->验证入参结束
		try {
			if (harvestAddr.getAddrDefault()==null) {
				harvestAddr.setAddrDefault(0);
			} else if (harvestAddr.getAddrDefault() == 1) {
				userHarvestAddrService.updateByXgh(harvestAddr.getXgh());
			}
			harvestAddr.setXgh(null);
			return userHarvestAddrService.updateById(harvestAddr);
		} catch (Exception e) {
			msg.setErrCode(MessageEnum.ERR.toString());
			msg.setErrMsg("修改收货地址出错："+e.getMessage());
			e.printStackTrace();
		}
		return msg;
	}
	
	/**
	 * 删除收货地址
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/deleteAddr",method={RequestMethod.POST})
	public BaseModel deleteAddr(HttpServletRequest request, HttpServletResponse response, Long id) {
		// -->验证入参开始
		List<ValidateObject> params = new LinkedList<ValidateObject>();
		params.add(new ValidateObject(id, "[收货地址主键ID]为空"));
		BaseModel msg = ValidateParam.validateParams(params);
		if (!MessageEnum.SUC.toString().equals(msg.getErrCode())) {
			return msg;
		}
		// -->验证入参结束
		try {
			return userHarvestAddrService.deleteById(id);
		} catch (Exception e) {
			msg.setErrCode(MessageEnum.ERR.toString());
			msg.setErrMsg("删除收货地址出错："+e.getMessage());
			e.printStackTrace();
		}
		return msg;
	}
	
	/**
	 * 根据ID查询收货地址
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/obtainAddrById",method={RequestMethod.POST})
	public UserHarvestAddr obtainAddrById(HttpServletRequest request, HttpServletResponse response, Long id) {
		UserHarvestAddr addr = new UserHarvestAddr();
		// -->验证入参开始
		List<ValidateObject> params = new LinkedList<ValidateObject>();
		params.add(new ValidateObject(id, "[主键ID]为空"));
		BaseModel msg = ValidateParam.validateParams(params);
		if (!MessageEnum.SUC.toString().equals(msg.getErrCode())) {
			addr.setErrCode(msg.getErrCode());
			addr.setErrMsg(msg.getErrMsg());
			return addr;
		}
		// -->验证入参结束
		try {
			addr = userHarvestAddrService.findById(id);
		} catch (Exception e) {
			addr.setErrCode(MessageEnum.ERR.toString());
			addr.setErrMsg("获取收货地址出错："+e.getMessage());
			e.printStackTrace();
		}
		return addr;
	}
	
}
