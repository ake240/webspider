package com.datayes.webspider.action.machine;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.datayes.webspider.action.BaseAction;
import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.constant.CommonConstants;
import com.datayes.webspider.service.machine.IMachineService;

@ParentPackage("webspider")
@Namespace("/")
@Results({ 
	@Result(name = "NormalSearch", location = "/WEB-INF/jsp/machine/enquiryMachine.jsp"), 
	@Result(name = "AjaxSearch", location = "/WEB-INF/jsp/ajax/enquiryMachineResult.jsp"), 
	@Result(name="jun.zhou", type="redirectAction", params={"actionName","enquiryKeywords"}),
})
public class EnquiryMachineAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private int isAjaxSearch;
	private PageDTO pageDTO;
	private String host;
	private Integer machineType;
	private Integer machineRoleId;
	private String status;
	private int pageNow=1;
	
	@Resource
	private IMachineService machineService;
	
	@Action("enquiryMachine")
	public String enquiryMachine(){
		if(session.get(CommonConstants.SESSION_OBJECT_KEY).equals("jun.zhou")){
			return "jun.zhou";
		}
		pageDTO = machineService.enquiryMachinePage(host, machineType, machineRoleId, 1, pageNow, 20);
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

	public PageDTO getPageDTO() {
		return pageDTO;
	}

	public void setPageDTO(PageDTO pageDTO) {
		this.pageDTO = pageDTO;
	}

	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}

	public Integer getMachineType() {
		return machineType;
	}

	public void setMachineType(Integer machineType) {
		this.machineType = machineType;
	}

	public Integer getMachineRoleId() {
		return machineRoleId;
	}

	public void setMachineRoleId(Integer machineRoleId) {
		this.machineRoleId = machineRoleId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public IMachineService getMachineService() {
		return machineService;
	}

	public void setMachineService(IMachineService machineService) {
		this.machineService = machineService;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
	
}
