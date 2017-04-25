package com.datayes.webspider.domain.website;

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
@Table(name = "website_verifycode")
@NamedQueries({
	@NamedQuery(name = "findByVerifyCodeId", query = "from WebSiteVerifyCode w where w.verifyCodeId = ?")
})
public class WebSiteVerifyCode {

	@Id
	@GeneratedValue
	@Column(name = "verifycodeid", nullable = false, unique = true)
	private Integer verifyCodeId;

	@ManyToOne
	@JoinColumn(name = "websiteid", referencedColumnName = "websiteid")
	private WebSite webSite;

	@Column(name = "machinehost")
	private String machineHost;

	@Column(name = "account")
	private String account;

	@Column(name = "verifycodeurl")
	private String verifyCodeUrl;

	@Column(name = "verifycodecreatetime")
	private Date verifyCodeCreateTime;

	@Column(name = "verifycodeexpire")
	private Integer verifyCodeExpire;

	@Column(name = "verifycode")
	private String verifyCode;
	
	@Column(name = "verifycodecause")
	private String verifyCodeCause;

	@Column(name = "inserttime")
	private Date insertTime;

	@Column(name = "updatetime")
	private Date updateTime;

	public Integer getVerifyCodeId() {
		return verifyCodeId;
	}

	public void setVerifyCodeId(Integer verifyCodeId) {
		this.verifyCodeId = verifyCodeId;
	}

	public WebSite getWebSite() {
		return webSite;
	}

	public void setWebSite(WebSite webSite) {
		this.webSite = webSite;
	}

	public String getMachineHost() {
		return machineHost;
	}

	public void setMachineHost(String machineHost) {
		this.machineHost = machineHost;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getVerifyCodeUrl() {
		return verifyCodeUrl;
	}

	public void setVerifyCodeUrl(String verifyCodeUrl) {
		this.verifyCodeUrl = verifyCodeUrl;
	}

	public Date getVerifyCodeCreateTime() {
		return verifyCodeCreateTime;
	}

	public void setVerifyCodeCreateTime(Date verifyCodeCreateTime) {
		this.verifyCodeCreateTime = verifyCodeCreateTime;
	}

	public Integer getVerifyCodeExpire() {
		return verifyCodeExpire;
	}

	public void setVerifyCodeExpire(Integer verifyCodeExpire) {
		this.verifyCodeExpire = verifyCodeExpire;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getVerifyCodeCause() {
		return verifyCodeCause;
	}

	public void setVerifyCodeCause(String verifyCodeCause) {
		this.verifyCodeCause = verifyCodeCause;
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
