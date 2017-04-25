package com.datayes.webspider.dao.websiteOps;

import java.util.List;

import com.datayes.webspider.dao.IBaseDao;
import com.datayes.webspider.domain.websiteOps.WebSiteOpsExceptionRule;

public interface IWebSiteOpsExceptionRuleDao extends IBaseDao<WebSiteOpsExceptionRule> {
	public int enquiryWebSiteOpsExceptionRuleCount(String ruleName, Integer webSiteOpsId, Integer status);
	
	public List enquiryWebSiteOpsExceptionRulePage(String ruleName, Integer webSiteOpsId, Integer status, int pageNow, int pageSize);
}
