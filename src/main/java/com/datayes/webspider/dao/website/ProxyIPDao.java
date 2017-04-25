package com.datayes.webspider.dao.website;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;

import com.datayes.webspider.dao.BaseDao;
import com.datayes.webspider.domain.website.ProxyIP;

@Repository("proxyIPDao")
public class ProxyIPDao extends BaseDao<ProxyIP> implements IProxyIPDao {

	@Override
	public Integer enquiryProxyIPCount(String host, Integer webSiteId, Integer status) {
		DetachedCriteria dc = buildCriteria(host, webSiteId, status);
		return (Integer) findCount(dc);
	}

	@Override
	public List enquiryProxyIPPage(String host, Integer webSiteId, Integer status, int pageNow, int pageSize) {
		DetachedCriteria dc = buildCriteria(host, webSiteId, status);
		dc.addOrder(Order.desc("insertTime"));
		return findPage(dc, pageNow, pageSize);
	}
	
	private DetachedCriteria buildCriteria(String host, Integer webSiteId, Integer status){
		DetachedCriteria dc = DetachedCriteria.forClass(ProxyIP.class);
		
		if (!StringUtils.isEmpty(host)) {
			dc.add(Property.forName("host").like(host, MatchMode.ANYWHERE));
		}
		
		/*if (webSiteId != null) {
			dc.add(Property.forName("webSite.webSiteId").eq(webSiteId));
		}*/
		
		if (status != null) {
			dc.add(Property.forName("status").eq(status));
		}
		
		return dc;
	}

}
