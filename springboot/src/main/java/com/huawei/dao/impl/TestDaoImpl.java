package com.huawei.dao.impl;
//package com.huawei.dao;
//
//import java.util.List;
//
//import org.apache.log4j.Logger;
//import org.hibernate.Query;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import com.huawei.entity.Acount;
//
//
//@Repository
//public class TestDao {
//	
//	private static Logger  logger = Logger.getLogger(TestDao.class);
//	
//	public TestDao(){
//		System.out.println("��ʼ�� dao");
//	}
//	
//	@Autowired
//	private SessionFactory sessionFactory;
//	
//	public List<Acount> getResult(){
//		
//		String hql="from Acount";
//		Session session = sessionFactory.openSession(); 
//		Query query=session.createQuery(hql);
//		logger.info("exec sql :" + hql);
//		List<Acount> list = query.list();
//		return list;
//	}
//}
