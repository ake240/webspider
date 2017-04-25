package com.datayes.webspider.service.websiteOps;

import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.domain.websiteOps.WebSiteOpsAntiRule;

public interface IWebSiteOpsAntiRuleService {
	
	public WebSiteOpsAntiRule getByAntiRuleId(Integer id);
	
	public WebSiteOpsAntiRule getByWebSiteOpsId(Integer webSiteOpsId);
	
	public void saveOrUpdate(WebSiteOpsAntiRule webSiteOpsAntiRule);
	
	public PageDTO enquiryWebSiteOpsAntiRulePage(String antiRuleName, Integer webSiteOpsId, int pageNow, int pageSize);
}
