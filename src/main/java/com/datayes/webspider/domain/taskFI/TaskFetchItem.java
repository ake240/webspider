package com.datayes.webspider.domain.taskFI;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class TaskFetchItem {
	private String id;
	private String insertTime;
	private String updateTime;
	private Integer websiteOperationId;//操作类型
	private String url;// 抓取请求URL
	private Map<String, Object> header;//抓取请求头部
	private Map<String, Object> param;// 抓取请求参数
	private Integer status=0;// 消息状态 0:未处理，1:正在处理，2:处理完成，9:处理出现异常
	private Integer flag=0;//0:不可用；1：可用
	private Long fetchTime=0L;//抓取时间
	private Long fetchInterval = 3600000L;// 抓取间隔,单位毫秒
	private Long nextFetchTime=0L;//下次抓取时间
	
	
	//added at 2015-01-12
	private String errorMsg;// 抓取错误消息
	private Integer needRecycle = 0;// 重复抓取 默认为0:不重复
	private String startPage;
	private String endPage;

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}
	public void setInsertTime(Date insertTime) {
		if(insertTime!=null){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			this.insertTime = sdf.format(insertTime);
		}
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		if(updateTime!=null){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			this.updateTime = sdf.format(updateTime);
		}
	}
	public Integer getWebsiteOperationId() {
		return websiteOperationId;
	}
	public void setWebsiteOperationId(Integer websiteOperationId) {
		this.websiteOperationId = websiteOperationId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Map<String, Object> getHeader() {
		return header;
	}
	public void setHeader(Map<String, Object> header) {
		this.header = header;
	}
	public Map<String, Object> getParam() {
		return param;
	}
	public void setParam(Map<String, Object> param) {
		this.param = param;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getFetchTime() {
		return fetchTime;
	}
	public void setFetchTime(Long fetchTime) {
		this.fetchTime = fetchTime;
	}
	
	public Date getDFetchTime() {
		if(0L==fetchTime){
			return null;
		}else{
			return new Date(fetchTime);
		}
	}
	
	public void setDFetchTime(Date dFetchTime){
		if(dFetchTime!=null){
			fetchTime=dFetchTime.getTime();
		}else{
			fetchTime=0L;
		}
	}
	
	public Long getFetchInterval() {
		return fetchInterval;
	}
	public void setFetchInterval(Long fetchInterval) {
		this.fetchInterval = fetchInterval;
	}
	public Long getNextFetchTime() {
		return nextFetchTime;
	}
	public void setNextFetchTime(Long nextFetchTime) {
		this.nextFetchTime = nextFetchTime;
	}
	public Date getDNextFetchTime() {
		if(0L==nextFetchTime){
			return null;
		}else{
			return new Date(nextFetchTime);
		}
	}
	public void setDNextFetchTime(Date dNextFetchTime){
		if(dNextFetchTime!=null){
			nextFetchTime=dNextFetchTime.getTime();
		}else{
			nextFetchTime=0L;
		}
	}
	
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public String getEndPage() {
		return endPage;
	}
	public void setEndPage(String endPage) {
		this.endPage = endPage;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public Integer getNeedRecycle() {
		return needRecycle;
	}
	public void setNeedRecycle(Integer needRecycle) {
		this.needRecycle = needRecycle;
	}
	public String getStartPage() {
		return startPage;
	}
	public void setStartPage(String startPage) {
		this.startPage = startPage;
	}

}
