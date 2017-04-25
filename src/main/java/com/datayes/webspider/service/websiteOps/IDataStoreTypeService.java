package com.datayes.webspider.service.websiteOps;

import java.util.List;

import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.domain.websiteOps.DataStoreType;

public interface IDataStoreTypeService {
	Integer enquiryDataStoreTypeCount(String name);
	
	PageDTO enquiryDataStoreTypePage(String name, int pageNow, int pageSize);
	
	DataStoreType findByDataStoreTypeId(Integer dataStoreTypeId);
	
	DataStoreType findByDataStoreCnName(String name);
	
	void saveOrUpdate(DataStoreType dataStoreType);
	
	void deleteDataStoreType(DataStoreType dataStoreType);
	
	List<DataStoreType> getAllDataStoreType();
}
