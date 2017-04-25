package com.datayes.webspider.action.taskFI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.alibaba.fastjson.JSONObject;
import com.datayes.webspider.action.BaseAction;
import com.datayes.webspider.domain.taskFI.TaskFetchItem;
import com.datayes.webspider.domain.websiteOps.DataStoreField;
import com.datayes.webspider.domain.websiteOps.DataStoreType;
import com.datayes.webspider.domain.websiteOps.WebSiteOperation;
import com.datayes.webspider.domain.websiteOps.WebSiteOperationParam;
import com.datayes.webspider.domain.websiteOps.WebSiteOpsRel;
import com.datayes.webspider.service.task.ITaskService;
import com.datayes.webspider.service.taskFI.ITaskFetchItemService;
import com.datayes.webspider.service.websiteOps.IDataStoreFieldService;
import com.datayes.webspider.service.websiteOps.IWebSiteOpsParamService;
import com.datayes.webspider.service.websiteOps.IWebSiteOpsRelService;
import com.datayes.webspider.service.websiteOps.IWebSiteOpsService;

@ParentPackage("webspider")
@Namespace("/")
@Results({
		@Result(name = "page", location = "/WEB-INF/jsp/taskFI/editTaskFetchItem.jsp"),
		@Result(name = "redirect2success", location = "/WEB-INF/jsp/success.jsp"),
		@Result(name = "json", type = "json", params = { "includeProperties",
				"jsonResult" }) })
public class EditTaskFIAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private final SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
//	private String taskName;
	private Integer websiteOpsId;
	private WebSiteOperation websiteOps;
