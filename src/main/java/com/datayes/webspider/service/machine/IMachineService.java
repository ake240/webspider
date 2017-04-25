package com.datayes.webspider.service.machine;

import java.util.List;

import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.domain.machine.Machine;

public interface IMachineService {
	public Machine getByMachineId(Integer machineId);
	
	public void saveOrUpdate(Machine machine);
	
	public void deleteMachine(Machine machine);
	
	public PageDTO enquiryMachinePage(String host, Integer machineType, Integer machineRoleId, int status, int pageNow, int pageSize);

	public List<Machine> findAllMachine();
}
