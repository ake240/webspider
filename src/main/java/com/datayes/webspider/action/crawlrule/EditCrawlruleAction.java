package com.datayes.webspider.action.crawlrule;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.alibaba.fastjson.JSONObject;
import com.datayes.webspider.action.BaseAction;
import com.datayes.webspider.constant.CommonConstants;
import com.datayes.webspider.domain.websiteOps.DataStoreField;
import com.datayes.webspider.service.crawlrule.ICrawlruleService;
import com.datayes.webspider.service.websiteOps.IDataStoreFieldService;
import com.kepler.spider.integration.bo.CrawlRuleBO;
import com.kepler.spider.integration.bo.CrawlRuleNodeBO;

@ParentPackage("webspider")
@Namespace("/")
@Results({ 
	@Result(name = "success", location = "/WEB-INF/jsp/crawlrule/editCrawlrule.jsp"), 
	@Result(name = "redirect2success", location = "/WEB-INF/jsp/success.jsp"),
	@Result(name = "json", type = "json", params = {"includeProperties","jsonResult"}), 
})
public class EditCrawlruleAction extends BaseAction {
	private static final long serialVersionUID = 1L;
//	private Crawlrule crawlrule;
	private CrawlRuleBO crawlrule;
	private List<RemoveCrawlExprValue> removeCrawlExprValues;
	private String crawlruleId;
	private Integer status;
	private int type;
	private String jsonResult;
	
	@Resource
	private ICrawlruleService crawlruleService;
	
	@Resource
	private IDataStoreFieldService dataStoreFieldService;
	
	@Action("crawlrule")
	public String crawlrule(){
		if (type != 0) {
			this.crawlrule = crawlruleService.getById(crawlruleId);
			//把两边的字段对正整齐
	
			int nodeType = this.crawlrule.getNodeType();
		
			if(nodeType == 2){
				//列表类型
				List<CrawlRuleNodeBO> childNodeList = this.crawlrule.getCrawlRuleNodeList().get(0).getChildCrawlRuleNodeList();
				int childLen = childNodeList.size();
				if(childLen > 0){
					for(int i = 0;i < childLen;i++){
						CrawlRuleNodeBO childNode = childNodeList.get(i);
						Integer id = childNode.getCrawlNodeId();
						if(id != null){
							DataStoreField dataStoreField = dataStoreFieldService.findByDataStoreFieldId(id);
							childNode.setCrawlNodeCnName(dataStoreField.getFieldCnName());
							childNode.setCrawlNodeEnName(dataStoreField.getFieldEnName());
						}
					}
				}
			}
			if(nodeType == 1){
				//节点类型
				int len = this.crawlrule.getCrawlRuleNodeList().size();
				for(int i = 0;i < len;i++){
					//节点id
					Integer id = this.crawlrule.getCrawlRuleNodeList().get(i).getCrawlNodeId();
					if(id != null){
						DataStoreField dataStoreField = dataStoreFieldService.findByDataStoreFieldId(id);
						//将上面查到的英文名称赋值给mongo中的CrawlNodeEnName和CrawlNodeCnName
						this.crawlrule.getCrawlRuleNodeList().get(i).setCrawlNodeEnName(dataStoreField.getFieldEnName());
						this.crawlrule.getCrawlRuleNodeList().get(i).setCrawlNodeCnName(dataStoreField.getFieldCnName());
					}
				}
			}
			crawlruleService.save(crawlrule);
			
		}
		return SUCCESS;
	}

	//添加新规则：在新添加的文档中，添加dataStoreFieldid字段
	@Action("newCrawlrule")
	public String newCrawlrule(){
		String usename = (String) session.get(CommonConstants.SESSION_OBJECT_KEY);
		/**
		 * 获取list中每个中文名对应的ID，并存入相应的文档中，
		 */
		int nodeType = this.crawlrule.getNodeType();
		Integer dataStoreType = this.crawlrule.getDataStoreTypeId() == null?0:this.crawlrule.getDataStoreTypeId();
		this.crawlrule = deal(nodeType, dataStoreType, this.crawlrule);	
		crawlrule.setStatus(0);
		crawlrule.setOperator(usename);
		crawlrule.setInsertTime(new Date());
		crawlrule.setUpdateTime(new Date());
		HashMap<String, Integer> removeExprValueMap = null;
		if (removeCrawlExprValues != null) {
			removeExprValueMap = new HashMap<String, Integer>();
			for (RemoveCrawlExprValue removeCrawlExprValue : removeCrawlExprValues) {
				removeExprValueMap.put(removeCrawlExprValue.getCrawlExprValue(), removeCrawlExprValue.getCrawlExprType());
			}
		}
		crawlrule.getCrawlRuleNodeList().get(0).setRemoveCrawlExprValues(removeExprValueMap);
		crawlrule.setId(null);
		crawlruleService.save(crawlrule);
		this.crawlruleId = crawlrule.getId();
		return "redirect2success";
	}
	
	
	
