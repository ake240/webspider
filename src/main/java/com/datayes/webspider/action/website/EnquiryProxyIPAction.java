package com.datayes.webspider.action.website;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.datayes.webspider.action.BaseAction;
import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.service.website.IProxyIPService;

@ParentPackage("webspider")
@Namespace("/")
@Results({ 
	@Result(name = "NormalSearch", location = "/WEB-INF/jsp/website/enquiryProxyIP.jsp"),
	@Result(name = "AjaxSearch", location = "/WEB-INF/jsp/ajax/enquiryProxyIPResult.jsp"), 
})
public class EnquiryProxyIPAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private int isAjaxSearch = 0;
	private int pageNow = 1;
	private String host;
	private Integer webSiteId;
	private Integer status;
	private PageDTO pageDTO;

	@Resource
	private IProxyIPService proxyIPService;

	@Action("enquiryProxyIP")
	public String enquiryProxyIP() {
		pageDTO = proxyIPService.enquiryProxyIPPage(host, webSiteId, status, pageNow, 20);
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

	public Integer getWebSiteId() {
		return webSiteId;
	}

	public void setWebSiteId(Integer webSiteId) {
		this.webSiteId = webSiteId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

}
