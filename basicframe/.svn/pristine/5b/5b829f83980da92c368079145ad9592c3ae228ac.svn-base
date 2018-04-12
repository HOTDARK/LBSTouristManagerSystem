package com.hd.sys.test;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.hd.sys.entity.SysOrg;
import com.hd.sys.service.SysOrgService;
import com.hd.sys.service.impl.SysOrgServiceImpl;

/**
 * 测试demo
 * @author 张伟荣
 *
 * 2015-11-24下午2:53:38
 */
public class InterfaceTest extends BaseUnit {
	private SysOrgService sysOrgService;
	
	@Before
	public void setup() {
		sysOrgService = context.getBean(SysOrgServiceImpl.class);
	}
	
	@Test
	public void testUserTemplateRequest() throws Exception {
		SysOrg sysOrg=new SysOrg();
		List<SysOrg> orgData = sysOrgService.getOrgData(sysOrg);
		System.out.println(orgData);
	}
}
