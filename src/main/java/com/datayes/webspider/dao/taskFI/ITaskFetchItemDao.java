package com.datayes.webspider.dao.taskFI;

import java.util.List;
import java.util.Map;

import com.datayes.webspider.domain.taskFI.TaskFetchItem;


public interface ITaskFetchItemDao {
	public TaskFetchItem getById(String id, String queueName);
	
	public long countByPId(String id, List<Integer> statusList, String queueName);
	
	public List<TaskFetchItem> getByPId(String id, List<Integer> statusList, String queueName);

	public boolean addFetchItem(TaskFetchItem item,String c);

	public boolean save(TaskFetchItem item,String queueName);
	
	public int enquiryItemCount(String status,String queueName);
	
	public boolean checkCollection(String queueName,String...args);
	
	public void delete(TaskFetchItem item,String queueName);

	public List<TaskFetchItem> enquiryItemPage(String enquiry, int pageNow, int pageSize, String queueName);

	public List<TaskFetchItem> enquiryItem(String jsonSql, int pageNow, int pageSize,
			String queueName);

	public String updateItems(String queueName, Map<String, Object> modifies,
			List<String> ids);

}
