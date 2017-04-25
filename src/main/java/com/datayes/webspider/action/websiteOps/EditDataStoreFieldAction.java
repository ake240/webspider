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
import com.datayes.webspider.domain.websiteOps.DataStoreField;
import com.datayes.webspider.domain.websiteOps.DataStoreType;
import com.datayes.webspider.service.websiteOps.IDataStoreFieldService;
import com.datayes.webspider.service.websiteOps.IDataStoreTypeService;

@ParentPackage("webspider")
@Namespace("/")
@Results({ 
	@Result(name = "page", location = "/WEB-INF/jsp/websiteOps/editDataStoreField.jsp"),
	@Result(name = "redirect2success", location = "/WEB-INF/jsp/success.jsp"),
	@Result(name = "json", type="json", params={"includeProperties","jsonResult"}), 
})
public class EditDataStoreFieldAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private DataStoreField dataStoreField;
	private Integer dataStoreTypeId;
	private Integer dataStoreFieldId;
	private int type;
	private String jsonResult;
	private String message;
	
	@Resource
	private IDataStoreFieldService dataStoreFieldService;
	
	@Resource
	private IDataStoreTypeService dataStoreTypeService;
	
	@Action("dataStoreField")
	public String dataStoreField(){
		if (type == 1) {
			dataStoreField = dataStoreFieldService.findByDataStoreFieldId(dataStoreFieldId);
			this.dataStoreTypeId = dataStoreField.getDataStoreType().getDataStoreTypeId();
		}
		return "page";
	}
	
	@Action("newDataStoreField")
	public String newDataStoreField(){
		boolean succeed = true;
		if (dataStoreField != null) {
			DataStoreType dataStoreTypeInDB = dataStoreTypeService.findByDataStoreTypeId(dataStoreTypeId);
			if (dataStoreTypeInDB != null) {
				dataStoreField.setDataStoreType(dataStoreTypeInDB);
				dataStoreField.setUpdateTime(new Date());
				dataStoreFieldService.saveOrUpdate(dataStoreField);
			}else{
				succeed = false;
				message = "找不到对应的存储类型, dataStoreTypeId:" + dataStoreTypeId;
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
	
	@Action("editDataStoreField")
	public String editDataStoreField(){
		boolean succeed = true;
		this.type = 1;
		
		if (dataStoreField != null) {
			DataStoreField dataStoreFieldInDB = dataStoreFieldService.findByDataStoreFieldId(dataStoreField.getDataStoreFieldId());
			if (dataStoreFieldInDB != null) {
				if (!dataStoreFieldInDB.getDataStoreType().getDataStoreTypeId().equals(dataStoreTypeId)) {
					DataStoreType dataStoreTypeInDB = dataStoreTypeService.findByDataStoreTypeId(dataStoreTypeId);
					if (dataStoreTypeInDB != null) {
						dataStoreFieldInDB.setDataStoreType(dataStoreTypeInDB);
					}else{
						succeed = false;
						message = "找不到对应的存储类型, dataStoreTypeId:" + dataStoreTypeId;
					}
				}
				
				dataStoreFieldInDB.setFieldCnName(dataStoreField.getFieldCnName());
				dataStoreFieldInDB.setFieldEnName(dataStoreField.getFieldEnName());
				dataStoreFieldInDB.setFieldType(dataStoreField.getFieldType());
				dataStoreFieldInDB.setIsUnique(dataStoreField.getIsUnique());
				dataStoreFieldInDB.setUpdateTime(new Date());
				dataStoreFieldService.saveOrUpdate(dataStoreFieldInDB);
			} else {
				succeed = false;
				message = "找不到对应的存储字段, dataStoreFieldId=" + dataStoreField.getDataStoreFieldId();
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
	
	@Action("deleteDataStoreField")
	public String deleteDataStoreField(){
		boolean succeed = true;
		String message = "";
		
		DataStoreField dataStoreFieldInDB = dataStoreFieldService.findByDataStoreFieldId(dataStoreFieldId);
		if (dataStoreFieldInDB != null) {
			dataStoreFieldService.deleteDataStoreField(dataStoreFieldInDB);
		}else{
			succeed = false;
			message = "找不到对应的存储字段, dataStoreFieldId=" + dataStoreFieldId;
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("succeed", succeed);
		jsonObject.put("message", message);
		this.jsonResult = jsonObject.toJSONString();
		return "json";
	}

	public DataStoreField getDataStoreField() {
		return dataStoreField;
	}

	public void setDataStoreField(DataStoreField dataStoreField) {
		this.dataStoreField = dataStoreField;
	}

	public Integer getDataStoreFieldId() {
		return dataStoreFieldId;
	}

	public void setDataStoreFieldId(Integer dataStoreFieldId) {
		this.dataStoreFieldId = dataStoreFieldId;
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
