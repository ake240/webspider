package com.datayes.webspider.service.machine;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.dao.machine.IMachineDao;
import com.datayes.webspider.domain.machine.Machine;

@Service("machineService")
public class MachineServiceImpl implements IMachineService {
	
	@Resource
	private IMachineDao machineDao;

	@Override
	public Machine getByMachineId(Integer machineId) {
		return machineDao.findById("getByMachineId", machineId);
	}

	@Override
	@Transactional
	public void saveOrUpdate(Machine machine) {
		machineDao.saveOrUpdate(machine);
	}

	@Override
	@Transactional
	public void deleteMachine(Machine machine) {
		machineDao.delete(machine);
	}

	@Override
	public PageDTO enquiryMachinePage(String host, Integer machineType, Integer machineRoleId, int status, int pageNow,
			int pageSize) {
		int total = machineDao.enquiryMachineCount(host, machineType, machineRoleId, status);
		List list = machineDao.enquiryMachinePage(host, machineType, machineRoleId, status, pageNow, pageSize);
		return new PageDTO(list, pageNow, pageSize, total);
	}

	@Override
	public List<Machine> findAllMachine() {
		return machineDao.findAll("getAllMachine");
	}

}
