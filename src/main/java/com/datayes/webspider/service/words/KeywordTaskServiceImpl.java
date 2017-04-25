package com.datayes.webspider.service.words;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.constant.TaskStatusConstant;
import com.datayes.webspider.dao.words.IWordsDao;
import com.datayes.webspider.dao.words.IWordsTaskDao;
import com.datayes.webspider.domain.words.IndexKeywords;
import com.datayes.webspider.domain.words.IndexTask;

@Service("keywordTaskService")
public class KeywordTaskServiceImpl implements IKeywordTaskService {

    private Logger logger = LoggerFactory.getLogger(KeywordTaskServiceImpl.class);

    @Resource
    private IWordsDao wordsDao;

    @Resource
    private IWordsTaskDao keywordTaskDao;

    @Override
    @Transactional
    public void addKeywordTask(String word) {
        IndexKeywords keyword = wordsDao.matchExactly(word);
        if (keyword == null) {
            logger.info("没有百度指数关键词：" + word);
            return;
        }
        IndexTask indexTaskHistory = new IndexTask();
        indexTaskHistory.setIndexKeyword(word);
        indexTaskHistory.setIndexId(keyword.getIndexId());
        indexTaskHistory.setInsertTime(new Date());
        indexTaskHistory.setUpdateTime(new Date());
        indexTaskHistory.setFetchType(TaskStatusConstant.BAIDU_KEYWORD_OMIT);
        keywordTaskDao.saveOrUpdate(indexTaskHistory);


        IndexTask indexTaskRT = new IndexTask();
        indexTaskRT.setIndexKeyword(word);
        indexTaskRT.setIndexId(keyword.getIndexId());
        indexTaskRT.setInsertTime(new Date());
        indexTaskRT.setUpdateTime(new Date());
        indexTaskRT.setFetchType(TaskStatusConstant.BAIDU_KEYWORD_REALTIME);
        keywordTaskDao.saveOrUpdate(indexTaskRT);

    }

    @Override
    @Transactional
    public void addWordTask(IndexTask task) {
        task.setUpdateTime(new Date());
        if (task.getInsertTime() == null) {
            task.setInsertTime(new Date());
        }
        keywordTaskDao.saveOrUpdate(task);
    }

    @Override
    public IndexTask getTaskById(IndexTask task) {
        List<IndexTask> list = keywordTaskDao.findByExample(task);
        if (list != null) {
            return list.get(0);
        }
        return null;
    }

    @Override
    @Transactional
    public PageDTO getTasks(Map<String, String> params, int page, int pageSize) {
        Integer total = keywordTaskDao.taskCount(params);
        List list = keywordTaskDao.findByParams(params, page, pageSize);
        return new PageDTO(list, page, pageSize, total);
    }

}
