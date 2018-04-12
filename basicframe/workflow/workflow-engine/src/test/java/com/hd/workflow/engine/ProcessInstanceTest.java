/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.engine;

import org.junit.Test;

import com.hd.workflow.engine.pvm.ProcessInstance;
import com.hd.workflow.engine.pvm.behavior.EndEventBehavior;
import com.hd.workflow.engine.pvm.behavior.StartEventBehavior;
import com.hd.workflow.engine.pvm.impl.ActivityImpl;
import com.hd.workflow.engine.pvm.impl.ProcessDefinitionImpl;

/**
 * TODO 类描述
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-2-6 下午3:05:57
 */
public class ProcessInstanceTest{
	
	@Test
	public void testStart(){
		ProcessDefinitionImpl processDefinition = new ProcessDefinitionImpl("hehe");
		
		
		ActivityImpl startActivity = processDefinition.createActivity("start");
		startActivity.setActivityBehavior(new StartEventBehavior());
		
		
		ActivityImpl endActivity = processDefinition.createActivity("end");
		endActivity.setActivityBehavior(new EndEventBehavior());
		
		ActivityImpl myActivity = processDefinition.createActivity("my");
		myActivity.setActivityBehavior(new MyTestBehavior());
		
		startActivity.createOutgoingTransition("start2my").setDestination(myActivity);
		myActivity.createOutgoingTransition("my2end").setDestination(endActivity);
		
		ProcessInstance instance = processDefinition.createProcessInstance();
		instance.setActivity(startActivity);
		instance.start();
		
	}
	
}
