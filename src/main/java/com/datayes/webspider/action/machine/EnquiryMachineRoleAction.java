package com.datayes.webspider.action.machine;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.datayes.webspider.action.BaseAction;
import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.service.machine.IMachineRoleService;

@ParentPackage("webspider")
@Namespace("/")
@Results({ 
	@Result(name = "NormalSearch", location = "/WEB-INF/jsp/machine/enquiryMachineRole.jsp"), 
	@Result(name = "AjaxSearch", location = "/WEB-INF/jsp/ajax/enquiryMachineRoleResult.jsp"), 
})
public class EnquiryMachineRoleAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private int isAjaxSearch = 0;
	private int pageNow = 1;
	private String roleName;
	private Integer webSiteConfigId;
	private String status;
	private PageDTO pageDTO;
	
	@Resource
	private IMachineRoleService machineRoleService;
	
	@Action("enquiryMachineRole")
	public String enquiryMachineRole(){
		this.pageDTO = machineRoleService.enquiryMachineRolePage(roleName, webSiteConfigId, status, pageNow, 20);
		if (isAjaxSearch == 0) {
			return "NormalSearch";
		}
		return "AjaxSearch";
	}

	public int getIsAjaxSearch() {
		return isAjaxSearch;
	}

	public void setIsAjaxSearch(int isAjaxSearch) {
		this.isAjaxSearch = isAjaxSearch;
	}

	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}

	public PageDTO getPageDTO() {
		return pageDTO;
	}

	public void setPageDTO(PageDTO pageDTO) {
		this.pageDTO = pageDTO;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getWebSiteConfigId() {
		return webSiteConfigId;
	}

	public void setWebSiteConfigId(Integer webSiteConfigId) {
		this.webSiteConfigId = webSiteConfigId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
