package com.huawei.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.huawei.dao.ProxyDao;

@Repository
public class ProxyDaoImpl implements ProxyDao{

	private String aa;
	
	@Override
	public void testProxy() {
		
		System.out.println("this is a target method ..............");
		Map<String, String> m = null;	
		try {
			m = new HashMap();	
			m.put("a", "1");
			m = serv();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(m.toString());
	}
	
	public Map<String , String> serv(){
		
		Map<String, String> m = new HashMap<>();
		m.put("b", "2");
		return m;
	}
	
	public static void main(String[] args) {
		ProxyDaoImpl proxyDaoImpl = new ProxyDaoImpl();
		proxyDaoImpl.testProxy();
	}
	
}
