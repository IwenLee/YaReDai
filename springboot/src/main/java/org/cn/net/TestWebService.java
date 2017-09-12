package org.cn.net;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface TestWebService {
	
	@WebMethod
	String sayHello(String str);

	@WebMethod
	String saveInfo(String user, String pwd);
	
}
