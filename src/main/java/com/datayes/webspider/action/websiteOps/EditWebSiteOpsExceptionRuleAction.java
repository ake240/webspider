package com.datayes.webspider.action.websiteOps;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.alibaba.fastjson.JSONObject;
import com.datayes.webspider.action.BaseAction;
import com.datayes.webspider.domain.websiteOps.WebSiteOperation;
import com.datayes.webspider.domain.websiteOps.WebSiteOpsExceptionRule;
import com.datayes.webspider.service.websiteOps.IWebSiteOpsExceptionRuleService;
import com.datayes.webspider.service.websiteOps.IWebSiteOpsService;

@ParentPackage("webspider")
@Namespace("/")
@Results({ 
	@Result(name = "page", location = "/WEB-INF/jsp/websiteOps/editWebSiteOpsExceptionRule.jsp"),
	@Result(name = "redirect2success", location = "/WEB-INF/jsp/success.jsp"),
	@Result(name = "json", type="json", params={"includeProperties","jsonResult"}), 
})
public class EditWebSiteOpsExceptionRuleAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private WebSiteOpsExceptionRule webSiteOpsExceptionRule;
	private Integer exceptionRuleId;
	private Integer webSiteOpsId;
	private Integer status;
	private int type;
	private String message;
	private String jsonResult;
	
	@Resource
	private IWebSiteOpsExceptionRuleService webSiteOpsExceptionRuleService;
	
	@Resource
	private IWebSiteOpsService webSiteOpsService;
	
	@Action("webSiteOpsExceptionRule")
	public String webSiteOpsExceptionRule(){
		if (type == 1) {
			webSiteOpsExceptionRule = webSiteOpsExceptionRuleService.getExceptionRuleByRuleId(exceptionRuleId);
			this.webSiteOpsId = webSiteOpsExceptionRule.getWebSiteOps().getWebSiteOpsId();
		}
		
		return "page";
	}
	
	@Action("newWebSiteOpsExceptionRule")
	public String newWebSiteOpsExceptionRule(){
		boolean succeed = true;
		if (webSiteOpsExceptionRule != null && webSiteOpsId != null) {
			WebSiteOperation webSiteOps = webSiteOpsService.getWebSiteOpsById(webSiteOpsId);
			if (webSiteOps != null) {
				webSiteOpsExceptionRule.setWebSiteOps(webSiteOps);
				webSiteOpsExceptionRule.setUpdateTime(new Date());
				webSiteOpsExceptionRuleService.saveOrUpdate(webSiteOpsExceptionRule);
			}else{
				succeed = false;
				message = "找不到对应的操作类型, webSiteOpsId: " + webSiteOpsId;
			}
		}else{
			succeed = false;
			message = "必填字段不允许为空";
		}
		
		if (succeed) {
			return "redirect2success";
		}
		return "page";
	}
	
	@Action("editWebSiteOpsExceptionRule")
	public String editWebSiteOpsExceptionRule(){
		boolean succeed = true;
		if (webSiteOpsExceptionRule != null && webSiteOpsId != null && exceptionRuleId != null) {
			WebSiteOpsExceptionRule exceptionRuleInDB = webSiteOpsExceptionRuleService.getExceptionRuleByRuleId(exceptionRuleId);
			if (exceptionRuleInDB != null) {
				if (!exceptionRuleInDB.getWebSiteOps().getWebSiteOpsId().equals(webSiteOpsId)) {
					WebSiteOperation webSiteOps = webSiteOpsService.getWebSiteOpsById(webSiteOpsId);
					if (webSiteOps != null) {
						exceptionRuleInDB.setWebSiteOps(webSiteOps);
					}else{
						succeed = false;
						message = "找不到对应的操作类型, webSiteOpsId: " + webSiteOpsId;
					}
				}
				
				if (succeed) {
					exceptionRuleInDB.setExceptionRuleName(webSiteOpsExceptionRule.getExceptionRuleName());
					exceptionRuleInDB.setExprType(webSiteOpsExceptionRule.getExprType());
					exceptionRuleInDB.setExprVal(webSiteOpsExceptionRule.getExprVal());
					exceptionRuleInDB.setUpdateTime(new Date());
					exceptionRuleInDB.setExceptionType(webSiteOpsExceptionRule.getExceptionType());
					exceptionRuleInDB.setExceptionProcessClass(webSiteOpsExceptionRule.getExceptionProcessClass());
					exceptionRuleInDB.setExceptionProcessClassContent(webSiteOpsExceptionRule.getExceptionProcessClassContent());
					exceptionRuleInDB.setMatchValue(webSiteOpsExceptionRule.getMatchValue());
					exceptionRuleInDB.setExtroParam(webSiteOpsExceptionRule.getExtroParam());
					webSiteOpsExceptionRuleService.saveOrUpdate(exceptionRuleInDB);
				}
			}else{
				succeed = false;
				message = "找不到对应的异常监控, exceptionRuleId: " + exceptionRuleId;
			}
		}else{
			succeed = false;
			message = "必填字段不允许为空";
		}
		
		if (succeed) {
			return "redirect2success";
		}
		return "page";
	}
	
	@Action("changeExceptionRuleStatus")
	public String changeExceptionRuleStatus(){
		boolean succeed = true;
		String msg = "";
		
		if (exceptionRuleId != null && status != null) {
			WebSiteOpsExceptionRule exceptionRule = webSiteOpsExceptionRuleService.getExceptionRuleByRuleId(exceptionRuleId);
			if (exceptionRule != null) {
				exceptionRule.setStatus(status);
				exceptionRule.setUpdateTime(new Date());
				webSiteOpsExceptionRuleService.saveOrUpdate(exceptionRule);
			}else{
				succeed = false;
				msg = "找不到对应的操作类型异常监控, exceptionRuleId: " + exceptionRuleId;
			}
		}else{
			succeed = false;
			msg = "exceptionRuleId不允许为空";
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("succeed", succeed);
		jsonObject.put("msg", msg);
		this.jsonResult = jsonObject.toJSONString();
		return "json";
	}

	public WebSiteOpsExceptionRule getWebSiteOpsExceptionRule() {
		return webSiteOpsExceptionRule;
	}

	public void setWebSiteOpsExceptionRule(WebSiteOpsExceptionRule webSiteOpsExceptionRule) {
		this.webSiteOpsExceptionRule = webSiteOpsExceptionRule;
	}

	public Integer getExceptionRuleId() {
		return exceptionRuleId;
	}

	public void setExceptionRuleId(Integer exceptionRuleId) {
		this.exceptionRuleId = exceptionRuleId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Integer getWebSiteOpsId() {
		return webSiteOpsId;
	}

	public void setWebSiteOpsId(Integer webSiteOpsId) {
		this.webSiteOpsId = webSiteOpsId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
