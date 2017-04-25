package com.datayes.webspider.dao.words;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.datayes.webspider.dao.IBaseDao;
import com.datayes.webspider.domain.words.IndexInfo;

public interface IIndexInfoDao extends IBaseDao<IndexInfo> {
	
	public int enquiryIndexInfoCount(Long wordId, String indexType, Date beginDate, Date endDate);
	public List enquiryIndexInfoPage(Long wordId, String indexType,  Date beginDate, Date endDate,int pageSize, int pageNow);
	public DetachedCriteria buildCriteria(Long keywordId, String indexType, Date beginDate, Date endDate);
}
