package com.datayes.webspider.service.crawlrule;

import java.util.List;

import com.datayes.webspider.common.PageDTO;
import com.kepler.spider.integration.bo.CrawlRuleBO;

public interface ICrawlruleService {
	public CrawlRuleBO getById(String id);
	
	public void save(CrawlRuleBO crawlrule);
	
	public PageDTO enquiryCrawlrulePage(String crawlruleName, Integer webOperationId, String status, int pageNow, int pageSize);

	public List<CrawlRuleBO> getList(Class<CrawlRuleBO> c);
}
