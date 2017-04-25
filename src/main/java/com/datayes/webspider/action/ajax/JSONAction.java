package com.datayes.webspider.action.ajax;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.datayes.webspider.action.BaseAction;
import com.datayes.webspider.domain.machine.Machine;
import com.datayes.webspider.domain.machine.MachineRole;
import com.datayes.webspider.domain.website.Category;
import com.datayes.webspider.domain.website.WebSite;
import com.datayes.webspider.domain.website.WebSiteConfig;
import com.datayes.webspider.domain.websiteOps.DataStoreField;
import com.datayes.webspider.domain.websiteOps.DataStoreType;
import com.datayes.webspider.domain.websiteOps.WebSiteOperation;
import com.datayes.webspider.domain.words.IndexKeywords;
import com.datayes.webspider.service.machine.IMachineRoleService;
import com.datayes.webspider.service.machine.IMachineService;
import com.datayes.webspider.service.website.ICategoryService;
import com.datayes.webspider.service.website.IWebSiteConfigService;
import com.datayes.webspider.service.website.IWebSiteService;
import com.datayes.webspider.service.websiteOps.IDataStoreFieldService;
import com.datayes.webspider.service.websiteOps.IDataStoreTypeService;
import com.datayes.webspider.service.websiteOps.IWebSiteOpsService;
import com.datayes.webspider.service.words.IKeywordService;

