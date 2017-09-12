//package com.huawei.mongodb;
//
//import javax.servlet.ServletContext;
//import javax.servlet.http.HttpServletRequest;
//
//import org.json.JSONObject;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.context.support.WebApplicationContextUtils;
//
//import com.huawei.response.ResponseResult;
//
//@Controller
//public class DemoController {
//
//	@Autowired
//	private TestMongodbSerivce testMongodbSerivce;
//	
//	@RequestMapping(value = "/test/mongodb",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
//	public @ResponseBody String testMongodb(@RequestBody DemoInfo demoInfo){
//		JSONObject responseJson = new JSONObject();
//		try {
//			if(testMongodbSerivce.testMongoDB(demoInfo)){
//				responseJson.put("status", "success");
//			}else {
//				responseJson.put("status", "failed");
//			}
//		} catch (Exception e) {
//			throw new RuntimeException(this.getClass().getName() + "Class running exception!");
//		}
//		return responseJson.toString();
//	}
//
//	@RequestMapping(value = "/test/mongodb/findByName" ,method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
//	public ResponseEntity<ResponseResult> testMongodbFind(@RequestBody String reBood){
//		JSONObject reJsonObject = new JSONObject(reBood);
//		return new ResponseEntity<ResponseResult>(testMongodbSerivce.testMongodbFind(reJsonObject.getString("name")), HttpStatus.OK);
//	} 
//	
//	@RequestMapping(value = "/test/mongodb/delete",method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
//	public ResponseEntity<ResponseResult> textMongodbDelete(@RequestBody String param) {
//		return new ResponseEntity<ResponseResult>(testMongodbSerivce.testMongodbDelete(param), HttpStatus.OK);
//	}
//	
//	@RequestMapping(value = "/t")
//	public String testBean(HttpServletRequest request) throws BeansException, InstantiationException, IllegalAccessException{
//		ServletContext sc = request.getServletContext();
//		DemoRepository d = WebApplicationContextUtils.getRequiredWebApplicationContext(sc).getBean(DemoRepository.class);
//		//此处打印的是org.springframework.data.mongodb.repository.support.SimpleMongoRepository@6b08a549，
//		//说明底层是使用反射得到的对象，然后放到spring容器中，，所以才可以安全的注入
//		//  注意   这里的  DemoRepository 接口我们是没有实现的，，
//		System.out.println(d);
//		return "success";
//	}
//}
