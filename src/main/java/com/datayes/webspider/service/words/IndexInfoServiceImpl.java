package com.datayes.webspider.service.words;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.datayes.webspider.bo.words.IndexInfoBO;
import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.dao.words.IIndexInfoDao;
import com.datayes.webspider.dao.words.IWordsDao;
import com.datayes.webspider.domain.words.IndexInfo;
import com.datayes.webspider.domain.words.IndexKeywords;

@Service("indexInfoService")
public class IndexInfoServiceImpl implements IIndexInfoService {

	@Resource
	private IIndexInfoDao indexInfoDao;
	@Resource
	private IWordsDao wordsDao;
	
	@Resource
	private IKeywordService keywordService;
	
	@Override
	public PageDTO enquiryIndexInfo(Long wordId, String indexType,
			Date beginDate, Date endDate, int pageNow, int pageSize) {
		Integer total = indexInfoDao.enquiryIndexInfoCount(wordId, indexType, beginDate, endDate);
		List indexInfos = indexInfoDao.enquiryIndexInfoPage(wordId, indexType, beginDate, endDate, pageSize, pageNow);
		List<IndexInfoBO> bos = new LinkedList<IndexInfoBO>();
		int size = indexInfos.size();
		for(int i=0;i<size;i++){
			IndexInfo indexInfo = (IndexInfo) indexInfos.get(i);
			IndexInfoBO bo = new IndexInfoBO(indexInfo);
			bo.setIndexWord(keywordService.getKeywordByIndexId(indexInfo.getIndexId()));
			bos.add(bo);
		}
		return new PageDTO(bos, pageNow, pageSize, total);
	}

	@Override
	public PageDTO enquiryIndexInfo(String keyword, String indexType,
			Date beginDate, Date endDate, int pageNow, int pageSize) {
		// TODO Auto-generated method stub
		
		IndexKeywords word = keywordService.exactMatch(keyword);
		if(word==null){
			return new PageDTO(new LinkedList<IndexInfoBO>(),pageNow, pageSize, 0);
		}
		return enquiryIndexInfo(word.getIndexId(), indexType, beginDate, endDate, pageNow, pageSize);
	}

}
