/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.log.script;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.lang.StringUtils;

import com.hd.workflow.engine.pvm.delegate.ActivityExecution;

/**
 * TODO 类描述
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-6-15 上午10:22:29
 */
public class ScriptUtil {
	private static ScriptEngineManager manager = new ScriptEngineManager();
	
	public static String execute(String script,ActivityExecution execution){
		
		if(StringUtils.isEmpty(script)){
			return null;
		}
		
		ScriptEngine engine = manager.getEngineByName("javascript");
		engine.setBindings(new VariableScopeBindings(execution), ScriptContext.ENGINE_SCOPE);
		Object obj = null;
		try {
			obj = engine.eval("(function(){"+script+"})()");
		} catch (ScriptException e) {
			e.printStackTrace();
		}

		return obj==null?null:obj.toString();
		
	}
}
