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
import com.datayes.webspider.service.websiteOps.IDataStoreFieldService;

@ParentPackage("webspider")
@Namespace("/")
@Results({ 
	@Result(name = "NormalSearch", location = "/WEB-INF/jsp/websiteOps/enquiryDataStoreField.jsp"), 
	@Result(name = "AjaxSearch", location = "/WEB-INF/jsp/ajax/enquiryDataStoreFieldResult.jsp"), 
})
public class EnquiryDataStoreFieldAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private Integer dataStoreTypeId;
	private Integer pageNow = 1;
	private int isAjaxSearch = 0;
	private PageDTO pageDTO;
	
	@Resource
	private IDataStoreFieldService dataStoreFieldService;

	@Action("enquiryDataStoreField")
	public String enquiryDataStoreField() {
		pageDTO = dataStoreFieldService.enquiryDataStoreFieldPage(dataStoreTypeId, pageNow, PageSizeConstants.DEFAULT_SIZE);
		if (isAjaxSearch == 0) {
			return "NormalSearch";
		}
		return "AjaxSearch";
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

	public Integer getDataStoreTypeId() {
		return dataStoreTypeId;
	}

	public void setDataStoreTypeId(Integer dataStoreTypeId) {
		this.dataStoreTypeId = dataStoreTypeId;
	}
	
}
