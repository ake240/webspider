package com.datayes.webspider.dao.words;

import java.util.List;

import com.datayes.webspider.dao.BaseDao;
import com.datayes.webspider.domain.words.IndexKeywords;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * Created by ruihua.huang on 2014/9/1.
 */
@Repository("wordsDao")
public class WordsDao extends BaseDao<IndexKeywords> implements IWordsDao {

	@Override
	public int enquiryKeywordCount(String keyword, Integer status) {
		DetachedCriteria criteria = buildCriteria(keyword, status);
		return (Integer) findCount(criteria);
	}

	@Override
	public List enquiryKeywordPage(String keyword, Integer status, int pageSize, int pageNow) {
		DetachedCriteria criteria = buildCriteria(keyword, status);
		return findPage(criteria, pageNow, pageSize);
	}
	
	private DetachedCriteria buildCriteria(String keyword, Integer status){
		DetachedCriteria criteria = DetachedCriteria.forClass(IndexKeywords.class);
		
		if(!StringUtils.isEmpty(keyword)){
			criteria.add(Property.forName("word").like(keyword, MatchMode.ANYWHERE));
		}
		if(status != null){
			if(status.intValue() != 2){ ///TODO refactor the constants.
				criteria.add(Restrictions.eq("active", status));
			}
		}
		return criteria;
	}

	@Override
	public IndexKeywords matchExactly(String keyword) {
		DetachedCriteria criteria = DetachedCriteria.forClass(IndexKeywords.class);
		criteria.add(Restrictions.eq("word", keyword));
		List<IndexKeywords> keywords = (List<IndexKeywords>)getHibernateTemplate().findByCriteria(criteria, 0, 1);
		if(keywords==null || keywords.size()==0){
			return null;
		}
		return keywords.get(0);
	}
	
}
