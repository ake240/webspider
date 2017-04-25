package com.datayes.webspider.dao.website;

import java.util.List;

import com.datayes.webspider.dao.IBaseDao;
import com.datayes.webspider.domain.website.WebSite;

public interface IWebSiteDao extends IBaseDao<WebSite> {
	public WebSite getWebSiteByName(String webSiteName);
	
	public int enquiryWebSiteCount(String webSiteName, Integer categoryId, String status);
	
	public List<WebSite> enquiryWebSitePage(String webSiteName, Integer categoryId, String status, int pageNow, int pageSize);
}
