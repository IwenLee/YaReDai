package com.huawei.response;

import java.io.Serializable;

public class ResponseEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String status;
	private String message;
	private String[] data;//可以用json代替
	
	public ResponseEntity(String status, String message, String[] data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String[] getData() {
		return data;
	}
	public void setData(String[] data) {
		this.data = data;
	}

	
	
}
