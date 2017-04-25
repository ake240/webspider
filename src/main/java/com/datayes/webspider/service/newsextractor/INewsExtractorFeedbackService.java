package com.datayes.webspider.service.newsextractor;

import com.datayes.webspider.domain.newsextractor.NewsExtractorFeedback;

public interface INewsExtractorFeedbackService {
	void saveOrUpdate(NewsExtractorFeedback feedback);
}
