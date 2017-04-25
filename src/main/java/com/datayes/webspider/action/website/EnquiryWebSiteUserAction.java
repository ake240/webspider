package com.datayes.webspider.action.website;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.datayes.webspider.action.BaseAction;
import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.constant.PageSizeConstants;
import com.datayes.webspider.service.website.IWebSiteUserService;

@ParentPackage("webspider")
@Namespace("/")
@Results({ 
	@Result(name = "NormalSearch", location = "/WEB-INF/jsp/website/enquiryWebSiteUser.jsp"), 
	@Result(name = "AjaxSearch", location = "/WEB-INF/jsp/ajax/enquiryWebSiteUserResult.jsp"), 
})
public class EnquiryWebSiteUserAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private String account;
	private Integer webSiteId;
	private PageDTO pageDTO;
	private int pageNow = 1;
	private int isAjaxSearch = 0;
	
	@Resource
	private IWebSiteUserService webSiteUserService;
	
	@Action("enquiryWebSiteUser")
	public String enquiryWebSiteUser(){
		pageDTO = webSiteUserService.enquiryWebSiteUserPage(account, webSiteId, null, pageNow, PageSizeConstants.WEBSITE_USER_PAGE_SIZE);
		if (isAjaxSearch == 0) {
			return "NormalSearch";
		} else {
			return "AjaxSearch";
		}
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Integer getWebSiteId() {
		return webSiteId;
	}

	public void setWebSiteId(Integer webSiteId) {
		this.webSiteId = webSiteId;
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
