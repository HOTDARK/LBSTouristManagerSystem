package com.hd.sys.dao;

import java.util.List;
import java.util.Map;

import com.hd.sfw.dao.mybatis.BaseMapper;
import com.hd.sys.entity.SysFunc;

/**
 * 
 * @author somnuscy
 *
 */
public interface SysFuncMapper extends BaseMapper<SysFunc, Long> {
	/**
	 * 根据权限获取功能树
	 * 
	 * @return 功能对象集合
	 */
	List<SysFunc> getPermFunctionTree(SysFunc func) throws Exception;
	/**
	 * 根据功能实体参数查询对应的功能ID
	 * 
	 * @param sysFunction
	 *            功能实体
	 * @return 返回对应的功能ID,如果没有则返回null
	 * @throws Exception
	 */
	Long getFuncIdByCode(SysFunc sysFunction) throws Exception;
	/**
	 * 查询系统所有图标
	 * 
	 * @author somnuscy
	 * @return 返回所有图标Map
	 * @throws Exception
	 */
	List<Map<String, String>> getAllIcon() throws Exception;

//	int editSysFunctionState(Map<String, Object> map);
//	List<SysFunc> getAllPermFunctionTree(SysFunc func);
//	List<SysFunc> getPermFunctionTreeByType(SysFunc func);
//	/**
//	 * 修改功能数据
//	 * 
//	 * @param sysFunction
//	 * @return 影响行数
//	 */
//	int dealSysFunction(SysFunc sysFunction) throws Exception;

//	/**
//	 * 通过functionId查询关联functionIds
//	 * 
//	 * @param functionId
//	 * @return
//	 */
//	String queryFunctionIds(Map<String, Object> param) throws Exception;
//
//	/**
//	 * 通过functionId查询关联functionIds
//	 * 
//	 * @param functionId
//	 * @return
//	 */
//	String queryFunctionIds(String functionIds) throws Exception;

//	/**
//	 * 通过MAP修改State
//	 * 
//	 * @param functionId
//	 *            state
//	 * @return
//	 */
//	int dealSysFunctionState(Map<String, Object> param) throws Exception;
	/**
	 * 根据功能编码模糊匹配功能数据
	 * @param funcCode
	 * @return
	 * @throws Exception
	 */
	public List<SysFunc> queryByFuncCode(String funcCode) throws Exception;
}
