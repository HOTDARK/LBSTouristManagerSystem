package com.hd.sfw.core.support.freemarker.directive; 

import java.io.IOException;
import java.util.Map;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class OverrideDirective implements TemplateDirectiveModel {

	public final static String DIRECTIVE_NAME = "override";
	
	@Override
	public void execute(Environment env,
            @SuppressWarnings("rawtypes") Map params, TemplateModel[] loopVars,
            TemplateDirectiveBody body) throws TemplateException, IOException {
		String name = DirectiveUtils.getRequiredParam(params, "name");
		String overrideVariableName = DirectiveUtils.getOverrideVariableName(name);
		
		TemplateDirectiveBodyOverrideWraper override = DirectiveUtils.getOverrideBody(env, name);
		TemplateDirectiveBodyOverrideWraper current = new TemplateDirectiveBodyOverrideWraper(body,env);
		if(override == null) {
			env.setVariable(overrideVariableName, current);
		}else {
			DirectiveUtils.setTopBodyForParentBody(env, current, override);
		}
	}
	
}
