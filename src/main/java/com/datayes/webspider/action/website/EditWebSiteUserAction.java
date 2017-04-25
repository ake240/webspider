package com.datayes.webspider.action.website;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.alibaba.fastjson.JSONObject;
import com.datayes.webspider.action.BaseAction;
import com.datayes.webspider.domain.website.WebSite;
import com.datayes.webspider.domain.website.WebSiteUser;
import com.datayes.webspider.service.website.IWebSiteService;
import com.datayes.webspider.service.website.IWebSiteUserService;

@ParentPackage("webspider")
@Namespace("/")
@Results({ 
	@Result(name = "success", type = "redirectAction", params = {"actionName", "enquiryWebSite"} ),
	@Result(name = "json", type = "json", params = {"includeProperties", "jsonResult"})
})
public class EditWebSiteUserAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String account;
	private String password;
	private Integer webSiteId;
	private Integer webSiteUserId;
	private Integer status;
	private String jsonResult;
	
	@Resource
	private IWebSiteUserService webSiteUserService;
	
	@Resource
	private IWebSiteService webSiteService;
	
	@Action("newWebSiteUser")
	public String newWebSiteUser() {
		boolean succeed = true;
		String msg = "";
		
		if (!StringUtils.isEmpty(account) && !StringUtils.isEmpty(password) && webSiteId != null) {
			WebSiteUser webSiteUser = new WebSiteUser();
			webSiteUser.setAccount(account);
			webSiteUser.setPassword(password);
			webSiteUser.setStatus(1);
			webSiteUser.setUpdateTime(new Date());
			
			WebSite webSite = webSiteService.getWebSiteById(webSiteId);
			if (webSite != null) {
				webSiteUser.setWebSite(webSite);
				webSiteUserService.saveOrUpdate(webSiteUser);
			}else{
				succeed = false;
				msg = "找不到对应的网站, webSiteId:" + webSiteId;
			}
		}else{
			succeed = false;
			msg = "必填字段不允许为空";
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("succeed", succeed);
		jsonObject.put("msg", msg);
		this.jsonResult = jsonObject.toJSONString();
		return "json";
	}
	
	@Action("editWebSiteUser")
	public String editWebSiteUser() {
		boolean succeed = true;
		String msg = "";
		
		if (!StringUtils.isEmpty(account) && !StringUtils.isEmpty(password) && webSiteId != null && webSiteUserId != null) {
			WebSiteUser webSiteUserInDB = webSiteUserService.getWebSiteUserById(webSiteUserId);
			if (webSiteUserInDB != null) {
				webSiteUserInDB.setAccount(account);
				webSiteUserInDB.setPassword(password);
				webSiteUserInDB.setUpdateTime(new Date());
				
				if (!webSiteUserInDB.getWebSite().getWebSiteId().equals(webSiteId)) {
					WebSite webSite = webSiteService.getWebSiteById(webSiteId);
					if (webSite != null) {
						webSiteUserInDB.setWebSite(webSite);
					}else{
						succeed = false;
						msg = "找不到对应的网站, webSiteId:" + webSiteId;
					}
				}
				
				if (succeed) {
					webSiteUserService.saveOrUpdate(webSiteUserInDB);
				}
			}else{
				succeed = false;
				msg = "找不到对应的网站用户, webSiteUserId: " + webSiteUserId;
			}
		}else{
			succeed = false;
			msg = "必填字段不允许为空";
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("succeed", succeed);
		jsonObject.put("msg", msg);
		this.jsonResult = jsonObject.toJSONString();
		return "json";
	}
	
	@Action("changeWebSiteUserStatus")
	public String changeWebSiteUserStatus(){
		boolean succeed = true;
		String msg = "";
		
		if (webSiteUserId != null && status != null) {
			WebSiteUser webSiteUserInDB = webSiteUserService.getWebSiteUserById(webSiteUserId);
			if (webSiteUserInDB != null) {
				if (status == 1) {
					webSiteUserInDB.setMessage("");
				}
				webSiteUserInDB.setStatus(status);
				webSiteUserInDB.setUpdateTime(new Date());
				webSiteUserService.saveOrUpdate(webSiteUserInDB);
			}else{
				succeed = false;
				msg = "找不到对应的用户, webSiteUserId: " + webSiteUserId;
			}
		}else{
			succeed = false;
			msg = "用户ID为空, 无法获取用户信息";
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("succeed", succeed);
		jsonObject.put("msg", msg);
		jsonResult = jsonObject.toJSONString();
		return "json";
	}

	@Action("deleteWebSiteUser")
	public String deleteWebSiteUser() {
		return "json";
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getWebSiteId() {
		return webSiteId;
	}

	public void setWebSiteId(Integer webSiteId) {
		this.webSiteId = webSiteId;
	}

	public Integer getWebSiteUserId() {
		return webSiteUserId;
	}

	public void setWebSiteUserId(Integer webSiteUserId) {
		this.webSiteUserId = webSiteUserId;
	}

	public String getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
