package com.datayes.webspider.action.websiteOps;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.datayes.webspider.action.BaseAction;
import com.datayes.webspider.domain.websiteOps.WebSiteOperation;
import com.datayes.webspider.domain.websiteOps.WebSiteOperationQueue;
import com.datayes.webspider.service.websiteOps.IWebSiteOpsQueueService;
import com.datayes.webspider.service.websiteOps.IWebSiteOpsService;

@ParentPackage("webspider")
@Namespace("/")
@Results({ 
	@Result(name = "page", location = "/WEB-INF/jsp/websiteOps/editWebSiteOpsQueue.jsp"),
	@Result(name = "redirect2success", location = "/WEB-INF/jsp/success.jsp"),
	@Result(name = "json", type="json", params={"includeProperties","jsonResult"}), 
})
public class EditWebSiteOpsQueueAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private Integer queueId;
	private WebSiteOperationQueue webSiteOpsQueue;
	private int type;
	private Integer webSiteOpsId;
	private String jsonResult;
	private String message;
	
	@Resource
	private IWebSiteOpsQueueService webSiteOpsQueueService;
	
	@Resource
	private IWebSiteOpsService webSiteOpsService;
	
	@Action("webSiteOpsQueue")
	public String webSiteOpsQueue(){
		if (type != 0) {
			webSiteOpsQueue = webSiteOpsQueueService.getWebSiteOpsQueueById(queueId);
			this.webSiteOpsId = webSiteOpsQueue.getWebSiteOps().getWebSiteOpsId();
		}
		return "page";
	}
	
	@Action("newWebSiteOpsQueue")
	public String newWebSiteOpsQueue(){
		boolean succeed = true;
		if (webSiteOpsQueue != null && !StringUtils.isEmpty(webSiteOpsQueue.getQueueCnName())
				&& !StringUtils.isEmpty(webSiteOpsQueue.getQueueEnName()) && webSiteOpsId != null) {
			WebSiteOperationQueue queueExist = webSiteOpsQueueService.getWebSiteOpsQueueByEnName(webSiteOpsQueue.getQueueEnName());
			if (queueExist == null) {
				WebSiteOperation webSiteOpsExist = webSiteOpsService.getWebSiteOpsById(webSiteOpsId);
				if (webSiteOpsExist !=null) {
					webSiteOpsQueue.setWebSiteOps(webSiteOpsExist);
					webSiteOpsQueue.setUpdateTime(new Date());
					webSiteOpsQueueService.saveOrUpdate(webSiteOpsQueue);
				}else{
					succeed = false;
					message = "找不到对应的网站操作类型, webSiteOpsId: " + webSiteOpsId;
				}
			}else{
				succeed = false;
				message = "队列英文名称已经存在";
			}
		}else{
			succeed = false;
			message = "必填字段不允许为空";
		}
		
		if (succeed) {
			return "redirect2success";
		}
		return "page";
	}

	@Action("editWebSiteOpsQueue")
	public String editWebSiteOpsQueue(){
		boolean succeed = true;
		if (webSiteOpsQueue != null && !StringUtils.isEmpty(webSiteOpsQueue.getQueueCnName())
				&& !StringUtils.isEmpty(webSiteOpsQueue.getQueueEnName()) && webSiteOpsId != null && queueId != null) {
			WebSiteOperationQueue queueInDB = webSiteOpsQueueService.getWebSiteOpsQueueById(queueId);
			if (queueInDB != null) {
				if (!queueInDB.getQueueEnName().equals(webSiteOpsQueue.getQueueEnName())) {
					WebSiteOperationQueue queueExist = webSiteOpsQueueService.getWebSiteOpsQueueByEnName(webSiteOpsQueue.getQueueEnName());
					if (queueExist != null) {
						succeed = false;
						message = "网站操作队列英文名称已经存在";
					}
				}
				
				if (succeed) {
					if (!queueInDB.getWebSiteOps().getWebSiteOpsId().equals(webSiteOpsId)) {
						WebSiteOperation webSiteOpsExist = webSiteOpsService.getWebSiteOpsById(webSiteOpsId);
						if (webSiteOpsExist !=null) {
							queueInDB.setWebSiteOps(webSiteOpsExist);
						}else{
							succeed = false;
							message = "找不到对应的网站操作类型, webSiteOpsId: " + webSiteOpsId;
						}
					}
				}
				
				if (succeed) {
					queueInDB.setQueueCnName(webSiteOpsQueue.getQueueCnName());
					queueInDB.setQueueEnName(webSiteOpsQueue.getQueueEnName());
					queueInDB.setUpdateTime(new Date());
					webSiteOpsQueueService.saveOrUpdate(queueInDB);
				}
			}else{
				succeed = false;
				message = "找不到对应的网站操作队列, queueId: " + queueId;
			}
		}
		if (succeed) {
			return "redirect2success";
		}
		return "page";
	}
	
	public Integer getQueueId() {
		return queueId;
	}

	public void setQueueId(Integer queueId) {
		this.queueId = queueId;
	}

	public WebSiteOperationQueue getWebSiteOpsQueue() {
		return webSiteOpsQueue;
	}

	public void setWebSiteOpsQueue(WebSiteOperationQueue webSiteOpsQueue) {
		this.webSiteOpsQueue = webSiteOpsQueue;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Integer getWebSiteOpsId() {
		return webSiteOpsId;
	}

	public void setWebSiteOpsId(Integer webSiteOpsId) {
		this.webSiteOpsId = webSiteOpsId;
	}

	public String getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}

	public IWebSiteOpsQueueService getWebSiteOpsQueueService() {
		return webSiteOpsQueueService;
	}

	public void setWebSiteOpsQueueService(IWebSiteOpsQueueService webSiteOpsQueueService) {
		this.webSiteOpsQueueService = webSiteOpsQueueService;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
