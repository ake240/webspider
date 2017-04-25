package com.datayes.webspider.dao.websiteOps;

import java.util.List;

import com.datayes.webspider.dao.IBaseDao;
import com.datayes.webspider.domain.websiteOps.DataStoreType;

public interface IDataStoreTypeDao extends IBaseDao<DataStoreType> {
	
	Integer enquiryDataStoreTypeCount(String name);
	
	List enquiryDataStoreTypePage(String name, int pageNow, int pageSize);
}