@ParentPackage("webspider")
@Namespace("/")
@Results({
	@Result(type = "json")
})
public class JSONAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	private Integer webSiteOpsId;
	private Integer dataStoreTypeId;

	private Map<Object, Object> jsonResult = new LinkedHashMap<Object, Object>();
	
	@Resource
	private ICategoryService categoryService;
	
	@Resource
	private IWebSiteService webSiteService;
	
	@Resource
	private IWebSiteOpsService webSiteOpsService;
	
	@Resource
	private IMachineService machineService;
	
	@Resource
	private IMachineRoleService machineRoleService;
	
	@Resource
	private IWebSiteConfigService webSiteConfigService;
	
	@Resource
	private IDataStoreTypeService dataStoreTypeService;
	
	@Resource
	private IDataStoreFieldService dataStoreFieldService;
	
	@Resource
	private IKeywordService keywordService;
	
	@Action("findAllCategory")
	public String findAllCategory(){
		List<Category> list = categoryService.getAllCategory();
		if (list != null && !list.isEmpty()) {
			for (Category category : list) {
				jsonResult.put(category.getCategoryId(), category.getCategoryName());
			}
		}
		return SUCCESS;
	}
	
	@Action("findAllWebSite")
	public String findAllWebSite(){
		List<WebSite> list = webSiteService.getAllWebSite();
		if (list != null && !list.isEmpty()) {
			for (WebSite webSite : list) {
				jsonResult.put(webSite.getWebSiteId(), webSite.getWebSiteName());
			}
		}
		return SUCCESS;
	}
	
	@Action("findAllWebSiteOps")
	public String findAllWebSiteOps(){
		long t1 = System.currentTimeMillis();
		List<WebSiteOperation> list = webSiteOpsService.getAllWebSiteOps();
		long t2 = System.currentTimeMillis();
		logger.debug("查询网站操作类型耗时：" + (t2 - t1));
		if(list!=null && !list.isEmpty()){
			for (WebSiteOperation webSiteOps : list) {
				jsonResult.put(webSiteOps.getWebSiteOpsId(), webSiteOps.getWebSiteOpsName());
			}
		}
		return SUCCESS;
	}
	
	@Action("findAllMachineJSON")
	public String findAllMachineJSON(){
		List<Machine> list = machineService.findAllMachine();
		if (list != null && !list.isEmpty()) {
			for (Machine machine : list) {
				jsonResult.put(machine.getHost(), machine);
			}
		}
		return SUCCESS;
	}
	
	@Action("findAllSlave")
	public String findAllSlave(){
		List<Machine> list = machineService.findAllMachine();
		if (list != null && !list.isEmpty()) {
			for (Machine machine : list) {
				if(machine.getMachineType() == 1){
					
				} else {
					jsonResult.put(machine.getHost(), machine.getHost());
				}
			}
		}
		return SUCCESS;
	}
	
	
	@Action("findAllMachine")
	public String findAllMachine() {
		List<Machine> list = machineService.findAllMachine();
		if (list != null && !list.isEmpty()) {
			for (Machine machine : list) {
				jsonResult.put(machine.getHost(), machine.getHost());
			}
		}
		return SUCCESS;
	}
	
	@Action("findAllMachineRole")
	public String findAllMachineRole(){
		List<MachineRole> list = machineRoleService.getAllMachineRole();
		if(list!=null && !list.isEmpty()){
			for (MachineRole machineRole : list) {
				jsonResult.put(machineRole.getMachineRoleId(), machineRole.getMachineRoleName());
			}
		}
		return SUCCESS;
	}
	
	@Action("findAllWebSiteConfig")
	public String findAllWebSiteConfig(){
		List<WebSiteConfig> list = webSiteConfigService.getAllWebSiteConfig();
		if (list != null && !list.isEmpty()) {
			for (WebSiteConfig webSiteConfig : list) {
				jsonResult.put(webSiteConfig.getWebSiteConfigId(), webSiteConfig.getWebSiteConfigName());
			}
		}
		return SUCCESS;
	}
	
	@Action("findAllDataStoreType")
	public String findAllDataStoreType(){
		List<DataStoreType> list = dataStoreTypeService.getAllDataStoreType();
		if (list != null && !list.isEmpty()) {
			for (DataStoreType dataStoreType : list) {
				jsonResult.put(dataStoreType.getDataStoreTypeId(), dataStoreType.getDataStoreTypeCnName());
			}
		}
		return SUCCESS;
	}
	
	@Action("findAllDataStoreField")
	public String findAllDataStoreField(){
		List<DataStoreField> list = dataStoreFieldService.findByDataStoreTypeId(dataStoreTypeId);
		for (DataStoreField dataStoreField : list) {
			Integer crawlNodeId = dataStoreField.getDataStoreFieldId();
			String fieldCnName = dataStoreField.getFieldCnName();
			jsonResult.put(crawlNodeId, fieldCnName);
		}
		return SUCCESS;
	}
	
	@Action("findAllFieldName")
	public String findAllFieldName(){
		WebSiteOperation webSiteOps = webSiteOpsService.getWebSiteOpsById(webSiteOpsId);
		Integer dataStoreTypeId = webSiteOps.getDataStoreType().getDataStoreTypeId();
		List<DataStoreField> list = dataStoreFieldService.findByDataStoreTypeId(dataStoreTypeId);
		
		for (DataStoreField dataStoreField : list) {
			String fieldCnName = dataStoreField.getFieldCnName();
			String fieldEnName = dataStoreField.getFieldEnName();
			jsonResult.put(fieldCnName, fieldEnName);
		}
		
		return SUCCESS;
	}

	@Action("findAllKeyword")
	public String findAllKeyword(){
		List<IndexKeywords> list = keywordService.getAllWords();
		for(IndexKeywords keyword: list){
//			String
			jsonResult.put(keyword.getWord(), keyword.getWord());
		}
		return SUCCESS;
	}
	
	/*
	 * Getters and Setters
	 */
	public Map<Object, Object> getJsonResult() {
		return jsonResult;
	}
	public void setJsonResult(Map<Object, Object> jsonResult) {
		this.jsonResult = jsonResult;
	}
	public Integer getWebSiteOpsId() {
		return webSiteOpsId;
	}
	public void setWebSiteOpsId(Integer webSiteOpsId) {
		this.webSiteOpsId = webSiteOpsId;
	}
	public Integer getDataStoreTypeId() {
		return dataStoreTypeId;
	}
	public void setDataStoreTypeId(Integer dataStoreTypeId) {
		this.dataStoreTypeId = dataStoreTypeId;
	}
	
}
