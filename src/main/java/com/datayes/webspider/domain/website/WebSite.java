package com.datayes.webspider.domain.website;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "website")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)  
@NamedQueries({
	@NamedQuery(name="getAllWebSite", query="from WebSite w order by w.insertTime desc"),
	@NamedQuery(name="getWebSiteByName", query="from WebSite w where w.webSiteName = ?"),
})
public class WebSite {
	@Id
	@GeneratedValue
	@Column(name = "websiteid", nullable = false, unique = true)
	private Integer webSiteId;

	@Column(name = "websitename")
	private String webSiteName;

	@Column(name = "inserttime")
	private Date insertTime = new Date();

	@Column(name = "updatetime")
	private Date updateTime;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "website_category_rel", joinColumns={@JoinColumn(name="websiteid", referencedColumnName="websiteid")},
				inverseJoinColumns={@JoinColumn(name="categoryid", referencedColumnName="categoryid")})
	private Set<Category> categories;
	
	@OneToMany(mappedBy = "webSite")
	private Set<WebSiteUser> webSiteUsers;

	public WebSite() {
	}

	public Integer getWebSiteId() {
		return webSiteId;
	}

	public void setWebSiteId(Integer webSiteId) {
		this.webSiteId = webSiteId;
	}

	public String getWebSiteName() {
		return webSiteName;
	}

	public void setWebSiteName(String webSiteName) {
		this.webSiteName = webSiteName;
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

	public Set<WebSiteUser> getWebSiteUsers() {
		return webSiteUsers;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public void setWebSiteUsers(Set<WebSiteUser> webSiteUsers) {
		this.webSiteUsers = webSiteUsers;
	}

}
