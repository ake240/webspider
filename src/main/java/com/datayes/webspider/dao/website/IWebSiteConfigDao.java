package com.datayes.webspider.dao.website;

import java.util.List;

import com.datayes.webspider.dao.IBaseDao;
import com.datayes.webspider.domain.website.WebSiteConfig;

public interface IWebSiteConfigDao extends IBaseDao<WebSiteConfig> {
	public Integer enquiryWebSiteConfigCount(Integer webSiteId, String name);
	
	public List enquiryWebSiteConfigPage(Integer webSiteId, String name, int pageNow, int pageSize);
}
