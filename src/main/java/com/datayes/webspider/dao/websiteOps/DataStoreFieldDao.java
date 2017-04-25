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
	
	//根据fieldcnname获得相应的datastorefield
	@Override
	public DataStoreField getDataStoreFieldByFieldCnName(String fieldCnName) {
		List<DataStoreField> list = (List<DataStoreField>)getHibernateTemplate().findByNamedQuery("getByFieldCnName", fieldCnName);
		if(!list.isEmpty()){
			if(list.get(0) instanceof DataStoreField){
				DataStoreField dataStoreField = list.get(0);				
				return dataStoreField;
			}
		}
		return null;
	}

	//根据crawlnodeId查询datastorefield
	@Override
	public DataStoreField getByDataStoreFieldId(Integer crawlNodeId) {
		DataStoreField dataStoreField = (DataStoreField) getHibernateTemplate().findByNamedQuery("getByDataStoreFieldId", crawlNodeId).get(0);
		return dataStoreField;
	}

	public List<DataStoreField> getByFieldCnName(String fieldCnName) {
		List<DataStoreField> list = (List<DataStoreField>)getHibernateTemplate().findByNamedQuery("getByFieldCnName", fieldCnName);
		if(!list.isEmpty()){			
			return list;
		}
		return null;
	}

	@Override
	public String getByExtName(String extName) {
		List<DataStoreField> list = (List<DataStoreField>)getHibernateTemplate().findByNamedQuery("getByExtName", extName);
		return null;
	}

}
