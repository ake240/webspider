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
import com.datayes.webspider.service.website.ICategoryService;

@ParentPackage("webspider")
@Namespace("/")
@Results({ 
	@Result(name = "NormalSearch", location = "/WEB-INF/jsp/website/enquiryCategory.jsp"), 
	@Result(name = "AjaxSearch", location = "/WEB-INF/jsp/ajax/enquiryCategoryResult.jsp"), 
})
public class EnquiryCategoryAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private String categoryName;
	private PageDTO pageDTO;
	private int pageNow = 1;
	private int isAjaxSearch = 0;
	
	@Resource
	private ICategoryService categoryService;

	@Action("enquiryCategory")
	public String enquiryCategory(){
		pageDTO = categoryService.enquiryCategoryPage(categoryName, null, pageNow, PageSizeConstants.CATEGORY_PAGE_SIZE);
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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
