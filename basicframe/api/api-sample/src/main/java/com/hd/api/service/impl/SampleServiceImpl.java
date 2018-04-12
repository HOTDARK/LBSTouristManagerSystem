package com.hd.api.service.impl;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hd.api.entity.PermitApplyDto;
import com.hd.api.entity.PermitApplyDtoList;
import com.hd.api.entity.TestObj;
import com.hd.api.server.log.BSASOAPInfo;
import com.hd.api.service.SampleService;
import com.hd.sfw.webservice.WebServiceManager;
import com.hd.sfw.webservice.exception.IwdWebServiceException;

/**
 * 接口测试例子
 * @author somnuscy
 *
 */
@Component
@WebService(targetNamespace="http://bean.erparc.webservice.cmg.hd.com")
public class SampleServiceImpl implements SampleService{
	
	@Autowired
	private WebServiceManager webServiceManager;
	
	@Override
	@BSASOAPInfo("发布接口测试")
	public PermitApplyDto queryInfo(Integer request) throws IwdWebServiceException {
		return webServiceManager.call(null, "TEST_001", new Object[]{request}, PermitApplyDto.class);
	}

	@Override
	@BSASOAPInfo("发布接口查询多个")
	public PermitApplyDtoList queryInfos(PermitApplyDto request) throws IwdWebServiceException {
		PermitApplyDtoList list = webServiceManager.call(null, "TEST_002", new Object[]{request}, PermitApplyDtoList.class);
		return list;
	}

	@Override
	@BSASOAPInfo("发布接口查询所有")
	public PermitApplyDtoList queryAllInfos() throws IwdWebServiceException {
		PermitApplyDtoList list = webServiceManager.call(null, "TEST_003", new Object[]{}, PermitApplyDtoList.class);
		return list;
	}

	@Override
	@BSASOAPInfo("测试接口")
	public TestObj testObjService(TestObj testObj) throws IwdWebServiceException {
		System.out.println(testObj.toString());
		return testObj;
	}
	
	
}
