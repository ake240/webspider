package com.datayes.webspider.dao.website;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.datayes.webspider.dao.BaseDao;
import com.datayes.webspider.domain.website.Category;

@Repository("categoryDao")
public class CategoryDao extends BaseDao<Category> implements ICategoryDao {

	@Override
	public Category getCategoryByName(String categoryName) {
		List list = getHibernateTemplate().findByNamedQuery("getCategoryByName", categoryName);
		if (!list.isEmpty()) {
			return (Category) list.get(0);
		}
		return null;
	}

	@Override
	public int enquiryCategoryCount(String categoryName, String status) {
		DetachedCriteria criteria = buildDetachedCriteria(categoryName, status);
		return (Integer) findCount(criteria);
	}

	@Override
	public List<Category> enquiryCategoryPage(String categoryName, String status, int pageNow, int pageSize) {
		DetachedCriteria criteria = buildDetachedCriteria(categoryName, status);
		criteria.addOrder(Order.desc("insertTime"));
		return findPage(criteria, pageNow, pageSize);
	}
	
	private DetachedCriteria buildDetachedCriteria(String categoryName, String status){
		DetachedCriteria criteria = DetachedCriteria.forClass(Category.class);
		if (!StringUtils.isEmpty(categoryName)) {
			criteria.add(Restrictions.ilike("categoryName", categoryName, MatchMode.ANYWHERE));
		}
		
		if (!StringUtils.isEmpty(status)) {
//			criteria.add(Restrictions.eq("status", status));
		}
		
		return criteria;
	}

}
