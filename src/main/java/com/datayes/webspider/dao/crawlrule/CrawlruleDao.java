package com.datayes.webspider.dao.crawlrule;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.kepler.spider.integration.bo.CrawlRuleBO;

@Repository("crawlruleDao")
public class CrawlruleDao implements ICrawlruleDao {
	@Resource
	private MongoTemplate mongoTemplate;

	@Override
	public CrawlRuleBO getById(String id) {
		return mongoTemplate.findById(id, CrawlRuleBO.class, "crawlrule");
	}

	@Override
	public void save(CrawlRuleBO crawlrule) {
		mongoTemplate.save(crawlrule, "crawlrule");
	}

	@Override
	public int enquiryCrawlruleCount(String crawlruleName, Integer webOperationId, String status) {
		Criteria criteria = buildCriteria(crawlruleName, webOperationId, status);
		Query query = new Query(criteria);
		return (int) mongoTemplate.count(query, "crawlrule");
	}

	@Override
	public List<CrawlRuleBO> enquiryCrawlrulePage(String crawlruleName, Integer webOperationId, String status, int pageNow, int pageSize) {
		Criteria criteria = buildCriteria(crawlruleName, webOperationId, status);
		Query query = new Query(criteria);
		query.with(new Sort(Sort.Direction.DESC, "insertTime"));
		query.skip((pageNow - 1) * pageSize).limit(pageSize);
		return mongoTemplate.find(query, CrawlRuleBO.class, "crawlrule");
	}

	private Criteria buildCriteria(String crawlruleName, Integer webOperationId, String status){
		Criteria criteria = new Criteria();
		if (!StringUtils.isEmpty(crawlruleName)) {
			criteria.and("crawlRuleName").regex(".*" + crawlruleName + ".*");
		}
		
		if (webOperationId != null) {
			criteria.and("websiteOperationId").is(webOperationId);
		}
		
		if (!StringUtils.isEmpty(status)) {
			criteria.and("status").is(Integer.parseInt(status));
		}else{
			criteria.and("status").ne(2);
		}
		
		return criteria;
	}

	@Override
	public List<CrawlRuleBO> getList(Class<CrawlRuleBO> c) {
		return mongoTemplate.findAll(c, "crawlrule");
	}
}
