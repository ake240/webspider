package com.datayes.webspider.service.machine;

import java.util.List;

import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.domain.machine.MachineRole;

public interface IMachineRoleService {
	public List<MachineRole> getAllMachineRole();
	
	public MachineRole getMachineRoleById(Integer id);
	
	public MachineRole getMachineRoleByName(String roleName);
	
	public PageDTO enquiryMachineRolePage(String roleName, Integer webSiteConfigId, String status, int pageNow, int pageSize);

	public void saveOrUpdate(MachineRole machineRole);
	
	public void deleteMachineRole(MachineRole machineRole);
}
