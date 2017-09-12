package com.huawei.uitls;

public class StringNull {

	public static boolean isNull(String s){
		try {
			if(s == null || s.equals("")){
				return true;
			}
			return false;
		} catch (Exception e) {
			throw new RuntimeException(StringNull.class.getName() + "String 匹配出错！");
		}
	}
	
}
