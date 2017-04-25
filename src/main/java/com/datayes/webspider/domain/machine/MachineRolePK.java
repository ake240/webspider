package com.datayes.webspider.domain.machine;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class MachineRolePK implements Serializable {

	@ManyToOne
	@JoinColumn(name = "machineid", referencedColumnName = "machineid")
	private Machine machine;
	
	@ManyToOne
	@JoinColumn(name = "machineroleid", referencedColumnName = "machineroleid")
	private MachineRole machineRole;

	public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

	public MachineRole getMachineRole() {
		return machineRole;
	}

	public void setMachineRole(MachineRole machineRole) {
		this.machineRole = machineRole;
	}
	
	
}
