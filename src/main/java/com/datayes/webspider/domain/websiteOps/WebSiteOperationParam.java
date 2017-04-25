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
@Table(name = "website_operation_param")
@NamedQueries({
	@NamedQuery(name = "getByFieldId", query = "from WebSiteOperationParam w where w.fieldId = ?"),
	@NamedQuery(name = "getByWebSiteOpsId", query = "from WebSiteOperationParam w where webSiteOps.webSiteOpsId = ? order by w.sortNo asc")
})
public class WebSiteOperationParam implements Cloneable {

	@Id
	@GeneratedValue
	@Column(name = "fieldid", nullable = false, unique = true)
	private Integer fieldId;

	@ManyToOne
	@JoinColumn(name = "websiteoperationid", referencedColumnName = "websiteoperationid")
	private WebSiteOperation webSiteOps;

	@Column(name="fieldcnname")
	private String fieldCnName;

	@Column(name="fieldenname")
	private String fieldEnName;

	@Column(name="fieldvalue")
	private String fieldValue;

	@Column(name="fieldtype")
	private Integer fieldType;

	@Column(name="fieldflag")
	private Integer fieldFlag;
	
	@Column(name="needencode")
	private Integer needEncode;

	@Column(name="placeholderflag")
	private Integer placeholderFlag;

	@Column(name="placeholder")
	private String placeholder;

	@Column(name="sortno")
	private Integer sortNo;
	
	@Column(name = "needstore")
	private Integer needStore;

	@Column(name="operators")
	private Integer operators;

	@Column(name="fielddesc")
	private String fieldDesc;

	@Column(name="inserttime")
	private Date insertTime = new Date();

	@Column(name="updatetime")
	private Date updateTime;

	@Override
	public Object clone() {
		WebSiteOperationParam webSiteOpsParam = new WebSiteOperationParam();
		webSiteOpsParam.setFieldCnName(this.fieldCnName);
		webSiteOpsParam.setFieldEnName(this.fieldEnName);
		webSiteOpsParam.setFieldValue(this.fieldValue);
		webSiteOpsParam.setFieldDesc(this.fieldDesc);
		webSiteOpsParam.setFieldFlag(this.fieldFlag);
		webSiteOpsParam.setFieldType(this.fieldType);
		webSiteOpsParam.setNeedEncode(this.needEncode);
		webSiteOpsParam.setOperators(this.operators);
		webSiteOpsParam.setPlaceholder(this.placeholder);
		webSiteOpsParam.setPlaceholderFlag(this.placeholderFlag);
		webSiteOpsParam.setSortNo(this.sortNo);
		return webSiteOpsParam;
	}

	public Integer getFieldId() {
		return fieldId;
	}

	public void setFieldId(Integer fieldId) {
		this.fieldId = fieldId;
	}

	public WebSiteOperation getWebSiteOps() {
		return webSiteOps;
	}

	public void setWebSiteOps(WebSiteOperation webSiteOps) {
		this.webSiteOps = webSiteOps;
	}

	public String getFieldCnName() {
		return fieldCnName;
	}

	public void setFieldCnName(String fieldCnName) {
		this.fieldCnName = fieldCnName;
	}

	public String getFieldEnName() {
		return fieldEnName;
	}

	public void setFieldEnName(String fieldEnName) {
		this.fieldEnName = fieldEnName;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	public Integer getFieldType() {
		return fieldType;
	}

	public void setFieldType(Integer fieldType) {
		this.fieldType = fieldType;
	}

	public Integer getFieldFlag() {
		return fieldFlag;
	}

	public void setFieldFlag(Integer fieldFlag) {
		this.fieldFlag = fieldFlag;
	}

	public Integer getPlaceholderFlag() {
		return placeholderFlag;
	}

	public void setPlaceholderFlag(Integer placeholderFlag) {
		this.placeholderFlag = placeholderFlag;
	}

	public Integer getNeedEncode() {
		return needEncode;
	}

	public void setNeedEncode(Integer needEncode) {
		this.needEncode = needEncode;
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public Integer getOperators() {
		return operators;
	}

	public void setOperators(Integer operators) {
		this.operators = operators;
	}

	public String getFieldDesc() {
		return fieldDesc;
	}

	public void setFieldDesc(String fieldDesc) {
		this.fieldDesc = fieldDesc;
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

	public Integer getNeedStore() {
		return needStore;
	}

	public void setNeedStore(Integer needStore) {
		this.needStore = needStore;
	}

}
