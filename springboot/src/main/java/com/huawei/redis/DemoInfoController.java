package com.huawei.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huawei.entity.Acount;

@Controller
@RequestMapping(value = "/redis")
public class DemoInfoController {
	
	private static final Logger logger = LoggerFactory.getLogger(DemoInfoController.class);
	 
	 @Autowired
	 private DemoInfoServiceImpl demoInfoServiceImpl;
	
	 @RequestMapping(value = "/test", method = {RequestMethod.GET, RequestMethod.POST})
	 public @ResponseBody String redisTest(){
	     StringBuffer sb = new StringBuffer();
	        demoInfoServiceImpl.set("str", "str");
	        sb.append("str=").append(demoInfoServiceImpl.get("str").toString()).append(",");
	        
	        demoInfoServiceImpl.hmSet("hmset","key","val");
	        sb.append("hmset=").append(demoInfoServiceImpl.hmGet("hmset","key")).append(",");
	        
	        demoInfoServiceImpl.lPush("list","val");
	        sb.append("list=").append(demoInfoServiceImpl.lRange("list",0,1).toString()).append(",");
	        
	        demoInfoServiceImpl.add("set","val");
	        sb.append("set=").append(demoInfoServiceImpl.setMembers("set").toString()).append(",");
	        
	        demoInfoServiceImpl.zAdd("zset","val1",1);
	        
	        demoInfoServiceImpl.zAdd("zset","val2",2);
	        
	        sb.append("zset=").append(demoInfoServiceImpl.rangeByScore("zset",1,2)).append(",");
	        return sb.toString();
	    }
	 
	 @RequestMapping(value = "/cacheById", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	 public @ResponseBody Acount cacheAcount(@RequestBody Acount acount){
		 Acount acountInfo = null;
		 try {
			 acountInfo = demoInfoServiceImpl.acountfindById(acount.getId());
		 } catch (Exception e) {
			 logger.error("cache Acount object error::" + e);
		 }
		 return acountInfo;
	 }
	 
	 @RequestMapping(value = "/cacheByNameAndId", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	 public @ResponseBody Acount cacheAcount1(@RequestBody Acount acount){
		 Acount acountInfo = null;
		 try {
			 acountInfo = demoInfoServiceImpl.acountfindByIdAndName(acount.getId() ,acount.getUserName());
		 } catch (Exception e) {
			 logger.error("cache Acount object error::" + e);
		 }
		 return acountInfo;
	 }
	 
	 @RequestMapping(value = "/deleteCache", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	 public @ResponseBody String deleteAcount(@RequestBody Acount acount){
		 try {
			 demoInfoServiceImpl.deleteFromCache(acount.getId(),acount.getUserName());
		 } catch (Exception e) {
			 logger.error("delete caching object error ::" + e);
		 }
		 return "success";
	 }
	 
}
