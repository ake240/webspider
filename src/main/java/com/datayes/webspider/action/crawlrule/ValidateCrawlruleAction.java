package com.datayes.webspider.action.crawlrule;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.datayes.webspider.action.BaseAction;
import com.kepler.spider.integration.bo.CrawlRuleBO;
import com.kepler.spider.util.SpiderUtil;
import com.kepler.spider.util.httpclient.CrawlHttpClientHelper;
import com.kepler.spider.util.span.ParserCompiler;
import com.kepler.spider.util.span.parser.HtmlPageData;

@ParentPackage("webspider")
@Namespace("/")
@Results({ 
	@Result(name = "success", location = "/WEB-INF/jsp/crawlrule/validateCrawlrule.jsp"), 
})
public class ValidateCrawlruleAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private CrawlRuleBO crawlrule;
	private HtmlPageData pageData;
	private String htmlContent;
	
	@Action("validateCrawlrule")
	public String validateCrawlrule(){
		if (crawlrule != null) {
			String url = crawlrule.getPageUrl();
			CrawlHttpClientHelper clientHelper = new CrawlHttpClientHelper();
			String referer = crawlrule.getReferer();
			if(StringUtils.isEmpty(referer)){
				referer = null;
			}
			try {
				htmlContent = clientHelper.doGet(url, SpiderUtil.getPageEncode(crawlrule.getPageEncode()), referer);
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.pageData = ParserCompiler.parser(url, htmlContent, crawlrule);
		}
		return SUCCESS;
	}

	public CrawlRuleBO getCrawlrule() {
		return crawlrule;
	}

	public void setCrawlrule(CrawlRuleBO crawlrule) {
		this.crawlrule = crawlrule;
	}

	public HtmlPageData getPageData() {
		return pageData;
	}

	public void setPageData(HtmlPageData pageData) {
		this.pageData = pageData;
	}

	public String getHtmlContent() {
		return htmlContent;
	}

	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}
}
