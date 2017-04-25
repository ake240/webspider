package com.datayes.webspider.service.machine;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.dao.machine.IMachineRoleDao;
import com.datayes.webspider.domain.machine.MachineRole;

@Service("machineRoleService")
public class MachineRoleServiceImpl implements IMachineRoleService {

	@Resource
	private IMachineRoleDao machineRoleDao;
	
	@Override
	public List<MachineRole> getAllMachineRole() {
		return machineRoleDao.findAll("getAllMachineRoles");
	}

	@Override
	public MachineRole getMachineRoleById(Integer id) {
		return machineRoleDao.findById("getMachineRoleById", id);
	}

	@Override
	public PageDTO enquiryMachineRolePage(String roleName, Integer webSiteConfigId, String status, int pageNow,
			int pageSize) {
		Integer total = machineRoleDao.enquiryMachineRoleCount(roleName, webSiteConfigId, status);
		List list = machineRoleDao.enquiryMachineRolePage(roleName, webSiteConfigId, status, pageNow, pageSize);
		return new PageDTO(list, pageNow, pageSize, total);
	}

	@Override
	@Transactional
	public void saveOrUpdate(MachineRole machineRole) {
		machineRoleDao.saveOrUpdate(machineRole);
	}

	@Override
	@Transactional
	public void deleteMachineRole(MachineRole machineRole) {
		machineRoleDao.delete(machineRole);
	}

	@Override
	public MachineRole getMachineRoleByName(String roleName) {
		return machineRoleDao.findByName("getMachineRoleByName", roleName);
	}

}
