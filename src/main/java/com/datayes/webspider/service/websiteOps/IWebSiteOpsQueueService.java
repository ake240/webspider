package com.datayes.webspider.service.websiteOps;

import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.domain.websiteOps.WebSiteOperationQueue;

public interface IWebSiteOpsQueueService {
	public WebSiteOperationQueue getWebSiteOpsQueueById(Integer id);
	
	public WebSiteOperationQueue getWebSiteOpsQueueByEnName(String name);
	
	public void saveOrUpdate(WebSiteOperationQueue webSiteOperationQueue);
	
	public void deleteWebSiteOpsQueue(WebSiteOperationQueue webSiteOperationQueue);
	
	public PageDTO enquiryWebSiteOpsQueuePage(String queueCnName, Integer webSiteOpsId, String status, int pageNow, int pageSize);
}
