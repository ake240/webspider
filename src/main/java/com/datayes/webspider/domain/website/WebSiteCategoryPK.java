package com.datayes.webspider.domain.website;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class WebSiteCategoryPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "categoryid", referencedColumnName = "categoryid")
	private Category category;

	@ManyToOne
	@JoinColumn(name = "websiteid", referencedColumnName = "websiteid")
	private WebSite webSite;

	public WebSiteCategoryPK() {
	}
	
	public WebSiteCategoryPK(Category category, WebSite webSite) {
		this.category = category;
		this.webSite = webSite;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public WebSite getWebSite() {
		return webSite;
	}

	public void setWebSite(WebSite webSite) {
		this.webSite = webSite;
	}

}
