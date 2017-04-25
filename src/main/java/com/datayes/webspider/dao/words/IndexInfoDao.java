package com.datayes.webspider.dao.words;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.datayes.webspider.dao.BaseDao;
import com.datayes.webspider.domain.words.IndexInfo;

@Repository("indexInfoDao")
public class IndexInfoDao extends BaseDao<IndexInfo> implements IIndexInfoDao {

	@Override
	public int enquiryIndexInfoCount(Long wordId, String indexType,
			Date beginDate, Date endDate) {
		DetachedCriteria criteria = buildCriteria(wordId, indexType, beginDate, endDate);
		return (Integer)findCount(criteria);
	}

	@Override
	public List enquiryIndexInfoPage(Long wordId, String indexType,
			Date beginDate, Date endDate, int pageSize, int pageNow) {
		DetachedCriteria criteria = buildCriteria(wordId, indexType, beginDate, endDate);
		return findPage(criteria, pageNow, pageSize);
	}
	
	@Override
	public DetachedCriteria buildCriteria(Long wordId, String indexType,
			Date beginDate, Date endDate) {
		DetachedCriteria criteria = DetachedCriteria.forClass(IndexInfo.class);
		if(wordId!=null){
			criteria.add(Restrictions.eq("indexId", wordId));
		}
		if(!StringUtils.isEmpty(indexType)){
			criteria.add(Restrictions.eq("indexType", indexType));
		}
		if(beginDate!=null && endDate!=null){
			criteria.add(Restrictions.between("indexDate", beginDate, endDate));
		} else if(beginDate!=null){
			criteria.add(Restrictions.ge("indexDate", beginDate));
		} else if(endDate!=null){
			criteria.add(Restrictions.le("indexDate", endDate));
		} else {
			/// Do nothing
		}
		criteria.addOrder(Order.desc("indexDate"));
		
		return criteria;
	}
}
