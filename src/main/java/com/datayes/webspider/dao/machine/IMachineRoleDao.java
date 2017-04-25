package com.datayes.webspider.dao.machine;

import java.util.List;

import com.datayes.webspider.dao.IBaseDao;
import com.datayes.webspider.domain.machine.MachineRole;

public interface IMachineRoleDao extends IBaseDao<MachineRole> {
	public Integer enquiryMachineRoleCount(String roleName, Integer webSiteConfigId, String status);
	
	public List enquiryMachineRolePage(String roleName, Integer webSiteConfigId, String status, int pageNow, int pageSize);
}
