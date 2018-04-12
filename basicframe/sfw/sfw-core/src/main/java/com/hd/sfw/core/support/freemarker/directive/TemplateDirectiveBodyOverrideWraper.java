/*
 * Copyright © 2014, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.sfw.core.support.freemarker.directive;

import java.io.IOException;
import java.io.Writer;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2014-12-16 下午7:24:26
 */
public class TemplateDirectiveBodyOverrideWraper implements TemplateDirectiveBody,TemplateModel{
	
	private TemplateDirectiveBody body;
	public TemplateDirectiveBodyOverrideWraper parentBody;
	public Environment env;
	
	public TemplateDirectiveBodyOverrideWraper(TemplateDirectiveBody body,
			Environment env) {
		super();
		this.body = body;
		this.env = env;
	}
	
	public void render(Writer out) throws TemplateException, IOException {
		if(body == null){
			return;
		}
		
		TemplateDirectiveBodyOverrideWraper preOverridy = (TemplateDirectiveBodyOverrideWraper)env.getVariable(DirectiveUtils.OVERRIDE_CURRENT_NODE);
		try {
			env.setVariable(DirectiveUtils.OVERRIDE_CURRENT_NODE, this);
			body.render(out);
		}finally {
			env.setVariable(DirectiveUtils.OVERRIDE_CURRENT_NODE, preOverridy);
		}
	}
}