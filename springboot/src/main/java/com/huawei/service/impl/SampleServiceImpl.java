package com.huawei.service.impl;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Proxy;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huawei.dao.AcountDao;
import com.huawei.dao.AspectProxyDao;
import com.huawei.dao.ProxyDao;
import com.huawei.dao.impl.ProxyDaoImpl;
import com.huawei.dto.WiselySettings;
import com.huawei.entity.Acount;
import com.huawei.monitor.MonitorImp;
import com.huawei.service.SampleService;
import com.huawei.uitls.JdkProxy;
import com.huawei.uitls.ScriptBat;

@Service
@Transactional
public class SampleServiceImpl implements SampleService{

	private static final Logger logger = LoggerFactory.getLogger(SampleServiceImpl.class);
	
	@Autowired
	private WiselySettings wiselySettings;

	@Autowired
	private ScriptBat scriptBat;

	@Autowired
	private AcountDao acountDao; 
	
	@Autowired
	private ProxyDao proxyDao;
	
	@Resource(name = "aspectProxyDaoImpl")
	private AspectProxyDao aspectProxyObject;
	
	@Override
	public String Test() {

		System.out.println("wiselySettings ：" + wiselySettings);
		System.out.println(wiselySettings.getUserName());
		System.out.println(wiselySettings.getPassWord());
		if (wiselySettings.getUserName() != null && !wiselySettings.getUserName().equals("")) {
			return "ok";
		} else {
			return "failed";
		}

	}

	@Override
	public String execBat() {
		String a = scriptBat.execBat();
		return a;
	}

	@Override
	@SuppressWarnings({ "null", "deprecation" })
	public void executePlugin(){
		URLClassLoader urlClassLoader = null;
		try {
			String pluginUrl = String.format("%s%s%s%s%s%s%s", System.getProperty("user.dir"), File.separator,
					"service", File.separator, "plugin", File.separator, "monitor.jar");
			File file = new File(pluginUrl);
			URL[] url = new URL[1];
			url[0] = file.toURL();
			urlClassLoader = new URLClassLoader(url, Thread.currentThread().getContextClassLoader());
			Class<?> clazz = urlClassLoader.loadClass("com.hw.monitor.RouterMonitor");
			MonitorImp monitor = (MonitorImp) clazz.newInstance();
			Map<String, JSONObject> map = new HashMap<>();
			JSONObject result = new JSONObject();
			result.put("lihao", "ok");
			map.put("lihao", result);
			monitor.service(map);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			System.err.println(e);
		} catch (MalformedURLException e) {
			System.err.println(e);
		} finally {
			try {
				if (urlClassLoader == null) {
					urlClassLoader.close();
				}
			} catch (IOException e) {
				System.err.println(e);
			}
		}
	}

	@Override
	public net.sf.json.JSONArray getOrSaveEntity() {
		List<Acount> list = null;
		try {
			acountDao.save(new Acount("胡锦涛","99999"));
			acountDao.save(new Acount("温家宝","55555"));
			acountDao.save(new Acount("习近平","111120"));
			
			list = acountDao.findAll();
		} catch (Exception e) {
			logger.error(":" + e);
		}
		return net.sf.json.JSONArray.fromObject(list);
	}

	/**
	 * 使用spring中ProxyFactory工厂类来创建代理对象，
	 * com.huawei.uitls.BeforeAdvice
	 * com.huawei.dao.impl.ProxyDaoImpl
	 * com.huawei.dao.ProxyDao
	 */
	@Override
	public void proxyObject() {
		
		ProxyFactory proxyFactory = new ProxyFactory(new ProxyDaoImpl()); 
		proxyFactory.setInterfaces(ProxyDao.class);
		proxyFactory.addAdvice(new com.huawei.uitls.BeforeAdvice());
		proxyFactory.setExposeProxy(true);
		
		ProxyDao proxy = (ProxyDao)proxyFactory.getProxy();
		proxy.testProxy();
	}
//	public static void main(String[] args) {
//		SampleServiceImpl a = new SampleServiceImpl();
//		a.proxyObject();
//	}
	
	/**
	 * 使用java原生的api实现代理对象
	 * com.huawei.uitls.JdkProxy
	 * com.huawei.dao.ProxyDao
	 */
	@Override
	public void jdkProxyObject(){
		ProxyDao proxyDao = new ProxyDaoImpl();
		JdkProxy jdkProxy = new JdkProxy(proxyDao);
		ProxyDao proxyDaoInstance = (ProxyDao)Proxy.newProxyInstance(proxyDao.getClass().getClassLoader(), 
				proxyDao.getClass().getInterfaces(), jdkProxy);
		proxyDaoInstance.testProxy();
	}
	
//	public static void main(String[] args) {
//		SampleServiceImpl a = new SampleServiceImpl();
//		a.jdkProxyObject();
//	}
	
	/**
	 *  通过spring 的注解来完成代理模式
	 *  com.huawei.uitls.AllAdvice
	 *  com.huawei.dao.impl.AspectProxyDaoImpl
	 */
	@Override
	public void annotionProxyObject(){
		aspectProxyObject.testProxy();
	}
}
