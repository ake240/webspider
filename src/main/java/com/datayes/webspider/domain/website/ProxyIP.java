package com.datayes.webspider.domain.website;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "proxyip")
public class ProxyIP {
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	/*@ManyToOne
	@JoinColumn(name = "websiteid", referencedColumnName = "websiteid")
	private WebSite webSite;*/

	@Column(name = "host")
	private String host;

	@Column(name = "port")
	private Integer port;

	@Column(name = "status")
	private Integer status;

	@Column(name = "inserttime")
	private Date insertTime;

	@Column(name = "updatetime")
	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/*public WebSite getWebSite() {
		return webSite;
	}

	public void setWebSite(WebSite webSite) {
		this.webSite = webSite;
	}*/

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
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

}