	@Action("editCrawlrule")
	public String editCrawlrule(){

		String usename = (String) session.get(CommonConstants.SESSION_OBJECT_KEY);

		/**
		 * 获取list中每个中文名对应的ID，并存入相应的文档中，
		 */
		int nodeType = this.crawlrule.getNodeType();
		Integer dataStoreType = this.crawlrule.getDataStoreTypeId() == null?0:this.crawlrule.getDataStoreTypeId();
		crawlrule = deal(nodeType,dataStoreType,this.crawlrule);
		crawlrule.setOperator(usename);
		crawlrule.setUpdateTime(new Date());
		HashMap<String, Integer> removeExprValueMap = null;
		if (removeCrawlExprValues != null) {
			removeExprValueMap = new HashMap<String, Integer>();
			for (RemoveCrawlExprValue removeCrawlExprValue : removeCrawlExprValues) {
				removeExprValueMap.put(removeCrawlExprValue.getCrawlExprValue(), removeCrawlExprValue.getCrawlExprType());
			}
		}
		crawlrule.getCrawlRuleNodeList().get(0).setRemoveCrawlExprValues(removeExprValueMap);
		crawlruleService.save(crawlrule);
		this.crawlruleId = crawlrule.getId();
		return "redirect2success";
	}
	
	private CrawlRuleBO deal(int nodeType, Integer dataStoreType,
			CrawlRuleBO crb) {
		String cnName = null;
		String enName = null;
		String extName = null;
		if(nodeType == 2){
			//列表类型,直接寻找其childlist
			List<CrawlRuleNodeBO> childNodeList = crb.getCrawlRuleNodeList().get(0).getChildCrawlRuleNodeList();
			int childLen = childNodeList.size();
			if(childLen > 0){
				for(int i = 0;i < childLen;i++){
					CrawlRuleNodeBO childNode = childNodeList.get(i);
					cnName = childNode.getCrawlNodeCnName();
					DataStoreField dataStore = getFieldId(cnName, dataStoreType);
					int id = dataStore.getDataStoreFieldId();
					if(id != 0){
						DataStoreField dataStoreField = dataStoreFieldService.findByDataStoreFieldId(id);
						childNode.setCrawlNodeEnName(dataStoreField.getFieldEnName());
						childNode.setCrawlNodeId(dataStoreField.getDataStoreFieldId());						
					}
				}
			}
			//处理同级的extid
			extName = crb.getCrawlRuleNodeList().get(0).getExtCrawlNodeName();
			DataStoreField dataStore = getFieldId(extName, dataStoreType);
			int extId = dataStore.getDataStoreFieldId();
			crb.getCrawlRuleNodeList().get(0).setExtCrawlNodeId(Integer.toString(extId));
			crb.getCrawlRuleNodeList().get(0).setExtCrawlNodeName(dataStore.getFieldEnName());
		}
		if(nodeType == 1){
			//节点类型 ：直接处理各个node
			int len = crb.getCrawlRuleNodeList().size();
			for(int i = 0;i < len;i++){
				//节点id
				cnName = crb.getCrawlRuleNodeList().get(i).getCrawlNodeCnName();
				DataStoreField dataStore = getFieldId(cnName, dataStoreType);
				int id = dataStore.getDataStoreFieldId();
				if(id != 0){
					DataStoreField dataStoreField = dataStoreFieldService.findByDataStoreFieldId(id);
					crb.getCrawlRuleNodeList().get(i).setCrawlNodeEnName(dataStoreField.getFieldEnName());
					crb.getCrawlRuleNodeList().get(i).setCrawlNodeId(dataStoreField.getDataStoreFieldId());					
				}
				
				
			}
		}
		return crb;
	}

	@Action("changeStatus")
	public String changeStatus(){
		boolean succeed = true;
		String msg = "";
		CrawlRuleBO crawlrule = crawlruleService.getById(crawlruleId);
		if (crawlrule != null) {
			if (status != null) {
				crawlrule.setStatus(status);
				crawlruleService.save(crawlrule);
			}else{
				succeed = false;
				msg = "状态未知";
			}
		}else{
			succeed = false;
			msg = "未找到对应的抽取规则, id:" + crawlruleId;
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("succeed", succeed);
		jsonObject.put("msg", msg);
		this.jsonResult = jsonObject.toJSONString();
		return "json";
	}

	public CrawlRuleBO getCrawlrule() {
		return crawlrule;
	}

	public void setCrawlrule(CrawlRuleBO crawlrule) {
		this.crawlrule = crawlrule;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getCrawlruleId() {
		return crawlruleId;
	}

	public void setCrawlruleId(String crawlruleId) {
		this.crawlruleId = crawlruleId;
	}

	public List<RemoveCrawlExprValue> getRemoveCrawlExprValues() {
		return removeCrawlExprValues;
	}

	public void setRemoveCrawlExprValues(List<RemoveCrawlExprValue> removeCrawlExprValues) {
		this.removeCrawlExprValues = removeCrawlExprValues;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}
	
	/**
	 * 获得fieldID
	 * @param cnName
	 * @param id
	 * @return
	 */
	public DataStoreField getFieldId(String cnName,int id){
		List<DataStoreField> list = dataStoreFieldService.getByFieldCnName(cnName);
		if(list != null){
			if(list.size() == 1){
				return list.get(0);
			}
			if(list.size() > 1 && id != 0){
				for(int i = 0;i < list.size() ;  i++){
					int dataStoreTypeId = list.get(i).getDataStoreType().getDataStoreTypeId();
					if(id == dataStoreTypeId){
						return list.get(i);
					}
				}				
			}
		}
		return null;
	}
	
}
