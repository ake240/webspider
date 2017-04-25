package com.datayes.webspider.action.websiteOps;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.datayes.webspider.action.BaseAction;
import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.service.websiteOps.IWebSiteOpsExceptionRuleService;

@ParentPackage("webspider")
@Namespace("/")
@Results({ 
	@Result(name = "NormalSearch", location = "/WEB-INF/jsp/websiteOps/enquiryWebSiteOpsExceptionRule.jsp"),
	@Result(name = "AjaxSearch", location = "/WEB-INF/jsp/ajax/enquiryWebSiteOpsExceptionRuleResult.jsp"), 
})
public class EnquiryWebSiteOpsExceptionRuleAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private PageDTO pageDTO;
	private String ruleName;
	private Integer webSiteOpsId;
	private Integer status;
	private int pageNow = 1;
	private int isAjaxSearch = 0;
	
	@Resource
	private IWebSiteOpsExceptionRuleService webSiteOpsExceptionRuleService;

	@Action("enquiryWebSiteOpsExceptionRule")
	public String enquiryWebSiteOpsExceptionRule(){
		pageDTO = webSiteOpsExceptionRuleService.enquiryWebSiteOpsExceptionRulePage(ruleName, webSiteOpsId, status, pageNow, 20);
		if (isAjaxSearch == 0) {
			return "NormalSearch";
		}
		return "AjaxSearch";
	}

	public PageDTO getPageDTO() {
		return pageDTO;
	}

	public void setPageDTO(PageDTO pageDTO) {
		this.pageDTO = pageDTO;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public Integer getWebSiteOpsId() {
		return webSiteOpsId;
	}

	public void setWebSiteOpsId(Integer webSiteOpsId) {
		this.webSiteOpsId = webSiteOpsId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}

	public int getIsAjaxSearch() {
		return isAjaxSearch;
	}

	public void setIsAjaxSearch(int isAjaxSearch) {
		this.isAjaxSearch = isAjaxSearch;
	}
	
	
}
