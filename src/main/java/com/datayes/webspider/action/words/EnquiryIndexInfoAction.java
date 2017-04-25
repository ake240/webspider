package com.datayes.webspider.action.words;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.datayes.webspider.action.BaseAction;
import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.constant.PageSizeConstants;
import com.datayes.webspider.service.words.IIndexInfoService;
import com.datayes.webspider.util.DateUtil;


@ParentPackage("webspider")
@Namespace("/")
@Results({ 
	@Result(name = "NormalSearch", location = "/WEB-INF/jsp/words/enquiryIndexInfo.jsp"), 
	@Result(name = "AjaxSearch", location = "/WEB-INF/jsp/ajax/enquiryIndexInfoResult.jsp"), 
})
public class EnquiryIndexInfoAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	private Long wordId;
	private String keyword;
	private String indexType;
	private String beginDate;
	private String endDate;
	
	private Integer pageNow = 1;
	private int isAjaxSearch = 0;
	private PageDTO pageDTO;
	
	@Resource
	private IIndexInfoService indexInfoService;
	
	@Action("enquiryIndexInfo")
	public String enquiryIndexInfoOps(){
		if(StringUtils.isEmpty(keyword)){
			pageDTO = indexInfoService.enquiryIndexInfo(wordId, indexType, DateUtil.convert(beginDate), DateUtil.convert(endDate), pageNow, PageSizeConstants.DEFAULT_SIZE);
		} else {
			pageDTO = indexInfoService.enquiryIndexInfo(keyword, indexType, DateUtil.convert(beginDate), DateUtil.convert(endDate), pageNow, PageSizeConstants.DEFAULT_SIZE);
		}
		if(isAjaxSearch==0){
			return "NormalSearch";
		}
		return "AjaxSearch";
	}

	
	/*
	 * Getters and Setters
	 */
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
	public Long getWordId() {
		return wordId;
	}
	public void setWordId(Long wordId) {
		this.wordId = wordId;
	}
	public String getIndexType() {
		return indexType;
	}
	public void setIndexType(String indexType) {
		this.indexType = indexType;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
}
