/*
 * Copyright © 2014, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.sfw.core.support.freemarker;

import java.io.IOException;

import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.hd.sfw.core.support.freemarker.directive.BlockDirective;
import com.hd.sfw.core.support.freemarker.directive.ExtendsDirective;
import com.hd.sfw.core.support.freemarker.directive.OverrideDirective;
import com.hd.sfw.core.support.freemarker.directive.SuperDirective;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

/**
 * 对freemarker进行增强
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2014-12-16 下午7:14:49
 */
public class IwdFreeMarkerConfigurer extends FreeMarkerConfigurer{
	@Override
	public void afterPropertiesSet() throws IOException, TemplateException {
		super.afterPropertiesSet();
		
		Configuration conf = this.getConfiguration();
		
		conf.setSharedVariable(BlockDirective.DIRECTIVE_NAME, new BlockDirective());
		conf.setSharedVariable(ExtendsDirective.DIRECTIVE_NAME, new ExtendsDirective());
		conf.setSharedVariable(OverrideDirective.DIRECTIVE_NAME, new OverrideDirective());
		conf.setSharedVariable(SuperDirective.DIRECTIVE_NAME, new SuperDirective());
	}
}
