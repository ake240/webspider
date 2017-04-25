package com.datayes.webspider.dao.websiteOps;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;

import com.datayes.webspider.dao.BaseDao;
import com.datayes.webspider.domain.websiteOps.WebSiteOperationQueue;

@Repository("webSiteOpsQueueDao")
public class WebSiteOpsQueueDao extends BaseDao<WebSiteOperationQueue> implements IWebSiteOpsQueueDao {

	@Override
	public int enquiryWebSiteOpsQueueCount(String queueCnName, Integer webSiteOpsId, String status) {
		DetachedCriteria dc = buildCriteria(queueCnName, webSiteOpsId, status);
		return (Integer) findCount(dc);
	}

	@Override
	public List enquiryWebSiteOpsQueuePage(String queueCnName, Integer webSiteOpsId, String status, int pageNow,
			int pageSize) {
		DetachedCriteria dc = buildCriteria(queueCnName, webSiteOpsId, status);
		dc.addOrder(Order.desc("insertTime"));
		return findPage(dc, pageNow, pageSize);
	}
	
	private DetachedCriteria buildCriteria(String queueCnName, Integer webSiteOpsId, String status){
		DetachedCriteria dc = DetachedCriteria.forClass(WebSiteOperationQueue.class);
		if (!StringUtils.isEmpty(queueCnName)) {
			dc.add(Property.forName("queueCnName").like(queueCnName, MatchMode.ANYWHERE));
		}
		
		if (webSiteOpsId != null) {
			dc.add(Property.forName("webSiteOps.webSiteOpsId").eq(webSiteOpsId));
		}
		
		//status
		
		return dc;
	}

}
