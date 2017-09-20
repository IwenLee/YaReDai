package com.huawei;

import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ServerPropertiesAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.huawei.dto.WiselySettings;
import com.huawei.listener.WebServiceListener;
import com.huawei.servlet.WebServiceServlet;
import com.huawei.webservice.cxf.WebServiceCXF;

//@Configuration  
//@ComponentScan(basePackages = {"springfox.*"})  
//@EnableAutoConfiguration
//@EnableWebMvc
@SpringBootApplication  //此注解是和以上的三注解等效的
@EnableConfigurationProperties(
		WiselySettings.class
	)
@ServletComponentScan 	//扫描自定义的servlet类上面的注解，包括监听，过滤器，servlet
@SuppressWarnings("all")
public class Application extends SpringBootServletInitializer{
	
	/**
	 * 用了此方法就不需要用上面的 @EnableConfigurationProperties注解和
	 * 实体类上面的@ConfigurationProperties(prefix="wisely")注解，，这两种初始化实体类是等效的
	 * @return
	 */
//	@Bean
//	@ConfigurationProperties(prefix = "wisely")
//	public WiselySettings WiselySettings(){
//		return new WiselySettings();
//	}
	
	/**
	  * 1、使用代码注册Servlet（不需要@ServletComponentScan注解和@webservice注解） ，代码的可读性高
	  * 2、如果只是在自定义的servlet上面使用了@webservlet注解是可以在外部tomcat中使用的
	  * 3、如果要在内嵌的tomcat容器中使用的话，除了此处的代码注册外，还有可以使用@webservlet和@ServletComponentScan注解来完成
	  */
//	@Bean
//	public ServletRegistrationBean servletRegistrationBean() {
//		ServletRegistrationBean servletRegistrationBean = 
//				new ServletRegistrationBean(new WebServiceServlet(), "");
//		servletRegistrationBean.setLoadOnStartup(0);
//		return servletRegistrationBean;// ServletName默认值为首字母小写，即myServlet
//	}
//	@Bean
//	public ServletListenerRegistrationBean<WebServiceListener> servletListenerRegistrationBean(){
//		return new ServletListenerRegistrationBean<WebServiceListener>(new WebServiceListener());
//	}
	
	/**
	 * 外部 tomcat 运行的配置
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}
	
	public static void main(String[] args) {  
        SpringApplication.run(Application.class,args);  
    }  
	
	/*@Bean  
	public MultipartConfigElement multipartConfigElement() {  
	  MultipartConfigFactory factory = new MultipartConfigFactory();  
	  //文件最大  
	  factory.setMaxFileSize("1024KB"); //KB,MB  
	  /// 设置总上传数据总大小  
      factory.setMaxRequestSize("102400KB");  
      return factory.createMultipartConfig();  
	}  */	
	
}
