package com.datayes.webspider.service.task;

import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.domain.task.Task;

public interface ITaskService {

	public void saveOrUpdate(Task task);
	
	public Task getTaskById(Integer id);
	
	public Task getTaskByEnName(String enName);
	
	public void deleteTask(Task task);
	
	public PageDTO enquiryTaskPage(String taskName, Integer webSiteOpsId, String procHost, Integer status, int pageNow, int pageSize);
}
