package com.datayes.webspider.dao.machine;

import java.util.List;

import com.datayes.webspider.dao.IBaseDao;
import com.datayes.webspider.domain.machine.Machine;

public interface IMachineDao extends IBaseDao<Machine> {
	public Integer enquiryMachineCount(String host, Integer machineType, Integer machineRoleId, int status);
	
	public List enquiryMachinePage(String host, Integer machineType, Integer machineRoleId, int status, int pageNow, int pageSize);
	
	public Machine findByHost(String host);
}
