package com.datayes.webspider.dao.websiteOps;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.datayes.webspider.dao.BaseDao;
import com.datayes.webspider.domain.websiteOps.WebSiteOperation;

@Repository("webSiteOpsDao")
public class WebSiteOpsDao extends BaseDao<WebSiteOperation> implements IWebSiteOpsDao {

	@Override
	public Integer enquiryWebSiteOpsCount(String webSiteOpsName, Integer webSiteId, String status) {
		DetachedCriteria criteria = buildDetachedCriteria(webSiteOpsName, webSiteId, status);
		return (Integer) findCount(criteria);
	}

	@Override
	public List enquiryWebSiteOpsPage(String webSiteOpsName, Integer webSiteId, String status, int pageNow, int pageSize) {
		DetachedCriteria criteria = buildDetachedCriteria(webSiteOpsName, webSiteId, status);
		criteria.addOrder(Order.desc("insertTime"));
		return findPage(criteria, pageNow, pageSize);
	}
	
	private DetachedCriteria buildDetachedCriteria(String webSiteOpsName, Integer webSiteId, String status) {
		DetachedCriteria criteria = DetachedCriteria.forClass(WebSiteOperation.class);
		if (!StringUtils.isEmpty(webSiteOpsName)) {
			criteria.add(Restrictions.ilike("webSiteOpsName", webSiteOpsName, MatchMode.ANYWHERE));
		}
		
		if (webSiteId != null) {
			criteria.add(Restrictions.eq("webSite.webSiteId", webSiteId));
		}
		
		if (!StringUtils.isEmpty(status)) {
//			criteria.add(Restrictions.eq("status", status));
		}
		
		return criteria;
	}
}
