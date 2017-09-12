package com.huawei.uitls;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class ScriptBat {

	
//	public static void main(String[] args) throws ParseException {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date date = sdf.parse("2017-02-24 17:05:36");
//		System.err.println(date.getTime());
//		
//		long timestamp = System.currentTimeMillis() - date.getTime();
//		System.err.println(System.currentTimeMillis());
//		
//		System.out.println("- :" + timestamp);
//		
//		System.out.println(timestamp/(24*60*60*1000));
//	}
	
	
	@SuppressWarnings("unused")
	public String execBat(){
		String single = null;
		Runtime run = Runtime.getRuntime();
		try {
//			Process ps = run.exec("cmd.exe /C start /b C:\\Users\\Administrator\\Desktop\\springboot启动.bat");
			Process ps = run.exec("cmd.exe /C start C:\\Users\\Administrator\\Desktop\\springboot启动.bat");
			InputStream ip = ps.getInputStream();
			single = "success";
		} catch (IOException e) {
			single = "failed";
			e.printStackTrace();
		}
		return single;
	}
}
