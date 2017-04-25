package com.datayes.webspider.service.crawlrule;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.dao.crawlrule.ICrawlruleDao;
import com.kepler.spider.integration.bo.CrawlRuleBO;

@Service("crawlruleService")
public class CrawlruleServiceImpl implements ICrawlruleService {
	
	@Resource
	private ICrawlruleDao crawlruleDao;

	@Override
	public CrawlRuleBO getById(String id) {
		return crawlruleDao.getById(id);
	}

	@Override
	public void save(CrawlRuleBO crawlrule) {
		crawlruleDao.save(crawlrule);
	}

	@Override
	public PageDTO enquiryCrawlrulePage(String crawlruleName, Integer webOperationId, String status, int pageNow, int pageSize) {
		int total = crawlruleDao.enquiryCrawlruleCount(crawlruleName, webOperationId, status);
		List list = crawlruleDao.enquiryCrawlrulePage(crawlruleName, webOperationId, status, pageNow, pageSize);
		return new PageDTO(list, pageNow, pageSize, total);
	}

	@Override
	public List<CrawlRuleBO> getList(Class<CrawlRuleBO> c) {

		return crawlruleDao.getList(c);
	}

}
