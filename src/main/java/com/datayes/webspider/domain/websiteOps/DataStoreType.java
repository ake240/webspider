package com.datayes.webspider.domain.websiteOps;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "datastoretype")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)  
@NamedQueries({
	@NamedQuery(name="getDataStoreTypeById", query="from DataStoreType d where d.dataStoreTypeId = ?"),
	@NamedQuery(name="getDataStoreTypeByName", query="from DataStoreType d where d.dataStoreTypeCnName = ?"),
	@NamedQuery(name="getAllDataStoreType", query="from DataStoreType d order by d.insertTime desc")
})
public class DataStoreType {

	@Id
	@GeneratedValue
	@Column(name = "datastoretypeid", nullable = false, unique = true)
	private Integer dataStoreTypeId;
	
	@Column(name = "datastoretypecnname")
	private String dataStoreTypeCnName;
	
	@Column(name = "collectionname")
	private String collectionName;
	
	@Column(name = "datastoretypeclass")
	private String dataStoreTypeClass;
	
	@Column(name = "inserttime")
	private Date insertTime = new Date();
	
	@Column(name = "updatetime")
	private Date updateTime;

	public Integer getDataStoreTypeId() {
		return dataStoreTypeId;
	}

	public void setDataStoreTypeId(Integer dataStoreTypeId) {
		this.dataStoreTypeId = dataStoreTypeId;
	}

	public String getDataStoreTypeCnName() {
		return dataStoreTypeCnName;
	}

	public void setDataStoreTypeCnName(String dataStoreTypeCnName) {
		this.dataStoreTypeCnName = dataStoreTypeCnName;
	}

	public String getCollectionName() {
		return collectionName;
	}

	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}

	public String getDataStoreTypeClass() {
		return dataStoreTypeClass;
	}

	public void setDataStoreTypeClass(String dataStoreTypeClass) {
		this.dataStoreTypeClass = dataStoreTypeClass;
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
