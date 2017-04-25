package com.datayes.webspider.action.website;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.alibaba.fastjson.JSONObject;
import com.datayes.webspider.action.BaseAction;
import com.datayes.webspider.domain.website.Category;
import com.datayes.webspider.domain.website.WebSite;
import com.datayes.webspider.service.website.ICategoryService;
import com.datayes.webspider.service.website.IWebSiteService;

@ParentPackage("webspider")
@Namespace("/")
@Results({ 
	@Result(name = "success", type = "redirectAction", params = {"actionName", "enquiryWebSite"} ),
	@Result(name = "json", type = "json", params = {"includeProperties", "jsonResult"})
})
public class EditWebSiteAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private String webSiteName;
	private Integer[] categoryIds;
	private Integer webSiteId;
	private String jsonResult;
	
	@Resource
	private IWebSiteService webSiteService;
	
	@Resource
	private ICategoryService categoryService;
	
	@Action("newWebSite")
	public String newWebSite(){
		boolean succeed = true;
		String msg = "";
		if (!StringUtils.isEmpty(webSiteName) && categoryIds != null) {
			WebSite webSite = webSiteService.getWebSiteByName(webSiteName);
			
			if (webSite == null) {
				webSite = new WebSite();
				webSite.setWebSiteName(webSiteName);
				webSite.setUpdateTime(new Date());
				
				Set<Category> set = new HashSet<Category>();
				for (Integer categoryId : categoryIds) {
					if (categoryId != null) {
						Category category = categoryService.getCategoryById(categoryId);
						if (category != null) {
							set.add(category);
						}else{
							succeed = false;
							msg = "找不到对应的网站分类,categoryId：" + categoryId ;
							break;
						}
					}
				}
				
				if (succeed) {
					webSite.setCategories(set);
					webSiteService.saveOrUpdate(webSite);
				}
			}else{
				succeed = false;
				msg = "此网站已经存在";
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
	
	@Action("editWebSite")
	public String editWebSite(){
		boolean succeed = true;
		String msg = "";
		if (webSiteId != null && !StringUtils.isEmpty(webSiteName) && categoryIds != null) {
			WebSite webSite = webSiteService.getWebSiteById(webSiteId);
			if (webSite != null) {
				webSite.setWebSiteName(webSiteName);
				webSite.setUpdateTime(new Date());
				Set<Category> set = webSite.getCategories();
				set.clear();
				for (Integer categoryId : categoryIds) {
					if (categoryId != null) {
						Category category = categoryService.getCategoryById(categoryId);
						if (category != null) {
							set.add(category);
						}else{
							succeed = false;
							msg = "找不到对应的网站分类,categoryId：" + categoryId ;
							break;
						}
					}
				}
				
				if (succeed) {
					webSiteService.saveOrUpdate(webSite);
				}
			}else{
				succeed = false;
				msg = "找不到对应的网站, webSiteId=" + webSiteId;
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
	
	@Action("removeWebSite")
	public String removeWebSite(){
		return "json";
	}

	public String getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}

	public String getWebSiteName() {
		return webSiteName;
	}

	public void setWebSiteName(String webSiteName) {
		this.webSiteName = webSiteName;
	}

	public Integer[] getCategoryIds() {
		return categoryIds;
	}

	public void setCategoryIds(Integer[] categoryIds) {
		this.categoryIds = categoryIds;
	}

	public Integer getWebSiteId() {
		return webSiteId;
	}

	public void setWebSiteId(Integer webSiteId) {
		this.webSiteId = webSiteId;
	}
	
}
