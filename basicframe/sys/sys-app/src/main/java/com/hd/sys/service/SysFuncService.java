package com.hd.sys.service;

import java.util.List;
import java.util.Map;

import com.hd.sys.entity.SysFunc;
import com.hd.sys.service.base.BaseService;

/**
 * <p>类名：SysFuncService </p>
 * <p>描述：系统功能业务层接口类 </p>
 * <p>作者：somnuscy </p>
 */
public interface SysFuncService extends BaseService<SysFunc, Long> {

	/**
	 * 根据权限获取功能树
	 * @return 功能对象集合
	 */
	List<SysFunc> getPermFunctionTree(SysFunc func) throws Exception;
	/**
	 * 激活(激活自己及其上级)、冻结（冻结自己及其下级）
	 * @param functionId state
	 * @return
	 */
	void dealSysFunctionState(SysFunc sysFunction,String state) throws Exception;
	/**
	 * 根据功能实体参数查询对应的功能ID
	 * @param sysFunction 功能实体
	 * @return 返回对应的功能ID,如果没有则返回null
	 * @throws Exception
	 */
	Long getFuncIdByCode(SysFunc sysFunction) throws Exception;
	
	/**
	 * 查询系统所有图标
	 * @return 返回所有图标Map
	 * @throws Exception
	 */
	List<Map<String, String>> getAllIcon() throws Exception;
//	List<SysFunc> getPermFunctionTreeByType(SysFunc func);
//	/**
//	 * 通过functionId查询关联functionIds
//	 * @param functionId
//	 * @return
//	 */
//	String queryFunctionIds(String functionIds)throws Exception;
	/**
	 * 根据功能编码模糊匹配功能数据
	 * @param funcCode
	 * @return
	 * @throws Exception
	 */
	public List<SysFunc> queryByFuncCode(String funcCode) throws Exception;
}
