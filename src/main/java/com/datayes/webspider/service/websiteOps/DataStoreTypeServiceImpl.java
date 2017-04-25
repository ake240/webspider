package com.datayes.webspider.service.websiteOps;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.datayes.webspider.common.PageDTO;
import com.datayes.webspider.dao.websiteOps.IDataStoreTypeDao;
import com.datayes.webspider.domain.websiteOps.DataStoreType;

@Service("dataStoreTypeService")
public class DataStoreTypeServiceImpl implements IDataStoreTypeService {

	@Resource
	private IDataStoreTypeDao dataStoreTypeDao;
	
	@Override
	public Integer enquiryDataStoreTypeCount(String name) {
		return dataStoreTypeDao.enquiryDataStoreTypeCount(name);
	}

	@Override
	public PageDTO enquiryDataStoreTypePage(String name, int pageNow, int pageSize) {
		int total = dataStoreTypeDao.enquiryDataStoreTypeCount(name);
		List list = dataStoreTypeDao.enquiryDataStoreTypePage(name, pageNow, pageSize);
		return new PageDTO(list, pageNow, pageSize, total);
	}

	@Override
	public DataStoreType findByDataStoreTypeId(Integer dataStoreTypeId) {
		return dataStoreTypeDao.findById("getDataStoreTypeById", dataStoreTypeId);
	}

	@Override
	public DataStoreType findByDataStoreCnName(String name) {
		return dataStoreTypeDao.findByName("getDataStoreTypeByName", name);
	}

	@Override
	@Transactional
	public void saveOrUpdate(DataStoreType dataStoreType) {
		dataStoreTypeDao.saveOrUpdate(dataStoreType);
	}

	@Override
	@Transactional
	public void deleteDataStoreType(DataStoreType dataStoreType) {
		dataStoreTypeDao.delete(dataStoreType);
	}

	@Override
	public List<DataStoreType> getAllDataStoreType() {
		return dataStoreTypeDao.findAll("getAllDataStoreType");
	}

}
