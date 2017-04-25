package com.datayes.webspider.action.words;


import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.datayes.webspider.action.BaseAction;
import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.constant.PageSizeConstants;
import com.datayes.webspider.service.words.IKeywordService;



@ParentPackage("webspider")
@Namespace("/")
@Results({ 
	@Result(name = "NormalSearch", location = "/WEB-INF/jsp/words/enquiryKeywords.jsp"), 
	@Result(name = "AjaxSearch", location = "/WEB-INF/jsp/ajax/enquiryKeywordsResult.jsp"), 
})
public class EnquiryKeywordAction extends BaseAction{

	private static final long serialVersionUID = 1L;

	
	private String keyword;
	private Integer status;
	private Integer pageNow = 1;
	private int isAjaxSearch = 0;
	private PageDTO pageDTO;
	
	@Resource
	private IKeywordService keywordService;
	
	@Action("enquiryKeywords")
	public String enquiryKeywords(){
		
		pageDTO = keywordService.enquiryKeywordPage(keyword, status, pageNow, PageSizeConstants.DEFAULT_SIZE);
		
		if(isAjaxSearch == 0){
			return "NormalSearch";
		}
		return "AjaxSearch";
	}
	
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
