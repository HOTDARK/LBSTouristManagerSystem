/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.engine;

import java.net.URL;
import java.util.concurrent.Future;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Before;
import org.junit.Test;

import com.hd.sfw.core.utils.IOUtils;
import com.hd.workflow.engine.pvm.ProcessInstance;
import com.hd.workflow.engine.pvm.factory.SimpleTaskDelegateFactory;
import com.hd.workflow.engine.pvm.impl.ExecutionImpl;
import com.hd.workflow.engine.pvm.impl.el.ExpressionManager;

/**
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-2-27 下午4:16:44
 */
public class WorkflowEngineTest {
	private WorkflowEngine engine;
	
	@Before
	public void init() throws Exception{
		
		//配置log4j
		URL log4jXml = Thread.currentThread().getContextClassLoader().getResource("log4j.xml");
		DOMConfigurator.configure(log4jXml);
		
		WorkflowEngineConfiguration configuration = new WorkflowEngineConfiguration();
		configuration.setExpressionManager(new ExpressionManager());
		configuration.setTaskDelegateFactory(new SimpleTaskDelegateFactory());
		
		engine = new WorkflowEngineImpl(configuration);
		
		engine.deploy("xxx", new String(IOUtils.readFromResource("process.xml")),1);
	}
	
	@Test
	public void testProcess() throws Exception{
		Future<ProcessInstance> future =  engine.startProcessInstance("processId", null);
		ExecutionImpl execution = (ExecutionImpl)future.get();
		
		if(execution.getException()!=null){
			throw execution.getException();
		}
	}
}
