package com.datayes.webspider.service.taskFI;

import java.util.List;
import java.util.Map;

import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.domain.taskFI.TaskFetchItem;

public interface ITaskFetchItemService {

	public TaskFetchItem getById(String id,String queueName);
	
	public long countByPId(String id,List<Integer> statusList,String collectionName);
	
	public List<TaskFetchItem> getByPId(String id,List<Integer> statusList,String queueName);
	
	public boolean addFetchItem(TaskFetchItem item, String queueName);
	
	public boolean save(TaskFetchItem item, String queueName);
	
	public boolean checkCollection(String queueName,String ...args);

	public void deleteById(String itemId, String queueName);

	public PageDTO enquiryTaskFetchItemPage(String enquiryParam, int pageNow,
			int defaultSize, String queueName);

	public String updateItems(String queueName, Map<String, Object> modifies,
			List<String> ids);

}
