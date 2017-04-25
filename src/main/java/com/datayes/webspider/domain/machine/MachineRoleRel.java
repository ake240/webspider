package com.datayes.webspider.domain.machine;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "machine_role_rel")
public class MachineRoleRel {

	@Id
	private MachineRolePK machineRolePK;

	public MachineRolePK getMachineRolePK() {
		return machineRolePK;
	}

	public void setMachineRolePK(MachineRolePK machineRolePK) {
		this.machineRolePK = machineRolePK;
	}
	
}
