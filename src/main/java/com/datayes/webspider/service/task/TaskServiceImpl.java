package com.datayes.webspider.service.task;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.dao.task.ITaskDao;
import com.datayes.webspider.domain.task.Task;

@Service("taskService")
public class TaskServiceImpl implements ITaskService {

	@Resource
	private ITaskDao taskDao;
	
	@Override
	@Transactional
	public void saveOrUpdate(Task task) {
		taskDao.saveOrUpdate(task);
	}

	@Override
	public Task getTaskById(Integer id) {
		return taskDao.findById("getTaskById", id);
	}

	@Override
	public Task getTaskByEnName(String enName) {
		return taskDao.findByName("getTaskByEnName", enName);
	}

	@Override
	@Transactional
	public void deleteTask(Task task) {
		taskDao.delete(task);
	}

	@Override
	public PageDTO enquiryTaskPage(String taskName, Integer webSiteOpsId, String procHost, Integer status, int pageNow, int pageSize) {
		Integer total = taskDao.enquiryTaskCount(taskName, webSiteOpsId, procHost, status);
		List list = taskDao.enquiryTaskPage(taskName, webSiteOpsId, procHost, status, pageNow, pageSize);
		return new PageDTO(list, pageNow, pageSize, total);
	}

}
