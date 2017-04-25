package com.datayes.webspider.action.websiteOps;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.datayes.webspider.action.BaseAction;
import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.constant.PageSizeConstants;
import com.datayes.webspider.service.websiteOps.IWebSiteOpsService;

@ParentPackage("webspider")
@Namespace("/")
@Results({ 
	@Result(name = "NormalSearch", location = "/WEB-INF/jsp/websiteOps/enquiryWebSiteOps.jsp"), 
	@Result(name = "AjaxSearch", location = "/WEB-INF/jsp/ajax/enquiryWebSiteOpsResult.jsp"), 
})
public class EnquiryWebSiteOpsAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String webSiteOpsName;
	private Integer webSiteId;
	private Integer pageNow = 1;
	private int isAjaxSearch = 0;
	private PageDTO pageDTO;
	
	@Resource
	private IWebSiteOpsService webSiteOpsService;

	@Action("enquiryWebSiteOps")
	public String enquiryWebSiteOps() {
		pageDTO = webSiteOpsService.enquiryWebSiteOpsPage(webSiteOpsName, webSiteId, null, pageNow, PageSizeConstants.WEBSITE_OPERATION_PAGE_SIZE);
		if (isAjaxSearch == 0) {
			return "NormalSearch";
		}
		return "AjaxSearch";
	}

	public String getWebSiteOpsName() {
		return webSiteOpsName;
	}

	public void setWebSiteOpsName(String webSiteOpsName) {
		this.webSiteOpsName = webSiteOpsName;
	}

	public Integer getWebSiteId() {
		return webSiteId;
	}

	public void setWebSiteId(Integer webSiteId) {
		this.webSiteId = webSiteId;
	}

	public Integer getPageNow() {
		return pageNow;
	}

	public void setPageNow(Integer pageNow) {
		this.pageNow = pageNow;
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
	
	
}
