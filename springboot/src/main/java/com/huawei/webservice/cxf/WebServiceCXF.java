package com.huawei.webservice.cxf;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(name = "WebServiceCXF",targetNamespace = "http://cxf.webservice.huawei.com/")
public interface WebServiceCXF {
	
	@WebMethod
	@WebResult(name = "String", targetNamespace = "")
	String getAccount(@WebParam(name = "id") int id);
	
	@WebMethod
	@WebResult(name = "String", targetNamespace = "")
	String helloCXF(@WebParam(name = "str") String str);
	
}
