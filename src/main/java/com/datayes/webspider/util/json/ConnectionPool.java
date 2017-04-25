package com.datayes.webspider.util.json;

public class ConnectionPool {
	// {"code":"0","data":[{"inUseConnections":7,"totalConnections":7,"machineRoleId":43,"machineRoleName":"猎聘网","exceptionConnections":0}]}
	int inUseConnections;
	int totalConnections;
	int machineRoleId;
	String machineRoleName;
	int exceptionConnections;
	
	@Override
	public String toString(){
		return "{" + machineRoleName + "}";
	}

	public int getInUseConnections() {
		return inUseConnections;
	}
	public void setInUseConnections(int inUseConnections) {
		this.inUseConnections = inUseConnections;
	}
	public int getTotalConnections() {
		return totalConnections;
	}
	public void setTotalConnections(int totalConnections) {
		this.totalConnections = totalConnections;
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
	public int getExceptionConnections() {
		return exceptionConnections;
	}
	public void setExceptionConnections(int exceptionConnections) {
		this.exceptionConnections = exceptionConnections;
	}

}
