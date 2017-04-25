package com.datayes.webspider.domain.website;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "website_config")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)  
@NamedQueries({
	@NamedQuery(name = "getAllWebSiteConfig", query = "from WebSiteConfig w order by w.insertTime desc"),
	@NamedQuery(name = "getWebSiteConfigById", query = "from WebSiteConfig w where w.webSiteConfigId = ?"),
	@NamedQuery(name = "getWebSiteConfigByWebSiteId", query = "from WebSiteConfig w where w.webSite.webSiteId = ?"),
})
public class WebSiteConfig {

	@Id
	@GeneratedValue
	@Column(name = "websiteconfigid")
	private Integer webSiteConfigId;
	
	@OneToOne
	@JoinColumn(name = "websiteid", referencedColumnName = "websiteid")
	private WebSite webSite;
	
	@Column(name = "websiteconfigname")
	private String webSiteConfigName;

	@Column(name = "description")
	private String description;

	@Column(name = "needproxyip")
	private Integer needProxyIP;
	
	@Column(name = "needfixedproxyip")
	private Integer needFixedProxyIP;

	@Column(name = "needlogin")
	private Integer needLogin;

	@Column(name = "loginclass")
	private String loginClass;

	@Column(name = "maxusernum")
	private Integer maxUserNum;

	@Column(name = "maxipnum")
	private Integer maxIPNum;

	@Column(name = "initusernum")
	private Integer initUserNum;

	@Column(name = "userloginintervalstart")
	private Integer userLoginIntervalStart = 0;

	@Column(name = "userloginintervalend")
	private Integer userLoginIntervalEnd = 0;

	@Column(name = "userloginminutes")
	private Integer userLoginMinutes = 0;
	
	@Column(name = "userlogincountinminutes")
	private Integer userLoginCountInMinutes = 0;

	@Column(name = "userloginhour")
	private Integer userLoginHour = 0;

	@Column(name = "userlogincountperhour")
	private Integer userLoginCountPerHour = 0;

	@Column(name = "userlogincountperday")
	private Integer userLoginCountPerDay = 0;

	@Column(name = "iploginintervalstart")
	private Integer ipLoginIntervalStart = 0;

	@Column(name = "iploginintervalend")
	private Integer ipLoginIntervalEnd = 0;
	
	@Column(name = "iploginminutes")
	private Integer ipLoginMinutes = 0;
	
	@Column(name = "iploginusercountinminutes")
	private Integer ipLoginUserCountInMinutes = 0;
	
	@Column(name = "iploginhour")
	private Integer ipLoginHour = 0;

	@Column(name = "iploginusercountperhour")
	private Integer ipLoginUserCountPerHour = 0;
	
	@Column(name = "iploginusercountperday")
	private Integer ipLoginUserCountPerDay = 0;
	
	@Column(name = "inserttime")
	private Date insertTime = new Date();
	
	@Column(name = "updatetime")
	private Date updateTime;
	
	public WebSiteConfig() {
	}

	public Integer getWebSiteConfigId() {
		return webSiteConfigId;
	}

	public void setWebSiteConfigId(Integer webSiteConfigId) {
		this.webSiteConfigId = webSiteConfigId;
	}

	public WebSite getWebSite() {
		return webSite;
	}

