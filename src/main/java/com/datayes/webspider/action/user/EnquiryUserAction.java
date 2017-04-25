package com.datayes.webspider.action.user;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.datayes.webspider.action.BaseAction;
import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.constant.CommonConstants;
import com.datayes.webspider.service.user.IUserService;

@ParentPackage("webspider")
@Namespace("/")
@Results({ 
	@Result(name = "NormalSearch", location = "/WEB-INF/jsp/user/enquiryUser.jsp"),
	@Result(name = "AjaxSearch", location = "/WEB-INF/jsp/ajax/enquiryUserResult.jsp"), 
	@Result(name = "redirect2forbidden", location = "/WEB-INF/jsp/forbidden.jsp"),
})
public class EnquiryUserAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	public String username;
	private Integer status;
	private PageDTO pageDTO;
	private int pageNow = 1;
	private int isAjaxSearch = 0;
	
	@Resource
	private IUserService userService;
	
	@Action("enquiryUser")
	public String enquiryUser(){
		String loggedUser = (String) session.get(CommonConstants.SESSION_OBJECT_KEY);
		if (("admin").equals(loggedUser)) {
			pageDTO = userService.enquiryUserPage(username, status, pageNow, 20);
			if (isAjaxSearch == 0) {
				return "NormalSearch";
			}
			return "AjaxSearch";
		}else{
			return "redirect2forbidden";
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
