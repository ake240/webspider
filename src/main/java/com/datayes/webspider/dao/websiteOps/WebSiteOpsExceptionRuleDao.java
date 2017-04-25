package com.datayes.webspider.dao.websiteOps;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;

import com.datayes.webspider.dao.BaseDao;
import com.datayes.webspider.domain.websiteOps.WebSiteOpsExceptionRule;

@Repository("webSiteOpsExceptionRuleDao")
public class WebSiteOpsExceptionRuleDao extends BaseDao<WebSiteOpsExceptionRule> implements IWebSiteOpsExceptionRuleDao {

	@Override
	public int enquiryWebSiteOpsExceptionRuleCount(String ruleName, Integer webSiteOpsId, Integer status) {
		DetachedCriteria dc = buildCriteria(ruleName, webSiteOpsId, status);
		return (Integer) findCount(dc);
	}

	@Override
	public List enquiryWebSiteOpsExceptionRulePage(String ruleName, Integer webSiteOpsId, Integer status, int pageNow,
			int pageSize) {
		DetachedCriteria dc = buildCriteria(ruleName, webSiteOpsId, status);
		dc.addOrder(Order.desc("insertTime"));
		return findPage(dc, pageNow, pageSize);
	}

	private DetachedCriteria buildCriteria(String ruleName, Integer webSiteOpsId, Integer status){
		DetachedCriteria dc = DetachedCriteria.forClass(WebSiteOpsExceptionRule.class);
		if (!StringUtils.isEmpty(ruleName)) {
			dc.add(Property.forName("exceptionRuleName").like(ruleName, MatchMode.ANYWHERE));
		}
		
		if (webSiteOpsId != null) {
			dc.add(Property.forName("webSiteOps.webSiteOpsId").eq(webSiteOpsId));
		}
		
		if (status != null) {
			dc.add(Property.forName("status").eq(status));
		}
		
		return dc;
	}
}
