package com.datayes.webspider.domain.website;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "website_category_rel")
public class WebSiteCategoryRel {

	@Id
	private WebSiteCategoryPK webSiteCategoryPK;

	public WebSiteCategoryRel() {
	}

	public WebSiteCategoryPK getWebSiteCategoryPK() {
		return webSiteCategoryPK;
	}

	public void setWebSiteCategoryPK(WebSiteCategoryPK webSiteCategoryPK) {
		this.webSiteCategoryPK = webSiteCategoryPK;
	}

}
