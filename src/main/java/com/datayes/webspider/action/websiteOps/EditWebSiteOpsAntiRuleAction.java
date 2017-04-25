package com.datayes.webspider.action.websiteOps;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.datayes.webspider.action.BaseAction;
import com.datayes.webspider.domain.websiteOps.WebSiteOperation;
import com.datayes.webspider.domain.websiteOps.WebSiteOpsAntiRule;
import com.datayes.webspider.service.websiteOps.IWebSiteOpsAntiRuleService;
import com.datayes.webspider.service.websiteOps.IWebSiteOpsService;

@ParentPackage("webspider")
@Namespace("/")
@Results({ 
	@Result(name = "page", location = "/WEB-INF/jsp/websiteOps/editWebSiteOpsAntiRule.jsp"),
	@Result(name = "redirect2success", location = "/WEB-INF/jsp/success.jsp"),
	@Result(name = "json", type="json", params={"includeProperties","jsonResult"}), 
})
public class EditWebSiteOpsAntiRuleAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private WebSiteOpsAntiRule webSiteOpsAntiRule;
	private Integer webSiteOpsId;
	private Integer antiRuleId;
	private int type;
	private String message;
	
	@Resource
	private IWebSiteOpsAntiRuleService webSiteOpsAntiRuleService;
	
	@Resource
	private IWebSiteOpsService webSiteOpsService;
	
	@Action("webSiteOpsAntiRule")
	public String webSiteOpsAntiRule(){
		if (type == 1) {
			webSiteOpsAntiRule = webSiteOpsAntiRuleService.getByAntiRuleId(antiRuleId);
			this.webSiteOpsId = webSiteOpsAntiRule.getWebSiteOps().getWebSiteOpsId();
		}
		return "page";
	}
	
	@Action("newWebSiteOpsAntiRule")
	public String newWebSiteOpsAntiRule(){
		boolean succeed = true;
		if (webSiteOpsAntiRule != null && webSiteOpsId != null) {
			WebSiteOpsAntiRule ruleExist = webSiteOpsAntiRuleService.getByWebSiteOpsId(webSiteOpsId);
			if (ruleExist == null) {
				WebSiteOperation webSiteOps = webSiteOpsService.getWebSiteOpsById(webSiteOpsId);
				if (webSiteOps != null) {
					webSiteOpsAntiRule.setWebSiteOps(webSiteOps);
					webSiteOpsAntiRule.setUpdateTime(new Date());
					webSiteOpsAntiRuleService.saveOrUpdate(webSiteOpsAntiRule);
				}else{
					succeed = false;
					message = "找不到对应的操作类型, webSiteOpsId: " + webSiteOpsId;
				}
			}else{
				succeed = false;
				message = "该操作类型已经配置了反监控, webSiteOpsId: " + webSiteOpsId;
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
	
	@Action("editWebSiteOpsAntiRule")
	public String editWebSiteOpsAntiRule(){
		boolean succeed = true;
		if (webSiteOpsAntiRule != null && webSiteOpsId != null && antiRuleId != null) {
			WebSiteOpsAntiRule antiRuleInDB = webSiteOpsAntiRuleService.getByAntiRuleId(antiRuleId);
			if (antiRuleInDB != null) {
				if (!webSiteOpsId.equals(antiRuleInDB.getWebSiteOps().getWebSiteOpsId())) {
					WebSiteOpsAntiRule antiRuleExist = webSiteOpsAntiRuleService.getByWebSiteOpsId(webSiteOpsId);
					if (antiRuleExist == null) {
						WebSiteOperation webSiteOps = webSiteOpsService.getWebSiteOpsById(webSiteOpsId);
						if (webSiteOps != null) {
							antiRuleInDB.setWebSiteOps(webSiteOps);
						}else{
							succeed = false;
							message = "找不到对应的网站操作类型, webSiteOpsId: " + webSiteOpsId;
						}
					}else{
						succeed = false;
						message = "该操作类型已经配置了反监控, webSiteOpsId: " + webSiteOpsId;
					}
				}
				
				if (succeed) {
					antiRuleInDB.setAntiRuleName(webSiteOpsAntiRule.getAntiRuleName());
					antiRuleInDB.setUserRequestIntervalStart(webSiteOpsAntiRule.getUserRequestIntervalStart());
					antiRuleInDB.setUserRequestIntervalEnd(webSiteOpsAntiRule.getUserRequestIntervalEnd());
					antiRuleInDB.setUserRequestSeconds(webSiteOpsAntiRule.getUserRequestSeconds());
					antiRuleInDB.setUserRequestCountInSeconds(webSiteOpsAntiRule.getUserRequestCountInSeconds());
					antiRuleInDB.setUserRequestHour(webSiteOpsAntiRule.getUserRequestHour());
					antiRuleInDB.setUserRequestCountPerHour(webSiteOpsAntiRule.getUserRequestCountPerHour());
					antiRuleInDB.setUserRequestCountPerDay(webSiteOpsAntiRule.getUserRequestCountPerDay());
					antiRuleInDB.setIpRequestIntervalStart(webSiteOpsAntiRule.getIpRequestIntervalStart());
					antiRuleInDB.setIpRequestIntervalEnd(webSiteOpsAntiRule.getIpRequestIntervalEnd());
					antiRuleInDB.setIpRequestSeconds(webSiteOpsAntiRule.getIpRequestSeconds());
					antiRuleInDB.setIpRequestCountInSeconds(webSiteOpsAntiRule.getIpRequestCountInSeconds());
					antiRuleInDB.setIpRequestHour(webSiteOpsAntiRule.getIpRequestHour());
					antiRuleInDB.setIpRequestCountPerHour(webSiteOpsAntiRule.getIpRequestCountPerHour());
					antiRuleInDB.setIpRequestCountPerDay(webSiteOpsAntiRule.getIpRequestCountPerDay());
					antiRuleInDB.setUpdateTime(new Date());
					webSiteOpsAntiRuleService.saveOrUpdate(antiRuleInDB);
				}
			}else{
				succeed = false;
				message = "找不到对应的反监控, antiRuleId: " + antiRuleId;
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

	public WebSiteOpsAntiRule getWebSiteOpsAntiRule() {
		return webSiteOpsAntiRule;
	}

	public void setWebSiteOpsAntiRule(WebSiteOpsAntiRule webSiteOpsAntiRule) {
		this.webSiteOpsAntiRule = webSiteOpsAntiRule;
	}

	public Integer getWebSiteOpsId() {
		return webSiteOpsId;
	}

	public void setWebSiteOpsId(Integer webSiteOpsId) {
		this.webSiteOpsId = webSiteOpsId;
	}

	public Integer getAntiRuleId() {
		return antiRuleId;
	}

	public void setAntiRuleId(Integer antiRuleId) {
		this.antiRuleId = antiRuleId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
