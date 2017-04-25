package com.datayes.webspider.service.websiteOps;

import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.domain.websiteOps.WebSiteOpsExceptionRule;

public interface IWebSiteOpsExceptionRuleService {
	public void saveOrUpdate(WebSiteOpsExceptionRule webSiteOpsExceptionRule);
	
	public WebSiteOpsExceptionRule getExceptionRuleByRuleId(Integer ruleId);
	
	public PageDTO enquiryWebSiteOpsExceptionRulePage(String ruleName, Integer webSiteOpsId, Integer status, int pageNow, int pageSize);
}
