package com.datayes.webspider.domain.machine;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "machine")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)  
@NamedQueries({
	@NamedQuery(name = "findByHost", query = "from Machine m where m.status = 1 and m.host = ?"),
	@NamedQuery(name = "getByMachineId", query = "from Machine m where m.status = 1 and m.machineId = ?"),
	@NamedQuery(name = "getAllMachine", query = "from Machine m where m.status = 1 order by m.insertTime desc"),
})
public class Machine {
	@Id
	@GeneratedValue
	@Column(name = "machineid", nullable = false, unique = true)
	private Integer machineId;
	
	@Column(name = "host")
	private String host;
	
	@Column(name = "port")
	private Integer port;
	
	@Column(name = "machinetype")
	private Integer machineType;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "machine_role_rel", joinColumns={@JoinColumn(name="machineid", referencedColumnName="machineid")},
	inverseJoinColumns={@JoinColumn(name="machineroleid", referencedColumnName="machineroleid")})
	private Set<MachineRole> machineRoles;
	
	@Column(name = "machinedesc")
	private String machineDesc;
	
	@Column(name = "status")
	private Integer status;
	
	@Column(name = "inserttime")
	private Date insertTime = new Date();
	
	@Column(name = "updatetime")
	private Date updateTime;

	public Integer getMachineId() {
		return machineId;
	}

	public void setMachineId(Integer machineId) {
		this.machineId = machineId;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Set<MachineRole> getMachineRoles() {
		return machineRoles;
	}

	public void setMachineRoles(Set<MachineRole> machineRoles) {
		this.machineRoles = machineRoles;
	}

	public Integer getMachineType() {
		return machineType;
	}

	public void setMachineType(Integer machineType) {
		this.machineType = machineType;
	}

	public String getMachineDesc() {
		return machineDesc;
	}

	public void setMachineDesc(String machineDesc) {
		this.machineDesc = machineDesc;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
