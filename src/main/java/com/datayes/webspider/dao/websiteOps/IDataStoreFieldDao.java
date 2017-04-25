package com.datayes.webspider.dao.websiteOps;

import java.util.List;

import com.datayes.webspider.dao.IBaseDao;
import com.datayes.webspider.domain.websiteOps.DataStoreField;

public interface IDataStoreFieldDao extends IBaseDao<DataStoreField> {
	
	Integer enquiryDataStoreFieldCount(Integer typeId);
	
	List enquiryDataStoreFieldPage(Integer typeId, int pageNow, int pageSize);
	
	//根据fieldcnname获得相应的datastorefield
	DataStoreField getDataStoreFieldByFieldCnName(String fieldCnName);
	
	DataStoreField getByDataStoreFieldId(Integer crawlNodeId);

	List<DataStoreField> getByFieldCnName(String fieldCnName);

	String getByExtName(String extName);

}
