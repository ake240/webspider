package com.datayes.webspider.action.website;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.datayes.webspider.action.BaseAction;
import com.datayes.webspider.domain.website.WebSite;
import com.datayes.webspider.domain.website.WebSiteConfig;
import com.datayes.webspider.service.website.IWebSiteConfigService;
import com.datayes.webspider.service.website.IWebSiteService;

@ParentPackage("webspider")
@Namespace("/")
@Results({ 
	@Result(name = "page", location = "/WEB-INF/jsp/website/editWebSiteConfig.jsp"),
	@Result(name = "redirect2success", location = "/WEB-INF/jsp/success.jsp"),
	@Result(name = "json", type="json", params={"includeProperties","jsonResult"}), 
})
public class EditWebSiteConfigAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private WebSiteConfig webSiteConfig;
	private Integer webSiteConfigId;
	private Integer webSiteId;
	private int type;
	private String jsonResult;
	private String message;
	
	@Resource
	private IWebSiteConfigService webSiteConfigService;
	
	@Resource
	private IWebSiteService webSiteService;
	
	@Action("webSiteConfig")
	public String webSiteConfig(){
		if (type==1) {
			webSiteConfig = webSiteConfigService.getById(webSiteConfigId);
			this.webSiteId = webSiteConfig.getWebSite().getWebSiteId();
		}
		return "page";
	}
	
	@Action("newWebSiteConfig")
	public String newWebSiteConfig(){
		boolean succeed = true;
		if (webSiteConfig != null && webSiteId != null) {
			WebSite webSite = webSiteService.getWebSiteById(webSiteId);
			if (webSite  != null) {
				WebSiteConfig existConfig = webSiteConfigService.getByWebSiteId(webSiteId);
				
				webSiteConfig.setWebSite(webSite);
				if (webSiteConfig.getNeedLogin() == 0) {
					webSiteConfig.setLoginClass("");
				}
				webSiteConfig.setUpdateTime(new Date());
				webSiteConfigService.saveOrUpdate(webSiteConfig);
				
//				webSiteConfig.setUpdateTime(new Date());
//				webSiteConfigService.saveOrUpdate(webSiteConfig);
//				if (existConfig == null) {
//					webSiteConfig.setWebSite(webSite);
//					if (webSiteConfig.getNeedLogin() == 0) {
//						webSiteConfig.setLoginClass("");
//					}
//					webSiteConfig.setUpdateTime(new Date());
//					webSiteConfigService.saveOrUpdate(webSiteConfig);
//				}else{
//					succeed = false;
//					message = "所属网站已经存在数据源配置, webSiteId: " + webSiteId;
//				}
			}else{
				succeed = false;
				message = "找不到对应的网站, webSiteId: " + webSiteId;
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
	
	@Action("editWebSiteConfig")
	public String editWebSiteConfig(){
		boolean succeed = true;
		if (webSiteConfig != null && webSiteConfigId != null && webSiteId != null) {
			WebSiteConfig webSiteConfigInDB = webSiteConfigService.getById(webSiteConfigId);
			if (webSiteConfigInDB != null) {
				if (!webSiteId.equals(webSiteConfigInDB.getWebSite().getWebSiteId())) {
					WebSiteConfig existConfig = webSiteConfigService.getByWebSiteId(webSiteId);
					if (existConfig == null) {
						WebSite webSite = webSiteService.getWebSiteById(webSiteId);
						if (webSite != null) {
							webSiteConfigInDB.setWebSite(webSite);
						}else{
							succeed = false;
							message = "找不到对应的网站, webSiteId: " + webSiteId;
						}
					}else{
						succeed = false;
						message = "所属网站已经存在数据源配置, webSiteId: " + webSiteId;
					}
				}
				
				if (succeed) {
					webSiteConfigInDB.setWebSiteConfigName(webSiteConfig.getWebSiteConfigName());
					webSiteConfigInDB.setNeedProxyIP(webSiteConfig.getNeedProxyIP());
					webSiteConfigInDB.setNeedFixedProxyIP(webSiteConfig.getNeedFixedProxyIP());
					webSiteConfigInDB.setDescription(webSiteConfig.getDescription());
					webSiteConfigInDB.setNeedLogin(webSiteConfig.getNeedLogin());
					if (webSiteConfig.getNeedLogin() == 0) {
						webSiteConfigInDB.setLoginClass("");
					}else{
						webSiteConfigInDB.setLoginClass(webSiteConfig.getLoginClass());
					}
					webSiteConfigInDB.setUserLoginIntervalStart(webSiteConfig.getUserLoginIntervalStart());
					webSiteConfigInDB.setUserLoginIntervalEnd(webSiteConfig.getUserLoginIntervalEnd());
					webSiteConfigInDB.setUserLoginMinutes(webSiteConfig.getUserLoginMinutes());
					webSiteConfigInDB.setUserLoginCountInMinutes(webSiteConfig.getUserLoginCountInMinutes());
					webSiteConfigInDB.setUserLoginHour(webSiteConfig.getUserLoginHour());
					webSiteConfigInDB.setUserLoginCountPerHour(webSiteConfig.getUserLoginCountPerHour());
					webSiteConfigInDB.setUserLoginCountPerDay(webSiteConfig.getUserLoginCountPerDay());
					webSiteConfigInDB.setIpLoginIntervalStart(webSiteConfig.getIpLoginIntervalStart());
					webSiteConfigInDB.setIpLoginIntervalEnd(webSiteConfig.getIpLoginIntervalEnd());
					webSiteConfigInDB.setIpLoginMinutes(webSiteConfig.getIpLoginMinutes());
					webSiteConfigInDB.setIpLoginUserCountInMinutes(webSiteConfig.getIpLoginUserCountInMinutes());
					webSiteConfigInDB.setIpLoginHour(webSiteConfig.getIpLoginHour());
					webSiteConfigInDB.setIpLoginUserCountPerHour(webSiteConfig.getIpLoginUserCountPerHour());
					webSiteConfigInDB.setIpLoginUserCountPerDay(webSiteConfig.getIpLoginUserCountPerDay());
					webSiteConfigInDB.setUpdateTime(new Date());
					webSiteConfigService.saveOrUpdate(webSiteConfigInDB);
				}
			}else{
				succeed = false;
				message = "找不到对应的数据源配置, webSiteConfigId: " + webSiteConfigId;
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

	public WebSiteConfig getWebSiteConfig() {
		return webSiteConfig;
	}

	public void setWebSiteConfig(WebSiteConfig webSiteConfig) {
		this.webSiteConfig = webSiteConfig;
	}

	public Integer getWebSiteConfigId() {
		return webSiteConfigId;
	}

	public void setWebSiteConfigId(Integer webSiteConfigId) {
		this.webSiteConfigId = webSiteConfigId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getWebSiteId() {
		return webSiteId;
	}

	public void setWebSiteId(Integer webSiteId) {
		this.webSiteId = webSiteId;
	}
	
}
