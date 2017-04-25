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
@Table(name = "website_operation_antimonitor_rule")
@NamedQueries({
	@NamedQuery(name = "getByAntiRuleId", query = "from WebSiteOpsAntiRule w where w.antiRuleId = ?"),
	@NamedQuery(name = "getAntiRuleByWebSiteOpsId", query = "from WebSiteOpsAntiRule w where w.webSiteOps.webSiteOpsId = ?"),
})
public class WebSiteOpsAntiRule {
	@Id
	@GeneratedValue
	@Column(name = "antimonitorruleid", unique = true, nullable = false)
	private Integer antiRuleId;
	
	@Column(name = "antimonitorrulename")
	private String antiRuleName;
	
	@ManyToOne
	@JoinColumn(name = "websiteoperationid", referencedColumnName = "websiteoperationid")
	private WebSiteOperation webSiteOps;
	
	@Column(name = "requestperiodstart")
	private Integer requestPeriodStart = 0;
	
	@Column(name = "requestperiodend")
	private Integer requestPeriodEnd = 0;
	
	@Column(name = "sleepperiodstart")
	private Integer sleepPeriodStart = 0;
	
	@Column(name = "sleepperiodend")
	private Integer sleepPeriodEnd = 0;
	
	@Column(name = "userrequestintervalstart")
	private Integer userRequestIntervalStart = 0;
	
	@Column(name = "userrequestintervalend")
	private Integer userRequestIntervalEnd = 0;
	
	@Column(name = "userrequestseconds")
	private Integer userRequestSeconds = 0;
	
	@Column(name = "userrequestcountinseconds")
	private Integer userRequestCountInSeconds = 0;
	
	@Column(name = "userrequesthour")
	private Integer userRequestHour = 0;
	
	@Column(name = "userrequestcountperhour")
	private Integer userRequestCountPerHour = 0;
	
	@Column(name = "userrequestcountperday")
	private Integer userRequestCountPerDay = 0;
	
	@Column(name = "iprequestintervalstart")
	private Integer ipRequestIntervalStart = 0;
	
	@Column(name = "iprequestintervalend")
	private Integer ipRequestIntervalEnd = 0;
	
	@Column(name = "iprequestseconds")
	private Integer ipRequestSeconds = 0;
	
	@Column(name = "iprequestcountinseconds")
	private Integer ipRequestCountInSeconds = 0;
	
	@Column(name = "iprequesthour")
	private Integer ipRequestHour = 0;
	
	@Column(name = "iprequestcountperhour")
	private Integer ipRequestCountPerHour = 0;
	
	@Column(name = "iprequestcountperday")
	private Integer ipRequestCountPerDay = 0;
	
	@Column(name = "insertTime")
	private Date insertTime = new Date();
	
	@Column(name = "updateTime")
	private Date updateTime;

	public Integer getAntiRuleId() {
		return antiRuleId;
	}

	public void setAntiRuleId(Integer antiRuleId) {
		this.antiRuleId = antiRuleId;
	}

	public String getAntiRuleName() {
		return antiRuleName;
	}

	public void setAntiRuleName(String antiRuleName) {
		this.antiRuleName = antiRuleName;
	}

	public WebSiteOperation getWebSiteOps() {
		return webSiteOps;
	}

	public void setWebSiteOps(WebSiteOperation webSiteOps) {
		this.webSiteOps = webSiteOps;
	}

	public Integer getRequestPeriodStart() {
		return requestPeriodStart;
	}

	public void setRequestPeriodStart(Integer requestPeriodStart) {
		this.requestPeriodStart = requestPeriodStart;
	}

	public Integer getRequestPeriodEnd() {
		return requestPeriodEnd;
	}

	public void setRequestPeriodEnd(Integer requestPeriodEnd) {
		this.requestPeriodEnd = requestPeriodEnd;
	}

	public Integer getSleepPeriodStart() {
		return sleepPeriodStart;
	}

	public void setSleepPeriodStart(Integer sleepPeriodStart) {
		this.sleepPeriodStart = sleepPeriodStart;
	}

	public Integer getSleepPeriodEnd() {
		return sleepPeriodEnd;
	}

	public void setSleepPeriodEnd(Integer sleepPeriodEnd) {
		this.sleepPeriodEnd = sleepPeriodEnd;
	}

	public Integer getUserRequestIntervalStart() {
		return userRequestIntervalStart;
	}

	public void setUserRequestIntervalStart(Integer userRequestIntervalStart) {
		this.userRequestIntervalStart = userRequestIntervalStart;
	}

	public Integer getUserRequestIntervalEnd() {
		return userRequestIntervalEnd;
	}

	public void setUserRequestIntervalEnd(Integer userRequestIntervalEnd) {
		this.userRequestIntervalEnd = userRequestIntervalEnd;
	}

	public Integer getUserRequestSeconds() {
		return userRequestSeconds;
	}

	public void setUserRequestSeconds(Integer userRequestSeconds) {
		this.userRequestSeconds = userRequestSeconds;
	}

	public Integer getUserRequestCountInSeconds() {
		return userRequestCountInSeconds;
	}

	public void setUserRequestCountInSeconds(Integer userRequestCountInSeconds) {
		this.userRequestCountInSeconds = userRequestCountInSeconds;
	}

	public Integer getUserRequestHour() {
		return userRequestHour;
	}

	public void setUserRequestHour(Integer userRequestHour) {
		this.userRequestHour = userRequestHour;
	}

	public Integer getUserRequestCountPerHour() {
		return userRequestCountPerHour;
	}

	public void setUserRequestCountPerHour(Integer userRequestCountPerHour) {
		this.userRequestCountPerHour = userRequestCountPerHour;
	}

	public Integer getUserRequestCountPerDay() {
		return userRequestCountPerDay;
	}

	public void setUserRequestCountPerDay(Integer userRequestCountPerDay) {
		this.userRequestCountPerDay = userRequestCountPerDay;
	}

	public Integer getIpRequestIntervalStart() {
		return ipRequestIntervalStart;
	}

	public void setIpRequestIntervalStart(Integer ipRequestIntervalStart) {
		this.ipRequestIntervalStart = ipRequestIntervalStart;
	}

	public Integer getIpRequestIntervalEnd() {
		return ipRequestIntervalEnd;
	}

	public void setIpRequestIntervalEnd(Integer ipRequestIntervalEnd) {
		this.ipRequestIntervalEnd = ipRequestIntervalEnd;
	}

	public Integer getIpRequestSeconds() {
		return ipRequestSeconds;
	}

	public void setIpRequestSeconds(Integer ipRequestSeconds) {
		this.ipRequestSeconds = ipRequestSeconds;
	}

	public Integer getIpRequestCountInSeconds() {
		return ipRequestCountInSeconds;
	}

	public void setIpRequestCountInSeconds(Integer ipRequestCountInSeconds) {
		this.ipRequestCountInSeconds = ipRequestCountInSeconds;
	}

	public Integer getIpRequestHour() {
		return ipRequestHour;
	}

	public void setIpRequestHour(Integer ipRequestHour) {
		this.ipRequestHour = ipRequestHour;
	}

	public Integer getIpRequestCountPerHour() {
		return ipRequestCountPerHour;
	}

	public void setIpRequestCountPerHour(Integer ipRequestCountPerHour) {
		this.ipRequestCountPerHour = ipRequestCountPerHour;
	}

	public Integer getIpRequestCountPerDay() {
		return ipRequestCountPerDay;
	}

	public void setIpRequestCountPerDay(Integer ipRequestCountPerDay) {
		this.ipRequestCountPerDay = ipRequestCountPerDay;
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
	
	
	
}
