package com.datayes.webspider.domain.crawlrule;

import java.util.List;
import java.util.Map;

public class CrawlRuleNode {
	private String crawlNodeCnName;
	private String crawlNodeEnName;
	private Integer nodeType;// 节点类型 1:单节点， 2：list节点

	private Integer crawlExprType; // 1:xpath扣取方式 ,2:正则扣取方式 ,3 文本值(可以设定其他节点获取替换)
	private String crawlExprVal;// 抓取表达式
	private Integer extWebsiteOperationId;// 依赖的抓取规则id,默认为空没有依赖
	private String extCrawlNodeName;// 作为输入参数的url 的nodeid

	private boolean selectable = true;// 该节点的结果是否为必选项
	private boolean nodeHtmlSourceOptional = false;
	private Integer nodeProperty;// 字段类型 1:字符串，2:整数，3:时间
	private Boolean isNodeList;
	private Integer minLength;
	private Integer maxLength;
	private Map<String, Integer> removeCrawlExprValues;
	private List<CrawlRuleNode> childCrawlRuleNodeList;

	public String getCrawlNodeCnName() {
		return crawlNodeCnName;
	}

	public void setCrawlNodeCnName(String crawlNodeCnName) {
		this.crawlNodeCnName = crawlNodeCnName;
	}

	public String getCrawlNodeEnName() {
		return crawlNodeEnName;
	}

	public void setCrawlNodeEnName(String crawlNodeEnName) {
		this.crawlNodeEnName = crawlNodeEnName;
	}

	public Integer getNodeType() {
		return nodeType;
	}

	public void setNodeType(Integer nodeType) {
		this.nodeType = nodeType;
	}

	public Integer getCrawlExprType() {
		return crawlExprType;
	}

	public void setCrawlExprType(Integer crawlExprType) {
		this.crawlExprType = crawlExprType;
	}

	public String getCrawlExprVal() {
		return crawlExprVal;
	}

	public void setCrawlExprVal(String crawlExprVal) {
		this.crawlExprVal = crawlExprVal;
	}

	public Integer getExtWebsiteOperationId() {
		return extWebsiteOperationId;
	}

	public void setExtWebsiteOperationId(Integer extWebsiteOperationId) {
		this.extWebsiteOperationId = extWebsiteOperationId;
	}

	public String getExtCrawlNodeName() {
		return extCrawlNodeName;
	}

	public void setExtCrawlNodeName(String extCrawlNodeName) {
		this.extCrawlNodeName = extCrawlNodeName;
	}

	public boolean isSelectable() {
		return selectable;
	}

	public void setSelectable(boolean selectable) {
		this.selectable = selectable;
	}

	public boolean isNodeHtmlSourceOptional() {
		return nodeHtmlSourceOptional;
	}

	public void setNodeHtmlSourceOptional(boolean nodeHtmlSourceOptional) {
		this.nodeHtmlSourceOptional = nodeHtmlSourceOptional;
	}

	public Integer getNodeProperty() {
		return nodeProperty;
	}

	public void setNodeProperty(Integer nodeProperty) {
		this.nodeProperty = nodeProperty;
	}

	public Integer getMinLength() {
		return minLength;
	}

	public void setMinLength(Integer minLength) {
		this.minLength = minLength;
	}

	public Integer getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
	}

	public Map<String, Integer> getRemoveCrawlExprValues() {
		return removeCrawlExprValues;
	}

	public void setRemoveCrawlExprValues(Map<String, Integer> removeCrawlExprValues) {
		this.removeCrawlExprValues = removeCrawlExprValues;
	}

	public List<CrawlRuleNode> getChildCrawlRuleNodeList() {
		return childCrawlRuleNodeList;
	}

	public void setChildCrawlRuleNodeList(List<CrawlRuleNode> childCrawlRuleNodeList) {
		this.childCrawlRuleNodeList = childCrawlRuleNodeList;
	}

}
