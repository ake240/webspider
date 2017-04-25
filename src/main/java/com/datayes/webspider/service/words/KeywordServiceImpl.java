package com.datayes.webspider.service.words;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.dao.words.IWordsDao;
import com.datayes.webspider.domain.words.IndexKeywords;


@Service("keywordService")
public class KeywordServiceImpl implements IKeywordService {

	public static Map<Long, String> wordsMap = new ConcurrentHashMap();
	
    @Resource
    private IWordsDao wordsDao;

    @Override
    public PageDTO enquiryKeywordPage(String keyword, Integer status, int pageNow, int pageSize) {
        Integer total = wordsDao.enquiryKeywordCount(keyword, status);
        List list = wordsDao.enquiryKeywordPage(keyword, status, pageSize, pageNow);
        return new PageDTO(list, pageNow, pageSize, total);
    }

    @Override
    public IndexKeywords getByIndexId(Long id) {
        return wordsDao.findById("getKeywordById", id);
    }

    @Override
    @Transactional
    public void saveOrUpdate(IndexKeywords keyword) {
        wordsDao.saveOrUpdate(keyword);
    }

    @Override
    public IndexKeywords exactMatch(String keyword) {
        return wordsDao.matchExactly(keyword);
    }

    @Override
    public List<IndexKeywords> getAllWords() {
        return wordsDao.loadAll();
    }

	@Override
	public String getKeywordByIndexId(Long id) {
		if(id==null){
			return null;
		}
		String result = wordsMap.get(id);
		if(!StringUtils.isEmpty(result)){
			return result;
		}
		IndexKeywords keyword = getByIndexId(id);
		if(keyword==null){
			return null;
		}
		wordsMap.put(id, keyword.getWord());
		return keyword.getWord();
	}

}
