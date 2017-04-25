package com.datayes.webspider.domain.task;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.datayes.webspider.constant.TaskStatusConstant;
import com.datayes.webspider.domain.website.WebSite;
import com.datayes.webspider.domain.websiteOps.WebSiteOperation;

@Entity
@Table(name = "task")
@NamedQueries({
	@NamedQuery(name = "getTaskById", query = "from Task t where t.taskId = ?"),
	@NamedQuery(name = "getTaskByEnName", query = "from Task t where t.taskEnName = ?"),
})
public class Task {
	@Id
	@GeneratedValue
	@Column(name = "taskid", nullable = false, unique = true)
	private Integer taskId;
	
	@Column(name = "parenttaskid")
	private Integer parentTaskId = 0;
	
	@Column(name = "taskname")
	private String taskName;
	
	@Column(name = "taskEnName", unique = true)
	private String taskEnName;
	
	@Column(name = "threadname")
	private String threadName = "";
	
	@ManyToOne
	@JoinColumn(name = "websiteid", referencedColumnName = "websiteid")
	private WebSite webSite;
	
	@ManyToOne
	@JoinColumn(name = "websiteoperationid", referencedColumnName = "websiteoperationid")
	private WebSiteOperation webSiteOps;
	
	@Column(name = "prochost")
	private String procHost;
	
	@Column(name = "procclass")
	private String procClass = "com.datayes.spider.util.scheduling.impl.DefaultProcessor";
	
	@Column(name = "extroparam")
	private String extroParam;
	
	@Column(name = "procexpr")
	private String procExpr;
	
	@Column(name = "tasktype")
	private Integer taskType;
	
	@Column(name = "needrecycle")
	private Integer needRecycle;
	
	@Column(name = "procinterval")
	private Integer procInterval = 0;
	
	@Column(name = "taskstatus")
	private Integer taskStatus = TaskStatusConstant.WAIT_TO_START_BY_PROGRAM; //-2:等待手动开始执行;-1:等待程序开始执行;0:开始执行;1:执行中;2:执行完成;9:执行出错
	
	@Column(name = "procmessage")
	private String procMessage = "";
	
	@Column(name = "sortno")
	private Integer sortNo = 0;
	
	@Column(name = "startexectime")
	private Integer startExecTime = 0;
	
	@Column(name = "lastexectime")
	private Integer lastExecTime = 0;
	
	@Column(name = "inserttime")
	private Date insertTime = new Date();
	
	@Column(name = "updatetime")
	private Date updateTime;

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public Integer getParentTaskId() {
		return parentTaskId;
	}

	public void setParentTaskId(Integer parentTaskId) {
		this.parentTaskId = parentTaskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskEnName() {
		return taskEnName;
	}

	public void setTaskEnName(String taskEnName) {
		this.taskEnName = taskEnName;
	}

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public WebSite getWebSite() {
		return webSite;
	}

	public void setWebSite(WebSite webSite) {
		this.webSite = webSite;
	}

	public WebSiteOperation getWebSiteOps() {
		return webSiteOps;
	}

	public void setWebSiteOps(WebSiteOperation webSiteOps) {
		this.webSiteOps = webSiteOps;
	}

	public String getProcHost() {
		return procHost;
	}

	public void setProcHost(String procHost) {
		this.procHost = procHost;
	}

	public String getProcClass() {
		return procClass;
	}

	public void setProcClass(String procClass) {
		this.procClass = procClass;
	}

	public String getExtroParam() {
		return extroParam;
	}

	public void setExtroParam(String extroParam) {
		this.extroParam = extroParam;
	}

	public String getProcExpr() {
		return procExpr;
	}

	public void setProcExpr(String procExpr) {
		this.procExpr = procExpr;
	}

	public Integer getTaskType() {
		return taskType;
	}

	public void setTaskType(Integer taskType) {
		this.taskType = taskType;
	}

	public Integer getNeedRecycle() {
		return needRecycle;
	}

	public void setNeedRecycle(Integer needRecycle) {
		this.needRecycle = needRecycle;
	}

	public Integer getProcInterval() {
		return procInterval;
	}

	public void setProcInterval(Integer procInterval) {
		this.procInterval = procInterval;
	}

	public Integer getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(Integer taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getProcMessage() {
		return procMessage;
	}

	public void setProcMessage(String procMessage) {
		this.procMessage = procMessage;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public Integer getStartExecTime() {
		return startExecTime;
	}

	public void setStartExecTime(Integer startExecTime) {
		this.startExecTime = startExecTime;
	}

	public Integer getLastExecTime() {
		return lastExecTime;
	}

	public void setLastExecTime(Integer lastExecTime) {
		this.lastExecTime = lastExecTime;
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
	
	
}
