package com.datayes.webspider.dao.task;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.springframework.stereotype.Repository;

import com.datayes.webspider.dao.BaseDao;
import com.datayes.webspider.domain.task.Task;

@Repository("taskDao")
public class TaskDao extends BaseDao<Task> implements ITaskDao {

	@Override
	public Integer enquiryTaskCount(String taskName, Integer webSiteOpsId, String procHost, Integer status) {
		DetachedCriteria dc = buildCriteria(taskName, webSiteOpsId, procHost, status);
		return (Integer) findCount(dc);
	}

	@Override
	public List<Task> enquiryTaskPage(String taskName, Integer webSiteOpsId, String procHost, Integer status, int pageNow, int pageSize) {
		DetachedCriteria dc = buildCriteria(taskName, webSiteOpsId, procHost, status);
		dc.addOrder(Order.desc("insertTime"));
		return findPage(dc, pageNow, pageSize);
	}

	private DetachedCriteria buildCriteria(String taskName, Integer webSiteOpsId, String procHost, Integer status){
		DetachedCriteria dc = DetachedCriteria.forClass(Task.class);
		if (!StringUtils.isEmpty(taskName)) {
			dc.add(Property.forName("taskName").like(taskName, MatchMode.ANYWHERE));
		}
		
		if (webSiteOpsId != null) {
			dc.add(Property.forName("webSiteOps.webSiteOpsId").eq(webSiteOpsId));
		}
		
		if (!StringUtils.isEmpty(procHost)) {
			dc.add(Property.forName("procHost").eq(procHost));
		}
		
		if (status != null) {
			dc.add(Property.forName("taskStatus").eq(status));
		}
		
		return dc;
	}
}
