package com.datayes.webspider.dao.task;

import java.util.List;

import com.datayes.webspider.dao.IBaseDao;
import com.datayes.webspider.domain.task.Task;

public interface ITaskDao extends IBaseDao<Task> {
	public Integer enquiryTaskCount(String taskName, Integer webSiteOpsId, String procHost, Integer status);
	
	public List<Task> enquiryTaskPage(String taskName, Integer webSiteOpsId, String procHost, Integer status, int pageNow, int pageSize);
}
