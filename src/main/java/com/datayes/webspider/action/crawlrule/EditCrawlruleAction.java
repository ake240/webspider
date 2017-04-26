package com.datayes.webspider.action.crawlrule;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.alibaba.fastjson.JSONObject;
import com.datayes.webspider.action.BaseAction;
import com.datayes.webspider.constant.CommonConstants;
import com.datayes.webspider.service.crawlrule.ICrawlruleService;
import com.kepler.spider.integration.bo.CrawlRuleBO;

@ParentPackage("webspider")
@Namespace("/")
@Results({ 
	@Result(name = "success", location = "/WEB-INF/jsp/crawlrule/editCrawlrule.jsp"), 
	@Result(name = "redirect2success", location = "/WEB-INF/jsp/success.jsp"),
	@Result(name = "json", type = "json", params = {"includeProperties","jsonResult"}), 
})
public class EditCrawlruleAction extends BaseAction {
	private static final long serialVersionUID = 1L;
//	private Crawlrule crawlrule;
	private CrawlRuleBO crawlrule;
	private List<RemoveCrawlExprValue> removeCrawlExprValues;
	private String crawlruleId;
	private Integer status;
	private int type;
	private String jsonResult;
	
	@Resource
	private ICrawlruleService crawlruleService;
	
	@Action("crawlrule")
	public String crawlrule(){
		if (type != 0) {
			this.crawlrule = crawlruleService.getById(crawlruleId);
		}
		return SUCCESS;
	}
	
	@Action("newCrawlrule")
	public String newCrawlrule(){
		String usename = (String) session.get(CommonConstants.SESSION_OBJECT_KEY);
		crawlrule.setStatus(0);
		crawlrule.setOperator(usename);
		crawlrule.setInsertTime(new Date());
		crawlrule.setUpdateTime(new Date());
		HashMap<String, Integer> removeExprValueMap = null;
		if (removeCrawlExprValues != null) {
			removeExprValueMap = new HashMap<String, Integer>();
			for (RemoveCrawlExprValue removeCrawlExprValue : removeCrawlExprValues) {
				removeExprValueMap.put(removeCrawlExprValue.getCrawlExprValue(), removeCrawlExprValue.getCrawlExprType());
			}
		}
		crawlrule.getCrawlRuleNodeList().get(0).setRemoveCrawlExprValues(removeExprValueMap);
		crawlrule.setId(null);
		crawlruleService.save(crawlrule);
		this.crawlruleId = crawlrule.getId();
		return "redirect2success";
	}
	
	@Action("editCrawlrule")
	public String editCrawlrule(){
		String usename = (String) session.get(CommonConstants.SESSION_OBJECT_KEY);
		crawlrule.setOperator(usename);
		crawlrule.setUpdateTime(new Date());
		HashMap<String, Integer> removeExprValueMap = null;
		if (removeCrawlExprValues != null) {
			removeExprValueMap = new HashMap<String, Integer>();
			for (RemoveCrawlExprValue removeCrawlExprValue : removeCrawlExprValues) {
				removeExprValueMap.put(removeCrawlExprValue.getCrawlExprValue(), removeCrawlExprValue.getCrawlExprType());
			}
		}
		crawlrule.getCrawlRuleNodeList().get(0).setRemoveCrawlExprValues(removeExprValueMap);
		crawlruleService.save(crawlrule);
		this.crawlruleId = crawlrule.getId();
		return "redirect2success";
	}
	
	@Action("changeStatus")
	public String changeStatus(){
		boolean succeed = true;
		String msg = "";
		CrawlRuleBO crawlrule = crawlruleService.getById(crawlruleId);
		if (crawlrule != null) {
			if (status != null) {
				crawlrule.setStatus(status);
				crawlruleService.save(crawlrule);
			}else{
				succeed = false;
				msg = "状态未知";
			}
		}else{
			succeed = false;
			msg = "未找到对应的抽取规则, id:" + crawlruleId;
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("succeed", succeed);
		jsonObject.put("msg", msg);
		this.jsonResult = jsonObject.toJSONString();
		return "json";
	}

	public CrawlRuleBO getCrawlrule() {
		return crawlrule;
	}

	public void setCrawlrule(CrawlRuleBO crawlrule) {
		this.crawlrule = crawlrule;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getCrawlruleId() {
		return crawlruleId;
	}

	public void setCrawlruleId(String crawlruleId) {
		this.crawlruleId = crawlruleId;
	}

	public List<RemoveCrawlExprValue> getRemoveCrawlExprValues() {
		return removeCrawlExprValues;
	}

	public void setRemoveCrawlExprValues(List<RemoveCrawlExprValue> removeCrawlExprValues) {
		this.removeCrawlExprValues = removeCrawlExprValues;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}
	
}
