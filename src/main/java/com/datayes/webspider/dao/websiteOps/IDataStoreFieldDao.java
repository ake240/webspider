package com.datayes.webspider.dao.websiteOps;

import java.util.List;

import com.datayes.webspider.dao.IBaseDao;
import com.datayes.webspider.domain.websiteOps.DataStoreField;

public interface IDataStoreFieldDao extends IBaseDao<DataStoreField> {
	
	Integer enquiryDataStoreFieldCount(Integer typeId);
	
	List enquiryDataStoreFieldPage(Integer typeId, int pageNow, int pageSize);
	
	DataStoreField getDataStoreFieldByFieldCnName(String fieldCnName,Integer dataStoreTypeId);
	
	DataStoreField getDataStoreFieldByFieldEnName(String fieldEnName,Integer dataStoreTypeId);
}
