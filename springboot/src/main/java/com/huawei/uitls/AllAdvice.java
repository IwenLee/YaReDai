package com.huawei.uitls;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AllAdvice {

	/**
	 * 配置一个通用的切面
	 */
//	@Pointcut(value = "execution(* *.*(..))")
	@Pointcut(value = "execution(* com.huawei.dao.impl.AspectProxyDaoImpl.*Proxy(..))")
	public void cusPoint(){}
	
	
//	@Before(value = "execution(* com.huawei.dao.impl.AspectProxyDaoImpl.*Proxy(..))")
	@Before(value = "cusPoint()")
	public void before(){
		System.out.println("before ................");
	}
	
	@After(value = "cusPoint()")
	public void after(){
		System.out.println("after..............");
	}
	
}
