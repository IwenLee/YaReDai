package com.huawei.dao.impl;

import org.springframework.stereotype.Repository;

import com.huawei.dao.AspectProxyDao;

@Repository
public class AspectProxyDaoImpl implements AspectProxyDao{

	@Override
	public void testProxy(){
		System.out.println("this is target method ......");
	}
	
}
