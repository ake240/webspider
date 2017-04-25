package com.datayes.webspider.service.taskFI;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.dao.taskFI.ITaskFetchItemDao;
import com.datayes.webspider.domain.taskFI.TaskFetchItem;

@Service("taskFetchItemService")
public class TaskFetchItemServiceImpl implements ITaskFetchItemService {

	@Resource
	private ITaskFetchItemDao taskFetchItemDao;
	
	@Override
	public TaskFetchItem getById(String id, String queueName) {
		return taskFetchItemDao.getById(id, queueName);
	}
	
	@Override
	public List<TaskFetchItem> getByPId(String id, List<Integer> statusList,
			String queueName) {
		return taskFetchItemDao.getByPId(id, statusList, queueName);
	}
	
	@Override
	public long countByPId(String id, List<Integer> statusList, String collectionName) {
		return taskFetchItemDao.countByPId(id, statusList, collectionName);
	}

	@Override
	public boolean addFetchItem(TaskFetchItem item, String queueName) {
		return taskFetchItemDao.addFetchItem(item, queueName);
	}

	@Override
	public boolean save(TaskFetchItem item, String queueName) {
		return taskFetchItemDao.save(item, queueName);
	}
	
	@Override
	public void deleteById(String itemId, String queueName) {
		TaskFetchItem item=this.getById(itemId, queueName);
		taskFetchItemDao.delete(item, queueName);
	}
	
	@Override
	public String updateItems(String queueName,Map<String,Object> modifies,List<String> ids){
		return taskFetchItemDao.updateItems(queueName, modifies, ids);
	}

	@Override
	public boolean checkCollection(String queueName, String... args) {
		return taskFetchItemDao.checkCollection(queueName, args);
	}

	@Override
	public PageDTO enquiryTaskFetchItemPage(String enquiryParam, int pageNow,
			int pageSize, String queueName) {
		try {
		List list=taskFetchItemDao.enquiryItem(enquiryParam, pageNow, pageSize, queueName);
		int total=taskFetchItemDao.enquiryItemCount(enquiryParam, queueName);
//		int total = taskFetchItemDao.enquiryItemCount(enquiryParam, queueName);
//		List list = taskFetchItemDao.enquiryItemPage(enquiryParam, pageNow, pageSize, queueName);
		return new PageDTO(list, pageNow, pageSize, total);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}


}
