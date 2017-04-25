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
@Table(name = "website_user")
@NamedQueries({
	@NamedQuery(name = "getWebSiteUserById", query = "from WebSiteUser w where w.id = ?"),
})
public class WebSiteUser {
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false, unique = true)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "websiteid", referencedColumnName = "websiteid")
	private WebSite webSite;
	
	@Column(name = "account")
	private String account;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "status")
	private Integer status;
	
	@Column(name = "message")
	private String message = "";

	@Column(name = "inserttime")
	private Date insertTime = new Date();
	
	@Column(name = "updatetime")
	private Date updateTime;
	
	public WebSiteUser(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public WebSite getWebSite() {
		return webSite;
	}

	public void setWebSite(WebSite webSite) {
		this.webSite = webSite;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
