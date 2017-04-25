package com.datayes.webspider.dao.taskFI;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoDataIntegrityViolationException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.datayes.webspider.domain.taskFI.TaskFetchItem;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;

@Repository("taskFetchItemDao")
public class TaskFetchItemDao implements ITaskFetchItemDao {

	@Resource
	private MongoTemplate mongoTemplate;

	@Override
	public TaskFetchItem getById(String id, String queueName) {
		return mongoTemplate.findById(id, TaskFetchItem.class, queueName);
	}
	
	@Override
	public long countByPId(String id, List<Integer> statusList, String queueName) {
		Criteria criteria = new Criteria();
		criteria.and("pid").is(id);
		criteria.and("status").in(statusList);
		Query query = new Query(criteria);
		return mongoTemplate.count(query, queueName);
	}
	
	@Override
	public List<TaskFetchItem> getByPId(String id, List<Integer> statusList,
			String queueName) {
		Criteria criteria = new Criteria();
		criteria.and("pid").is(id);
		criteria.and("status").in(statusList);
		Query query = new Query(criteria);
		return mongoTemplate.find(query, TaskFetchItem.class, queueName);
	}
	
	@Override
	public boolean addFetchItem(TaskFetchItem item, String queueName) {
		boolean saveFlag = true;
		mongoTemplate.save(item, queueName);
		return saveFlag;
	}

	@Override
	public boolean save(TaskFetchItem item, String queueName) {
		boolean saveFlag=false;
		DBCursor cur=mongoTemplate.getCollection(queueName).find(new BasicDBObject("param", item.getParam()));
		int count=cur.count();
		if(count<1||item.getId()!=null){
			mongoTemplate.save(item, queueName);
			saveFlag=true;
		}else{
			saveFlag=false;
		}
		cur.close();
		return saveFlag;
	}

	@Override
	public void delete(TaskFetchItem item, String queueName) {
		mongoTemplate.remove(item, queueName);
	}

	// @Override
	// public int enquiryItemCount(String enquiry, String queueName) {
	// Criteria criteria=buildCriteria(enquiry);
	// Query query=new Query(criteria);
	// return (int)mongoTemplate.count(query,queueName);
	// }
	
	@Override
	public String updateItems(String queueName, Map<String, Object> modifies,
			List<String> ids) {
		String message = "";
		if (modifies != null && modifies.size() > 0 && ids != null
				&& ids.size() > 0) {
			Query query = new Query(new Criteria("_id").in(ids));
			Update updater = new Update();
			for (String key : modifies.keySet()) {
				updater.set(key, modifies.get(key));
			}
			try{
				WriteResult result = mongoTemplate.updateMulti(query, updater,queueName);
			}catch(MongoDataIntegrityViolationException e){
				message = e.getMessage();
			}
		}
		return message;
	}

	@Override
	public int enquiryItemCount(String jsonSql, String queueName) {
		try {
			if (StringUtils.isEmpty(jsonSql)) {
				jsonSql = "{}";
			}
			BasicDBObject obj = (BasicDBObject) JSON.parse(jsonSql);
			DBCollection c = mongoTemplate.getCollection(queueName);
			DBCursor cur = c.find(obj);
			int count = cur.count();
			cur.close();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	private Criteria buildCriteria(String status) {
		Criteria criteria = new Criteria();
		if (status != null) {
			criteria.and("status").is(status);
		}
		return criteria;
	}

	/***
	 * query by status selector,which is deprecated;
	 */
	@Deprecated
	@Override
	public List<TaskFetchItem> enquiryItemPage(String status, int pageNow,
			int pageSize, String queueName) {
		Criteria criteria = buildCriteria(status);
		Query query = new Query(criteria);
		query.with(new Sort(Sort.Direction.DESC, "insertTime"));
		query.skip((pageNow - 1) * pageSize).limit(pageSize);
		return mongoTemplate.find(query, TaskFetchItem.class, queueName);

	}

	@Override
	public List<TaskFetchItem> enquiryItem(String jsonSql, int pageNow,
			int pageSize, String queueName) {
		List<TaskFetchItem> results = new LinkedList<TaskFetchItem>();
		if (StringUtils.isEmpty(jsonSql)) {
			jsonSql = "{}";
		}
		BasicDBObject obj = (BasicDBObject) JSON.parse(jsonSql);
		DBCursor cur = mongoTemplate.getCollection(queueName).find(obj);
		cur.sort(new BasicDBObject("insertTime", -1));
		cur.skip((pageNow - 1) * pageSize).limit(pageSize);
		while (cur.hasNext()) {
			TaskFetchItem bean = new TaskFetchItem();
			BasicDBObject red = (BasicDBObject) cur.next();
			Field[] fields = bean.getClass().getDeclaredFields();
			for (Field field : fields) {
				String varName = field.getName();
				Object object = red.get(varName);
				if ("id".equals(varName)) {
					object=red.getObjectId("_id");
				}
				if (object != null) {
					try {
						BeanUtils.setProperty(bean, varName, object);
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
			results.add(bean);
		}
		cur.close();
		return results;
	}

	@Override
	public boolean checkCollection(String queueName, String... args) {
		boolean flag = false;
		try {
			if (null != queueName) {
				if (!mongoTemplate.collectionExists(queueName)) {
					mongoTemplate.createCollection(queueName);
					Index comIndex = new Index();
					comIndex.on("status", Direction.ASC);
					comIndex.on("flag", Direction.ASC);
					comIndex.on("nextFetchTime", Direction.DESC);
					mongoTemplate.indexOps(queueName).ensureIndex(comIndex);
					Index index = new Index();
					for (String arg : args) {
						index.on("param." + arg, Direction.ASC);
					}
					index.unique();
					if (args.length > 0)
						mongoTemplate.indexOps(queueName).ensureIndex(index);
				}
				flag = true;
			}
		} catch (Exception e) {
			flag = false;
			System.out.println("create indexs on:" + queueName
					+ " throws exception.");
		}
		return flag;
	}

}
