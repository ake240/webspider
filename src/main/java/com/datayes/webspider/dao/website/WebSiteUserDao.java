package com.datayes.webspider.dao.website;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.datayes.webspider.dao.BaseDao;
import com.datayes.webspider.domain.website.WebSiteUser;

@Repository("webSiteUserDao")
public class WebSiteUserDao extends BaseDao<WebSiteUser> implements IWebSiteUserDao {

	@Override
	public int enquiryWebSiteUserCount(String account, Integer webSiteId, String status) {
		DetachedCriteria criteria = buildDetachedCriteria(account, webSiteId, status);
		return (Integer) findCount(criteria);
	}

	@Override
	public List enquiryWebSiteUserPage(String account, Integer webSiteId, String status, int pageNow, int pageSize) {
		DetachedCriteria criteria = buildDetachedCriteria(account, webSiteId, status);
		criteria.addOrder(Order.desc("insertTime"));
		return findPage(criteria, pageNow, pageSize);
	}

	private DetachedCriteria buildDetachedCriteria(String account, Integer webSiteId, String status){
		DetachedCriteria criteria = DetachedCriteria.forClass(WebSiteUser.class);
		if (!StringUtils.isEmpty(account)) {
			criteria.add(Restrictions.ilike("account", account, MatchMode.ANYWHERE));
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
