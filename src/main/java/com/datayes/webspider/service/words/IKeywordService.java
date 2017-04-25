package com.datayes.webspider.service.words;

import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.domain.words.IndexKeywords;

import java.util.List;

public interface IKeywordService {
	
	public PageDTO enquiryKeywordPage(String keyword, Integer status, int pageNow, int pageSize);
	public IndexKeywords getByIndexId(Long id);
	public void saveOrUpdate(IndexKeywords keyword);
	public IndexKeywords exactMatch(String keyword);
    public List<IndexKeywords> getAllWords();
	public String getKeywordByIndexId(Long id);


}
