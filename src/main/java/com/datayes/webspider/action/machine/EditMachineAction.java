package com.datayes.webspider.action.machine;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.datayes.webspider.action.BaseAction;
import com.datayes.webspider.domain.machine.Machine;
import com.datayes.webspider.domain.machine.MachineRole;
import com.datayes.webspider.service.machine.IMachineRoleService;
import com.datayes.webspider.service.machine.IMachineService;

@ParentPackage("webspider")
@Namespace("/")
@Results({ 
	@Result(name = "success", location = "/WEB-INF/jsp/machine/editMachine.jsp"),
	@Result(name = "redirect2success", location = "/WEB-INF/jsp/success.jsp"),
})
public class EditMachineAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private Integer machineId;
	private Machine machine;
	private Integer[] machineRoleIds;
	private String roleIds;
	private int type;
	
	@Resource
	private IMachineRoleService machineRoleService;
	
	@Resource
	private IMachineService machineService;
	
	@Action("machine")
	public String machine(){
		if (type==1) {
			this.machine = machineService.getByMachineId(machineId);
			roleIds = "";
			if (machine.getMachineType() ==2 && machine.getMachineRoles() != null) {
				for (MachineRole role : machine.getMachineRoles()) {
					roleIds += role.getMachineRoleId() + ",";
				}
				if (!StringUtils.isEmpty(roleIds)) {
					roleIds = roleIds.substring(0, roleIds.length() - 1);
				}
			}
		}
		
		return SUCCESS;
	}
	
	@Action("newMachine")
	public String newMachine(){
		if (machine != null) {
			Set<MachineRole> machineRoles = new HashSet<MachineRole>();
			if (machine.getMachineType() == 2 && machineRoleIds != null) {
				for (Integer machineRoleId : machineRoleIds) {
					MachineRole machineRole = machineRoleService.getMachineRoleById(machineRoleId);
					if (machineRole != null) {
						machineRoles.add(machineRole);
					}
				}
				
				machine.setMachineRoles(machineRoles);
			}
			machine.setStatus(1);
			machine.setUpdateTime(new Date());
			machineService.saveOrUpdate(machine);
			this.machineId = machine.getMachineId();
		}
		return "redirect2success";
	}
	
	@Action("editMachine")
	public String editMachine(){
		if (machineId != null && machine != null) {
			Machine machineInDB = machineService.getByMachineId(machineId);
			if (machineInDB != null) {
				machineInDB.setHost(machine.getHost());
				machineInDB.setPort(machine.getPort());
				machineInDB.setMachineDesc(machine.getMachineDesc());
				
				Set<MachineRole> machineRoles = new HashSet<MachineRole>();
				if (machine.getMachineType() == 2 && machineRoleIds != null) {
					for (Integer machineRoleId : machineRoleIds) {
						MachineRole machineRole = machineRoleService.getMachineRoleById(machineRoleId);
						if (machineRole != null) {
							machineRoles.add(machineRole);
						}
					}
				}
				
				machineInDB.setMachineType(machine.getMachineType());
				machineInDB.setMachineRoles(machineRoles);
				machineInDB.setUpdateTime(new Date());
				machineService.saveOrUpdate(machineInDB);
			}
		}
		return "redirect2success";
	}

	public Integer getMachineId() {
		return machineId;
	}

	public void setMachineId(Integer machineId) {
		this.machineId = machineId;
	}

	public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

	public Integer[] getMachineRoleIds() {
		return machineRoleIds;
	}

	public void setMachineRoleIds(Integer[] machineRoleIds) {
		this.machineRoleIds = machineRoleIds;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}
	
}
