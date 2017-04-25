package com.datayes.webspider.action.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datayes.webspider.action.BaseAction;
import com.datayes.webspider.domain.taskFI.TaskFetchItem;
import com.datayes.webspider.domain.websiteOps.WebSiteOpsRel;
import com.datayes.webspider.service.taskFI.ITaskFetchItemService;
import com.datayes.webspider.service.websiteOps.IWebSiteOpsRelService;
import com.datayes.webspider.util.ErrorCode;
import com.datayes.webspider.util.JsonResultPO;
import com.kepler.logicframework.biz.exception.BizException;
import com.kepler.spider.constant.SpiderConstants;
import com.kepler.util.validate.Validate;


@ParentPackage("webspider_nologin")
@Namespace("/")
@Results({
	@Result(name = "json", type = "json", params = { "includeProperties","jsonResult" })
})
public class TaskAction extends BaseAction {
	

	private static final long serialVersionUID = 1L;
	
	private final int crawltype_news = 1;
	private final int crawltype_weibo = 2;
	private final int crawltype_weixin = 3;
	
	private final int crawlmethod_keyword = 1;
	
	private SimpleDateFormat gDateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Resource
	private ITaskFetchItemService itemService;
	@Resource
	private IWebSiteOpsRelService webSiteOpsRelService;

	
	private String jsonResult;	

