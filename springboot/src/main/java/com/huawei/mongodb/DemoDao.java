//package com.huawei.mongodb;
//
//import java.util.Arrays;
//import java.util.List;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import com.huawei.response.ResponseResult;
//import com.huawei.uitls.StringNull;
//
//@Repository
//public class DemoDao{
//
//	@Autowired
//	private DemoRepository demoRepository;
//	
//	//通过save存储一条数据对象（pojo对象）到类存中
//	public boolean testmongoDB(DemoInfo demoInfo){
//		boolean flag = true;
//		try {
////			Iterable<DemoInfo> i = Arrays.asList(new DemoInfo("3","changbo",24),new DemoInfo("4","你好",29)); 
//			if(!StringNull.isNull(demoInfo.getId())){
//				Iterable<DemoInfo> i = Arrays.asList(demoInfo); 
//				demoRepository.save(i);
//			}else {
//				String msg = "对象的主键为空";
//				flag = false;
//			}
//		} catch (Exception e) {
//			flag = false;
//			throw new RuntimeException(this.getClass().getName() + "running exception!");
//		}
//		return flag;
//	}
//	
//	/**通过name来检索一天数据*/
//	public ResponseResult testMongodbFind(String name){
//		ResponseResult responseResult = new ResponseResult();
//		String msg = "";
//		try {
//			if(!StringNull.isNull(name)){
//				List<DemoInfo> demoInfos = demoRepository.findByName(name);
//				JSONObject reJsonObject = new JSONObject();
//				reJsonObject.put("data", new JSONArray(demoInfos));
//				responseResult.setData(reJsonObject.toString());
//				msg = "query success!";
//			}else {
//				msg = "find by name , name is null!";
//			}
//		} catch (Exception e) {
//			msg = "find data error!";
//			responseResult.setStatus("failed");
//			throw new RuntimeException(this.getClass().getName() + "find data error!");
//		}
//		responseResult.setStatus("success");
//		responseResult.setMessage(new String[]{msg});
//		return responseResult;
//	}
//	
//	/**通过demoinfo 来删除一跳数据*/
//	public ResponseResult testMongodbDelete(String param){
//		ResponseResult responseResult = null;
//		try {
//			responseResult = new ResponseResult();
//			JSONObject jsonObject = new JSONObject(param);
//			DemoInfo demoInfo = new DemoInfo();
//			if(jsonObject.has("name")){
//				demoInfo.setName(jsonObject.getString("name"));
//			}
//			if(jsonObject.has("id")){
//				demoInfo.setId(jsonObject.getString("id"));
//			}
//			if(jsonObject.has("age")){
//				demoInfo.setAge(jsonObject.getInt("age"));
//			};
//			demoRepository.delete(demoInfo);
//			responseResult.setStatus("success");
//			responseResult.setMessage(new String[]{"delete data success!"});
//		} catch (Exception e) {
//			responseResult.setStatus("failed");
//			responseResult.setMessage(new String[]{"delete data failed !"});
//			throw new RuntimeException(this.getClass().getName() + "delete");
//		}
//		return responseResult;
//	}	
//	
//}