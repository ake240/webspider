package com.datayes.webspider.domain.websiteOps;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.datayes.webspider.domain.machine.MachineRole;
import com.datayes.webspider.domain.website.WebSite;

@Entity
@Table(name = "website_operation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)  
@NamedQueries({
	@NamedQuery(name="getAllWebSiteOps", query="from WebSiteOperation w order by w.insertTime desc"),
	@NamedQuery(name="getWebSiteOpsById", query="from WebSiteOperation w where w.webSiteOpsId = ?"),
})
public class WebSiteOperation {
	@Id
	@GeneratedValue
	@Column(name = "websiteoperationid", nullable = false, unique = true)
	private Integer webSiteOpsId;
	
	/*@ManyToOne
	@JoinColumn(name = "extwebsiteoperationId", referencedColumnName = "websiteoperationid")
	private WebSiteOperation webSiteOps;*/
	
	@Column(name = "pwebsiteoperationId")
	private int pWebSiteOpsId;
	
	@Column(name = "extwebsiteoperationId")
	private Integer extWebSiteOpsId;
	
	@Column(name = "websiteoperationname")
	private String webSiteOpsName;
	
	@Column(name = "operationclass")
	private String operationClass;
	
	@Column(name = "extroparam")
	private String extraParam;

	@Column(name = "consumers")
	private Integer consumers;
	
	@Column(name = "savetodb")
	private Integer saveToDB;
	
	@Column(name = "savetomq")
	private Integer saveToMQ;
	
	@Column(name = "savetofile")
	private Integer saveToFile;

	@ManyToOne
	@JoinColumn(name = "websiteid", referencedColumnName = "websiteid")
	private WebSite webSite;
	
	@OneToMany(mappedBy="webSiteOps")
	private Set<WebSiteOperationParam> webSiteOpsParams;
	
	@ManyToOne
	@JoinColumn(name = "datastoretypeid", referencedColumnName = "datastoretypeid")
	private DataStoreType dataStoreType;
	
	@ManyToOne
	@JoinColumn(name = "machineroleid", referencedColumnName = "machineroleid")
	private MachineRole machineRole;
	
	@Column(name = "pageencode")
	private Integer pageEncode;
	
	@Column(name = "reqmethod")
	private Integer requestMethod;
	
	@Column(name = "pagination")
	private Integer pagination;
	
	@Column(name = "startpage")
	private String startPage;
	
	@Column(name = "endpage")
	private String endPage;
	
	@Column(name = "step")
	private Integer step;

	@Column(name = "inserttime")
	private Date insertTime = new Date();
	
	@Column(name = "updatetime")
	private Date updateTime;

	@Column(name = "intermediateresult")
	private Integer intermediateResult = 1; // 最终结果： 是 否
	
	@Column(name = "reqaction")
	private String reqAction; // 表单提交URL
	
	@Column(name= "dynamicReq")
	private Integer dynamicReq = 1; // 动态请求 是 否
	
	@Column(name = "filequeuemaxsize")
	private int fileQueueMaxSize = 100; // 输出文件缓存队列最大数量 文本框，输入数字
	
	@Column(name = "filequeueflushinterval")
	private int fileQueueFlushInterval = 30; // textbox
	
	@Column(name = "needrecycle")
	private Integer needRecycle = 1; // 重复抓取 是 否
	
	@Column(name = "urlfilterregex")
	private String urlFilterRegex;
	
	@Column(name = "needupdate")
	private Integer needUpdate = 1; // 数据已存在时是否更新
	
	/*
	 * Getters and Setters
	 */
	public String getUrlFilterRegex() {
		return urlFilterRegex;
	}
	public void setUrlFilterRegex(String urlFilterRegex) {
		this.urlFilterRegex = urlFilterRegex;
	}
	public Integer getWebSiteOpsId() {
		return webSiteOpsId;
	}
	public void setWebSiteOpsId(Integer webSiteOpsId) {
		this.webSiteOpsId = webSiteOpsId;
	}
	/*public WebSiteOperation getWebSiteOps() {
		return webSiteOps;
	}
	public void setWebSiteOps(WebSiteOperation webSiteOps) {
		this.webSiteOps = webSiteOps;
	}*/
	public String getWebSiteOpsName() {
		return webSiteOpsName;
	}
	public Integer getExtWebSiteOpsId() {
		return extWebSiteOpsId;
	}
	public void setExtWebSiteOpsId(Integer extWebSiteOpsId) {
		this.extWebSiteOpsId = extWebSiteOpsId;
	}
	public void setWebSiteOpsName(String webSiteOpsName) {
		this.webSiteOpsName = webSiteOpsName;
	}
	public WebSite getWebSite() {
		return webSite;
	}
	public void setWebSite(WebSite webSite) {
		this.webSite = webSite;
	}
	public MachineRole getMachineRole() {
		return machineRole;
	}
	public void setMachineRole(MachineRole machineRole) {
		this.machineRole = machineRole;
	}
	public Integer getPageEncode() {
		return pageEncode;
	}
	public void setPageEncode(Integer pageEncode) {
		this.pageEncode = pageEncode;
	}
	public Integer getRequestMethod() {
		return requestMethod;
	}
	public void setRequestMethod(Integer requestMethod) {
		this.requestMethod = requestMethod;
	}
	public Integer getPagination() {
		return pagination;
	}
	public void setPagination(Integer pagination) {
		this.pagination = pagination;
	}
	public String getStartPage() {
		return startPage;
	}
	public void setStartPage(String startPage) {
		this.startPage = startPage;
	}
	public String getEndPage() {
		return endPage;
	}
	public void setEndPage(String endPage) {
		this.endPage = endPage;
	}
	public Integer getStep() {
		return step;
	}
	public void setStep(Integer step) {
		this.step = step;
	}
	public Date getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Set<WebSiteOperationParam> getWebSiteOpsParams() {
		return webSiteOpsParams;
	}
	public void setWebSiteOpsParams(Set<WebSiteOperationParam> webSiteOpsParams) {
		this.webSiteOpsParams = webSiteOpsParams;
	}
	public DataStoreType getDataStoreType() {
		return dataStoreType;
	}
	public void setDataStoreType(DataStoreType dataStoreType) {
		this.dataStoreType = dataStoreType;
	}
	public String getOperationClass() {
		return operationClass;
	}
	public void setOperationClass(String operationClass) {
		this.operationClass = operationClass;
	}
	public String getExtraParam() {
		return extraParam;
	}
	public void setExtraParam(String extraParam) {
		this.extraParam = extraParam;
	}
	public Integer getConsumers() {
		return consumers;
	}
	public void setConsumers(Integer consumers) {
		this.consumers = consumers;
	}
	public Integer getSaveToDB() {
		return saveToDB;
	}
	public void setSaveToDB(Integer saveToDB) {
		this.saveToDB = saveToDB;
	}
	public Integer getSaveToMQ() {
		return saveToMQ;
	}
	public void setSaveToMQ(Integer saveToMQ) {
		this.saveToMQ = saveToMQ;
	}
	public Integer getSaveToFile() {
		return saveToFile;
	}
	public void setSaveToFile(Integer saveToFile) {
		this.saveToFile = saveToFile;
	}
	public int getPWebSiteOpsId() {
		return pWebSiteOpsId;
	}
	public void setPWebSiteOpsId(int pWebSiteOpsId) {
		this.pWebSiteOpsId = pWebSiteOpsId;
	}
	
	
	public Integer getIntermediateResult() {
		return intermediateResult;
	}
	public void setIntermediateResult(Integer intermediateResult) {
		this.intermediateResult = intermediateResult;
	}
	public String getReqAction() {
		return reqAction;
	}
	public void setReqAction(String reqAction) {
		this.reqAction = reqAction;
	}
	public Integer getDynamicReq() {
		return dynamicReq;
	}
	public void setDynamicReq(Integer dynamicReq) {
		this.dynamicReq = dynamicReq;
	}
	public int getFileQueueMaxSize() {
		return fileQueueMaxSize;
	}
	public void setFileQueueMaxSize(int fileQueueMaxSize) {
		this.fileQueueMaxSize = fileQueueMaxSize;
	}
	public int getFileQueueFlushInterval() {
		return fileQueueFlushInterval;
	}
	public void setFileQueueFlushInterval(int fileQueueFlushInterval) {
		this.fileQueueFlushInterval = fileQueueFlushInterval;
	}
	public Integer getNeedRecycle() {
		return needRecycle;
	}
	public void setNeedRecycle(Integer needRecycle) {
		this.needRecycle = needRecycle;
	}
	public Integer getNeedUpdate() {
		return needUpdate;
	}
	public void setNeedUpdate(Integer needUpdate) {
		this.needUpdate = needUpdate;
	}
	
}
