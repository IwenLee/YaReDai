package com.huawei.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.xml.ws.Endpoint;

import org.cn.net.TestWebServiceImpl;

@WebServlet(value = "", loadOnStartup = 0)
public class WebServiceServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		/**启动webservice接口 --------------start*/
//		String url = "http://10.100.33.143:9992/service/springboot";	//http://ip:port/service/项目名称
//		Endpoint.publish( url, new TestWebServiceImpl());
//		System.err.println("webservice start successful by HttpServlet...... url:" +url);
		/**启动webservice接口 ---------------end*/
	}

	
}
