package com.huawei.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.xml.ws.Endpoint;

import org.cn.net.TestWebServiceImpl;

@WebListener
public class WebServiceListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		/**启动webservice接口 --------------start*/
//		String url = "http://10.100.33.143:9991/service/springboot";	//http://ip:port/service/项目名称
//		Endpoint.publish( url, new TestWebServiceImpl());
//		System.err.println("webservice start successful by ServletContextListener...... url:" +url);
		/**启动webservice接口 ---------------end*/
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

	
}
