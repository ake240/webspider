package com.datayes.webspider.dao.websiteOps;

import java.util.List;

import com.datayes.webspider.dao.IBaseDao;
import com.datayes.webspider.domain.websiteOps.WebSiteOpsAntiRule;

public interface IWebSiteOpsAntiRuleDao extends IBaseDao<WebSiteOpsAntiRule> {
	public int enquiryWebSiteOpsAntiRuleCount(String antiRuleName, Integer webSiteOpsId);
	
	public List enquiryWebSiteOpsAntiRulePage(String antiRuleName, Integer webSiteOpsId, int pageNow, int pageSize);
}