//	private Integer taskId;
	private String itemId;
	private TaskFetchItem item;
	private String header;
	private String params;
	private int type;
	private File uploadFile;
	private String jsonResult;
	private String message;
	private String fetchTime;
	private String nextFetchTime;
	private Integer changeFlag;

	@Resource
	private ITaskFetchItemService taskFIService;
	@Resource
	private ITaskService taskService;
	@Resource
	private IWebSiteOpsParamService webSOPService;
	@Resource
	private IWebSiteOpsService webSiteOpsService;
	@Resource
	private IDataStoreFieldService dataStoreFieldService;
	@Resource
	private IWebSiteOpsRelService webSiteOpsRelService;

	@Action("taskFI")
	public String task() {
		if (websiteOpsId != null) {
			websiteOps = webSiteOpsService.getWebSiteOpsById(websiteOpsId);
		}
		if (type == 3) {
			setItem(taskFIService.getById(itemId, "queue_" + websiteOpsId));
			Long fet = item.getFetchTime();
			if (fet == null || fet == 0L) {
				fetchTime = "0";
			} else {
				fetchTime = sdf.format(new Date(fet));
			}
			fet = item.getNextFetchTime();
			if (fet == null || fet == 0L) {
				nextFetchTime = "0";
			} else {
				nextFetchTime = sdf.format(new Date(fet));
			}
		}
		return "page";
	}

	@Action("changeTaskFIStatus")
	public String changeStatus() {
		boolean success = true;
		String msg = "";
		if (itemId != null && websiteOpsId != null) {
			String queueName = "queue_" + websiteOpsId.toString();
			TaskFetchItem item = taskFIService.getById(itemId, queueName);
			if (item != null) {
				int dbFlag = item.getFlag();
				dbFlag ^= 1;
				item.setFlag(dbFlag);
				item.setUpdateTime(sdf.format(new Date()));
				taskFIService.save(item, queueName);
			} else {
				success = false;
				msg = "找不到对应的任务, itemId: " + itemId;
			}
		} else {
			success = false;
			msg = "itemId不允许为空";
		}

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("succeed", success);
		jsonObject.put("msg", msg);
		this.jsonResult = jsonObject.toJSONString();
		return "json";
	}

	@Action("deleteTaskFI")
	public String deleteTaskFI() {
		String queueName = "queue_" + websiteOpsId.toString();
		taskFIService.deleteById(itemId, queueName);
		System.out.println("delete item:" + itemId + " in " + queueName);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("succeed", true);
		jsonObject.put("msg", "成功删除 item:" + itemId + " in " + queueName);
		this.jsonResult = jsonObject.toJSONString();
		return "json";
	}

	@Action("saveTaskFI")
	public String saveTaskFI() {
		boolean success = true;
		String timeStr = sdf.format(new Date());
		String queueName = "queue_" + websiteOpsId.toString();
		if (ensureCollection(queueName)) {
			Map<String,Object> temp=parseJson(params);
			if (uploadFile!=null) {
				BufferedReader br = null;
				try {
					br = new BufferedReader(new InputStreamReader(new FileInputStream(uploadFile), "utf-8"));
					String line;
					int failCount=0;
					while ((line = br.readLine()) != null) {
						if(!constructFetchItem(line, queueName, timeStr, temp)){
							success=false;
							failCount++;
						}
					}
					message+=",导入文件中不符合规范的参数条目数："+failCount;
					br.close();
				} catch (FileNotFoundException e) {
					success &= false;
					message += "未找到目标文件；";
					e.printStackTrace();
				} catch (IOException ioe) {
					success &= false;
					message += "文件读取失败；";
					ioe.printStackTrace();
				}
//				catch(Exception ex){
//					success &= false;
//					message+="从文件导入item，遭遇异常；";
//					ex.printStackTrace();
//				}
				finally {
					if (br != null) {
						try {
							br.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			} else if(StringUtils.isNotBlank(params)){
				if ( temp != null || assertReqParams(websiteOpsId)) {
				if (!assertPWebsiteOpertionExist(websiteOpsId)) {
					if(temp==null||temp.isEmpty()){
						success=false;
						message+=",参数项不能为空";
					}else{
						success &= assertIndex(queueName, temp.keySet());
					}
				}
				if (success){
					item.setParam(temp);
					item.setHeader(parseJson(header));
					item.setInsertTime(timeStr);
					item.setUpdateTime(timeStr);
					item.setWebsiteOperationId(websiteOpsId);
					if(taskFIService.save(item, queueName)){
						success=true;
					}else{
						success=false;
						message=",新建失败：相同param存在数据库中。";
					}
				}
				}else{
					success=false;
					message += ",参数不能为空.";
				}
			} else {
				success = false;
				message += ",缺少有效的入库项。";
			}
		} else {
			success = false;
			message+="创建表格和索引失败。";
		}
		if (success)
			return "redirect2success";
		else
			return "page";
	}
	
	private boolean constructFetchItem(String line,String queueName,String timeStr,Map<String,Object> temp){
		boolean subSuc=true;
		try {
			TaskFetchItem tfi = JSONObject.parseObject(line, TaskFetchItem.class);
			temp=tfi.getParam();
			if ( temp != null || assertReqParams(websiteOpsId)) {
				if (!assertPWebsiteOpertionExist(websiteOpsId)) {
					if(temp==null||temp.isEmpty()){
						subSuc=false;
					}else{
						subSuc &= assertIndex(queueName, temp.keySet());
					}
				}
				}else{
					subSuc=false;
				}
			
			if(subSuc){
				if(tfi.getInsertTime()==null){
					tfi.setInsertTime(timeStr);
				}
				if(tfi.getUpdateTime()==null){
				tfi.setUpdateTime(timeStr);}
				if(tfi.getWebsiteOperationId()==null){
				tfi.setWebsiteOperationId(this.websiteOpsId);}
				taskFIService.save(tfi, queueName);
			}
		} catch (Exception e) {
			e.printStackTrace();
			subSuc=false;
		}
		return subSuc;
	}
	

	/**
	 * 判断请求参数可以为null
	 * 
	 * @return
	 */
	private boolean assertReqParams(final int websiteOpsId) {
		boolean ret = false;
		boolean dynamicReq = assertDynamicReq(websiteOpsId);
		boolean pwebsiteOpertionExist = assertPWebsiteOpertionExist(websiteOpsId);

		if (dynamicReq == false && pwebsiteOpertionExist == true) {
			ret = true;
		}

		return ret;
	}

	/**
	 * 判断父操作类型为null
	 * 
	 * @return
	 */
	private boolean assertPWebsiteOpertionExist(final int websiteOpsId) {
		List<WebSiteOpsRel> lst = webSiteOpsRelService
				.enquiryParentList(websiteOpsId);
		if (null == lst || lst.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * 判断为动态请求
	 * 
	 * @return
	 */
	private boolean assertDynamicReq(final int websiteOpsId) {
		boolean dynamicReq = false;
		List<WebSiteOperationParam> webSiteOperationParamList = webSOPService
				.getByWebSiteOpsId(websiteOpsId);
		for (WebSiteOperationParam webSiteOperationParam : webSiteOperationParamList) {
			int placeholderFlag = webSiteOperationParam.getPlaceholderFlag();
			if (placeholderFlag == 1) {
				dynamicReq = true;
				break;
			}
		}
		return dynamicReq;
	}

	private boolean assertIndex(String queueName, Set<String> fields) {
		boolean flag = true;
		WebSiteOperation websiteOps = webSiteOpsService
				.getWebSiteOpsById(websiteOpsId);
		DataStoreType dataStoreType = websiteOps.getDataStoreType();
		int dataStoreTypeId = dataStoreType.getDataStoreTypeId();
		List<DataStoreField> dataStoreFieldList = dataStoreFieldService
				.findByDataStoreTypeId(dataStoreTypeId);
		for (DataStoreField dataStoreField : dataStoreFieldList) {
			String name = dataStoreField.getFieldEnName();
			if (dataStoreField.getIsUnique() == 1 && !fields.contains(name)) {
				flag = false;
				message += ",注意：参数缺少unique项" + name;
			}
		}
		return flag;
	}

	private boolean ensureCollection(String queueName) {
		boolean flag = false;
		message = queueName + "上建立唯一性索引:";
		try {
			List<String> names = new ArrayList<String>();
			WebSiteOperation websiteOps = webSiteOpsService
					.getWebSiteOpsById(websiteOpsId);

			if (!assertPWebsiteOpertionExist(websiteOpsId)) {
				DataStoreType dataStoreType = websiteOps.getDataStoreType();
				int dataStoreTypeId = dataStoreType.getDataStoreTypeId();
				List<DataStoreField> dataStoreFieldList = dataStoreFieldService
						.findByDataStoreTypeId(dataStoreTypeId);
				for (DataStoreField dataStoreField : dataStoreFieldList) {
					if (dataStoreField.getIsUnique() == 1) {
						String name = dataStoreField.getFieldEnName();
						names.add(name);
						message += "," + name;
					}
				}
				flag = taskFIService.checkCollection(queueName,
						(String[]) names.toArray(new String[names.size()]));
			} else {
				flag = taskFIService.checkCollection(queueName);
				message = queueName + "上无唯一性索引";
			}
		} catch (Exception e) {
			flag = false;
		}
		if (flag)
			message += "。";
		else
			message += "建立失败；";
		return flag;
	}

	private Map<String, Object> parseJson(String jsonStr) {
		Map<String, Object> map = null;
		try {
			if (StringUtils.isEmpty(jsonStr)) {
				return map;
			} else {
				map = new HashMap<String, Object>();
				JSONObject obj = JSONObject.parseObject(jsonStr);
				for (String name : obj.keySet()) {
					map.put(name, obj.get(name));
				}
				if (map.size() < 1) {
					map = null;
				}
			}
		} catch (Exception e) {
			System.out
					.println("error occurs in parsing the jsonStr:" + jsonStr);
		}
		return map;
	}
	
	private boolean assertInputStrChanged(TaskFetchItem item,TaskFetchItem dbItem){
		boolean isChanged=false;
		try {
			String str=item.getUrl();
			String dbStr=dbItem.getUrl();
			if(StringUtils.isNotBlank(dbStr)){
				if(!dbStr.equals(str)){
					isChanged=true;
					dbItem.setUrl(str);
				}
			}else if(StringUtils.isNotBlank(str)){
				isChanged=true;
				dbItem.setUrl(str);
			}
			str=item.getErrorMsg();
			dbStr=dbItem.getErrorMsg();
			if(StringUtils.isNotBlank(dbStr)){
				if(!dbStr.equals(str)){
					isChanged=true;
					dbItem.setErrorMsg(str);
				}
			}else if(StringUtils.isNotBlank(str)){
				isChanged=true;
				dbItem.setErrorMsg(str);
			}
			str=item.getStartPage();
			dbStr=dbItem.getStartPage();
			if(StringUtils.isNotBlank(dbStr)){
				if(!dbStr.equals(str)){
					isChanged=true;
					dbItem.setStartPage(str);
				}
			}else if(StringUtils.isNotBlank(str)){
				isChanged=true;
				dbItem.setStartPage(str);
			}
			str=item.getEndPage();
			dbStr=dbItem.getEndPage();
			if(StringUtils.isNotBlank(dbStr)){
				if(!dbStr.equals(str)){
					isChanged=true;
					dbItem.setEndPage(str);
				}
			}else if(StringUtils.isNotBlank(str)){
				isChanged=true;
				dbItem.setEndPage(str);
			}
			if (item.getStatus() != dbItem.getStatus()) {
				isChanged = true;
				dbItem.setStatus(item.getStatus());
			}
			if (item.getFlag() != dbItem.getFlag()) {
				isChanged = true;
				dbItem.setFlag(item.getFlag());
			}
			if (item.getNeedRecycle()!=(dbItem.getNeedRecycle())) {
				isChanged = true;
				dbItem.setNeedRecycle(item.getNeedRecycle());
			}
		} catch (Exception e) {
			isChanged=false;
			e.printStackTrace();
		}
		return isChanged;
	}

	@Action("editTaskFI")
	public String editTaskFI() {
		String queueName = "queue_" + websiteOpsId.toString();
		boolean success = true;
		boolean isChanged = false;
		TaskFetchItem dbItem = taskFIService.getById(itemId, queueName);
		if (dbItem != null && item != null) {
			isChanged=assertInputStrChanged(item, dbItem);
			Map<String, Object> dbTemp = dbItem.getParam();
			Map<String, Object> temp = parseJson(params);
			
			if (StringUtils.isEmpty(fetchTime) || fetchTime.equals("0")) {
				item.setFetchTime(0L);
			} else {
				try {
					item.setDFetchTime(sdf.parse(fetchTime));
				} catch (ParseException e) {
					e.printStackTrace();
					success = false;
					message += ",获取抓取时间出错" + fetchTime;
				}
			}
			if (!item.getFetchTime().equals(dbItem.getFetchTime())) {
				isChanged = true;
				dbItem.setFetchTime(item.getFetchTime());
			}
			if (!item.getFetchInterval().equals(dbItem.getFetchInterval())) {
				dbItem.setFetchInterval(item.getFetchInterval());
				isChanged = true;
			}

			if (!item.getNextFetchTime().equals(dbItem.getNextFetchTime())) {
				dbItem.setNextFetchTime(item.getNextFetchTime());
				isChanged = true;
			}
			
			if (null != temp || assertReqParams(websiteOpsId)) {
				if (StringUtils.isNotEmpty(params) && temp == null) {
					success=false;
					message += ",参数输入格式错误.";
				} else {
					if (!String.valueOf(temp).equals(String.valueOf(dbTemp))) {
						isChanged = true;
						dbItem.setParam(temp);
						if(!assertPWebsiteOpertionExist(websiteOpsId)){
							if(temp==null||temp.isEmpty()){
								success=false;
								message+=",参数项不能为空";
							}else{
								success &= assertIndex(queueName, temp.keySet());
							}
						}
					}				
				}
			} else {
				success=false;
				message += ",参数不能为空";
			}
			dbTemp = dbItem.getHeader();
			temp = parseJson(header);
			if (StringUtils.isNotEmpty(header) && temp == null) {
				success=false;
				message += ",头部输入格式错误.";
			} else {
				if (!String.valueOf(temp).equals(String.valueOf(dbTemp))) {
					dbItem.setHeader(temp);
					isChanged = true;
				}			}
			if (isChanged) {
				dbItem.setUpdateTime(sdf.format(new Date()));
			} else {
				message += ",无修改项改变。";
			}
		}
		if ( isChanged &&success) {
			taskFIService.save(dbItem, queueName);
			System.out.println("edit and save or update item:" + itemId
					+ " in " + queueName);
			return "redirect2success";
		} else {
			return "page";
		}
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public TaskFetchItem getItem() {
		return item;
	}

	public void setItem(TaskFetchItem item) {
		this.item = item;
		if (item != null) {
			item.setId(itemId);
			item.setWebsiteOperationId(websiteOpsId);
			if (item.getParam() != null)
				this.params = JSONObject.toJSONString(item.getParam());
			if (item.getHeader() != null)
				this.header = JSONObject.toJSONString(item.getHeader());

		}
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Integer getWebsiteOpsId() {
		return websiteOpsId;
	}

	public void setWebsiteOpsId(Integer websiteOpsId) {
		this.websiteOpsId = websiteOpsId;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFetchTime() {
		return fetchTime;
	}

	public void setFetchTime(String fetchTime) {
		this.fetchTime = fetchTime;

	}

	public String getNextFetchTime() {
		return nextFetchTime;
	}

	public void setNextFetchTime(String nextFetchTime) {
		this.nextFetchTime = nextFetchTime;
		if (StringUtils.isEmpty(nextFetchTime) || nextFetchTime.equals("0")) {
			item.setFetchTime(0L);
		} else {
			try {
				item.setDNextFetchTime(sdf.parse(nextFetchTime));
			} catch (ParseException e) {
				message += ",获取下次抓取时间出错" + nextFetchTime;
				e.printStackTrace();
			}
		}
	}

	public WebSiteOperation getWebsiteOps() {
		websiteOps = webSiteOpsService.getWebSiteOpsById(websiteOpsId);
		return websiteOps;
	}

	public void setWebsiteOps(WebSiteOperation websiteOps) {
		this.websiteOps = websiteOps;
	}

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}

	public Integer getChangeFlag() {
		return changeFlag;
	}

	public void setChangeFlag(Integer changeFlag) {
		this.changeFlag = changeFlag;
	}
	
}
