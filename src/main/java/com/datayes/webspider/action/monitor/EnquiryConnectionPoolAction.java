package com.datayes.webspider.action.monitor;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.datayes.webspider.action.BaseAction;
import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.util.HttpUtil;
import com.datayes.webspider.util.json.Pool;


@ParentPackage("webspider")
@Namespace("/")
@Results({ 
	@Result(name = "NormalSearch", location = "/WEB-INF/jsp/monitor/enquiryConnectionPool.jsp"), 
	@Result(name = "AjaxSearch", location = "/WEB-INF/jsp/ajax/enquiryConnectionPoolResult.jsp"), 
})
public class EnquiryConnectionPoolAction extends BaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2634198483306545721L;
	
	
	private boolean ajaxSearch;
	private String host;
	private Pool pool;
	
	@Action("enquiryConnectionPool")
	public String query(){
		if(StringUtils.isEmpty(host)){
			return "NormalSearch";
		}
		
		if( ajaxSearch ){
			///TODO
			pool = HttpUtil.getPool(host);
//			if(pool == null){
//				return ERROR;
//			}
			return "AjaxSearch";
		}
		return "NormalSearch";
	}

	/*
	 * Getter and Setter
	 */
	public boolean getAjaxSearch() {
		return ajaxSearch;
	}
	public void setAjaxSearch(boolean ajaxSearch) {
		this.ajaxSearch = ajaxSearch;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public Pool getPool() {
		return pool;
	}
	public void setPool(Pool pool) {
		this.pool = pool;
	}
}
