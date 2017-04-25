package com.datayes.webspider.util.json;

import java.util.List;

public class Pool {
	//{"code":"0","data":[{"inUseConnections":7,"totalConnections":7,"machineRoleId":43,"machineRoleName":"猎聘网","exceptionConnections":0}]}
	int code;
	List<ConnectionPool> data;
	
	@Override
	public String toString(){
		return "{ code : " + code+ "}" + data;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public List<ConnectionPool> getData() {
		return data;
	}
	public void setData(List<ConnectionPool> data) {
		this.data = data;
	}
}
