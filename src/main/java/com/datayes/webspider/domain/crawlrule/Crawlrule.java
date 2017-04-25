package com.datayes.webspider.domain.crawlrule;

import java.util.Date;
import java.util.List;

public class Crawlrule {
	private String id;
	private String crawlRuleName;
	private Integer websiteId;
	private Integer websiteOperationId;
	private String pageUrl;
	private Integer pageEncode; // 1:utf-8; 2:gb2312; 3:gbk; 4:iso-8859-1
	private Integer nodeType; // 节点类型 1:单节点; 2：list节点
	private List<CrawlRuleNode> crawlRuleNodeList;
	private Integer status;
	private String updator;
	private Date insertTime;
	private Date updateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCrawlRuleName() {
		return crawlRuleName;
	}

	public void setCrawlRuleName(String crawlRuleName) {
		this.crawlRuleName = crawlRuleName;
	}

	public Integer getWebsiteId() {
		return websiteId;
	}

	public void setWebsiteId(Integer websiteId) {
		this.websiteId = websiteId;
	}

	public Integer getWebsiteOperationId() {
		return websiteOperationId;
	}

	public void setWebsiteOperationId(Integer websiteOperationId) {
		this.websiteOperationId = websiteOperationId;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public Integer getPageEncode() {
		return pageEncode;
	}

	public void setPageEncode(Integer pageEncode) {
		this.pageEncode = pageEncode;
	}

	public Integer getNodeType() {
		return nodeType;
	}

	public void setNodeType(Integer nodeType) {
		this.nodeType = nodeType;
	}

	public List<CrawlRuleNode> getCrawlRuleNodeList() {
		return crawlRuleNodeList;
	}

	public void setCrawlRuleNodeList(List<CrawlRuleNode> crawlRuleNodeList) {
		this.crawlRuleNodeList = crawlRuleNodeList;
	}

	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdator() {
		return updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
