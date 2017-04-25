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
@Table(name = "website_operation_queue")
@NamedQueries({
	@NamedQuery(name = "getWebSiteOpsQueueById", query = "from WebSiteOperationQueue w where w.queueId = ?"),
	@NamedQuery(name = "getWebSiteOpsQueueByEnName", query = "from WebSiteOperationQueue w where w.queueEnName = ?"),
})
public class WebSiteOperationQueue {

	@Id
	@GeneratedValue
	@Column(name = "queueid", unique = true, nullable = false)
	private Integer queueId;
	
	@Column(name = "queuecnname")
	private String queueCnName;
	
	@Column(name = "queueenname")
	private String queueEnName;
	
	@ManyToOne
	@JoinColumn(name = "websiteoperationid", referencedColumnName = "websiteoperationid")
	private WebSiteOperation webSiteOps;
	
	@Column(name = "inserttime")
	private Date insertTime = new Date();
	
	@Column(name = "updatetime")
	private Date updateTime;

	public Integer getQueueId() {
		return queueId;
	}

	public void setQueueId(Integer queueId) {
		this.queueId = queueId;
	}

	public String getQueueCnName() {
		return queueCnName;
	}

	public void setQueueCnName(String queueCnName) {
		this.queueCnName = queueCnName;
	}

	public String getQueueEnName() {
		return queueEnName;
	}

	public void setQueueEnName(String queueEnName) {
		this.queueEnName = queueEnName;
	}

	public WebSiteOperation getWebSiteOps() {
		return webSiteOps;
	}

	public void setWebSiteOps(WebSiteOperation webSiteOps) {
		this.webSiteOps = webSiteOps;
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
