package com.datayes.webspider.action.machine;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.datayes.webspider.action.BaseAction;
import com.datayes.webspider.domain.machine.MachineRole;
import com.datayes.webspider.domain.website.WebSiteConfig;
import com.datayes.webspider.service.machine.IMachineRoleService;
import com.datayes.webspider.service.website.IWebSiteConfigService;

@ParentPackage("webspider")
@Namespace("/")
@Results({ 
	@Result(name = "page", location = "/WEB-INF/jsp/machine/editMachineRole.jsp"),
	@Result(name = "redirect2success", location = "/WEB-INF/jsp/success.jsp"),
})
public class EditMachineRoleAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private MachineRole machineRole;
	private Integer roleId;
	private Integer webSiteConfigId;
	private int type;
	private String message;
	
	@Resource
	private IMachineRoleService machineRoleService;
	
	@Resource
	private IWebSiteConfigService webSiteConfigService;

	@Action("machineRole")
	public String machineRole() {
		if (type == 1) {
			this.machineRole = machineRoleService.getMachineRoleById(roleId);
			this.webSiteConfigId = machineRole.getWebSiteConfig().getWebSiteConfigId();
		}
		return "page";
	}
	
	@Action("newMachineRole")
	public String newMachineRole() {
		boolean succeed = true;
		if (machineRole != null && webSiteConfigId != null) {
			MachineRole machineRoleInDB = machineRoleService.getMachineRoleByName(machineRole.getMachineRoleName());
			if (machineRoleInDB != null) {
				succeed = false;
				message = "角色名称已经存在";
			}
			
			if (succeed) {
				WebSiteConfig webSiteConfig = webSiteConfigService.getById(webSiteConfigId);
				if (webSiteConfig != null) {
					machineRole.setWebSiteConfig(webSiteConfig);
					machineRole.setUpdateTime(new Date());
					machineRoleService.saveOrUpdate(machineRole);
					this.roleId = machineRole.getMachineRoleId();
				} else {
					succeed = false;
					message = "找不到对应的网站配置, webSiteConfigId: " + webSiteConfigId;
				}
			}
		} else {
			succeed = false;
			message = "必填字段不允许为空";
		}
		
		if (succeed) {
			return "redirect2success";
		} else {
			return "page";
		}
	}
	
	@Action("editMachineRole")
	public String editMachineRole() {
		boolean succeed = true;
		if (machineRole != null && webSiteConfigId != null && roleId != null) {
			MachineRole machineRoleInDB = machineRoleService.getMachineRoleById(roleId);
			if (machineRoleInDB != null) {
				if (!machineRoleInDB.getMachineRoleName().equals(machineRole.getMachineRoleName())) {
					MachineRole machineRoleExist = machineRoleService.getMachineRoleByName(machineRole.getMachineRoleName());
					if (machineRoleExist != null) {
						succeed = false;
						message = "角色名称已经存在";
					}
				}
				
				if (succeed) {
					boolean configExist = true;
					if (!machineRoleInDB.getWebSiteConfig().getWebSiteConfigId().equals(webSiteConfigId)) {
						WebSiteConfig webSiteConfig = webSiteConfigService.getById(webSiteConfigId);
						if (webSiteConfig != null) {
							machineRoleInDB.setWebSiteConfig(webSiteConfig);
						} else {
							configExist = false;
						}
					}
					
					if (configExist) {
						machineRoleInDB.setMachineRoleName(machineRole.getMachineRoleName());
						machineRoleInDB.setUpdateTime(new Date());
						machineRoleService.saveOrUpdate(machineRoleInDB);
					} else {
						succeed = false;
						message = "找不到对应的网站配置, webSiteConfigId: " + webSiteConfigId;
					}
				}
			} else {
				succeed = false;
				message = "找不到对应的服务器角色, machineRoleId: " + roleId;
			}
		} else {
			succeed = false;
			message = "必填字段不允许为空";
		}
		
		if (succeed) {
			return "redirect2success";
		} else{
			return "page";
		}
	}
	
	@Action("deleteMachineRole")
	public String deleteMachineRole() {
		return "json";
	}

	public MachineRole getMachineRole() {
		return machineRole;
	}

	public void setMachineRole(MachineRole machineRole) {
		this.machineRole = machineRole;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Integer getWebSiteConfigId() {
		return webSiteConfigId;
	}

	public void setWebSiteConfigId(Integer webSiteConfigId) {
		this.webSiteConfigId = webSiteConfigId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
