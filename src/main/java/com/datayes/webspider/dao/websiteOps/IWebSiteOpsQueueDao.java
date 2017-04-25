package com.datayes.webspider.dao.websiteOps;

import java.util.List;

import com.datayes.webspider.dao.IBaseDao;
import com.datayes.webspider.domain.websiteOps.WebSiteOperationQueue;

public interface IWebSiteOpsQueueDao extends IBaseDao<WebSiteOperationQueue> {
	public int enquiryWebSiteOpsQueueCount(String queueCnName, Integer webSiteOpsId, String status);
	
	public List enquiryWebSiteOpsQueuePage(String queueCnName, Integer webSiteOpsId, String status, int pageNow, int pageSize);
}
