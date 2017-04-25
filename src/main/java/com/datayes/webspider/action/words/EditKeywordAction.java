package com.datayes.webspider.action.words;

import java.sql.Date;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.alibaba.fastjson.JSONObject;
import com.datayes.webspider.action.BaseAction;
import com.datayes.webspider.domain.words.IndexKeywords;
import com.datayes.webspider.service.words.IKeywordService;
import com.datayes.webspider.service.words.IKeywordTaskService;

@ParentPackage("webspider")
@Namespace("/")
@Results({ 
	@Result(name = "page", location = "/WEB-INF/jsp/words/addKeyword.jsp"),
	@Result(name = "redirect2success", location = "/WEB-INF/jsp/success.jsp"),
	@Result(name = "json", type = "json", params = {"includeProperties","jsonResult"}),
})
public class EditKeywordAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	private Long keywordId;
	private Integer status;
	private String jsonResult;
	
	private String message;
	private IndexKeywords keyword;
	
	@Resource
	private IKeywordService keywordService;
	
	@Resource
	private IKeywordTaskService keywordTaskService;
	
	@Action("addKeywordPage")
	public String addKeyWordPage(){
		return "page";
	}
	
	@Action("addKeyword")
	public String addKeyword(){
		boolean succeed = true;
		IndexKeywords tempKeywords = keywordService.exactMatch(keyword.getWord());
		if(tempKeywords != null){
			message = "此关键词已经存在：" + keyword.getWord();
			return "page";
		}
		keyword.setInsertTime(new Date(System.currentTimeMillis()));
		keyword.setUpdateTime(new Date(System.currentTimeMillis()));
		keywordService.saveOrUpdate(keyword);
		
		keywordTaskService.addKeywordTask(keyword.getWord());
		
		return "redirect2success";
	}
	
	@Action("keywordStatusChange")
	public String statusChange(){
		boolean succeed = true;
		String msg = "";
		IndexKeywords keyword = keywordService.getByIndexId(keywordId);
		if(keyword!=null){
			keyword.setActive(status);
			keyword.setUpdateTime(new Date(System.currentTimeMillis()));
			keywordService.saveOrUpdate(keyword);
		} else {
			succeed = false;
			msg = "未找到相应关键词, id:" + keywordId;
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("succeed", succeed);
		jsonObject.put("msg", msg);
		jsonResult = jsonObject.toJSONString();
		return "json";
	}

	
	
	/*
	 * Getters and Setters
	 * */
	public String getJsonResult() {
		return jsonResult;
	}
	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}
	public Long getKeywordId() {
		return keywordId;
	}
	public void setKeywordId(Long keywordId) {
		this.keywordId = keywordId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public IndexKeywords getKeyword() {
		return keyword;
	}

	public void setKeyword(IndexKeywords keyword) {
		this.keyword = keyword;
	}

}
