package com.huawei.control;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huawei.dto.AccountBalanceModel;
import com.huawei.response.ResponseEntity;
import com.huawei.service.impl.SampleServiceImpl;

@RequestMapping(value="/app")
@Controller
public class SampleController {

	@Autowired
	private SampleServiceImpl sampleService;
	
	private static Boolean flag = true;
	
	@Value("${security.user.name:load error!}")
	private String userName ;
	@Value("${security.user.password:load error!}")
	private String passWorld ;
	
	
	@RequestMapping(value = "/repository" , method = RequestMethod.GET,produces = "application/json;charset=utf-8")
	public @ResponseBody net.sf.json.JSONArray saveEntity(){
//		try {
			int a = 1/0;
			System.out.println(a);
//		} catch (ArithmeticException e) {
//			throw new RuntimeException("运行错误");
//		}
		return sampleService.getOrSaveEntity();
	}
	
	@RequestMapping(value = "/ams/t3pAccSurplus/accQuery", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody Map<String, Object> balanceQuery(@RequestBody AccountBalanceModel model){
		Date d = new Date();
		System.out.println("ssssss :" + d);
		return null;
	}
	
	@RequestMapping(value = "/index")
	public String testHtml() {
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("index");
//		return modelAndView;
		return "index";
	}
	
    @RequestMapping("/")
    public @ResponseBody net.sf.json.JSONObject home() {
    	net.sf.json.JSONObject responseJson = new net.sf.json.JSONObject();
    	responseJson.put("statu", "success");
    	responseJson.put("userName", userName);
    	responseJson.put("passWord", passWorld);
        return responseJson;
    }
    
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity test(HttpServletResponse response){
    	//跨域
    	response.setHeader("Access-Control-Allow-Origin","*");
    	String status = sampleService.Test();
    	if(status.equals("ok")){
    		return new ResponseEntity("500","The response is successful!",new String[0]);
    	}else{
    		return new ResponseEntity("404","The response is failed!",new String[0]);
    	}
    }
    
    @RequestMapping(value = "/execBat",method = RequestMethod.GET)
    public @ResponseBody String execBat(){
    	
    	JSONObject result = new JSONObject();
    	result.put("status", "success");
    	String single = sampleService.execBat();
    	if(single.equals("failed")){
    		result.put("status", "failed");
    	}
    	return result.toString();
    }
    
    /**
     * 调起插件中的线程测试
     * @return
     */
    @RequestMapping(value = "/execPlugin",method = RequestMethod.GET)
    public @ResponseBody String execPlugin(){
    	sampleService.executePlugin();
    	
    	Thread thread2 = new Thread(new Runnable() {
			public void run() {
				try {
					while (flag) {
						System.out.println("#########");
						Thread.sleep(3000l);
					}
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					System.err.println("monitor-flag :" + e);
				}
			}
		});
		thread2.setName("monitor-flag");
		thread2.start();
    	
    	return "ok";
    }
    
    /**
     * 通过线程的interrupt()方法测试，关闭线程，线程是否还在内存中
     * 
     * 要是想关闭一个正在运行的线程，就必须同一个标志标量，和一个interrupt()方法，来联合使用，
     * interrupt()方法主要是关闭正阻塞的线程，
     * @return
     */
    @RequestMapping(value = "/getThread",method = RequestMethod.GET)
    public @ResponseBody String getThread(){
    	
    	Thread[] threads = new Thread[100];
		int i = Thread.enumerate(threads);
		System.out.println("Threads count :" +i);
		System.out.println(Arrays.toString(threads));
		//通过flag标志来停止一个线程，，
		flag = false;
		
		//停止线程monitor.jar包中吊起的线程
		for (Thread t : threads) {
			if(t != null){
				String threadName = t.getName();
				if(threadName.equalsIgnoreCase("monitor1")){
					System.err.println(threadName);
					t.interrupt();
				}else if(threadName.equalsIgnoreCase("monitor2")){
					System.err.println(threadName);
					t.interrupt();
				}else if(threadName.equalsIgnoreCase("monitor-flag")){
					System.err.println(threadName);
				}
			}
		}
    	return Arrays.toString(threads);
    }
    
  //测试微服务
  	@RequestMapping(value="/RestClient", method=RequestMethod.GET)
  	public @ResponseBody String testClient(){
//  		String url = "http://localhost:8082/ssh-springmvc/getResult";
  		return "success";
  	}
    
  	
  	@RequestMapping(value = "/AspecProxy", method = RequestMethod.GET)
  	public @ResponseBody String testAspectProxyObj(){
  		
  		sampleService.annotionProxyObject();
  		
  		return "success";
  	}
  	
}