package com.datayes.webspider.action.monitor;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.datayes.webspider.action.BaseAction;
import com.datayes.webspider.util.HttpUtil;
import com.datayes.webspider.util.json.Connection;
import com.datayes.webspider.util.json.Data;
import com.datayes.webspider.util.json.Operation;
import com.datayes.webspider.util.json.Pool;

@ParentPackage("webspider")
@Namespace("/")
@Results({ 
	@Result(name = "success", location = "/WEB-INF/jsp/monitor/enquiryConnection.jsp"), 
	@Result(name ="enquiryConnectionDirect", location = "/WEB-INF/jsp/monitor/enquiryConnectionAjax.jsp"),
	@Result(name = "ajaxSearch", location="/WEB-INF/jsp/ajax/enquiryConnectionResult.jsp"),
	@Result(name = "json", type="json"),
})
public class EnquiryConnectionAction extends BaseAction {
	
	private Map<Object, Object> jsonResult = new LinkedHashMap<Object, Object>();
	private String ip="";
	private String account="";
	private String status="";
	private boolean ajaxSearch;
	
	private String host;
	private int id;
	private String role;

	private Connection connection;
	
	@Action("enquiryConnectionJSON")
	public String queryJSON(){
		String connectionString = HttpUtil.getConnectionString(host, id);
		connection = HttpUtil.getConnection(host, id);
		getJsonResult().put("code", 200);
		getJsonResult().put("connection", connection);
		getJsonResult().put("connectionString", connectionString);
		return "json";
	}
	
	@Action("enquiryConnectionDirect")
	public String enquiryConnectionRedirect(){
		return "enquiryConnectionDirect";
	}
	
	@Action("enquiryConnection")
	public String query(){
		
		if(StringUtils.isEmpty(host) || id<=0){
			return ERROR;
		}
		connection = HttpUtil.getConnection(host, id);
		if(connection == null){
			return ERROR;
		}
		if(ajaxSearch){
			processIp(connection, ip);
			processAccount(connection, account);
			processStatus(connection, status);
//			List<Data> datas = connection.getData();
//			Iterator<Data> dataIterator = datas.iterator();
//			while(dataIterator.hasNext()){
//				Data data = dataIterator.next();
//				if(StringUtils.isEmpty(ip)){
//					
//				} else {
//					
//					if(ip.equalsIgnoreCase(data.getHost())){
//						
//					} else {
//						dataIterator.remove();
//					} 
//				}
//			}
			return "ajaxSearch";
		}
		return SUCCESS;
	}

	private static void processIp(Connection con, String ip){
		if(StringUtils.isEmpty(ip)){
			return;
		}
		Iterator<Data> dataIterator = con.getData().iterator();
		while(dataIterator.hasNext()){
			if(ip.equalsIgnoreCase(dataIterator.next().getHost())){
				
			} else {
				dataIterator.remove();
			}
		}
		return;
	}
	private static void processAccount(Connection con, String account){
		if(StringUtils.isEmpty(account)){
			return;
		}
		Iterator<Data> dataIterator = con.getData().iterator();
		while(dataIterator.hasNext()){
			if(account.equalsIgnoreCase(dataIterator.next().getAccount())){
				
			}
			else{
				dataIterator.remove();
			}
		}
		return;
	}
	private static void processStatus(Connection connection, String status){
		if(StringUtils.isEmpty(status)){
			return;
		}
		
		Iterator<Data> dataIterator = connection.getData().iterator();
		while(dataIterator.hasNext()){
			Data data = dataIterator.next();
			Iterator<Operation> operationIterator = data.getWebsiteOperationReqList().iterator();
			while(operationIterator.hasNext()){
				Operation operation = operationIterator.next();
				boolean flag = true;
				if(StringUtils.isEmpty(operation.getExceptionRuleName())){
					if(operation.getMaxIpRequestNumPerHour() > 0 && operation.getIpReqNumPerHour() >= operation.getMaxIpRequestNumPerHour()){
//						operationIterator.remove();
						flag = false;
					}
					if(operation.getMaxIpRequestNumPerDay() > 0 && operation.getIpReqNumPerDay() >= operation.getMaxIpRequestNumPerDay()){
//						operationIterator.remove();
						flag = false;
					}
					if(operation.getMaxUserRequestNumPerHour() > 0 &&operation.getUserReqNumPerHour() >= operation.getMaxUserRequestNumPerHour()){
//						operationIterator.remove();
						flag = false;
					}
					if(operation.getMaxUserRequestNumPerDay() > 0 && operation.getUserReqNumPerDay() >= operation.getMaxUserRequestNumPerDay()){
//						operationIterator.remove();
						flag = false;
					}
				} else {
//					operationIterator.remove();
					flag = false;
				}
				
				if("正常".equals(status)){
					
					if(!flag){
						operationIterator.remove();
					}
				} else {
					
					if(flag){
						operationIterator.remove();
					}
				}
			}
		}
		
		Iterator<Data> dataIterator2 = connection.getData().iterator();
		while(dataIterator2.hasNext()){
			Data tempData = dataIterator2.next();
			if(tempData.getWebsiteOperationReqList()== null || tempData.getWebsiteOperationReqList().size() ==0){
				dataIterator2.remove();
			}
//			if(dataIteratorFilter.next().getWebsiteOperationReqList())
		}
		return;
	}
	
	/*
	 * Getter and Setter
	 */
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Connection getConnection() {
		return connection;
	}
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public boolean getAjaxSearch() {
		return ajaxSearch;
	}

	public void setAjaxSearch(boolean ajaxSearch) {
		this.ajaxSearch = ajaxSearch;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Map<Object, Object> getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(Map<Object, Object> jsonResult) {
		this.jsonResult = jsonResult;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}

}