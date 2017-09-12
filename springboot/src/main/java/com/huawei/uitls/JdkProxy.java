package com.huawei.uitls;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JdkProxy implements InvocationHandler{

	private Object target;
	
	public JdkProxy() {
		super();
	}
	
	public JdkProxy(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		System.out.println("++++++before " + method.getName() + "++++++");
		Object result = method.invoke(target, args);
		System.out.println("++++++after " + method.getName() + "++++++");
		
		return result;
	}

}
