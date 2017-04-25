package com.datayes.webspider.dao.website;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.datayes.webspider.dao.BaseDao;
import com.datayes.webspider.domain.website.WebSite;
import com.datayes.webspider.domain.website.WebSiteCategoryPK;
import com.datayes.webspider.domain.website.WebSiteCategoryRel;

@Repository("webSiteDao")
public class WebSiteDao extends BaseDao<WebSite> implements IWebSiteDao {

	@Override
	public WebSite getWebSiteByName(String webSiteName) {
		List list = getHibernateTemplate().findByNamedQuery("getWebSiteByName", webSiteName);
		if (!list.isEmpty()) {
			return (WebSite) list.get(0);
		}
		return null;
	}

	@Override
	public int enquiryWebSiteCount(String webSiteName, Integer categoryId, String status) {
		DetachedCriteria criteria = buildDetachedCriteria(webSiteName, categoryId, status);
		return (Integer) findCount(criteria);
	}

	@Override
	public List<WebSite> enquiryWebSitePage(String webSiteName, Integer categoryId, String status, int pageNow,
			int pageSize) {
		DetachedCriteria criteria = buildDetachedCriteria(webSiteName, categoryId, status);
		criteria.addOrder(Order.desc("insertTime"));
		return findPage(criteria, pageNow, pageSize);
	}

	private DetachedCriteria buildDetachedCriteria(String webSiteName, Integer categoryId, String status){
		DetachedCriteria criteria = DetachedCriteria.forClass(WebSite.class);
		if (!StringUtils.isEmpty(webSiteName)) {
			criteria.add(Property.forName("webSiteName").like(webSiteName, MatchMode.ANYWHERE));
		}
		
		if (categoryId != null) {
			DetachedCriteria subCriteria = DetachedCriteria.forClass(WebSiteCategoryRel.class).setProjection(Property.forName("webSiteCategoryPK.webSite.webSiteId"));
			subCriteria.add(Property.forName("webSiteCategoryPK.category.categoryId").eq(categoryId));
			criteria.add(Property.forName("webSiteId").in(subCriteria));
		}
		
		if (!StringUtils.isEmpty(status)) {
//			criteria.add(Restrictions.eq("status", status));
		}
		
		return criteria;
	}
	
}
