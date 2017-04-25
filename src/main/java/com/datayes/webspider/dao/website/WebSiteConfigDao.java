package com.datayes.webspider.dao.website;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;

import com.datayes.webspider.dao.BaseDao;
import com.datayes.webspider.domain.website.WebSiteConfig;

@Repository("webSiteConfigDao")
public class WebSiteConfigDao extends BaseDao<WebSiteConfig> implements IWebSiteConfigDao {

	@Override
	public Integer enquiryWebSiteConfigCount(Integer webSiteId, String name) {
		DetachedCriteria dc = buildCriteria(webSiteId, name);
		return (Integer) findCount(dc);
	}

	@Override
	public List enquiryWebSiteConfigPage(Integer webSiteId, String name, int pageNow, int pageSize) {
		DetachedCriteria dc = buildCriteria(webSiteId, name);
		dc.addOrder(Order.desc("insertTime"));
		return findPage(dc, pageNow, pageSize);
	}

	private DetachedCriteria buildCriteria(Integer webSiteId, String name){
		DetachedCriteria dc = DetachedCriteria.forClass(WebSiteConfig.class);
		if (webSiteId != null) {
			dc.add(Property.forName("webSite.webSiteId").eq(webSiteId));
		}
		
		if (!StringUtils.isEmpty(name)) {
			dc.add(Property.forName("webSiteConfigName").like(name, MatchMode.ANYWHERE));
		}
		
		return dc;
	}
}
