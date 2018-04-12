/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.engine.pvm.behavior;

import java.util.List;

import com.hd.sfw.core.utils.CollectionUtils;
import com.hd.workflow.engine.exception.WorkflowEngineException;
import com.hd.workflow.engine.pvm.PvmActivity;
import com.hd.workflow.engine.pvm.PvmTransition;
import com.hd.workflow.engine.pvm.context.Context;
import com.hd.workflow.engine.pvm.delegate.ActivityExecution;

/**
 * 并发网关<br>
 * 目前并发网关即作为fork节点又作为join节点使用，fork时如果分支只有一个则不进行并发处理，join时如果分支存在多个将按普通执行节点逻辑抛出异常。
 * 并发网关必须成对出现，并且在流程中间不支持再次fork join(此支持将在以后版本中实现).<br><br>
 * <pre>
 * 如果当前execution处于非并发状态:
 * 		1.如果分支大于1对各个分支进行并发处理，否则作为普通节点越过；
 * 如果当前execution处于并发状态:
 * 		1.如果是子任务进行join标记分支任务完成并检测是否所有分支任务完成，
 * 		2.如果是主线程join后判断是否所有任务完成，如果完成进行并发网关的下一步流转，如果未完成阻塞主线程
 * </pre>
 * 
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-2-12 下午3:54:30
 */
public class ParallelGatewayBehavior extends AbstractActivityBehavior{

	@Override
	public void execute(ActivityExecution execution) throws Exception {
		if(execution.isForkJoin()){
			join(execution);
		}else{
			fork(execution);
		}
	}
	
	private void fork(ActivityExecution execution){
		PvmActivity activity = execution.getActivity();
		List<PvmTransition> transitions = execution.getTransition(activity);
		if(CollectionUtils.isEmpty(transitions)){
			throw new WorkflowEngineException("并发网关fork时无后续分支");
		}else if(transitions.size()==1){
			//离开，进入下一个分支
			leave(execution);
		}else{
			//1.选择一个分支在此线程中执行
			//2.余下分支作为子任务放入线程池中执行
			int size = transitions.size();
			
			execution.setForkNumber(transitions.size());
			execution.setForkJoin(true);
			
			for(int i=0;i<size;i++){
				final PvmTransition t = transitions.get(i);
				
				//最后一个节点作为主线程
				if(i==size-1){
					execution.take(t);
				}else{
					final ActivityExecution subExecution = execution.createAsyncExecution();
					Context.getEngine().submitForkJoin(new Runnable() {
						@Override
						public void run() {
							subExecution.take(t);
						}
					});
				}
			}
			
		}
	}
	
	private void join(ActivityExecution execution) throws Exception {
		if(execution.isMainThread()){
			execution.notifyJoin();
			
			//判断是否所有子任务都到达
			execution.awaitJoin();
			
			if(execution.getException()!=null){
				throw execution.getException();
			}
			
			leave(execution);
		}else{
			//执行器结束
			execution.end();
			
			//通知父线程任务完成
			execution.getParent().notifyJoin();
		}
	}

}
