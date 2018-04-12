package com.hd.sfw.generator;

import cn.org.rapid_framework.generator.Generator;
import cn.org.rapid_framework.generator.GeneratorFacade;
import cn.org.rapid_framework.generator.GeneratorProperties;

public class App {
	public static void main(String[] args) throws Exception{
		Generator generator = new Generator();
		generator.setTemplateRootDir(Thread.currentThread().getContextClassLoader().getResource("template").getFile());
		generator.setOutRootDir(GeneratorProperties.getProperty("outRoot"));
		
		GeneratorFacade facade = new GeneratorFacade();
		facade.deleteOutRootDir();
		facade.setGenerator(generator);
		
		facade.generateByAllTable();
	}
}