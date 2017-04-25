package com.datayes.webspider.service.newsextractor;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.datayes.webspider.dao.newsextractor.INewsExtractorFeedbackDao;
import com.datayes.webspider.domain.newsextractor.NewsExtractorFeedback;

@Service("newsExtractorFeedbackService")
public class NewsExtractorFeedbackServiceImpl implements INewsExtractorFeedbackService {

	@Resource
	private INewsExtractorFeedbackDao newsExtractorFeedbackDao;
	
	@Override
	@Transactional
	public void saveOrUpdate(NewsExtractorFeedback feedback) {
		newsExtractorFeedbackDao.saveOrUpdate(feedback);
	}

}
