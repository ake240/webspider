package com.datayes.webspider.dao.websiteOps;

import java.util.List;

import com.datayes.webspider.dao.IBaseDao;
import com.datayes.webspider.domain.websiteOps.WebSiteOperation;

public interface IWebSiteOpsDao extends IBaseDao<WebSiteOperation>{
	public Integer enquiryWebSiteOpsCount(String webSiteOpsName, Integer webSiteId, String status);
	
	public List enquiryWebSiteOpsPage(String webSiteOpsName, Integer webSiteId, String status, int pageNow, int pageSize);
}
