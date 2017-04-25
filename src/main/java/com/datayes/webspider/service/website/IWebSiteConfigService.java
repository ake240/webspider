package com.datayes.webspider.service.website;

import java.util.List;

import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.domain.website.WebSiteConfig;

public interface IWebSiteConfigService {
	public List<WebSiteConfig> getAllWebSiteConfig();
	
	public WebSiteConfig getById(Integer id);
	
	public WebSiteConfig getByWebSiteId(Integer webSiteId);
	
	public void saveOrUpdate(WebSiteConfig webSiteConfig);
	
	public PageDTO enquiryWebSiteConfigPage(Integer webSiteId, String name, int pageNow, int pageSize);
}
