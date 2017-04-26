package com.datayes.webspider.service.websiteOps;


import java.util.List;

import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.domain.websiteOps.DataStoreField;

public interface IDataStoreFieldService {
	Integer enquiryDataStoreFieldCount(Integer typeId);
	
	PageDTO enquiryDataStoreFieldPage(Integer typeId, int pageNow, int pageSize);
	
	DataStoreField findByDataStoreFieldId(Integer dataStoreFieldId);
	
	void saveOrUpdate(DataStoreField dataStoreField);
	
	void deleteDataStoreField(DataStoreField dataStoreField);
	
	List<DataStoreField> findByDataStoreTypeId(Integer dataStoreTypeId);
	
	DataStoreField getDataStoreFieldByFieldCnName(String fieldCnName, Integer dataStoreTypeId);
	
	DataStoreField getDataStoreFieldByFieldEnName(String fieldEnName, Integer dataStoreTypeId);
}
