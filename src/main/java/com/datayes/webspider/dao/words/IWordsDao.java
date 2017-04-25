package com.datayes.webspider.dao.words;

import java.util.List;

import com.datayes.webspider.dao.IBaseDao;
import com.datayes.webspider.domain.words.IndexKeywords;

/**
 * Created by ruihua.huang on 2014/9/1.
 */
public interface IWordsDao extends IBaseDao<IndexKeywords> {
	public int enquiryKeywordCount(String keyword, Integer status);
	public List enquiryKeywordPage(String keyword, Integer status, int pageSize, int pageNow);
	public IndexKeywords matchExactly(String keyword);
}
