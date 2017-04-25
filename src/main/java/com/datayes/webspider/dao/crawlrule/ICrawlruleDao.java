package com.datayes.webspider.dao.crawlrule;

import java.util.List;

import com.kepler.spider.integration.bo.CrawlRuleBO;

public interface ICrawlruleDao {

	public CrawlRuleBO getById(String id);
	
	public void save(CrawlRuleBO crawlrule);
	
	public int enquiryCrawlruleCount(String crawlruleName, Integer webOperationId, String status);
	
	public List<CrawlRuleBO> enquiryCrawlrulePage(String crawlruleName, Integer webOperationId, String status, int pageNow, int pageSize);

	public List<CrawlRuleBO> getList(Class<CrawlRuleBO> c);
}
