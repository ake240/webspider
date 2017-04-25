package com.datayes.webspider.action.task;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.datayes.webspider.action.BaseAction;
import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.service.task.ITaskService;

@ParentPackage("webspider")
@Namespace("/")
@Results({ @Result(name = "NormalSearch", location = "/WEB-INF/jsp/task/enquiryTask.jsp"),
		@Result(name = "AjaxSearch", location = "/WEB-INF/jsp/ajax/enquiryTaskResult.jsp"), })
public class EnquiryTaskAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private int isAjaxSearch;
	private PageDTO pageDTO;
	private String taskName;
	private Integer webSiteOpsId;
	private Integer taskStatus;
	private String procHost;
	private int pageNow = 1;
	
	@Resource
	private ITaskService taskService;

	@Action("enquiryTask")
	public String enquiryTask() {
		pageDTO = taskService.enquiryTaskPage(taskName, webSiteOpsId, procHost, taskStatus, pageNow, 20);
		if (isAjaxSearch == 0) {
			return "NormalSearch";
		} else {
			return "AjaxSearch";
		}
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

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Integer getWebSiteOpsId() {
		return webSiteOpsId;
	}

	public void setWebSiteOpsId(Integer webSiteOpsId) {
		this.webSiteOpsId = webSiteOpsId;
	}

	public Integer getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(Integer taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getProcHost() {
		return procHost;
	}

	public void setProcHost(String procHost) {
		this.procHost = procHost;
	}

}
