/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.engine.pvm.delegate;

import java.util.List;

import com.hd.workflow.engine.pvm.ProcessDefinition;
import com.hd.workflow.engine.pvm.PvmActivity;
import com.hd.workflow.engine.pvm.PvmTransition;
import com.hd.workflow.engine.pvm.VariableScope;

/**
 * 节点执行器
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-1-29 下午4:59:58
 */
public interface ActivityExecution extends VariableScope {
	
	/**
	 * 返回当前执行节点
	 * @return
	 */
	public PvmActivity getActivity();
	
	/**
	 * 设置当前执行节点
	 * @param activity
	 */
	public void setActivity(PvmActivity activity);
	
	/**
	 * 获取当前执行的分支
	 * @return
	 */
	public PvmTransition getTransition();
	
	/**
	 * 获取满足条件的流转分支
	 * @param activity
	 * @return
	 */
	public List<PvmTransition> getTransition(PvmActivity activity);
	
	/**
	 * 进入下一个执行节点
	 * @param transition
	 */
	public void take(PvmTransition transition);
	
	/**
	 * 遍历进入一批执行节点<br>
	 * 如果在遍历执行过程中任意分支节点进入到结束节点则流程结束
	 * @param transitions
	 */
	public void take(List<PvmTransition> transitions);
	
	/**
	 * 以并行的方式进入下一批执行节点。<br>
	 * 并且保证有一个执行节点在流程主线程中执行
	 * @param transitions
	 */
	public void takeAsync(List<PvmTransition> transitions);
	
	public void end();
	
	public boolean isEnded();
	
	public ProcessDefinition getProcessDefinition();
	
	public ActivityExecution getParent();
	
	public void setParent(ActivityExecution execution);
	
	/**
	 * 创建用于异步执行子任务的execution
	 * @return
	 */
	public ActivityExecution createAsyncExecution();
	
	/**
	 * 是否是主线程
	 * @return 如果作为子任务执行则返回false
	 */
	public boolean isMainThread();
	
	public void setMainThread(boolean isMainThread);
	
	/**
	 * 是否处于fork join状态
	 * @return
	 */
	public boolean isForkJoin();
	
	public void setForkJoin(boolean isForkJoin);
	
	/**
	 * 设置fork的线程数
	 * @param num
	 */
	public void setForkNumber(int num);
	
	/**
	 * 子任务结束时调用此通知进行join
	 */
	public void notifyJoin();
	
	/**
	 * 等待所有并发分支合并
	 */
	public void awaitJoin();
	
	/**
	 * 获取执行顺序id
	 * @return
	 */
	public String getExecSeqId();
	
	/**
	 * 如果执行出现异常返回非空，否则返回null 
	 * @return
	 */
	public Exception getException();

}
