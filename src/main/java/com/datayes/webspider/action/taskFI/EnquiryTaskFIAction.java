package com.datayes.webspider.action.taskFI;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.alibaba.fastjson.JSONObject;
import com.datayes.webspider.action.BaseAction;
import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.constant.PageSizeConstants;
import com.datayes.webspider.service.taskFI.ITaskFetchItemService;
import com.datayes.webspider.service.websiteOps.IWebSiteOpsService;
@ParentPackage("webspider")
@Namespace("/")
@Results({
	@Result(name="NormalSearch",location="/WEB-INF/jsp/taskFI/enquiryTaskFetchItem.jsp"),
	@Result(name="AjaxSearch",location="/WEB-INF/jsp/ajax/enquiryTaskFetchItemResult.jsp"),
	@Result(name = "json", type = "json", params = { "includeProperties",
	"jsonResult" })
})
public class EnquiryTaskFIAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private int isAjaxSearch;
	private PageDTO pageDTO;
//	private Integer taskId;
//	private String taskName;
	private String enquiryStatus;
	private String enquiryParam;
	private Integer webSiteOpsId;
	private String webSiteOpsName;
	private int pageNow=1;
	private Integer batchStatus;
	private Integer batchFlag;
	private String[] checkFIs;
	private String jsonResult;
	
	@Resource
	private IWebSiteOpsService ebSiteOpsService; 
	@Resource
	private ITaskFetchItemService itemService;


	@Action("enquiryTaskFI")
	public String enquiryTaskFI(){
		String queueName="queue_"+webSiteOpsId.toString();
		if(webSiteOpsId!=null){
			webSiteOpsName=ebSiteOpsService.getWebSiteOpsById(webSiteOpsId).getWebSiteOpsName();
			pageDTO=itemService.enquiryTaskFetchItemPage(enquiryParam, pageNow, PageSizeConstants.DEFAULT_SIZE, queueName);
		}
		if(0==isAjaxSearch){
			return "NormalSearch";
		}else{
			return "AjaxSearch";
		}
	}
	
	@Action("batchEdit")
	public String batchEditTaskFI(){
		boolean success=false;
		String result="";
		String queueName="queue_"+webSiteOpsId.toString();
		Map<String,Object> queryPara=new HashMap<String,Object>();
		if(batchFlag!=null){
			success=true;
			queryPara.put("flag", batchFlag);
		}else{
			result+="抓取标识无修改，";
		}
		if(batchStatus!=null){
			success=true;
			queryPara.put("status", batchStatus);
		}else{
			result+="抓取状态无修改，";
		}
		if(checkFIs==null){
			success=false;
			result+="无选中项，";
		}else{
			checkFIs=checkFIs[0].split(",");
			List<String> ids=Arrays.asList(checkFIs);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			queryPara.put("updateTime", sdf.format(new Date()));
			String sub=itemService.updateItems(queueName, queryPara, ids);
			if(StringUtils.isEmpty(sub)){
				success&=true;
			}else{
				success=false;
				result+=sub;
			}
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("succeed", success);
		jsonObject.put("msg", "批量操作结果：" + result + " in " + queueName);
		this.setJsonResult(jsonObject.toJSONString());
		return "json";
	}
	
	public int getIsAjaxSearch() {
		return isAjaxSearch;
	}

	public void setIsAjaxSearch(int isAjaxSearch) {
		this.isAjaxSearch = isAjaxSearch;
	}

	public PageDTO getPageDTO() {
		return pageDTO;
	}

	public void setPageDTO(PageDTO pageDTO) {
		this.pageDTO = pageDTO;
	}


	public String getEnquiryStatus() {
		return enquiryStatus;
	}

	public void setEnquiryStatus(String enquiryStatus) {
		this.enquiryStatus = enquiryStatus;
	}

	public int getPageNow() {
		return pageNow;
	}

	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}

	public ITaskFetchItemService getItemService() {
		return itemService;
	}

	public void setItemService(ITaskFetchItemService itemService) {
		this.itemService = itemService;
	}

	public String getEnquiryParam() {
		return enquiryParam;
	}

	public void setEnquiryParam(String enquiryParam) {
		this.enquiryParam = enquiryParam;
	}

	public Integer getWebSiteOpsId() {
		return webSiteOpsId;
	}

	public void setWebSiteOpsId(Integer webSiteOpsId) {
		this.webSiteOpsId = webSiteOpsId;
	}

	public String getWebSiteOpsName() {
		return webSiteOpsName;
	}

	public void setWebSiteOpsName(String webSiteOpsName) {
		this.webSiteOpsName = webSiteOpsName;
	}

	public String[] getCheckFIs() {
		return checkFIs;
	}

	public void setCheckFIs(String[] checkFIs) {
		this.checkFIs = checkFIs;
	}

	public Integer getBatchFlag() {
		return batchFlag;
	}

	public void setBatchFlag(Integer batchFlag) {
		this.batchFlag = batchFlag;
	}

	public Integer getBatchStatus() {
		return batchStatus;
	}

	public void setBatchStatus(Integer batchStatus) {
		this.batchStatus = batchStatus;
	}

	public String getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}

	
}
