package com.datayes.webspider.domain.websiteOps;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "website_operation_exception_rule")
@NamedQueries({
	@NamedQuery(name = "getExceptionRuleByRuleId", query = "from WebSiteOpsExceptionRule w where w.exceptionRuleId = ?"),
})
public class WebSiteOpsExceptionRule {
	@Id
	@GeneratedValue
	@Column(name = "exceptionruleid", unique = true, nullable = false)
	private Integer exceptionRuleId;
	
	@Column(name = "exceptionrulename")
	private String exceptionRuleName;
	
	@Column(name= "exceptiontype")
	private Integer exceptionType;
	
	@Column(name= "exceptionprocessclass")
	private String exceptionProcessClass;
	
	@Column(name= "exceptionprocessclasscontent")
	private String exceptionProcessClassContent;
	
	@Column(name= "extroParam")
	private String extroParam;
	
	@Column(name = "exprval")
	private String exprVal;
	
	@Column(name = "matchvalue")
	private String matchValue = "";
	
	@Column(name = "exprtype")
	private Integer exprType;
	
	@ManyToOne
	@JoinColumn(name = "websiteoperationid", referencedColumnName = "websiteoperationid")
	private WebSiteOperation webSiteOps;
	
	@Column(name = "status")
	private Integer status = 0;
	
	@Column(name = "inserttime")
	private Date insertTime = new Date();
	
	@Column(name = "updatetime")
	private Date updateTime;

	
	/*
	 * Getters and Setters
	 */
	public Integer getExceptionRuleId() {
		return exceptionRuleId;
	}
	public void setExceptionRuleId(Integer exceptionRuleId) {
		this.exceptionRuleId = exceptionRuleId;
	}
	public String getExceptionRuleName() {
		return exceptionRuleName;
	}
	public void setExceptionRuleName(String exceptionRuleName) {
		this.exceptionRuleName = exceptionRuleName;
	}
	public String getExprVal() {
		return exprVal;
	}
	public void setExprVal(String exprVal) {
		this.exprVal = exprVal;
	}
	public Integer getExprType() {
		return exprType;
	}
	public void setExprType(Integer exprType) {
		this.exprType = exprType;
	}
	public WebSiteOperation getWebSiteOps() {
		return webSiteOps;
	}
	public void setWebSiteOps(WebSiteOperation webSiteOps) {
		this.webSiteOps = webSiteOps;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	public Integer getExceptionType() {
		return exceptionType;
	}
	public void setExceptionType(Integer exceptionType) {
		this.exceptionType = exceptionType;
	}
	public String getExceptionProcessClass() {
		return exceptionProcessClass;
	}
	public void setExceptionProcessClass(String exceptionProcessClass) {
		this.exceptionProcessClass = exceptionProcessClass;
	}
	public String getExceptionProcessClassContent() {
		return exceptionProcessClassContent;
	}
	public void setExceptionProcessClassContent(String exceptionProcessClassContent) {
		this.exceptionProcessClassContent = exceptionProcessClassContent;
	}
	public String getExtroParam() {
		return extroParam;
	}
	public void setExtroParam(String extroParam) {
		this.extroParam = extroParam;
	}
	public String getMatchValue() {
		return matchValue;
	}
	public void setMatchValue(String matchValue) {
		this.matchValue = matchValue;
	}
}
