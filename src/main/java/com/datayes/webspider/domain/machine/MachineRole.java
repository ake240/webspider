package com.datayes.webspider.domain.machine;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.datayes.webspider.domain.website.WebSiteConfig;

@Entity
@Table(name = "machine_role")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)  
@NamedQueries({
	@NamedQuery(name="getAllMachineRoles", query="from MachineRole m order by m.insertTime desc"),
	@NamedQuery(name="getMachineRoleById", query="from MachineRole m where m.machineRoleId = ?"),
	@NamedQuery(name="getMachineRoleByName", query="from MachineRole m where m.machineRoleName = ?"),
})
public class MachineRole {
	@Id
	@GeneratedValue
	@Column(name = "machineroleid", nullable = false, unique = true)
	private Integer machineRoleId;
	
	@Column(name = "machinerolename")
	private String machineRoleName;
	
	@ManyToMany(cascade=CascadeType.ALL, mappedBy="machineRoles")
	private Set<Machine> machines;
	
	@ManyToOne
	@JoinColumn(name = "websiteconfigid", referencedColumnName = "websiteconfigid")
	private WebSiteConfig webSiteConfig;
	
	@Column(name = "inserttime")
	private Date insertTime = new Date();
	
	@Column(name = "updatetime")
	private Date updateTime;

	public Integer getMachineRoleId() {
		return machineRoleId;
	}

	public void setMachineRoleId(Integer machineRoleId) {
		this.machineRoleId = machineRoleId;
	}

	public String getMachineRoleName() {
		return machineRoleName;
	}

	public void setMachineRoleName(String machineRoleName) {
		this.machineRoleName = machineRoleName;
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

	public Set<Machine> getMachines() {
		return machines;
	}

	public void setMachines(Set<Machine> machines) {
		this.machines = machines;
	}

	public WebSiteConfig getWebSiteConfig() {
		return webSiteConfig;
	}

	public void setWebSiteConfig(WebSiteConfig webSiteConfig) {
		this.webSiteConfig = webSiteConfig;
	}
	
}
