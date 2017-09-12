package com.huawei.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ConfigurationProperties 初始化实体类注解
 * @author Administrator
 */
//如果参数配置文件没有在springboot自带的配置文件中指定，，需要指定外部properties文件的类路径
//@ConfigurationProperties(locations ="classpath:wiselySettings.properties",prefix="wisely")
//如果参数在springboot中指定了，，就不需要指定类路径，
@ConfigurationProperties(prefix="wisely")
public class WiselySettings {
	
	private String userName;
	private String passWord;
	
	private WiselySettings(){
	}
	
	public String getUserName() {
		System.out.println("get"+userName);
		return userName;
	}
	public void setUserName(String userName) {
		System.out.println("set"+userName);
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	
}