	public String getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}

	private static final Logger logger = LoggerFactory.getLogger(TaskAction.class);
	
	@Action("getTaskFIStatus")
	public String getTaskFetchItem(){
		JsonResultPO jsonResultPO = JsonResultPO.getSuccessJsonResult();
		
		try{
			String fetchItem = getRequest().getParameter("fi");
			if(Validate.isEmpty(fetchItem)){
				throw new BizException(ErrorCode.TASK_FETCHITEM_ID_MISS.getErrorCode(),ErrorCode.TASK_FETCHITEM_ID_MISS.getErrorMsg()); 
			}
			boolean isDone = true;
			List<Integer> statusDoingList = new ArrayList<Integer>();
			statusDoingList.add(0);
			statusDoingList.add(1);
			statusDoingList.add(3);
			List<Integer> statusDoneList = new ArrayList<Integer>();
			statusDoneList.add(2);
			statusDoneList.add(4);
			JSONObject jsonObject = JSONObject.fromObject(fetchItem);
			for(Iterator it = jsonObject.keys(); it.hasNext();){
				String websiteOperationId = (String)it.next();
				String collectionName = "queue_" + websiteOperationId; 
				JSONArray fetchItemIdList = (JSONArray)jsonObject.get(websiteOperationId);
				for(Object fetchItemId : fetchItemIdList){
					String id_1 = "" + fetchItemId;
					
					TaskFetchItem fetchItemBO = itemService.getById(id_1, collectionName);
					int status = fetchItemBO.getStatus();
					if(status == 0 || status == 1){
						isDone = false;
					}else{
						List<WebSiteOpsRel> webSiteOpsRelList_2 = webSiteOpsRelService.enquiryChildrenList(Integer.valueOf(websiteOperationId));
						if(Validate.isEmpty(webSiteOpsRelList_2)){
							break;
						}
						for(WebSiteOpsRel webSiteOpsRel_2 : webSiteOpsRelList_2){
							int childWebsiteOperationId_2 = webSiteOpsRel_2.getKey().getWebsiteOperationId();
							String subCollectionName_2 = "queue_" + childWebsiteOperationId_2;	
							long num_2 = itemService.countByPId(id_1, statusDoingList, subCollectionName_2);
							if(num_2 > 0){
								isDone = false;
								break;
							}else{
								List<TaskFetchItem> taskFetchItemList_2 = itemService.getByPId(id_1, statusDoneList, subCollectionName_2);
								for(TaskFetchItem taskFetcItem_2 : taskFetchItemList_2){
									String id_2 = taskFetcItem_2.getId();
									List<WebSiteOpsRel> webSiteOpsRelList_3 = webSiteOpsRelService.enquiryChildrenList(childWebsiteOperationId_2);
									if(Validate.isEmpty(webSiteOpsRelList_3)){
										break;
									}
									for(WebSiteOpsRel webSiteOpsRel_3 : webSiteOpsRelList_3){
										int childWebsiteOperationId_3 = webSiteOpsRel_3.getKey().getWebsiteOperationId();
										String subCollectionName_3 = "queue_" + childWebsiteOperationId_3;	
										long num_3 = itemService.countByPId(id_2, statusDoingList, subCollectionName_3);
										if(num_3 > 0){
											isDone = false;
											break;
										}
									}
									if(!isDone){
										break;
									}
								}
							}
						}
					}
					if(!isDone){
						break;
					}
				}
			}
			Map<String,Object> data = new HashMap<String,Object>();
			data.put("status", isDone?2:1);
			
			jsonResultPO.setData(data);
		}catch(BizException e){
			jsonResultPO.setStatus(e.getErrorCode());
			jsonResultPO.setMsg(e.getErrorMessage());
			logger.error(e.getMessage(), e);
		} catch (Throwable e) {
			jsonResultPO.setStatus(ErrorCode.NOT_KNONW_ERROR.getErrorCode());
			jsonResultPO.setMsg(ErrorCode.NOT_KNONW_ERROR.getErrorMsg());
			logger.error(e.getMessage(), e);
		}
		
		writeJson(jsonResultPO);  
		
		return null;
	}

	@Action("addTaskFI")
	public String addTaskFetchItem(){
		JsonResultPO jsonResultPO = JsonResultPO.getSuccessJsonResult();
		
		try{
			String strCrawlType = getRequest().getParameter("crawlType");//抓取类型 1：新闻，2：微博，3:微信
			String strCrawlMethod = getRequest().getParameter("crawlMethod");//抓取方式 1:关键词 
			
			if(Validate.isEmpty(strCrawlType)){
				throw new BizException(ErrorCode.CRAWLTYPE_MISS.getErrorCode(),ErrorCode.CRAWLTYPE_MISS.getErrorMsg());
			}
			if(Validate.isEmpty(strCrawlMethod)){
				throw new BizException(ErrorCode.CRAWLMETHOD_MISS.getErrorCode(),ErrorCode.CRAWLMETHOD_MISS.getErrorMsg());
			}
			int crawlType = 0;
			try{
				crawlType = Integer.valueOf(strCrawlType);
			}catch(Exception e){
			}
			int crawlMethod = 0;
			try{
				crawlMethod = Integer.valueOf(strCrawlMethod);
			}catch(Exception e){
			}
			if(!checkCrawlType(crawlType)){
				throw new BizException(ErrorCode.CRAWLTYPE_NOT_SUPPORT.getErrorCode(),ErrorCode.CRAWLTYPE_NOT_SUPPORT.getErrorMsg());
			}
			if(crawlType == crawltype_news){
				jsonResultPO = addNews(crawlMethod);
			}
			if(crawlType == crawltype_weibo){
				jsonResultPO = addWeibo(crawlMethod);
			}
			if(crawlType == crawltype_weixin){
				jsonResultPO = addWeixin(crawlMethod);
			}
		}catch(BizException e){
			jsonResultPO.setStatus(e.getErrorCode());
			jsonResultPO.setMsg(e.getErrorMessage());
			logger.error(e.getMessage(), e);
		} catch (Throwable e) {
			jsonResultPO.setStatus(ErrorCode.NOT_KNONW_ERROR.getErrorCode());
			jsonResultPO.setMsg(ErrorCode.NOT_KNONW_ERROR.getErrorMsg());
			logger.error(e.getMessage(), e);
		}
		
		writeJson(jsonResultPO);  
		
		return null;
	}

	private JsonResultPO addWeixin(int crawlMethod) {
		if(!checkCrawlMethodForWeixin(crawlMethod)){
			throw new BizException(ErrorCode.CRAWLMETHOD_NOT_SUPPORT.getErrorCode(),ErrorCode.CRAWLMETHOD_NOT_SUPPORT.getErrorMsg());
		}
		String keyword = getRequest().getParameter("keyword");
		if(Validate.isEmpty(keyword)){
			throw new BizException(ErrorCode.KEYWORD_MISS.getErrorCode(),ErrorCode.KEYWORD_MISS.getErrorMsg());
		}
		JsonResultPO jsonResultPO = JsonResultPO.getSuccessJsonResult();
		if(crawlMethod == crawlmethod_keyword){
			Map<String,List<String>> operationFetchItemMap = new HashMap<String,List<String>>();
			int websiteOperationId = 323;//微信站内搜素
			String collectionName = "queue_" + websiteOperationId;
			List<String> idList = new ArrayList<String>();
			String[] keywordList = keyword.split(",");
			for(String word : keywordList){
				TaskFetchItem item = new TaskFetchItem();
				item.setWebsiteOperationId(websiteOperationId);
				item.setStatus(SpiderConstants.FETCH_STATUS_UNDO);
				item.setFetchTime(0L);
				item.setNeedRecycle(0);
				item.setFetchInterval(3600*1000L);
				item.setNextFetchTime(0L);
				item.setFlag(1);
				item.setEndPage("10");
				Date d = new Date();
				item.setInsertTime(gDateformat.format(d));
				item.setUpdateTime(gDateformat.format(d));
				Map<String, Object> param = new HashMap<String,Object>();
				param.put("query", word);
				item.setParam(param);
				itemService.addFetchItem(item, collectionName);
				String _id = item.getId();
				idList.add(_id);
			}
			operationFetchItemMap.put(""+websiteOperationId, idList);
			jsonResultPO.setData(operationFetchItemMap);
		}
		return jsonResultPO;
	}

	private JsonResultPO addWeibo(int crawlMethod) {
		if(!checkCrawlMethodForWeibo(crawlMethod)){
			throw new BizException(ErrorCode.CRAWLMETHOD_NOT_SUPPORT.getErrorCode(),ErrorCode.CRAWLMETHOD_NOT_SUPPORT.getErrorMsg());
		}
		JsonResultPO jsonResultPO = JsonResultPO.getSuccessJsonResult();
		if(crawlMethod == crawlmethod_keyword){
			String str_start_date = getRequest().getParameter("startDate");
			String str_end_date = getRequest().getParameter("endDate");
			String keyword = getRequest().getParameter("keyword");
			if(Validate.isEmpty(keyword)){
				throw new BizException(ErrorCode.KEYWORD_MISS.getErrorCode(),ErrorCode.KEYWORD_MISS.getErrorMsg());
			}
			if(!Validate.isEmpty(str_start_date) && !Validate.isEmpty(str_end_date)){
				if(!Validate.isDate(str_start_date, "yyyy-MM-dd", true)){
					throw new BizException(ErrorCode.DATE_FORMAT_ERROR.getErrorCode(),ErrorCode.DATE_FORMAT_ERROR.getErrorMsg()+"!格式：yyyy-MM-dd");
				}
				if(!Validate.isDate(str_end_date, "yyyy-MM-dd", true)){
					throw new BizException(ErrorCode.DATE_FORMAT_ERROR.getErrorCode(),ErrorCode.DATE_FORMAT_ERROR.getErrorMsg()+"!格式：yyyy-MM-dd");
				}
				if(str_start_date.compareTo(str_end_date) > 0){
					throw new BizException(ErrorCode.START_DATE_GT_END_DATE.getErrorCode(),ErrorCode.START_DATE_GT_END_DATE.getErrorMsg());
				}
			}else{
				throw new BizException(ErrorCode.CRAWL_DATE_MISS.getErrorCode(),ErrorCode.CRAWL_DATE_MISS.getErrorMsg());
			}
			Map<String,List<String>> operationFetchItemMap = new HashMap<String,List<String>>();
			int websiteOperationId = 267;//新浪微博站内搜素
			String collectionName = "queue_" + websiteOperationId;
			List<String> idList = new ArrayList<String>();
			String[] keywordList = keyword.split(",");
			for(String word : keywordList){
				TaskFetchItem item = new TaskFetchItem();
				item.setWebsiteOperationId(websiteOperationId);
				item.setStatus(SpiderConstants.FETCH_STATUS_UNDO);
				item.setFetchTime(0L);
				item.setNeedRecycle(0);
				item.setFetchInterval(3600*1000L);
				item.setNextFetchTime(0L);
				item.setFlag(1);
				item.setEndPage("50");
				Date d = new Date();
				item.setInsertTime(gDateformat.format(d));
				item.setUpdateTime(gDateformat.format(d));
				Map<String, Object> param = new HashMap<String,Object>();
				param.put("timescope", "custom:"+str_start_date+":"+str_end_date);
				param.put("scope", "ori");//搜素范围 ori:原创
				param.put("query", word);
				item.setParam(param);
				itemService.addFetchItem(item, collectionName);
				String _id = item.getId();
				idList.add(_id);
			}
			operationFetchItemMap.put(""+websiteOperationId, idList);
			jsonResultPO.setData(operationFetchItemMap);
		}
		return jsonResultPO;
	}

	private JsonResultPO addNews(int crawlMethod) {
		if(!checkCrawlMethodForNews(crawlMethod)){
			throw new BizException(ErrorCode.CRAWLMETHOD_NOT_SUPPORT.getErrorCode(),ErrorCode.CRAWLMETHOD_NOT_SUPPORT.getErrorMsg());
		}
		JsonResultPO jsonResultPO = JsonResultPO.getSuccessJsonResult();
		if(crawlMethod == crawlmethod_keyword){
			String str_start_date = getRequest().getParameter("startDate");
			String str_end_date = getRequest().getParameter("endDate");
			String keyword = getRequest().getParameter("keyword");
			if(Validate.isEmpty(keyword)){
				throw new BizException(ErrorCode.KEYWORD_MISS.getErrorCode(),ErrorCode.KEYWORD_MISS.getErrorMsg());
			}
			if(!Validate.isEmpty(str_start_date) && !Validate.isEmpty(str_end_date)){
				if(!Validate.isDate(str_start_date, "yyyy-MM-dd", true)){
					throw new BizException(ErrorCode.DATE_FORMAT_ERROR.getErrorCode(),ErrorCode.DATE_FORMAT_ERROR.getErrorMsg()+"!格式：yyyy-MM-dd");
				}
				if(!Validate.isDate(str_end_date, "yyyy-MM-dd", true)){
					throw new BizException(ErrorCode.DATE_FORMAT_ERROR.getErrorCode(),ErrorCode.DATE_FORMAT_ERROR.getErrorMsg()+"!格式：yyyy-MM-dd");
				}
				if(str_start_date.compareTo(str_end_date) > 0){
					throw new BizException(ErrorCode.START_DATE_GT_END_DATE.getErrorCode(),ErrorCode.START_DATE_GT_END_DATE.getErrorMsg());
				}
			}else{
				throw new BizException(ErrorCode.CRAWL_DATE_MISS.getErrorCode(),ErrorCode.CRAWL_DATE_MISS.getErrorMsg());
			}
			Map<String,List<String>> operationFetchItemMap = new HashMap<String,List<String>>();
			int websiteOperationId = 280;//新浪新闻站内搜素
			String collectionName = "queue_" + websiteOperationId;
			List<String> idList = new ArrayList<String>();
			String[] keywordList = keyword.split(",");
			for(String word : keywordList){
				TaskFetchItem item = new TaskFetchItem();
				item.setWebsiteOperationId(websiteOperationId);
				item.setStatus(SpiderConstants.FETCH_STATUS_UNDO);
				item.setFetchTime(0L);
				item.setNeedRecycle(0);
				item.setFetchInterval(3600*1000L);
				item.setNextFetchTime(0L);
				item.setFlag(1);
				Date d = new Date();
				item.setInsertTime(gDateformat.format(d));
				item.setUpdateTime(gDateformat.format(d));
				Map<String, Object> param = new HashMap<String,Object>();
				param.put("stime", str_start_date);
				param.put("etime", str_end_date);
				param.put("time", "custom");
				param.put("range", "all");//搜素范围 all:新闻全文，title:标题
				param.put("col", "1-3");//频道 新闻频道：1_3
				param.put("q", word);
				item.setParam(param);
				itemService.addFetchItem(item, collectionName);
				String _id = item.getId();
				idList.add(_id);
			}
			operationFetchItemMap.put(""+websiteOperationId, idList);
			jsonResultPO.setData(operationFetchItemMap);
		}
		return jsonResultPO;
	}

	private void writeJson(JsonResultPO jsonResultPO) {
		//将实体对象转换为JSON Object转换  
	    JSONObject responseJSONObject = JSONObject.fromObject(jsonResultPO);  
	    getResponse().setCharacterEncoding("UTF-8");  
	    getResponse().setContentType("application/json; charset=utf-8");  
	    PrintWriter out = null;  
	    try {  
	        out = getResponse().getWriter();  
	        out.append(responseJSONObject.toString());  
	    } catch (IOException e) {  
	    	logger.error("出现异常：" + e.getMessage(), e);
	    } finally {  
	        if (out != null) {  
	            out.close();  
	        }  
	    }
	}
	
	private boolean checkCrawlMethodForWeixin(int crawlMethod) {
		Set<Integer> crawlMethodSet = new HashSet<Integer>();
		crawlMethodSet.add(crawlmethod_keyword);
		if(crawlMethodSet.contains(crawlMethod)){
			return true;
		}
		return false;
	}

	private boolean checkCrawlMethodForNews(int crawlMethod) {
		Set<Integer> crawlMethodSet = new HashSet<Integer>();
		crawlMethodSet.add(crawlmethod_keyword);
		if(crawlMethodSet.contains(crawlMethod)){
			return true;
		}
		return false;
	}
	
	private boolean checkCrawlMethodForWeibo(int crawlMethod) {
		Set<Integer> crawlMethodSet = new HashSet<Integer>();
		crawlMethodSet.add(crawlmethod_keyword);
		if(crawlMethodSet.contains(crawlMethod)){
			return true;
		}
		return false;
	}

	private boolean checkCrawlType(int crawlType) {
		
		Set<Integer> crawlTypeSet = new HashSet<Integer>();
		crawlTypeSet.add(crawltype_news);
		crawlTypeSet.add(crawltype_weibo);
		crawlTypeSet.add(crawltype_weixin);
		if(crawlTypeSet.contains(crawlType)){
			return true;
		}
		return false;
		
		
		
	}
}
