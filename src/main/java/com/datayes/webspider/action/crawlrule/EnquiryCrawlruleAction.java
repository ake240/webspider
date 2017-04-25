package com.datayes.webspider.action.crawlrule;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.datayes.webspider.action.BaseAction;
import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.service.crawlrule.ICrawlruleService;

@ParentPackage("webspider")
@Namespace("/")
@Results({ 
	@Result(name = "NormalSearch", location = "/WEB-INF/jsp/crawlrule/enquiryCrawlrule.jsp"), 
	@Result(name = "AjaxSearch", location = "/WEB-INF/jsp/ajax/enquiryCrawlruleResult.jsp"), 
})
public class EnquiryCrawlruleAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private PageDTO pageDTO;
	private String crawlruleName;
	private Integer webOperationId;
	private String status;
	private int pageNow = 1;
	private int isAjaxSearch = 0;
	
	@Resource
	private ICrawlruleService crawlruleService;
	
	@Action("enquiryCrawlrule")
	public String enquiryCrawlrule(){
		pageDTO = crawlruleService.enquiryCrawlrulePage(crawlruleName, webOperationId, status, pageNow, 20);
		if (isAjaxSearch == 0) {
			return "NormalSearch";
		} else {
			return "AjaxSearch";
		}
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

	public String getCrawlruleName() {
		return crawlruleName;
	}

	public void setCrawlruleName(String crawlruleName) {
		this.crawlruleName = crawlruleName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getWebOperationId() {
		return webOperationId;
	}

	public void setWebOperationId(Integer webOperationId) {
		this.webOperationId = webOperationId;
	}
	
}
