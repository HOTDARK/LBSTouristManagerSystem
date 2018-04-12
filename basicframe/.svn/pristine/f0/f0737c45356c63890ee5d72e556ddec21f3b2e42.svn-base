package com.hd.sys.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hd.sfw.core.model.Pagination;
import com.hd.sys.dao.SysFuncMapper;
import com.hd.sys.dao.SysPermitMapper;
import com.hd.sys.entity.SysFunc;
import com.hd.sys.entity.SysPermit;
import com.hd.sys.service.SysFuncService;

/**
 * <p>类名：SysFunctionService </p>
 * <p>描述：系统功能业务层实现类 </p>
 * <p>时间：2014年12月8日 星期一 </p>
 */
@Service
public class SysFuncServiceImpl implements SysFuncService {

	@Autowired
	private SysFuncMapper sysFuncMapper;
	@Autowired
	private SysPermitMapper sysPermitMapper;

	@Override
	public List<SysFunc> getPermFunctionTree(SysFunc func) throws Exception{
		 List<SysFunc> sysFunctionListTemp = new ArrayList<SysFunc>();
		 //查询菜单和工能及菜单 FUNCTION_TYPE IN (0,2)
		 List<SysFunc> sysFunctionList = sysFuncMapper.getPermFunctionTree(func);
		 for(SysFunc sysFunction : sysFunctionList){
			 SysFunc sysFunctionTmep = new SysFunc();
			 //判断是否是顶级节点 ParentFunctionId等于0
			 if(sysFunction.getParentFunctionId().equals(0L)){
				 //传集合和对象去遍历取得对应的子节点信息
				 sysFunctionTmep = recombination(sysFunctionList, sysFunction);
				 //判断反结果是否返回为NULL
				 if(sysFunctionTmep != null){
					 //如果为不为NULL则添加返回的对象
					 sysFunctionListTemp.add(sysFunctionTmep);
				 }else{
					 //如果为不为NULL则添加原有对象
					 sysFunctionListTemp.add(sysFunction);
				 }
			 }
		 }
		 return sysFunctionListTemp;
	} 
	
	@Override
	public void dealSysFunctionState(SysFunc sysFunction, String state)  throws Exception{
		 //激动和冻结操作
		if("1".equals(state)){
			// 激活
			jiHuo(sysFunction);
		}else{
			// 冻结
			updateFreeze(sysFunction);
		}
	}
	
	public void jiHuo(SysFunc sysFunction){
		sysFunction.setState(1L);
		sysFuncMapper.updateByPK(sysFunction);
		sysFunction = sysFuncMapper.findByPK(sysFunction.getFunctionId());
		if (sysFunction != null) {
			SysFunc sFunction = new SysFunc();
			sFunction.setFunctionId(sysFunction.getParentFunctionId());
			jiHuo(sFunction);
		}
	}
	
	public void updateFreeze(SysFunc sysFunction) throws Exception {
		sysFunction.setState(0L);
		sysFuncMapper.updateByPK(sysFunction);
		SysFunc pType = new SysFunc();
		pType.setParentFunctionId(sysFunction.getFunctionId());
		List<SysFunc> typeDicts = findByCondition(pType);
		if (typeDicts != null && typeDicts.size() > 0) {
			for (SysFunc td : typeDicts) {
				updateFreeze(td);
			}
		}
	}
	
	//递归获取菜单列表
	public SysFunc recombination(List<SysFunc> sysFunctionList, SysFunc sysFunction){
		for(SysFunc sys_Function : sysFunctionList){
			//判断父级ID和传入对象的的ID是否相等判断是否关联关系
			if(sys_Function.getParentFunctionId().equals(sysFunction.getFunctionId())){
				// 如果当前不是叶子节点
				if (sys_Function.getFunctionLeafNode() != null && sys_Function.getFunctionLeafNode() == 0L) {
					SysFunc sysFunc = recombination(sysFunctionList, sys_Function);
					sysFunction.getSysFunctionList().add(sysFunc);
				} else {
					//如果有就添加到对象中的集合中
					sysFunction.getSysFunctionList().add(sys_Function);
				}
			}
		}
		return sysFunction;
	}

	@Override
	public void save(SysFunc entity) throws Exception {
		sysFuncMapper.insert(entity);
		//添加功能权限
		SysPermit sysPermission = new SysPermit();
		sysPermission.setCreateTime(new Date());
		sysPermission.setState(1l);
		sysPermission.setRoleId(1l);
		sysPermission.setFunctionId(entity.getFunctionId());
		sysPermitMapper.saveFuncPermission(sysPermission);
	}

	@Override
	public Long getFuncIdByCode(SysFunc sysFunction) throws Exception {
		return sysFuncMapper.getFuncIdByCode(sysFunction);
	}

	@Override
	public List<Map<String, String>> getAllIcon() throws Exception {
		return sysFuncMapper.getAllIcon();
	}

	@Override
	public SysFunc findById(Long id) throws Exception {
		return sysFuncMapper.findByPK(id);
	}

	@Override
	public List<SysFunc> findByCondition(SysFunc entity) throws Exception {
		return sysFuncMapper.findByCondition(entity);
	}

	@Override
	public void updateById(SysFunc entity) throws Exception {
		sysFuncMapper.updateByPK(entity);
	}

	@Override
	public void deleteById(Long id) throws Exception {
		sysFuncMapper.deleteByPK(id);
	}

	@Override
	public Pagination<SysFunc> findPageByCondition(SysFunc entity) throws Exception {
		int num = sysFuncMapper.findNumByCondition(entity);
		if (num == 0) {
			return new Pagination<SysFunc>();
		} else {
			List<SysFunc> funcs = sysFuncMapper.findByPage(entity, entity.getiDisplayStart(), entity.getiDisplayLength());
			return new Pagination<>(num, funcs);
		}
	}

	@Override
	public List<SysFunc> queryByFuncCode(String funcCode) throws Exception {
		return sysFuncMapper.queryByFuncCode(funcCode);
	}
	
//	@Override
//	public List<SysFunc> getPermFunctionTreeByType(SysFunc func) {
//		return sysFuncMapper.getPermFunctionTreeByType(func);
//	}
	
//	@Override
//	public String queryFunctionIds(String functionId) throws Exception{
//		return sysFuncMapper.queryFunctionIds(functionId);
//	}
	
}
