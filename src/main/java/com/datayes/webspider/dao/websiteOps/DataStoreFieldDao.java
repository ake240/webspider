package com.datayes.webspider.dao.websiteOps;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.datayes.webspider.dao.BaseDao;
import com.datayes.webspider.domain.websiteOps.DataStoreField;

@Repository("dataStoreFieldDao")
public class DataStoreFieldDao extends BaseDao<DataStoreField> implements IDataStoreFieldDao {

	@Override
	public Integer enquiryDataStoreFieldCount(Integer typeId) {
		DetachedCriteria criteria = buildCriteria(typeId);
		return (Integer) findCount(criteria);
	}

	@Override
	public List enquiryDataStoreFieldPage(Integer typeId, int pageNow, int pageSize) {
		DetachedCriteria criteria = buildCriteria(typeId);
		criteria.addOrder(Order.desc("insertTime"));
		return findPage(criteria, pageNow, pageSize);
	}
	
	private DetachedCriteria buildCriteria(Integer typeId){
		DetachedCriteria criteria = DetachedCriteria.forClass(DataStoreField.class);
		if (typeId != null) {
			criteria.add(Restrictions.eq("dataStoreType.dataStoreTypeId", typeId));
		}
		return criteria;
	}
	
	@Override
	public DataStoreField getDataStoreFieldByFieldCnName(String fieldCnName,Integer dataStoreTypeId) {
		List<DataStoreField> list = (List<DataStoreField>)getHibernateTemplate().findByNamedQuery("getByFieldCnName", fieldCnName, dataStoreTypeId);
		if(!list.isEmpty()){
			if(list.get(0) instanceof DataStoreField){
				DataStoreField dataStoreField = list.get(0);				
				return dataStoreField;
			}
		}
		return null;
	}
	
	@Override
	public DataStoreField getDataStoreFieldByFieldEnName(String fieldEnName,Integer dataStoreTypeId) {
		List<DataStoreField> list = (List<DataStoreField>)getHibernateTemplate().findByNamedQuery("getByFieldEnName", fieldEnName, dataStoreTypeId);
		if(!list.isEmpty()){
			if(list.get(0) instanceof DataStoreField){
				DataStoreField dataStoreField = list.get(0);				
				return dataStoreField;
			}
		}
		return null;
	}
}
