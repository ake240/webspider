package com.datayes.webspider.dao.website;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.datayes.webspider.dao.BaseDao;
import com.datayes.webspider.domain.website.WebSiteVerifyCode;

@Repository("webSiteVerifyCodeDao")
public class WebSiteVerifyCodeDao extends BaseDao<WebSiteVerifyCode> implements IWebSiteVerifyCodeDao {

	@Override
	public Integer enquiryWebSiteVerifyCodeCount(Integer webSiteId) {
		DetachedCriteria criteria = buildCriteria(webSiteId);
		return (Integer) findCount(criteria);
	}

	@Override
	public List enquiryWebSiteVerifyCodePage(Integer webSiteId, int pageNow, int pageSize) {
		DetachedCriteria criteria = buildCriteria(webSiteId);
		criteria.addOrder(Order.desc("insertTime"));
		return findPage(criteria, pageNow, pageSize);
	}
	
	private DetachedCriteria buildCriteria(Integer webSiteId){
		DetachedCriteria criteria = DetachedCriteria.forClass(WebSiteVerifyCode.class);
		if (webSiteId != null) {
			criteria.add(Restrictions.eq("webSite.webSiteId", webSiteId));
		}
		
		return criteria;
	}

}
