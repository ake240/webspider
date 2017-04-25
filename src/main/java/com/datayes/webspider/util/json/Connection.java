package com.datayes.webspider.util.json;

import java.util.List;

public class Connection {
	int code;
	List<Data> data;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public List<Data> getData() {
		return data;
	}
	public void setData(List<Data> data) {
		this.data = data;
	}
}
