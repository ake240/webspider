package com.datayes.webspider.action.websiteOps;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.alibaba.fastjson.JSONObject;
import com.datayes.webspider.action.BaseAction;
import com.datayes.webspider.domain.websiteOps.DataStoreType;
import com.datayes.webspider.service.websiteOps.IDataStoreTypeService;

@ParentPackage("webspider")
@Namespace("/")
@Results({ 
	@Result(name = "page", location = "/WEB-INF/jsp/websiteOps/editDataStoreType.jsp"),
	@Result(name = "redirect2success", location = "/WEB-INF/jsp/success.jsp"),
	@Result(name = "json", type="json", params={"includeProperties","jsonResult"}), 
})
public class EditDataStoreTypeAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private DataStoreType dataStoreType;
	private Integer dataStoreTypeId;
	private int type;
	private String jsonResult;
	private String message;
	
	@Resource
	private IDataStoreTypeService dataStoreTypeService;
	
	@Action("dataStoreType")
	public String dataStoreType(){
		if (type == 1) {
			dataStoreType = dataStoreTypeService.findByDataStoreTypeId(dataStoreTypeId);
		}
		return "page";
	}
	
	@Action("newDataStoreType")
	public String newDataStoreType(){
		boolean succeed = true;
		if (dataStoreType != null) {
			DataStoreType existedDT = dataStoreTypeService.findByDataStoreCnName(dataStoreType.getDataStoreTypeCnName());
			if (existedDT == null) {
				dataStoreType.setUpdateTime(new Date());
				dataStoreType.setDataStoreTypeClass("");
				dataStoreTypeService.saveOrUpdate(dataStoreType);
			} else {
				succeed = false;
				message = "存储类型名称已经存在";
			}
		}else{
			succeed = false;
			message = "必填字段不允许为空";
		}
		
		if (succeed) {
			return "redirect2success";
		}
		return "page";
	}
	
	@Action("editDataStoreType")
	public String editDataStoreType(){
		boolean succeed = true;
		this.type = 1;
		
		if (dataStoreType != null) {
			DataStoreType dataStoreTypeInDB = dataStoreTypeService.findByDataStoreTypeId(dataStoreType.getDataStoreTypeId());
			if (dataStoreTypeInDB != null) {
				if (!dataStoreTypeInDB.getDataStoreTypeCnName().equals(dataStoreType.getDataStoreTypeCnName())) {
					DataStoreType existedDT = dataStoreTypeService.findByDataStoreCnName(dataStoreType.getDataStoreTypeCnName());
					if (existedDT == null) {
						dataStoreTypeInDB.setDataStoreTypeCnName(dataStoreType.getDataStoreTypeCnName());
					}else{
						succeed = false;
						message = "存储类型名称已经存在";
					}
				}
				
				dataStoreTypeInDB.setCollectionName(dataStoreType.getCollectionName());
				dataStoreTypeInDB.setDataStoreTypeClass(dataStoreType.getDataStoreTypeClass());
				dataStoreTypeInDB.setUpdateTime(new Date());
				dataStoreTypeService.saveOrUpdate(dataStoreTypeInDB);
			} else {
				succeed = false;
				message = "找不到对应的存储类型, dataStoreTypeId=" + dataStoreType.getDataStoreTypeId();
			}
		}else{
			succeed = false;
			message = "必填字段不允许为空";
		}
		
		if (succeed) {
			return "redirect2success";
		}
		return "page";
	}
	
	@Action("deleteDataStoreType")
	public String deleteDataStoreType(){
		boolean succeed = true;
		String message = "";
		
		DataStoreType dataStoreTypeInDB = dataStoreTypeService.findByDataStoreTypeId(dataStoreTypeId);
		if (dataStoreTypeInDB != null) {
			dataStoreTypeService.deleteDataStoreType(dataStoreTypeInDB);
		}else{
			succeed = false;
			message = "找不到对应的存储类型, dataStoreTypeId=" + dataStoreTypeId;
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("succeed", succeed);
		jsonObject.put("message", message);
		this.jsonResult = jsonObject.toJSONString();
		return "json";
	}

	public DataStoreType getDataStoreType() {
		return dataStoreType;
	}

	public void setDataStoreType(DataStoreType dataStoreType) {
		this.dataStoreType = dataStoreType;
	}

	public Integer getDataStoreTypeId() {
		return dataStoreTypeId;
	}

	public void setDataStoreTypeId(Integer dataStoreTypeId) {
		this.dataStoreTypeId = dataStoreTypeId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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
	
}
