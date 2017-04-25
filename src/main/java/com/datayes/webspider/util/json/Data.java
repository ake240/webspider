package com.datayes.webspider.util.json;

import java.util.List;

public class Data {
	int port;
	String host;
	int machineRoleId;
	String machineRoleName;
	String account;
	List<Operation> websiteOperationReqList;
	
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getMachineRoleId() {
		return machineRoleId;
	}
	public void setMachineRoleId(int machineRoleId) {
		this.machineRoleId = machineRoleId;
	}
	public String getMachineRoleName() {
		return machineRoleName;
	}
	public void setMachineRoleName(String machineRoleName) {
		this.machineRoleName = machineRoleName;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public List<Operation> getWebsiteOperationReqList() {
		return websiteOperationReqList;
	}
	public void setWebsiteOperationReqList(List<Operation> websiteOperationReqList) {
		this.websiteOperationReqList = websiteOperationReqList;
	}
}
