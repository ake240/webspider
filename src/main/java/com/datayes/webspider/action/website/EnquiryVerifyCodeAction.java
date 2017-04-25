package com.datayes.webspider.action.website;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.datayes.webspider.action.BaseAction;
import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.constant.PageSizeConstants;
import com.datayes.webspider.domain.website.WebSiteVerifyCode;
import com.datayes.webspider.service.website.IWebSiteVerifyCodeService;

@ParentPackage("webspider")
@Namespace("/")
@Results({ 
	@Result(name = "NormalSearch", location = "/WEB-INF/jsp/website/enquiryVerifyCode.jsp"), 
	@Result(name = "AjaxSearch", location = "/WEB-INF/jsp/ajax/enquiryVerifyCodeResult.jsp"), 
	@Result(name = "json", type = "json", params = {"includeProperties","jsonResult"})
})
public class EnquiryVerifyCodeAction extends BaseAction {
	private static final Logger LOG = LoggerFactory.getLogger(EnquiryVerifyCodeAction.class);
	private Integer verifyCodeId;
	private String verifyCode;
	private Integer webSiteId;
	private PageDTO pageDTO;
	private String jsonResult;
	private int pageNow = 1;
	private int isAjaxSearch = 0;
	
	@Resource
	private IWebSiteVerifyCodeService webSiteVerifyCodeService;
	
	@Action("enquiryVerifyCode")
	public String enquiryVerifyCode(){
		pageDTO = webSiteVerifyCodeService.enquiryWebSiteVerifyCodePage(webSiteId, pageNow, PageSizeConstants.DEFAULT_SIZE);
		if (isAjaxSearch == 0) {
			return "NormalSearch";
		}
		return "AjaxSearch";
	}
	
	@Action("saveVerifyCode")
	public String saveVerifyCode(){
		boolean succeed = true;
		String message = "";
		
		if (verifyCodeId != null && !StringUtils.isEmpty(verifyCode)) {
			WebSiteVerifyCode  webSiteVerifyCode = webSiteVerifyCodeService.findByVerifyCodeId(verifyCodeId);
			if (webSiteVerifyCode != null) {
				webSiteVerifyCode.setVerifyCode(verifyCode);
				webSiteVerifyCode.setUpdateTime(new Date());
				webSiteVerifyCodeService.saveOrUpdate(webSiteVerifyCode);
			}else{
				succeed = false;
				message = "找不到对应的验证码, verifyCodeId: " + verifyCodeId;
			}
		} else {
			succeed = false;
			message = "验证码ID和验证码不允许为空";
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("succeed", succeed);
		jsonObject.put("msg", message);
		this.jsonResult = jsonObject.toJSONString();
		return "json";
	}

	public Integer getVerifyCodeId() {
		return verifyCodeId;
	}

	public void setVerifyCodeId(Integer verifyCodeId) {
		this.verifyCodeId = verifyCodeId;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
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

	public String getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}
	
}
