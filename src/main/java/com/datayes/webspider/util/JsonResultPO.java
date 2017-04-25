package com.datayes.webspider.util;

import java.util.HashMap;


public class JsonResultPO {

	private int status;
	private Object data;
	private String msg;

	public JsonResultPO(){
		
	}
	
	public static JsonResultPO getSuccessJsonResult(){
		return new JsonResultPO(200);
	}

	public JsonResultPO(int status){
		this.status = status;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Object getData() {
		if(null == data){
			data = new HashMap<String,String>();
		}
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	


}
