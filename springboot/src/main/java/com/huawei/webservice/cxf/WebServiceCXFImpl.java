package com.huawei.webservice.cxf;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.huawei.dao.AcountDao;

@WebService(serviceName = "WebServiceCXF",
		targetNamespace="http://cxf.webservice.huawei.com/",endpointInterface = "com.huawei.webservice.cxf.WebServiceCXF")
public class WebServiceCXFImpl implements WebServiceCXF{
	
	@Autowired
	private AcountDao acountDao;

	@Override
	public String getAccount(int id) {
		return acountDao.findById(id) == null?null:(acountDao.findById(id).toString());
	}

	@Override
	public String helloCXF(String str) {
		return "hello " + str;
	}
}
