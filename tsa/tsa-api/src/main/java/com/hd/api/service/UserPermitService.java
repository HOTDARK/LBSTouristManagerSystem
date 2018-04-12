package com.hd.api.service;

import java.util.List;

import com.hd.api.entity.UserBackRelation;
import com.hd.api.entity.vo.UserBackRelationList;
import com.hd.api.server.service.base.BaseWebService;
import com.hd.sfw.core.model.BaseModel;

/**
 * 用户绑定后台关系
 * @author somnuscy
 *
 */
public interface UserPermitService extends BaseWebService<UserBackRelation, UserBackRelationList, Long> {
	
	/**
	 * 根据前端用户、后台用户绑定后台用户权限
	 * @param openId
	 * @param backAccount
	 * @param backType
	 * @return
	 * @throws Exception
	 */
	public BaseModel optionBackPermit(String openId, String backAccount, String backType) throws RuntimeException;
	
	/**
	 * 根据openId获取用户后台绑定权限
	 * @param openId
	 * @param backType
	 * @return
	 * @throws Exception
	 */
	public UserBackRelation obtainBackPermit(String openId, String backType) throws Exception;
	
	/**
	 * 按后台工号、后台类型查询绑定用户关系
	 * @param backAccount
	 * @param backType
	 * @return
	 * @throws Exception
	 */
	public UserBackRelation findByBackAccount(String backAccount, String backType);
	
	/**
	 * 根据后台用户列表、后台权限列表修改前端用户绑定后台权限(当后台修改用户角色或往角色下添加人员时调用)
	 * @param relations
	 * @return
	 * @throws RuntimeException 控制事务
	 */
	public BaseModel updatePermit(List<UserBackRelation> relations) throws RuntimeException;
	
}
