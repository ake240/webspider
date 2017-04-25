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
import com.datayes.webspider.service.website.IWebSiteService;

@ParentPackage("webspider")
@Namespace("/")
@Results({ 
	@Result(name = "NormalSearch", location = "/WEB-INF/jsp/website/enquiryWebSite.jsp"), 
	@Result(name = "AjaxSearch", location = "/WEB-INF/jsp/ajax/enquiryWebSiteResult.jsp"), 
})
public class EnquiryWebSiteAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private String webSiteName;
	private Integer categoryId;
	private PageDTO pageDTO;
	private int pageNow = 1;
	private int isAjaxSearch = 0;
	
	@Resource
	private IWebSiteService webSiteService;
	
	@Action("enquiryWebSite")
	public String enquiryWebSite(){
		pageDTO = webSiteService.enquiryWebSitePage(webSiteName, categoryId, null, pageNow, PageSizeConstants.WEBSITE_PAGE_SIZE);
		if (isAjaxSearch == 0) {
			return "NormalSearch";
		}else{
			return "AjaxSearch";
		}
	}

	public String getWebSiteName() {
		return webSiteName;
	}

	public void setWebSiteName(String webSiteName) {
		this.webSiteName = webSiteName;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public PageDTO getPageDTO() {
		return pageDTO;
	}

	public void setPageDTO(PageDTO pageDTO) {
		this.pageDTO = pageDTO;
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
	
}
