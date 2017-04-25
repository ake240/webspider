package com.datayes.webspider.dao.words;

import com.datayes.webspider.dao.IBaseDao;
import com.datayes.webspider.domain.words.IndexTask;

import java.util.List;
import java.util.Map;

public interface IWordsTaskDao extends IBaseDao<IndexTask> {
    public List<IndexTask> findByExample(IndexTask task);
    public List<IndexTask> findByParams(Map<String,String> params,int page,int pageSize);

    Integer taskCount(Map<String, String> params);
}
