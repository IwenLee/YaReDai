package com.huawei.webservice.cxf;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigCxfBean {

	@Autowired
	private Bus bus ;
	
//	 @Bean(name = Bus.DEFAULT_BUS_ID)
//	 public SpringBus springBus(){
//		 return new SpringBus();
//	 }
	
	/**
	 * 其中，Spring Boot已帮你自动注册了servlet,默认为/services，如果需要修改（如修改成webservice），两个方式：
	 * 方式一：通过配置文件，在resources下新建application.properties文件
  	 * 		cxf.path=/webservice
     * 方式二：通过代码，在CXFConfig中添加以下代码：
     * 		如下bean
	 */
//	 @Bean
//	 public ServletRegistrationBean dispatcherServlet() {
//		 ServletRegistrationBean servletRegistrationBean = 
//				 new ServletRegistrationBean(new CXFServlet(), "/webservice/*");
//		 servletRegistrationBean.setLoadOnStartup(0);	//启动的时机
//		 servletRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);	//注册bean的顺序
//	     return servletRegistrationBean;
//	 }
	 
	 @Bean
	 public WebServiceCXF webServiceCXF(){
		 return new WebServiceCXFImpl();
	 }
	 
	 @Bean
	 public Endpoint endpoint() {
	     EndpointImpl endpoint = new EndpointImpl(bus, webServiceCXF());
	     endpoint.publish("/Hello");
//	     endpoint.getInInterceptors().add(arg0) //添加拦截器
	     return endpoint;
	}
}
