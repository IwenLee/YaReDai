package com.huawei.init;

import javax.xml.ws.Endpoint;

import org.cn.net.TestWebServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value=1) //此注解主要定义每个实现 CommandLineRunner 接口的类的初始化的顺序
public class initService implements CommandLineRunner {
	
	@Override
	public void run(String... arg0) throws Exception {
		
		//此处如果放到外部会报错
//		System.out.println(arg0[0]);
//		System.out.println(arg0[1]);
//		System.out.println("nihao________");
		
		/**启动webservice接口 --------------start*/
//		String url = "http://10.100.33.143:9990/service/springboot";	//http://ip:port/service/项目名称
//		Endpoint.publish( url, new TestWebServiceImpl());
//		System.err.println("webservice start successful by CommandLineRunner...... url:" +url);
		/**启动webservice接口 ---------------end*/
		
		
	}
	
}
