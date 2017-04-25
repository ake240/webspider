package com.datayes.webspider.action.task;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.alibaba.fastjson.JSONObject;
import com.datayes.webspider.action.BaseAction;
import com.datayes.webspider.constant.TaskStatusConstant;
import com.datayes.webspider.domain.task.Task;
import com.datayes.webspider.domain.website.WebSite;
import com.datayes.webspider.domain.websiteOps.WebSiteOperation;
import com.datayes.webspider.service.task.ITaskService;
import com.datayes.webspider.service.websiteOps.IWebSiteOpsService;

@ParentPackage("webspider")
@Namespace("/")
@Results({ 
	@Result(name = "page", location = "/WEB-INF/jsp/task/editTask.jsp"),
	@Result(name = "redirect2success", location = "/WEB-INF/jsp/success.jsp"),
	@Result(name = "json", type="json", params={"includeProperties","jsonResult"}), 
})
public class EditTaskAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private Integer taskId;
	private Task task;
	private int type;
	private Integer status;
	private Integer webSiteOpsId;
	private String message;
	private String jsonResult;
	
	@Resource
	private ITaskService taskService;
	
	@Resource
	private IWebSiteOpsService webSiteOpsService;
	
	@Action("task")
	public String task() {
		if (type != 0) {
			task = taskService.getTaskById(taskId);
			this.webSiteOpsId = task.getWebSiteOps().getWebSiteOpsId();
		}
		return "page";
	}
	
	@Action("newTask")
	public String newTask() {
		boolean succeed = true;
		if (task != null && !StringUtils.isEmpty(task.getTaskName()) && webSiteOpsId != null
				&& !StringUtils.isEmpty(task.getTaskEnName()) && !StringUtils.isEmpty(task.getProcClass())) {
			Task taskExist = taskService.getTaskByEnName(task.getTaskEnName());
			if (taskExist != null) {
				succeed = false;
				message = "任务英文名称已经存在";
			}
			
			if (succeed) {
				WebSiteOperation webSiteOps = webSiteOpsService.getWebSiteOpsById(webSiteOpsId);
				if (webSiteOps != null) {
					WebSite webSite = webSiteOps.getWebSite();
					task.setWebSite(webSite);
					task.setWebSiteOps(webSiteOps);
					
					if (StringUtils.isEmpty(task.getExtroParam())) {
						task.setExtroParam("{}");
					}
					if (task.getProcInterval() == null) {
						task.setProcInterval(1);
					}
					
					task.setUpdateTime(new Date());
					taskService.saveOrUpdate(task);
				}else{
					succeed = false;
					message = "找不到对应的操作类型, webSiteOpsId: " + webSiteOpsId;
				}
			}
		}else{
			succeed = false;
			message = "必填字段不允许为空";
		}
		
		if (!succeed) {
			return "page";
		}
		return "redirect2success";
	}
	
	@Action("editTask")
	public String editTask() {
		boolean succeed = true;
		if (task != null && !StringUtils.isEmpty(task.getTaskName()) && webSiteOpsId != null
				&& !StringUtils.isEmpty(task.getTaskEnName()) && !StringUtils.isEmpty(task.getProcClass())) {
			Task taskInDB = taskService.getTaskById(taskId);
			if (taskInDB != null) {
				if (!taskInDB.getTaskEnName().equals(task.getTaskEnName())) {
					Task taskExist = taskService.getTaskByEnName(task.getTaskEnName());
					if (taskExist != null) {
						succeed = false;
						message = "任务英文名称已经存在";
					}
				}
				
				if (!taskInDB.getWebSiteOps().getWebSiteOpsId().equals(webSiteOpsId)) {
					WebSiteOperation webSiteOps = webSiteOpsService.getWebSiteOpsById(webSiteOpsId);
					if (webSiteOps != null) {
						WebSite webSite = webSiteOps.getWebSite();
						taskInDB.setWebSite(webSite);
						taskInDB.setWebSiteOps(webSiteOps);
					}else{
						succeed = false;
						message = "找不到对应的操作类型, webSiteOpsId: " + webSiteOpsId;
					}
				}
				
				if (succeed) {
					if (StringUtils.isEmpty(task.getExtroParam())) {
						taskInDB.setExtroParam("{}");
					}else{
						taskInDB.setExtroParam(task.getExtroParam());
					}
					if (task.getProcInterval() == null) {
						taskInDB.setProcInterval(1);
					}else{
						taskInDB.setProcInterval(task.getProcInterval());
					}
					
					taskInDB.setTaskName(task.getTaskName());
					taskInDB.setTaskEnName(task.getTaskEnName());
					taskInDB.setProcHost(task.getProcHost());
					taskInDB.setProcClass(task.getProcClass());
					taskInDB.setProcExpr(task.getProcExpr());
					taskInDB.setTaskType(task.getTaskType());
					taskInDB.setNeedRecycle(task.getNeedRecycle());
					taskInDB.setTaskStatus(task.getTaskStatus());
					taskInDB.setUpdateTime(new Date());
					taskService.saveOrUpdate(taskInDB);
				}
			}else{
				succeed = false;
				message = "找不到对应的任务, taskId: " + taskId;
			}
		}else{
			succeed = false;
			message = "必填字段不允许为空";
		}
		
		if (!succeed) {
			return "page";
		}
		return "redirect2success";
	}
	
	@Action("changeTaskStatus")
	public String changeStatus(){
		boolean succeed = true;
		String msg = "";
		if (taskId != null && status != null) {
			Task taskInDB = taskService.getTaskById(taskId);
			if (taskInDB != null) {
				if (taskInDB.getTaskStatus().equals(TaskStatusConstant.FINISHED) ||
						taskInDB.getTaskStatus().equals(TaskStatusConstant.ERROR)) {
					taskInDB.setProcMessage("");
				}
				taskInDB.setTaskStatus(status);
				taskInDB.setUpdateTime(new Date());
				taskService.saveOrUpdate(taskInDB);
			}else{
				succeed = false;
				msg = "找不到对应的任务, taskId: " + taskId;
			}
		}else{
			succeed = false;
			msg = "taskId或status不允许为空";
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("succeed", succeed);
		jsonObject.put("msg", msg);
		this.jsonResult = jsonObject.toJSONString();
		return "json";
	}

	public Integer getWebSiteOpsId() {
		return webSiteOpsId;
	}

	public void setWebSiteOpsId(Integer webSiteOpsId) {
		this.webSiteOpsId = webSiteOpsId;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
