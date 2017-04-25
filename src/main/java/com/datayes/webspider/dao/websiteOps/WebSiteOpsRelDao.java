package com.datayes.webspider.dao.websiteOps;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.datayes.webspider.dao.BaseDao;
import com.datayes.webspider.domain.websiteOps.WebSiteOpsRel;

@Repository("webSiteOpsRelDao")
public class WebSiteOpsRelDao extends BaseDao<WebSiteOpsRel> implements IWebSiteOpsRelDao {

	@Override
	public List<WebSiteOpsRel> enquiryChildrenList(int webSiteOpsId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(WebSiteOpsRel.class);
		criteria.add(Restrictions.eq("key.parentWebsiteOperationId", webSiteOpsId));
		return (List<WebSiteOpsRel>)getHibernateTemplate().findByCriteria(criteria);
//		return getHibernateTemplate().findByNamedQuery("enquiryChildren", webSiteOpsId);
	}

	@Override
	public List<WebSiteOpsRel> enquiryChildrenListNamedQuery(int webSiteOpsId) {
		return (List<WebSiteOpsRel>)getHibernateTemplate().findByNamedQuery("enquiryChildren", webSiteOpsId);
	}

	@Override
	public List<WebSiteOpsRel> enquiryParentList(int webSiteOpsId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(WebSiteOpsRel.class);
		criteria.add(Restrictions.eq("key.websiteOperationId", webSiteOpsId));
		return (List<WebSiteOpsRel>)getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	public boolean addList(List<WebSiteOpsRel> list) {
		try {
			getHibernateTemplate().saveOrUpdate(list);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean removeRecords(int webSiteOpsId) {
		try{
			getHibernateTemplate().deleteAll(enquiryParentList(webSiteOpsId));
			return true;
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
