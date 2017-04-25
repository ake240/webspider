package com.datayes.webspider.dao.websiteOps;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;

import com.datayes.webspider.dao.BaseDao;
import com.datayes.webspider.domain.websiteOps.WebSiteOpsAntiRule;

@Repository("webSiteOpsAntiRuleDao")
public class WebSiteOpsAntiRuleDao extends BaseDao<WebSiteOpsAntiRule> implements IWebSiteOpsAntiRuleDao {

	@Override
	public int enquiryWebSiteOpsAntiRuleCount(String antiRuleName, Integer webSiteOpsId) {
		DetachedCriteria dc = buildCriteria(antiRuleName, webSiteOpsId);
		return (Integer) findCount(dc);
	}

	@Override
	public List enquiryWebSiteOpsAntiRulePage(String antiRuleName, Integer webSiteOpsId, int pageNow, int pageSize) {
		DetachedCriteria dc = buildCriteria(antiRuleName, webSiteOpsId);
		dc.addOrder(Order.desc("insertTime"));
		return findPage(dc, pageNow, pageSize);
	}

	private DetachedCriteria buildCriteria(String antiRuleName, Integer webSiteOpsId){
		DetachedCriteria dc = DetachedCriteria.forClass(WebSiteOpsAntiRule.class);
		if (!StringUtils.isEmpty(antiRuleName)) {
			dc.add(Property.forName("antiRuleName").like(antiRuleName, MatchMode.ANYWHERE));
		}
		
		if (webSiteOpsId != null) {
			dc.add(Property.forName("webSiteOps.webSiteOpsId").eq(webSiteOpsId));
		}
		
		return dc;
	}
}
