package com.datayes.webspider.dao.websiteOps;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.datayes.webspider.dao.BaseDao;
import com.datayes.webspider.domain.websiteOps.DataStoreType;

@Repository("dataStoreTypeDao")
public class DataStoreTypeDao extends BaseDao<DataStoreType> implements IDataStoreTypeDao {

	@Override
	public Integer enquiryDataStoreTypeCount(String name) {
		DetachedCriteria criteria = buildCriteria(name);
		return (Integer) findCount(criteria);
	}

	@Override
	public List enquiryDataStoreTypePage(String name, int pageNow, int pageSize) {
		DetachedCriteria criteria = buildCriteria(name);
		criteria.addOrder(Order.desc("insertTime"));
		return findPage(criteria, pageNow, pageSize);
	}

	private DetachedCriteria buildCriteria(String name){
		DetachedCriteria criteria = DetachedCriteria.forClass(DataStoreType.class);
		if (!StringUtils.isEmpty(name)) {
			criteria.add(Restrictions.like("dataStoreTypeCnName", name, MatchMode.ANYWHERE));
		}
		
		return criteria;
	}

}
