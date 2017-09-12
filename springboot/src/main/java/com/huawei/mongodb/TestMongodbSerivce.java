//package com.huawei.mongodb;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.huawei.response.ResponseResult;
//
//@Service
//public class TestMongodbSerivce {
//
//	@Autowired
//	private DemoDao demoDao;
//	
//	public boolean testMongoDB(DemoInfo demoInfo){
//		boolean flag = true;
//		try {
//			if(!demoDao.testmongoDB(demoInfo)){
//				flag = false;
//			}
//		} catch (Exception e) {
//			flag = false;
//			throw new RuntimeException(this.getClass().getName() +"running exception!");
//		}
//		return flag;
//	}
//	
//	public ResponseResult testMongodbFind(String name){
//		ResponseResult responseResult = null;
//		try {
//			responseResult = demoDao.testMongodbFind(name);
//		} catch (Exception e) {
//			responseResult.setStatus("failed");
//			throw new RuntimeException(this.getClass().getName() + "running exception !");
//		}
//		return responseResult;
//	}
//	
//	public ResponseResult testMongodbDelete(String param){
//		ResponseResult responseResult = null;
//		try {
//			responseResult = demoDao.testMongodbDelete(param);
//		} catch (Exception e) {
//			responseResult.setStatus("failed");
//			throw new RuntimeException(this.getClass().getName() + "running exception !");
//		}
//		return responseResult;
//	}
//}
