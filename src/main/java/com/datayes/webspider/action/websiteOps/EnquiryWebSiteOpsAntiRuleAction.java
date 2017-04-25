package com.datayes.webspider.action.websiteOps;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.datayes.webspider.action.BaseAction;
import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.service.websiteOps.IWebSiteOpsAntiRuleService;

@ParentPackage("webspider")
@Namespace("/")
@Results({ 
	@Result(name = "NormalSearch", location = "/WEB-INF/jsp/websiteOps/enquiryWebSiteOpsAntiRule.jsp"),
	@Result(name = "AjaxSearch", location = "/WEB-INF/jsp/ajax/enquiryWebSiteOpsAntiRuleResult.jsp"), 
})
public class EnquiryWebSiteOpsAntiRuleAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String antiRuleName;
	private Integer webSiteOpsId;
	private PageDTO pageDTO;
	private int pageNow = 1;
	private int isAjaxSearch = 0;
	
	@Resource
	private IWebSiteOpsAntiRuleService webSiteOpsAntiRuleService;
	
	@Action("enquiryWebSiteOpsAntiRule")
	public String enquiryWebSiteOpsAntiRule(){
		pageDTO = webSiteOpsAntiRuleService.enquiryWebSiteOpsAntiRulePage(antiRuleName, webSiteOpsId, pageNow, 20);
		if (isAjaxSearch == 0) {
			return "NormalSearch";
		}
		return "AjaxSearch";
	}

	public String getAntiRuleName() {
		return antiRuleName;
	}

	public void setAntiRuleName(String antiRuleName) {
		this.antiRuleName = antiRuleName;
	}

	public Integer getWebSiteOpsId() {
		return webSiteOpsId;
	}

	public void setWebSiteOpsId(Integer webSiteOpsId) {
		this.webSiteOpsId = webSiteOpsId;
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

	public int getIsAjaxSearch() {
		return isAjaxSearch;
	}

	public void setIsAjaxSearch(int isAjaxSearch) {
		this.isAjaxSearch = isAjaxSearch;
	}
	
	
}
