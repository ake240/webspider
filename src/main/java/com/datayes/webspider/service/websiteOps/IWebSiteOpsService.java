package com.datayes.webspider.service.websiteOps;

import java.util.List;

import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.domain.websiteOps.WebSiteOperation;

public interface IWebSiteOpsService {
	public Integer enquiryWebSiteOpsCount(String webSiteOpsName, Integer webSiteId, String status);
	
	public PageDTO enquiryWebSiteOpsPage(String webSiteOpsName, Integer webSiteId, String status, int pageNow, int pageSize);

	public List<WebSiteOperation> getAllWebSiteOps();
	
	public WebSiteOperation getWebSiteOpsById(Integer id);
	
	public void saveOrUpdate(WebSiteOperation webSiteOps);
	
	public WebSiteOperation findByName(String name);
	
	public void deleteWebSiteOps(WebSiteOperation webSiteOps);
}
