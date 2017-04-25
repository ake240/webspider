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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "datastorefield")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)  
@NamedQueries({
	@NamedQuery(name="getFieldsByTypeId",query="from DataStoreField d where d.dataStoreType.dataStoreTypeId = ?"),
	@NamedQuery(name="getByDataStoreFieldId", query="from DataStoreField d where d.dataStoreFieldId = ?"),
	@NamedQuery(name="getByFieldCnName",query="from DataStoreField d where d.fieldCnName = ?")
})
public class DataStoreField {
	@Id
	@GeneratedValue
	@Column(name="datastorefieldid", nullable=false, unique=true)
	private Integer dataStoreFieldId;
	
	@ManyToOne
	@JoinColumn(name = "datastoretypeid", referencedColumnName = "datastoretypeid")
	private DataStoreType dataStoreType;
	
	@Column(name="fieldcnname")
	private String fieldCnName;
	
	@Column(name="fieldenname")
	private String fieldEnName;
	
	@Column(name="fieldtype")
	private Integer fieldType;
	
	@Column(name="isunique")
	private Integer isUnique;
	
	@Column(name="inserttime")
	private Date insertTime = new Date();
	
	@Column(name="updatetime")
	private Date updateTime;

	public Integer getDataStoreFieldId() {
		return dataStoreFieldId;
	}

	public void setDataStoreFieldId(Integer dataStoreFieldId) {
		this.dataStoreFieldId = dataStoreFieldId;
	}

	public DataStoreType getDataStoreType() {
		return dataStoreType;
	}

	public void setDataStoreType(DataStoreType dataStoreType) {
		this.dataStoreType = dataStoreType;
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

	public Integer getFieldType() {
		return fieldType;
	}

	public void setFieldType(Integer fieldType) {
		this.fieldType = fieldType;
	}

	public Integer getIsUnique() {
		return isUnique;
	}

	public void setIsUnique(Integer isUnique) {
		this.isUnique = isUnique;
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
