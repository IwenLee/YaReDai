package com.huawei.service;

import net.sf.json.JSONArray;

public interface SampleService {

	String Test();
	
	String execBat();
	
	void executePlugin();
	
	JSONArray getOrSaveEntity();
		
	void proxyObject();
	
	void annotionProxyObject();
	
	void jdkProxyObject();
}
