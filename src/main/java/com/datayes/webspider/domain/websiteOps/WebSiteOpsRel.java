package com.datayes.webspider.domain.websiteOps;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "website_operation_ref")
@NamedQueries({
	@NamedQuery(name="enquiryChildren", query="from WebSiteOpsRel w where w.key.parentWebsiteOperationId = ?"),
})
public class WebSiteOpsRel {
	
	@EmbeddedId
	private Key key;

	public static class Key implements Serializable{
		@Column(name="websiteoperationid")
		private int websiteOperationId;
		@Column(name="pwebsiteoperationid")
		private int parentWebsiteOperationId;
		
		protected Key(){}
		protected Key(int websiteOperationId, int parentWebsiteOperationId){
			this.websiteOperationId = websiteOperationId;
			this.parentWebsiteOperationId = parentWebsiteOperationId;
		}
		
		/*
		 * Getters and Setters
		 */
		public int getWebsiteOperationId() {
			return websiteOperationId;
		}
		public void setWebsiteOperationId(int websiteOperationId) {
			this.websiteOperationId = websiteOperationId;
		}
		public int getParentWebsiteOperationId() {
			return parentWebsiteOperationId;
		}
		public void setParentWebsiteOperationId(int parentWebsiteOperationId) {
			this.parentWebsiteOperationId = parentWebsiteOperationId;
		}
	}
	
	/*
	 * Constructors
	 */
	public WebSiteOpsRel(){}
	public WebSiteOpsRel(int websiteOperationId, int parentWebsiteOperationId){
		this.key = new Key(websiteOperationId, parentWebsiteOperationId);
	}
	
	/*
	 * Getters and Setters
	 */
	public Key getKey() {
		return key;
	}
	public void setKey(Key key) {
		this.key = key;
	}
	
	
}