	public void setWebSite(WebSite webSite) {
		this.webSite = webSite;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getNeedProxyIP() {
		return needProxyIP;
	}

	public void setNeedProxyIP(Integer needProxyIP) {
		this.needProxyIP = needProxyIP;
	}

	public Integer getNeedLogin() {
		return needLogin;
	}

	public void setNeedLogin(Integer needLogin) {
		this.needLogin = needLogin;
	}

	public String getLoginClass() {
		return loginClass;
	}

	public void setLoginClass(String loginClass) {
		this.loginClass = loginClass;
	}

	public Integer getMaxUserNum() {
		return maxUserNum;
	}

	public void setMaxUserNum(Integer maxUserNum) {
		this.maxUserNum = maxUserNum;
	}

	public Integer getMaxIPNum() {
		return maxIPNum;
	}

	public void setMaxIPNum(Integer maxIPNum) {
		this.maxIPNum = maxIPNum;
	}

	public Integer getInitUserNum() {
		return initUserNum;
	}

	public void setInitUserNum(Integer initUserNum) {
		this.initUserNum = initUserNum;
	}

	public Integer getUserLoginIntervalStart() {
		return userLoginIntervalStart;
	}

	public void setUserLoginIntervalStart(Integer userLoginIntervalStart) {
		this.userLoginIntervalStart = userLoginIntervalStart;
	}

	public Integer getUserLoginIntervalEnd() {
		return userLoginIntervalEnd;
	}

	public void setUserLoginIntervalEnd(Integer userLoginIntervalEnd) {
		this.userLoginIntervalEnd = userLoginIntervalEnd;
	}

	public Integer getUserLoginMinutes() {
		return userLoginMinutes;
	}

	public void setUserLoginMinutes(Integer userLoginMinutes) {
		this.userLoginMinutes = userLoginMinutes;
	}

	public Integer getUserLoginHour() {
		return userLoginHour;
	}

	public void setUserLoginHour(Integer userLoginHour) {
		this.userLoginHour = userLoginHour;
	}

	public Integer getUserLoginCountPerHour() {
		return userLoginCountPerHour;
	}

	public void setUserLoginCountPerHour(Integer userLoginCountPerHour) {
		this.userLoginCountPerHour = userLoginCountPerHour;
	}

	public Integer getUserLoginCountPerDay() {
		return userLoginCountPerDay;
	}

	public void setUserLoginCountPerDay(Integer userLoginCountPerDay) {
		this.userLoginCountPerDay = userLoginCountPerDay;
	}

	public Integer getIpLoginIntervalStart() {
		return ipLoginIntervalStart;
	}

	public void setIpLoginIntervalStart(Integer ipLoginIntervalStart) {
		this.ipLoginIntervalStart = ipLoginIntervalStart;
	}

	public Integer getIpLoginIntervalEnd() {
		return ipLoginIntervalEnd;
	}

	public void setIpLoginIntervalEnd(Integer ipLoginIntervalEnd) {
		this.ipLoginIntervalEnd = ipLoginIntervalEnd;
	}

	public Integer getIpLoginMinutes() {
		return ipLoginMinutes;
	}

	public void setIpLoginMinutes(Integer ipLoginMinutes) {
		this.ipLoginMinutes = ipLoginMinutes;
	}

	public Integer getIpLoginUserCountInMinutes() {
		return ipLoginUserCountInMinutes;
	}

	public void setIpLoginUserCountInMinutes(Integer ipLoginUserCountInMinutes) {
		this.ipLoginUserCountInMinutes = ipLoginUserCountInMinutes;
	}

	public Integer getIpLoginHour() {
		return ipLoginHour;
	}

	public void setIpLoginHour(Integer ipLoginHour) {
		this.ipLoginHour = ipLoginHour;
	}

	public Integer getIpLoginUserCountPerHour() {
		return ipLoginUserCountPerHour;
	}

	public void setIpLoginUserCountPerHour(Integer ipLoginUserCountPerHour) {
		this.ipLoginUserCountPerHour = ipLoginUserCountPerHour;
	}

	public Integer getIpLoginUserCountPerDay() {
		return ipLoginUserCountPerDay;
	}

	public void setIpLoginUserCountPerDay(Integer ipLoginUserCountPerDay) {
		this.ipLoginUserCountPerDay = ipLoginUserCountPerDay;
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

	public String getWebSiteConfigName() {
		return webSiteConfigName;
	}

	public void setWebSiteConfigName(String webSiteConfigName) {
		this.webSiteConfigName = webSiteConfigName;
	}

	public Integer getUserLoginCountInMinutes() {
		return userLoginCountInMinutes;
	}

	public void setUserLoginCountInMinutes(Integer userLoginCountInMinutes) {
		this.userLoginCountInMinutes = userLoginCountInMinutes;
	}

	public Integer getNeedFixedProxyIP() {
		return needFixedProxyIP;
	}

	public void setNeedFixedProxyIP(Integer needFixedProxyIP) {
		this.needFixedProxyIP = needFixedProxyIP;
	}
	
}
	
