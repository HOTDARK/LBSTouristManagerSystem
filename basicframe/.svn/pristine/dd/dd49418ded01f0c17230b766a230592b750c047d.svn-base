/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.engine;

import java.io.FileInputStream;

import org.junit.Test;

import com.hd.sfw.core.utils.IOUtils;
import com.hd.workflow.engine.parse.ProcessParser;
import com.hd.workflow.engine.pvm.ProcessDefinition;

/**
 * TODO 类描述
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-2-27 上午11:17:20
 */
public class TestProcessParse {
	
	@Test
	public void testParse() throws Exception{
		ProcessDefinition definition = new ProcessParser().execute(IOUtils.toString(new FileInputStream("c:/schema.xml")));
		System.out.println(definition);
	}
}
