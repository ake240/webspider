package com.datayes.webspider.service.website;

import java.util.List;

import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.domain.website.WebSite;

public interface IWebSiteService {
	public PageDTO enquiryWebSitePage(String webSiteName, Integer categoryId, String status, int pageNow, int pageSize);

	public WebSite getWebSiteById(Integer webSiteId);
	
	public void saveOrUpdate(WebSite webSite);
	
	public void removeWebSite(WebSite webSite);
	
	public WebSite getWebSiteByName(String webSiteName);
	
	public List<WebSite> getAllWebSite();
}
