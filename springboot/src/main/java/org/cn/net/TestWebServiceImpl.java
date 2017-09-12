package org.cn.net;

import javax.jws.WebService;

/**
 * webservice的基础原理例子，使用jdk来实现 的
 * 启动的时候我们可以使用ServletContextListener监听和servlet来启动，也可以使用springboot的特性CommandLineRunner来启动
 * 详细的启动方式请查看：com.huawei.init 包下的 initService.java .和
 * 				  com.huawei.listener 包下的  WebServiceListener.java 类
 *                com.huawei.servlet 包下的  WebServiceServlet.java 类   
 * 
 * 验证是否启动成功：使用浏览器访问//http://10.100.33.143:9990/service/springboot?wsdl
 * 
 * **注意通过 Endpoint 类来启动的webservice接口实际上是独立的，，如果是web项目和当前的web服务器无关，
 * 	   他是独立于当前web服务器的jvm之外的另一个jvm的
 * 
 * @author Administrator
 */
@WebService
public class TestWebServiceImpl implements TestWebService{
	
	/**
	 * welcome class
	 * @param str
	 * @return
	 */
	public String sayHello(String str){
		return "hello " + str;
	}

	/**
	 * save info
	 * @param user
	 * @param pwd
	 * @return
	 */
	public String saveInfo(String user, String pwd){
		return "save success.... user:" + user + "password:" + pwd;
	}
	
	/**
	 * 非web项目启动
	 * @param args
	 * 启动，访问 http://10.100.33.143:9990/service/springboot?wsdl
	 */
//	public static void main(String[] args) {
//		Endpoint.publish("http://10.100.33.143:9990/service/springboot", new TestWebServiceImpl());
//		System.err.println("service start successful....");
//	}
}
