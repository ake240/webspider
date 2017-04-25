package com.datayes.webspider.action.websiteOps;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.datayes.webspider.action.BaseAction;
import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.service.websiteOps.IWebSiteOpsQueueService;

@ParentPackage("webspider")
@Namespace("/")
@Results({ 
	@Result(name = "NormalSearch", location = "/WEB-INF/jsp/websiteOps/enquiryWebSiteOpsQueue.jsp"),
	@Result(name = "AjaxSearch", location = "/WEB-INF/jsp/ajax/enquiryWebSiteOpsQueueResult.jsp"), 
})
public class EnquiryWebSiteOpsQueueAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String queueCnName;
	private Integer webSiteOpsId;
	private PageDTO pageDTO;
	private int pageNow = 1;
	private int isAjaxSearch = 0;

	@Resource
	private IWebSiteOpsQueueService webSiteOpsQueueService;

	@Action("enquiryWebSiteOpsQueue")
	public String enquiryWebSiteOpsQueue() {
		pageDTO = webSiteOpsQueueService.enquiryWebSiteOpsQueuePage(queueCnName, webSiteOpsId, null, pageNow, 20);
		if (isAjaxSearch == 0) {
			return "NormalSearch";
		}
		return "AjaxSearch";
	}

	public String getQueueCnName() {
		return queueCnName;
	}

	public void setQueueCnName(String queueCnName) {
		this.queueCnName = queueCnName;
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
