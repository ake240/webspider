package com.datayes.webspider.action.websiteOps;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.alibaba.fastjson.JSONObject;
import com.datayes.webspider.action.BaseAction;
import com.datayes.webspider.domain.machine.MachineRole;
import com.datayes.webspider.domain.website.WebSite;
import com.datayes.webspider.domain.websiteOps.DataStoreType;
import com.datayes.webspider.domain.websiteOps.WebSiteOperation;
import com.datayes.webspider.domain.websiteOps.WebSiteOperationParam;
import com.datayes.webspider.domain.websiteOps.WebSiteOpsRel;
import com.datayes.webspider.service.machine.IMachineRoleService;
import com.datayes.webspider.service.website.IWebSiteService;
import com.datayes.webspider.service.websiteOps.IDataStoreTypeService;
import com.datayes.webspider.service.websiteOps.IWebSiteOpsParamService;
import com.datayes.webspider.service.websiteOps.IWebSiteOpsRelService;
import com.datayes.webspider.service.websiteOps.IWebSiteOpsService;

@ParentPackage("webspider")
@Namespace("/")
@Results({ 
	@Result(name = "page", location = "/WEB-INF/jsp/websiteOps/editWebSiteOps.jsp"),
	@Result(name = "redirect2success", location = "/WEB-INF/jsp/success.jsp"),
	@Result(name = "json", type="json", params={"includeProperties","jsonResult"}), 
})
public class EditWebSiteOpsAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private WebSiteOperation webSiteOps;
	private Integer webSiteOpsId;
	private Integer webSiteId;
	private Integer dataStoreTypeId;
	private Integer machineRoleId;
	private int type;
	private String jsonResult;
	private String message;
	private String saveToDB;
	private String saveToMQ;
	private String saveToFile;
	private Integer[] parentWebsiteOpsIds;
	private String websiteOpsIds="";
	
	
	@Resource
	private IWebSiteOpsRelService webSiteOpsRelService;
	
	@Resource
	private IWebSiteOpsService webSiteOpsService;
	
	@Resource
	private IWebSiteOpsParamService webSiteOpsParamService;
	
	@Resource
	private IWebSiteService webSiteService;
	
	@Resource
	private IMachineRoleService machineRoleService;
	
	@Resource
	private IDataStoreTypeService dataStoreTypeService;

	@Action("webSiteOps")
	public String webSiteOps(){
		if (type != 0) {
			this.webSiteOps = webSiteOpsService.getWebSiteOpsById(webSiteOpsId);
			this.webSiteId = webSiteOps.getWebSite().getWebSiteId();
			this.dataStoreTypeId = webSiteOps.getDataStoreType().getDataStoreTypeId();
			this.machineRoleId = webSiteOps.getMachineRole().getMachineRoleId();
			List<WebSiteOpsRel> relList = webSiteOpsRelService.findWebSiteOpsList(webSiteOpsId);
			for(WebSiteOpsRel rel : relList){
				websiteOpsIds += rel.getKey().getParentWebsiteOperationId() + "," ;
			}
			if (!StringUtils.isEmpty(websiteOpsIds)) {
				websiteOpsIds = websiteOpsIds.substring(0, websiteOpsIds.length() - 1);
			}
		}
		return "page";
	}
	
	@Action("newWebSiteOps")
	public String newWebSiteOps(){
		boolean succeed = true;
		
		if (webSiteOps != null && webSiteId != null && machineRoleId != null) {
			WebSite webSite = webSiteService.getWebSiteById(webSiteId);
			MachineRole machineRole = machineRoleService.getMachineRoleById(machineRoleId);
			DataStoreType dataStoreType = dataStoreTypeService.findByDataStoreTypeId(dataStoreTypeId);
			if (webSite != null && machineRole != null && dataStoreType != null) {
				webSiteOps.setWebSite(webSite);
				webSiteOps.setMachineRole(machineRole);
				webSiteOps.setDataStoreType(dataStoreType);
				if (webSiteOps.getOperationClass() == null) {
					webSiteOps.setOperationClass("");
				}
				
				if (webSiteOps.getPagination() == 0) {
					webSiteOps.setStartPage("");
					webSiteOps.setEndPage("");
					webSiteOps.setStep(1);
				}else{
					if (webSiteOps.getStep() == null) {
						webSiteOps.setStep(1);
					}
				}
				
				if (webSiteOps.getExtWebSiteOpsId() == null) {
					webSiteOps.setExtWebSiteOpsId(0);
				}
				
				webSiteOps.setSaveToDB("true".equals(saveToDB) ? 1 : 0);
				webSiteOps.setSaveToMQ("true".equals(saveToMQ) ? 1 : 0);
				webSiteOps.setSaveToFile("true".equals(saveToFile) ? 1 : 0);
				
				webSiteOps.setUpdateTime(new Date());
				Integer oldId = webSiteOps.getWebSiteOpsId();
				webSiteOps.setWebSiteOpsId(null);
				
				webSiteOpsService.saveOrUpdate(webSiteOps);
				Integer newId = webSiteOps.getWebSiteOpsId();
				if(parentWebsiteOpsIds == null || parentWebsiteOpsIds.length == 0){}
				else {
					List<WebSiteOpsRel> relList = new LinkedList<WebSiteOpsRel>();
					for(Integer parentInteger : parentWebsiteOpsIds ){
						relList.add(new WebSiteOpsRel(newId, parentInteger));
					}
					webSiteOpsRelService.saveOrUpdate(relList);
				}
				
				copyWebSiteOpsParam(oldId, newId);
			}else{
				succeed = false;
				message = "找不到对应的所属网站,机器角色,存储类型, webSiteId:" + webSiteId + ", machineRoleId:" + machineRoleId + ", dataStoreTypeId:" + dataStoreTypeId;
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
	
	private void copyWebSiteOpsParam(Integer oldId, Integer newId){
		WebSiteOperation webSiteOps = webSiteOpsService.getWebSiteOpsById(newId);
		if (webSiteOps != null) {
			List<WebSiteOperationParam> list = webSiteOpsParamService.getByWebSiteOpsId(oldId);
			for (WebSiteOperationParam webSiteOpsParam : list) {
				WebSiteOperationParam param = (WebSiteOperationParam) webSiteOpsParam.clone();
				param.setWebSiteOps(webSiteOps);
				param.setInsertTime(new Date());
				param.setUpdateTime(new Date());
				webSiteOpsParamService.saveOrUpdate(param);
			}
		}
	}
	
	@Action("editWebSiteOps")
	public String editWebSiteOps(){
		boolean succeed = true;
		this.type = 1;
		if (webSiteOps != null && webSiteOps.getWebSiteOpsId() != null && webSiteId != null && machineRoleId != null && dataStoreTypeId != null) {
			WebSiteOperation webSiteOpsInDB = webSiteOpsService.getWebSiteOpsById(webSiteOps.getWebSiteOpsId());
			if (webSiteOpsInDB != null) {
				webSiteOpsInDB.setWebSiteOpsName(webSiteOps.getWebSiteOpsName());
				webSiteOpsInDB.setPageEncode(webSiteOps.getPageEncode());
				webSiteOpsInDB.setRequestMethod(webSiteOps.getRequestMethod());
				
				WebSite webSite = webSiteService.getWebSiteById(webSiteId);
				MachineRole machineRole = machineRoleService.getMachineRoleById(machineRoleId);
				DataStoreType dataStoreType = dataStoreTypeService.findByDataStoreTypeId(dataStoreTypeId);
				if (webSite != null && machineRole != null && dataStoreType != null) {
					webSiteOpsInDB.setWebSite(webSite);
					webSiteOpsInDB.setMachineRole(machineRole);
					webSiteOpsInDB.setDataStoreType(dataStoreType);
					
					if (webSiteOps.getOperationClass() != null) {
						webSiteOpsInDB.setOperationClass(webSiteOps.getOperationClass());
					} else {
						webSiteOpsInDB.setOperationClass("");
					}
					
					if(webSiteOps.getExtraParam() != null){
						webSiteOpsInDB.setExtraParam(webSiteOps.getExtraParam());
					} else {
						webSiteOpsInDB.setExtraParam("");
					}
					
					if (webSiteOps.getPagination() == 0) {
						webSiteOpsInDB.setStartPage("");
						webSiteOpsInDB.setEndPage("");
						webSiteOpsInDB.setStep(1);
					} else {
						webSiteOpsInDB.setStartPage(StringUtils.isEmpty(webSiteOps.getStartPage()) ? "" : webSiteOps.getStartPage());
						webSiteOpsInDB.setEndPage(StringUtils.isEmpty(webSiteOps.getEndPage()) ? "" : webSiteOps.getEndPage());
						webSiteOpsInDB.setStep(webSiteOps.getStep() == null ? 1 : webSiteOps.getStep());
					}
					
					if(webSiteOps.getPWebSiteOpsId() == 0){
						
					} else {
						webSiteOpsInDB.setPWebSiteOpsId(webSiteOps.getPWebSiteOpsId());
					}
					
					if (webSiteOps.getExtWebSiteOpsId() == null) {
						webSiteOpsInDB.setExtWebSiteOpsId(0);
					} else {
						webSiteOpsInDB.setExtWebSiteOpsId(webSiteOps.getExtWebSiteOpsId());
					}
					
					webSiteOpsInDB.setSaveToDB("true".equals(saveToDB) ? 1 : 0);
					webSiteOpsInDB.setSaveToMQ("true".equals(saveToMQ) ? 1 : 0);
					webSiteOpsInDB.setSaveToFile("true".equals(saveToFile) ? 1 : 0);
					webSiteOpsInDB.setConsumers(webSiteOps.getConsumers());
					
					webSiteOpsInDB.setPagination(webSiteOps.getPagination());
					webSiteOpsInDB.setUpdateTime(new Date());
					
					webSiteOpsInDB.setIntermediateResult(webSiteOps.getIntermediateResult());
					webSiteOpsInDB.setReqAction(webSiteOps.getReqAction());
					webSiteOpsInDB.setDynamicReq(webSiteOps.getDynamicReq());
					if(webSiteOps.getFileQueueMaxSize() != 0){
						webSiteOpsInDB.setFileQueueMaxSize(webSiteOps.getFileQueueMaxSize());
					}
					if(webSiteOps.getFileQueueFlushInterval()!=0){
						webSiteOpsInDB.setFileQueueFlushInterval(webSiteOps.getFileQueueFlushInterval());
					}
					webSiteOpsInDB.setNeedRecycle(webSiteOps.getNeedRecycle());
					webSiteOpsInDB.setUrlFilterRegex(webSiteOps.getUrlFilterRegex());
					webSiteOpsInDB.setNeedUpdate(webSiteOps.getNeedUpdate());
					
					webSiteOpsRelService.removeRel(webSiteOps.getWebSiteOpsId());
					if(parentWebsiteOpsIds == null || parentWebsiteOpsIds.length == 0){}
					else {
						List<WebSiteOpsRel> relList = new LinkedList<WebSiteOpsRel>();
						for(Integer parentInteger : parentWebsiteOpsIds ){
							relList.add(new WebSiteOpsRel(webSiteOps.getWebSiteOpsId(), parentInteger));
						}
						webSiteOpsRelService.saveOrUpdate(relList);
					}
					
					webSiteOpsService.saveOrUpdate(webSiteOpsInDB);
					this.webSiteOpsId = webSiteOpsInDB.getWebSiteOpsId();
				}else{
					succeed = false;
					message = "找不到对应的所属网站或机器角色, webSiteId:" + webSiteId + ", machineRoleId:" + machineRoleId;
				}
			} else {
				succeed = false;
				message = "找不到对应的网站操作类型, webSiteOpsId: " + webSiteOps.getWebSiteOpsId();
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
	
	@Action("deleteWebSiteOps")
	public String deleteWebSiteOps(){
		boolean succeed = true;
		String message = "";
		
		WebSiteOperation webSiteOpsInDB = webSiteOpsService.getWebSiteOpsById(webSiteOpsId);
		if (webSiteOpsInDB != null) {
			webSiteOpsService.deleteWebSiteOps(webSiteOpsInDB);
		}else{
			succeed = false;
			message = "找不到对应的操作类型,webSiteOpsId:" + webSiteOpsId;
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("succeed", succeed);
		jsonObject.put("message", message);
		this.jsonResult = jsonObject.toJSONString();
		return "json";
	}

	
	/*
	 * Getters and Setters
	 */
	
	public Integer getWebSiteId() {
		return webSiteId;
	}
	public void setWebSiteId(Integer webSiteId) {
		this.webSiteId = webSiteId;
	}
	public Integer getMachineRoleId() {
		return machineRoleId;
	}
	public void setMachineRoleId(Integer machineRoleId) {
		this.machineRoleId = machineRoleId;
	}
	public Integer getDataStoreTypeId() {
		return dataStoreTypeId;
	}
	public void setDataStoreTypeId(Integer dataStoreTypeId) {
		this.dataStoreTypeId = dataStoreTypeId;
	}
	public WebSiteOperation getWebSiteOps() {
		return webSiteOps;
	}
	public void setWebSiteOps(WebSiteOperation webSiteOps) {
		this.webSiteOps = webSiteOps;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Integer getWebSiteOpsId() {
		return webSiteOpsId;
	}
	public void setWebSiteOpsId(Integer webSiteOpsId) {
		this.webSiteOpsId = webSiteOpsId;
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
	public String getSaveToDB() {
		return saveToDB;
	}
	public void setSaveToDB(String saveToDB) {
		this.saveToDB = saveToDB;
	}
	public String getSaveToMQ() {
		return saveToMQ;
	}
	public void setSaveToMQ(String saveToMQ) {
		this.saveToMQ = saveToMQ;
	}
	public String getSaveToFile() {
		return saveToFile;
	}
	public void setSaveToFile(String saveToFile) {
		this.saveToFile = saveToFile;
	}
	public Integer[] getParentWebsiteOpsIds() {
		return parentWebsiteOpsIds;
	}
	public void setParentWebsiteOpsIds(Integer[] parentWebsiteOpsIds) {
		this.parentWebsiteOpsIds = parentWebsiteOpsIds;
	}
	public String getWebsiteOpsIds() {
		return websiteOpsIds;
	}
	public void setWebsiteOpsIds(String websiteOpsIds) {
		this.websiteOpsIds = websiteOpsIds;
	}
}
