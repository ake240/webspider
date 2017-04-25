package com.datayes.webspider.dao.newsextractor;

import org.springframework.stereotype.Repository;

import com.datayes.webspider.dao.BaseDao;
import com.datayes.webspider.domain.newsextractor.NewsExtractorFeedback;

@Repository("newsExtractorFeedbackDao")
public class NewsExtractorFeedbackDao extends BaseDao<NewsExtractorFeedback> implements INewsExtractorFeedbackDao {

}
